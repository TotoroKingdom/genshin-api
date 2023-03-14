package com.totoro.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.totoro.pojo.Role;

import java.util.List;

/**
 * @author:totoro
 * @createDate:2023/2/13
 * @description:
 */
public interface RoleService {

    /**
     * 新增角色
     * @param role
     * @return
     */
    Integer add(Role role);

    /**
     * 查询角色详情
     * @param id
     * @return
     */
    Role findById(Long id);

    /**
     * 更新
     * @param role
     * @return
     */
    Integer renew(Role role);

    /**
     * 分页列表
     * @param role
     * @return
     */
    Page<Role> page(Role role);

    /**
     * 移除
     * @param id
     * @return
     */
    Integer remove(Long id);

    /**
     * 根据用户ID查询角色
     * @param userId
     * @return
     */
    List<Role> findByUserId(Long userId);
}
