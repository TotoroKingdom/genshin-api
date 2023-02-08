package com.totoro.controller;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author:totoro
 * @createDate:2023/2/8
 * @description:
 */
@Slf4j
@RestController
public class AuthTestController {

    @GetMapping("test1")
    public String test1(){
        return "test1";
    }
}
