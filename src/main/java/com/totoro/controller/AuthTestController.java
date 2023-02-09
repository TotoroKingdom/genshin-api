package com.totoro.controller;

import com.totoro.annonation.ApiResult;
import com.totoro.constants.Result;
import com.totoro.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:totoro
 * @createDate:2023/2/8
 * @description:
 */
@Slf4j
@RestController
@RequestMapping("test")
public class AuthTestController {

    @GetMapping("hello")
    public Result<User> hello(){
        User user = new User();
        user.setId(1l);
        user.setNickname("钟离");
        user.setUsername("zhongli");
        user.setPassword("zhonglipwd");
        return Result.success(user);
    }

    @ApiResult
    @GetMapping("user")
    public User user(){
        User user = new User();
        user.setId(1l);
        user.setNickname("钟离");
        user.setUsername("zhongli");
        user.setPassword("zhonglipwd");
        return user;
    }

    @GetMapping("test1")
    @PreAuthorize("hasAuthority('test')")
    public String test1(){
        return "test1";
    }

    @GetMapping("pre/auth")
    @PreAuthorize("hasAuthority('auth')")
    public String preAuth(){
        return "auth";
    }
}
