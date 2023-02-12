package com.totoro.service;

import com.totoro.pojo.User;

/**
 * @author:totoro
 * @createDate:2023/2/10
 * @description:
 */
public interface UserService {

    /**
     * 根据注册码查询
     * @param registerCode
     * @return
     */
    User findUserByRegisterCode(String registerCode);

    /**
     * 根据email查询
     * @param email
     * @return
     */
    User findUserByEmail(String email);
}
