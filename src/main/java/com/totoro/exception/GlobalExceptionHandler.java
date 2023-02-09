package com.totoro.exception;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpStatus;
import com.totoro.constants.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author:totoro
 * @createDate:2023/2/9
 * @description:
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    //业务异常
    @ExceptionHandler(ServiceException.class)
    public Result handleServiceException(ServiceException e, HttpServletRequest request){
        log.error(e.getMessage(), e);
        Integer code = e.getCode();
        String message = e.getMessage();
        return ObjectUtil.isNotNull(code) ? Result.error(code, message) : Result.error(message);
    }

    //未授权
    @ExceptionHandler(AccessDeniedException.class)
    public Result handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request){
        log.error("请求地址'{}',权限校验失败'{}'", request.getRequestURI(), e.getMessage());
        return Result.error(HttpStatus.HTTP_FORBIDDEN, "没有权限，请联系管理员授权");
    }

    //请求方式不支持
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handlerHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e
            , HttpServletRequest request){
        log.error("请求地址'{}',不支持'{}'请求", request.getRequestURI(), e.getMethod());
        return Result.error(e.getMessage());
    }

    //运行时异常
    @ExceptionHandler(RuntimeException.class)
    public Result handlerRuntimeException(RuntimeException e, HttpServletRequest request){
        log.error("请求地址'{}',发生未知异常.", request.getRequestURI(), e);
        log.error("运行时异常:"+e.getMessage());
        return Result.error("网络异常");
    }

    //系统异常
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e, HttpServletRequest request){
        log.error("请求地址'{}',发生系统异常.", request.getRequestURI(), e);
        return Result.error(e.getMessage());
    }

    //校验异常
    @ExceptionHandler(BindException.class)
    public Result handleBindException(BindException e){
        log.error(e.getMessage(), e);
        String errorMsg = e.getAllErrors().get(0).getDefaultMessage();
        return Result.error(errorMsg);
    }

    //自定义校验异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error(e.getMessage(), e);
        String errorMsg = e.getBindingResult().getFieldError().getDefaultMessage();
        return Result.error(errorMsg);
    }

}
