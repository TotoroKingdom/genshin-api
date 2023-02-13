package com.totoro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.totoro.mapper.RolePermissionMapper;
import com.totoro.pojo.RolePermission;
import com.totoro.service.RolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: totoro
 * @createDate: 2023 02 13 22 13
 * @description:
 **/
@Slf4j
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

    @Resource
    private RolePermissionMapper rolePermissionMapper;


}
