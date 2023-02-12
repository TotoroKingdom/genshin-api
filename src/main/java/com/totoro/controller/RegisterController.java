package com.totoro.controller;

import com.totoro.constants.Result;
import com.totoro.pojo.auth.LoginBody;
import com.totoro.service.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author:totoro
 * @createDate:2023/2/10
 * @description:
 */
@Slf4j
@RequestMapping("register")
@RestController
public class RegisterController {

    @Resource
    private RegisterService registerService;

    @PostMapping("send")
    public Result register(@RequestBody @Validated LoginBody loginBody){
        return registerService.register(loginBody);
    }

    @GetMapping("active")
    public Result active(@RequestParam String code){
        return registerService.active(code);
    }
}
