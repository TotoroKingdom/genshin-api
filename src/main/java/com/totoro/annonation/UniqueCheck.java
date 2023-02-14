package com.totoro.annonation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author:totoro
 * @createDate:2023/2/14
 * @description:
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueCheckValidator.class)
public @interface UniqueCheck {

    //需要校验的字段
    String filedName();

    String message() default "字段已经存在";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
