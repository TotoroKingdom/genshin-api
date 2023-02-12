package com.totoro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.totoro.constants.UserConstants;
import com.totoro.mapper.UserMapper;
import com.totoro.pojo.User;
import com.totoro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author:totoro
 * @createDate:2023/2/10
 * @description:
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public User findUserByRegisterCode(String registerCode) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getRegisterCode, registerCode);

        User user = userMapper.selectOne(wrapper);

        return user;
    }

    @Override
    public User findUserByEmail(String email) {

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getEmail, email);
        wrapper.ne(User::getStatus, UserConstants.DISABLED);

        User user = userMapper.selectOne(wrapper);

        return user;
    }
}
