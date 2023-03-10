package com.totoro.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.totoro.constants.UserConstants;
import com.totoro.mapper.UserMapper;
import com.totoro.pojo.Permission;
import com.totoro.pojo.Role;
import com.totoro.pojo.User;
import com.totoro.pojo.dto.Params;
import com.totoro.pojo.vo.UserVo;
import com.totoro.service.PermissionService;
import com.totoro.service.RoleService;
import com.totoro.service.UserService;
import com.totoro.utils.ParamsUtils;
import com.totoro.utils.RsaUtils;
import com.totoro.utils.SecurityUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:totoro
 * @createDate:2023/2/10
 * @description:
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;

    @Override
    public UserVo info() {
        UserVo vo = new UserVo();
        User user = SecurityUtils.getUser();
        List<Role> roles = roleService.findByUserId(user.getId());
        List<Permission> menus = permissionService.findByUserId(user.getId());
        vo.setRoles(roles);
        vo.setMenus(menus);
        return vo;
    }


    @Override
    public int renew(User user) {
        User u = new User();
        u.setNickname(u.getNickname());
        u.setUsername(u.getUsername());
        u.setStatus(user.getStatus());
        u.setAvatar(user.getAvatar());
        return userMapper.updateById(u);
    }

    @SneakyThrows
    @Override
    public int add(User user) {
        if (ObjectUtil.isNotNull(user.getPassword())){
            String pwd = RsaUtils.decryptByPrivateKey(user.getPassword());
            user.setPassword(SecurityUtils.encryptPassword(pwd));
        }

        return userMapper.insert(user);
    }

    @Override
    public IPage<UserVo> findUserVoList(User user) {

        Params params = ParamsUtils.getParams();

        QueryWrapper<Object> wrapper = new QueryWrapper<>();
        if (ObjectUtil.isNotNull(params.getBeginTime()) && ObjectUtil.isNotNull(params.getEndTime())){
            wrapper.between("create_time",params.getBeginTime(),params.getEndTime());
        }
        wrapper.like(ObjectUtil.isNotNull(user.getNickname()),"nickname",user.getNickname());
        wrapper.like(ObjectUtil.isNotNull(user.getUsername()),"username",user.getUsername());
        wrapper.like(ObjectUtil.isNotNull(user.getEmail()),"email",user.getEmail());
        wrapper.eq(ObjectUtil.isNotNull(user.getStatus()),"status",user.getStatus());

        Page<UserVo> userVoList = userMapper.findUserVoList(ParamsUtils.getPage(), wrapper);

        return userVoList;
    }

    @Override
    public UserVo findById(Long id) {

        User user = userMapper.selectById(id);

        return null;
    }

    @Override
    public int deleteById(Long id) {
        return userMapper.deleteById(id);
    }

    @Override
    public User findUserByRegisterCode(String registerCode) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getRegisterCode, registerCode);

        User user = userMapper.selectOne(wrapper);

        return user;
    }

    @Override
    public User findUserByEmail(String email) {

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getEmail, email);
        wrapper.ne(User::getStatus, UserConstants.DISABLED);

        User user = userMapper.selectOne(wrapper);

        return user;
    }

    @Override
    public User findUserByUserName(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);

        User user = userMapper.selectOne(wrapper);
        return user;
    }
}
