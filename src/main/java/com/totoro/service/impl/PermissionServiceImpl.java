package com.totoro.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.totoro.mapper.PermissionMapper;
import com.totoro.pojo.Permission;
import com.totoro.service.PermissionService;
import com.totoro.utils.ParamsUtils;
import com.totoro.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: totoro
 * @createDate: 2023 02 13 22 12
 * @description:
 **/
@Slf4j
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Resource
    private PermissionMapper permissionMapper;


    @Override
    public List<Permission> findByUserId(Long userID) {

        boolean admin = SecurityUtils.isAdmin();
        QueryWrapper<Object> wrapper = new QueryWrapper<>();
        if (admin){
            List<Permission> allMenu = permissionMapper.findAllMenu();

            return filter(this.setChild(allMenu));
        }else {
            wrapper.eq("u.id",userID);
        }

        List<Permission> menu = permissionMapper.findMenuByUserId(wrapper);
        return filter(this.setChild(menu));
    }

    public List<Permission> filter(List<Permission> permissions){
        if (CollUtil.isEmpty(permissions)){
            return null;
        }
        List<Permission> collect = permissions.stream().filter(p -> p.getParentId().equals(0l)).collect(Collectors.toList());
        return collect;
    }

    @Override
    public int add(Permission permission) {

        permission.setCreateBy(SecurityUtils.getUserId());
        if (ObjectUtil.isNull(permission.getParentId())){
            permission.setParentId(0L);
        }

        return permissionMapper.insert(permission);
    }

    @Override
    public Permission find(Long id) {
        Permission permission = permissionMapper.selectById(id);
        return permission;
    }

    @Override
    public int renew(Permission permission) {
        int i = permissionMapper.updateById(permission);
        return i;
    }

    @Override
    public Page<Permission> page(Permission permission) {
        //分页工具，获取前端传来的分页参数
        Page page = ParamsUtils.getPage();
        long current = page.getCurrent();
        long size = page.getSize();
        //索引起点
        long start = ((current - 1) * size);
        //索引截至点
        long end = start + size;

        //查询所有
        List<Permission> list = selectAll();

        //设置子节点
        List<Permission> permissions = setChild(list);

        //只要父级点
        List<Permission> collect = permissions.stream().filter(p -> p.getParentId().equals(0l)).collect(Collectors.toList());
        //手动分页
        List<Permission> records = new ArrayList<>();
        if (end >= collect.size()){
            end = collect.size();
        }
        for (long i = start; i < end; i++) {
            records.add(collect.get((int) i));
        }

        page.setRecords(records);
        page.setTotal(collect.size());

        return page;
    }

    public List<Permission> selectAll(){
        LambdaQueryWrapper<Permission> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(Permission::getOrderNum);
        List<Permission> list = permissionMapper.selectList(wrapper);
        return list;
    }

    /**
     * 设置子节点，
     * chatGPT优化方案
     * @param list
     * @return
     */
    public List<Permission> setChild(List<Permission> list){
        if (CollUtil.isEmpty(list)){
            return null;
        }
        Map<Long, List<Permission>> map = list.stream().collect(Collectors.groupingBy(Permission::getParentId));
        for (Permission permission : list) {
            List<Permission> temp = map.get(permission.getId());
            setChild(temp);
            permission.setChild(temp);
        }
        return list;
    }


    @Override
    public int delete(Long id) {
        int i = permissionMapper.deleteById(id);
        return i;
    }

    @Override
    public Set<String> findKeyByUserName(String username) {

        QueryWrapper<Object> wrapper = new QueryWrapper<>();
        wrapper.eq("u.username",username);
        List<String> permissionKeys = permissionMapper.findKeyByUsername(wrapper);
        Set<String> permissions = permissionKeys.stream().collect(Collectors.toSet());
        return permissions;
    }
}
