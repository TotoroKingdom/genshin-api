package com.totoro.advice;

import com.totoro.annonation.ApiResult;
import com.totoro.constants.Result;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author:totoro
 * @createDate:2023/2/9
 * @description:
 */
@Order(0)
@ControllerAdvice
public class MyResponseBodyAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return !isStringConverter(converterType) && isApiResult(returnType);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        return Result.success(body);
    }

    protected boolean isStringConverter(Class convertType){
        return convertType.equals(StringHttpMessageConverter.class);
    }

    protected boolean isApiResult(MethodParameter returnType){
        return returnType.hasMethodAnnotation(ApiResult.class);
    }
}
