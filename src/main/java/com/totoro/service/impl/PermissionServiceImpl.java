package com.totoro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.totoro.mapper.PermissionMapper;
import com.totoro.pojo.Permission;
import com.totoro.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
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
    public Set<String> findKeyByUserName(String username) {

        QueryWrapper<Object> wrapper = new QueryWrapper<>();
        wrapper.eq("u.username",username);
        List<String> permissionKeys = permissionMapper.findKeyByUsername(wrapper);
        Set<String> permissions = permissionKeys.stream().collect(Collectors.toSet());
        return permissions;
    }
}
