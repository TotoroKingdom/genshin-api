package com.totoro.annonation;


import com.totoro.constants.Result;

import java.lang.annotation.*;

/**
 * @author:totoro
 * @createDate:2023/2/9
 * @description:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiResult {

    String value() default "";

    int successCode() default 200;

    String successMsg() default "success";

    Class<? extends Result> resultClass() default Result.class;
}
