package com.totoro.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.totoro.constants.Result;
import com.totoro.pojo.User;
import com.totoro.pojo.vo.UserVo;
import com.totoro.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 用户管理API
 * @author:totoro
 * @createDate:2023/2/9
 * @description:
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 分页查询
     * @param user
     * @return
     */
    @RequestMapping("page")
    public Result<IPage<UserVo>> page(@RequestBody User user){

        IPage<UserVo> userVoList = userService.findUserVoList(user);

        return Result.success(userVoList);
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @RequestMapping("add")
    public Result<IPage<UserVo>> add(@RequestBody User user){

        userService.add(user);

        return Result.success(null);
    }
}
