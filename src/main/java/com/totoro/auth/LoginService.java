package com.totoro.auth;

import com.totoro.pojo.auth.LoginBody;

import java.util.Map;

/**
 * @author:totoro
 * @createDate:2023/2/8
 * @description:
 */
public interface LoginService {

    Map login(LoginBody body);
}
