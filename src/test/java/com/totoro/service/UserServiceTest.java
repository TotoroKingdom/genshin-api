package com.totoro.service;

import com.totoro.mapper.UserMapper;
import com.totoro.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author:totoro
 * @createDate:2023/2/10
 * @description:
 */
@SpringBootTest
class UserServiceTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void insert(){
        User user = new User();
        user.setId(1l);
        user.setNickname("凌华");
        user.setUsername("linghua");
        userMapper.insert(user);

    }

}