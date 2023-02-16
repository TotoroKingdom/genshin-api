package com.totoro.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.totoro.constants.Result;
import com.totoro.exception.ServiceException;
import com.totoro.pojo.User;
import com.totoro.pojo.vo.UserVo;
import com.totoro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 测试接口
 * @author:totoro
 * @createDate:2023/2/8
 * @description:
 */
@Slf4j
@RestController
@RequestMapping("test")
public class AppleTestController {

    @Resource
    private UserService userService;


    /**
     * 全局异常测试
     * @param user
     * @return
     */
    @RequestMapping("union")
    public Result union(@RequestBody User user){

        if (true){
            throw new ServiceException("异常测试");
        }

        IPage<UserVo> userVoList = userService.findUserVoList(user);

        return Result.success(userVoList);
    }

    /**
     * 校验注解测试
     * @param user
     * @return
     */
    @RequestMapping("add")
    public Result add(@RequestBody @Validated User user){
        userService.add(user);
        return Result.success("新增成功");
    }

    /**
     * 权限测试1
     * @return
     */
    @GetMapping("test1")
    @PreAuthorize("hasAuthority('test')")
    public String test1(){
        return "test1";
    }

    /**
     * 权限测试2
     * @return
     */
    @GetMapping("pre/auth")
    @PreAuthorize("hasAuthority('auth')")
    public String preAuth(){
        return "auth";
    }
}
