package com.totoro.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.totoro.pojo.Permission;

import java.util.Set;

/**
 * @author: totoro
 * @createDate: 2023 02 13 22 12
 * @description:
 **/
public interface PermissionService {

    /**
     * 新增菜单
     * @param permission
     * @return
     */
    int add(Permission permission);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    Permission find(Long id);

    /**
     * 更新
     * @param permission
     * @return
     */
    int renew(Permission permission);

    /**
     * 分页查询
     * @param permission
     * @return
     */
    Page<Permission> page(Permission permission);

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(Long id);


    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    Set<String> findKeyByUserName(String username);
}
