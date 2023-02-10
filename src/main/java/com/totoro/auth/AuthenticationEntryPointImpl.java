package com.totoro.auth;

import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONObject;
import com.totoro.constants.Result;
import com.totoro.utils.ServletUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author:totoro
 * @createDate:2023/2/9
 * @description: 未登录
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Result<Object> fail = Result.error(HttpStatus.HTTP_UNAUTHORIZED, "未登录");
        JSONObject jsonObject = new JSONObject(fail);
        ServletUtils.renderString(response, jsonObject.toString());
    }
}
