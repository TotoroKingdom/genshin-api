package com.totoro.controller;

import cn.hutool.core.lang.Assert;
import com.totoro.auth.LoginService;
import com.totoro.constants.Result;
import com.totoro.pojo.auth.LoginBody;
import com.totoro.utils.RsaUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * 登录API
 * @author:totoro
 * @createDate:2023/2/8
 * @description:
 */
@Slf4j
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 登录接口
     * @param body
     * @return
     * @throws Exception
     */
    @PostMapping("login")
    public Result login(@RequestBody LoginBody body) throws Exception {
        Assert.notEmpty(body.getPassword(),"密码不能为空");
        String pwd = RsaUtils.decryptByPrivateKey(body.getPassword());
        body.setPassword(pwd);
        String jwtToken = loginService.login(body);
        return Result.success(jwtToken);
    }


}
