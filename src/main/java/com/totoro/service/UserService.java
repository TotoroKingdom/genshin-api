package com.totoro.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.totoro.pojo.Permission;
import com.totoro.pojo.User;
import com.totoro.pojo.vo.UserVo;

import java.util.List;

/**
 * @author:totoro
 * @createDate:2023/2/10
 * @description:
 */
public interface UserService {

    /**
     * 获取当前登录用户信息
     * @return
     */
    UserVo info();


    /**
     * 更新
     * @param user
     */
    int renew(User user);

    /**
     * 新增
     * @param user
     */
    int add(User user);

    /**
     * 查询用户及关联的角色
     * @return
     */
    IPage<UserVo> findUserVoList(User user);

    /**
     * 根据ID查用户信息
     * @param id
     * @return
     */
    UserVo findById(Long id);

    /**
     * 根据ID删除
     * @param id
     * @return
     */
    int deleteById(Long id);

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
