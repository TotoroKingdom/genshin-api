package com.totoro.controller;

import com.totoro.auth.LoginService;
import com.totoro.pojo.auth.LoginBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author:totoro
 * @createDate:2023/2/8
 * @description:
 */
@Slf4j
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("login")
    public Map login(@RequestBody LoginBody body){
        Map login = loginService.login(body);
        return login;
    }
}
