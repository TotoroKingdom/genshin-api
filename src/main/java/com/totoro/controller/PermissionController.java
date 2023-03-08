package com.totoro.controller;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.totoro.constants.Result;
import com.totoro.pojo.Permission;
import com.totoro.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 权限菜单接口
 * @author:totoro
 * @createDate:2023/2/16
 * @description:
 */
@Slf4j
@RestController
@RequestMapping("menu")
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    /**
     * 新增菜单
     * @param permission
     * @return
     */
    @PostMapping("add")
    public Result<Integer> add(@RequestBody @Valid Permission permission){
        int add = permissionService.add(permission);
        return Result.success(add);
    }

    /**
     * 根据ID查询菜单
     * @param id
     * @return
     */
    @PostMapping("find")
    public Result<Permission> find(@RequestParam("id") Long id){
        Permission permission = permissionService.find(id);
        return Result.success(permission);
    }

    /**
     * 修改菜单
     * @param permission
     * @return
     */
    @PostMapping("update")
    public Result<Integer> renew(@RequestBody Permission permission){
        int renew = permissionService.renew(permission);
        return Result.success(renew);
    }

    /**
     * 分页查询菜单
     * @param permission
     * @return
     */
    @PostMapping("page")
    public Result<Page<Permission>> page(@RequestBody Permission permission){
        Page<Permission> page = permissionService.page(permission);
        return Result.success(page);
    }


    /**
     * 删除菜单
     * @param id
     * @return
     */
    @PostMapping("delete")
    public Result<Integer> delete(@RequestParam("id") Long id){
        int delete = permissionService.delete(id);
        return Result.success(delete);
    }
}
