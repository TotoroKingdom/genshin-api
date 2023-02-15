package com.totoro.annonation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author:totoro
 * @createDate:2023/2/14
 * @description:
 */
@Documented //用来文档的
@Target(ElementType.TYPE) //作用目标：Class, interface (including annotation type), or enum declaration
@Retention(RetentionPolicy.RUNTIME) //生命周期：运行时
@Constraint(validatedBy = UniqueCheckValidator.class) //限定自定义注解使用的方法
public @interface UniqueCheck {

    //需要校验的字段
    String filedName();

    //默认提示的文本
    String message() default "字段已经存在";

    //分组校验
    Class<?>[] groups() default {};

    //这个不知道干嘛用的
    Class<? extends Payload>[] payload() default {};
}
