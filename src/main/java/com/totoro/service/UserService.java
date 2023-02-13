package com.totoro.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.totoro.pojo.User;
import com.totoro.pojo.vo.UserVo;

/**
 * @author:totoro
 * @createDate:2023/2/10
 * @description:
 */
public interface UserService {


    /**
     * 更新
     * @param user
     */
    void renew(User user);

    /**
     * 新增
     * @param user
     */
    void add(User user);

    /**
     * 查询用户及关联的角色
     * @return
     */
    IPage<UserVo> findUserVoList(User user);

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

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    User findUserByUserName(String username);
}
