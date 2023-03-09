package com.totoro.utils;

import com.totoro.constants.UserConstants;
import com.totoro.pojo.User;
import com.totoro.pojo.auth.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author: totoro
 * @createDate: 2023 02 12 23 38
 * @description:
 **/
public class SecurityUtils {

    /**
     * 生成BCryptPasswordEncoder密码
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    //获取登录的用户
    public static LoginUser getLoginUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (LoginUser) authentication.getPrincipal();
    }

    //获取用户信息
    public static User getUser(){
        return getLoginUser().getUser();
    }

    //获取用户信息
    public static Long getUserId(){
        return getLoginUser().getUser().getId();
    }

    public static boolean isAdmin(){
        String registerCode = getUser().getRegisterCode();
        if (registerCode.equals(UserConstants.ADMIN)){
            return true;
        }
        return false;
    }

}
