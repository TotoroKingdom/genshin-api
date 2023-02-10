package com.totoro.service;

import com.totoro.constants.Result;
import com.totoro.pojo.auth.LoginBody;

/**
 * @author:totoro
 * @createDate:2023/2/10
 * @description:
 */
public interface RegisterService {

    /**
     * 注册
     * @param loginBody
     * @return
     */
    Result register(LoginBody loginBody);

    /**
     * 激活
     * @param code
     * @return
     */
    Result active(String code);
}
