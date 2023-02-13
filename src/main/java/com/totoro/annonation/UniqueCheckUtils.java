package com.totoro.annonation;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.totoro.exception.ServiceException;
import org.apache.ibatis.binding.MapperRegistry;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author:totoro
 * @createDate:2023/2/13
 * @description:
 */
public class UniqueCheckUtils {

    private static String id;

    private static Long idValue;

    private static String field;

    private static Object fieldValue;

    private static String db_field;

    private static Object object;

    public static <aClass> boolean fieldRepeat(String id, String field, Object object, String tips){
        UniqueCheckUtils.id = id;
        UniqueCheckUtils.field = field;
        UniqueCheckUtils.object = object;
        getFieldValue();

        // 工厂模式 + ar动态语法
        Class<?> aClass = object.getClass();



//        List list = entity.selectList( new EntityWrapper().eq( db_field, fieldValue ) );
        List list = null;
        // 如果数据重复返回false -> 再返回自定义错误消息到前端
        if ( idValue == null ){
            if ( CollUtil.isNotEmpty( list ) ){
                throw new ServiceException( tips );
            }
        } else {
            if ( CollUtil.isNotEmpty( list ) ){
                // fieldValueNew：前端输入字段值
                Object fieldValueNew = fieldValue;
//                UniqueCheckUtils.object = entity.selectById( idValue );
                // 获取该id所在对象的校验字段值 - 旧数据
                getFieldValue();
                if ( !fieldValueNew.equals( fieldValue ) || list.size() > 1 ){
                    throw new ServiceException( tips );
                }
            }
        }
        return true;


    }

    public static void getFieldValue(){
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field f : fields) {
            //设置对象中成员属性 private 为可读
            f.setAccessible(true);
            //判断字段注解是否存在
            if (f.isAnnotationPresent(UniqueCheck.class)){
                if (f.getName().equals(field)){
                    try {
                        fieldValue = f.get(object);
                        TableField annotation = f.getAnnotation(TableField.class);
                        db_field = annotation.value();

                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }

                if (id.equals(f.getName())){
                    try {
                        idValue = (Long) f.get(object);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }
}
