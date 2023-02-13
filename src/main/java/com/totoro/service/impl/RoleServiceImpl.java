package com.totoro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.totoro.mapper.RoleMapper;
import com.totoro.pojo.Role;
import com.totoro.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author:totoro
 * @createDate:2023/2/13
 * @description:
 */
@Slf4j
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Resource
    private RoleMapper roleMapper;

}
