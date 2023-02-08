package com.totoro.controller;

import com.totoro.auth.AuthUtil;
import com.totoro.pojo.auth.LoginUser;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author:totoro
 * @createDate:2023/2/8
 * @description:
 */
@Slf4j
@RestController
public class AuthTestController {

    @GetMapping("test1")
    @PreAuthorize("hasAuthority('test')")
    public String test1(){
        return "test1";
    }

    @GetMapping("pre/auth")
    @PreAuthorize("hasAuthority('auth')")
    public String preAuth(){
        return "auth";
    }
}
