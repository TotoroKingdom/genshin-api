package com.totoro.controller;

import com.totoro.auth.AuthUtil;
import com.totoro.auth.LoginService;
import com.totoro.pojo.auth.LoginBody;
import com.totoro.pojo.auth.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    @GetMapping("loginout")
    public String logout(HttpServletRequest request){
        LoginUser loginUser = AuthUtil.getLoginUser();
        System.out.println(loginUser);
        HttpSession session = request.getSession();
        session.removeAttribute("token"+loginUser.getUser().getId());
        return "logout success";
    }


}
