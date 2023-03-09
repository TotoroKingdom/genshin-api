package com.totoro.service;

import com.totoro.pojo.Role;

import java.util.List;

/**
 * @author:totoro
 * @createDate:2023/2/13
 * @description:
 */
public interface RoleService {

    /**
     * 根据用户ID查询角色
     * @param userId
     * @return
     */
    List<Role> findByUserId(Long userId);
}
