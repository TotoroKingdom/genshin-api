package com.totoro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.totoro.mapper.RoleMapper;
import com.totoro.pojo.Role;
import com.totoro.service.RoleService;
import com.totoro.utils.ParamsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public Integer add(Role role) {
        int insert = roleMapper.insert(role);
        return insert;
    }

    @Override
    public Role findById(Long id) {
        Role role = roleMapper.selectById(id);
        return role;
    }

    @Override
    public Integer renew(Role role) {
        int i = roleMapper.updateById(role);
        return i;
    }

    @Override
    public Page<Role> page(Role role) {
        Page page = ParamsUtils.getPage();
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();

        Page<Role> list = roleMapper.selectPage(page, wrapper);

        return list;
    }

    @Override
    public Integer remove(Long id) {
        int i = roleMapper.deleteById(id);
        return i;
    }

    @Override
    public List<Role> findByUserId(Long userId) {
        List<Role> roles = roleMapper.findRoleByUserId(userId);
        return roles;
    }
}
