package com.totoro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.totoro.mapper.UserMapper;
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
}
