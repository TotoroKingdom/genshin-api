package com.totoro.service;

import java.util.Set;

/**
 * @author: totoro
 * @createDate: 2023 02 13 22 12
 * @description:
 **/
public interface PermissionService {


    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    Set<String> findKeyByUserName(String username);
}
