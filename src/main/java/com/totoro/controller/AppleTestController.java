package com.totoro.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.totoro.annonation.ApiResult;
import com.totoro.constants.Result;
import com.totoro.pojo.Apple;
import com.totoro.pojo.User;
import com.totoro.pojo.vo.UserVo;
import com.totoro.service.AppleService;
import com.totoro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:totoro
 * @createDate:2023/2/8
 * @description:
 */
@Slf4j
@RestController
@RequestMapping("test")
public class AppleTestController {

    @Resource
    private AppleService appleService;
    @Resource
    private UserService userService;

    @RequestMapping("page")
    public Result page(){
        Page<Apple> list = appleService.page();
        return Result.success(list);
    }

    @RequestMapping("union")
    public Result union(@RequestBody User user){

        IPage<UserVo> userVoList = userService.findUserVoList(user);

        return Result.success(userVoList);
    }

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