package com.totoro.constants;

import cn.hutool.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @author:totoro
 * @createDate:2023/2/9
 * @description:
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer code;

    private String msg;

    private T data;

    public static <T> Result<T> success(T data){
        Result result = new Result();
        result.setCode(HttpStatus.HTTP_OK);
        result.setMsg("success");
        result.setData(data);;
        return result;
    }

    public static <T> Result<T> error(Integer code, String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(null);;
        return result;
    }

    public static <T> Result<T> error(String msg){
        Result result = new Result();
        result.setCode(HttpStatus.HTTP_INTERNAL_ERROR);
        result.setMsg(msg);
        result.setData(null);;
        return result;
    }
}
