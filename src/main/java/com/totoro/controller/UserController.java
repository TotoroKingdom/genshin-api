package com.totoro.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.totoro.constants.Result;
import com.totoro.pojo.User;
import com.totoro.pojo.vo.UserVo;
import com.totoro.service.UserService;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.springframework.validation.annotation.Validated;
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
     * 更新用户
     * @param user
     * @return
     */
    @PostMapping("update")
    public Result<Integer> update(@RequestBody @Validated(Update.class) User user){

        return Result.success(userService.renew(user));
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping("add")
    public Result<Integer> add(@RequestBody @Validated(Insert.class) User user){

        return Result.success(userService.add(user));
    }

    /**
     * 分页查询
     * @param user
     * @return
     */
    @PostMapping("page")
    public Result<IPage<UserVo>> page(@RequestBody User user){

        IPage<UserVo> userVoList = userService.findUserVoList(user);

        return Result.success(userVoList);
    }

    /**
     * 根据ID查用户
     * @param id
     * @return
     */
    @PostMapping("find")
    public Result<UserVo> find(@RequestParam("id") Long id){

        return Result.success(userService.findById(id));
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @PostMapping("delete")
    public Result<Integer> delete(@RequestParam("id") Long id){

        return Result.success(userService.deleteById(id));
    }


}
