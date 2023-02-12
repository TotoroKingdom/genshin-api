package com.totoro.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.totoro.constants.EmailConstants;
import com.totoro.constants.Result;
import com.totoro.constants.UserConstants;
import com.totoro.email.Email;
import com.totoro.mapper.UserMapper;
import com.totoro.pojo.User;
import com.totoro.pojo.auth.LoginBody;
import com.totoro.service.RegisterService;
import com.totoro.service.UserService;
import com.totoro.utils.CodeUtils;
import com.totoro.utils.RsaUtils;
import com.totoro.utils.SecurityUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author:totoro
 * @createDate:2023/2/10
 * @description:
 */
@Slf4j
@Service
public class RegisterServiceImpl implements RegisterService {

    @Resource
    private UserService userService;
    @Resource
    private UserMapper userMapper;
    @Resource
    private Email email;

    @Value("${register.callback.url}")
    private String registerCallbackUrl;

    @SneakyThrows
    @Override
    public Result register(LoginBody loginBody) {

        String pwd = RsaUtils.decryptByPrivateKey(loginBody.getPassword());

        User user = userService.findUserByEmail(loginBody.getEmail());

        if (ObjectUtil.isNotNull(user)){
            return Result.success("该邮箱已经注册");
        }

        User newUser = new User();
        newUser.setUsername(loginBody.getEmail());
        newUser.setEmail(loginBody.getEmail());
        newUser.setPassword(SecurityUtils.encryptPassword(pwd));
        newUser.setRegisterCode(CodeUtils.getRegisterCode());
        newUser.setStatus(UserConstants.NOT_ACTIVATED);
        int insert = userMapper.insert(newUser);
        //发邮件
        String url = registerCallbackUrl + newUser.getRegisterCode();
        email.send(newUser.getEmail()
                , EmailConstants.EMAIL_REGISTER_SUBJECT
                , url
                , EmailConstants.EMAIL_REGISTER_TEMPLATE);
        return Result.success("你已经注册成功!请去邮箱激活");
    }

    @Override
    public Result active(String code) {

        User user = userService.findUserByRegisterCode(code);
        if (ObjectUtil.isNull(user)){
            return Result.success("无效的激活码");
        }
        if ( UserConstants.ACTIVATED.equals(user.getStatus()) ){
            return Result.success("账号已激活，请直接去登录");
        }

        user.setStatus(UserConstants.ACTIVATED);
        userMapper.updateById(user);

        return Result.success("激活成功，请去登录");
    }
}
