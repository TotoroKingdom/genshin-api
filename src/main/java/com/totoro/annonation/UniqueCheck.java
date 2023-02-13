package com.totoro.annonation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author:totoro
 * @createDate:2023/2/13
 * @description:
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UniqueCheckValidator.class)
public @interface UniqueCheck {

    String id() default "id";

    String field();

    //提示
    String tips() default "存在重复";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
