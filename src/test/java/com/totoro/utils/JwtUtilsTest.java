package com.totoro.utils;

import com.totoro.auth.TokenService;
import com.totoro.pojo.auth.LoginUser;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @author:totoro
 * @createDate:2023/2/9
 * @description:
 */
@SpringBootTest
public class JwtUtilsTest {

    @Resource
    private TokenService tokenService;


    @Test
    void createToken(){
        String secret = tokenService.createToken(new LoginUser());
        System.out.println(secret);
    }
}
