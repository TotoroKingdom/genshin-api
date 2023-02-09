package com.totoro.auth;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import cn.hutool.json.JSONObject;
import com.totoro.constants.Result;
import com.totoro.pojo.auth.LoginUser;
import com.totoro.utils.ServletUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author:totoro
 * @createDate:2023/2/9
 * @description: 重新这个类后，就不需要写退出登录接口了
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    @Resource
    private TokenService tokenService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (ObjectUtil.isNotNull(loginUser)){
            String username = loginUser.getUsername();
            tokenService.removeLoginUser(loginUser.getToken());
        }
        Result<Object> success = Result.success("退出成功",null);
        JSONObject jsonObject = new JSONObject(success);
        ServletUtils.renderString(response, jsonObject.toString());
    }
}
