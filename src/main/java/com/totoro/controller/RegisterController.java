package com.totoro.controller;

import com.totoro.constants.Result;
import com.totoro.pojo.auth.LoginBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author:totoro
 * @createDate:2023/2/10
 * @description:
 */
@Slf4j
@RequestMapping("register")
@RestController
public class RegisterController {

    @PostMapping("send")
    public Result register(@RequestBody @Validated LoginBody loginBody){

        return null;
    }

    @PostMapping("active")
    public Result active(@RequestParam String code){

        return null;
    }
}
