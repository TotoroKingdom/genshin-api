package com.totoro.annonation;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.totoro.utils.SpringUtils;
import lombok.SneakyThrows;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author:totoro
 * @createDate:2023/2/14
 * @description:
 */
public class UniqueCheckValidator implements ConstraintValidator<UniqueCheck, Object> {

    private String fieldName;
    private Long id;

    @Override
    public void initialize(UniqueCheck constraintAnnotation) {
        this.fieldName = constraintAnnotation.filedName();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext context) {
        String message = check(o);
        if (ObjectUtil.isNull(message)){
            return true;
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        return false;
    }

    @SneakyThrows
    private String check(Object o){
        //需要校验的字段
        String[] split = fieldName.split(",");

        Class<?> aClass = o.getClass();
        String beanName = aClass.getSimpleName().toLowerCase() + "Mapper";
        BaseMapper mapper = (BaseMapper) SpringUtils.getBean(beanName);

        Map<String, Object> paramsMap = new LinkedHashMap<>();
        Field id = aClass.getDeclaredField("id");
        id.setAccessible(true);
        Object idValue = id.get(o);
        Optional.ofNullable(id).ifPresent( i ->{
            this.id = (Long) idValue;
        });
        for (String checkField : split) {
            Field field = aClass.getDeclaredField(checkField);
            field.setAccessible(true);
            Object fieldValue = field.get(o);
            //获取注解上的值（数据库字段名称）
            String dbValue = field.getAnnotation(TableField.class).value();
            paramsMap.put(dbValue, fieldValue);
        }
        return spliceWrapper(paramsMap,mapper);
    }

    //拼接参数
    private String spliceWrapper(Map<String, Object> paramsMap, BaseMapper mapper){
        if (paramsMap.size() < 1){
            //没有需要校验的字段
            return null;
        }

        QueryWrapper<Object> wrapper = new QueryWrapper<>();
        Optional.ofNullable(id).ifPresent(o -> {
            wrapper.ne("id",id);
        });

        for (Map.Entry<String, Object> entry : paramsMap.entrySet()) {
            wrapper.eq(entry.getKey(),entry.getValue());
            Long count = mapper.selectCount(wrapper);
            if (count > 0){
                return entry.getValue() + "已经存在";
            }
            wrapper.clear();
            Optional.ofNullable(id).ifPresent(o -> {
                wrapper.ne("id",id);
            });
        }
        return null;
    }
}
