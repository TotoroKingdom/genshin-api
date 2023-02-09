package com.totoro.controller;

import com.totoro.pojo.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:totoro
 * @createDate:2023/2/9
 * @description:
 */
@RestController
@RequestMapping("user")
public class UserController {

    @PostMapping("info")
    public User info(@RequestParam("id") Long id){
        User user = new User();
        user.setId(id);
        user.setNickname("甘雨");
        user.setUsername("ganyu");
        return user;
    }
}
