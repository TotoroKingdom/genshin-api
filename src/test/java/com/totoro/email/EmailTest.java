package com.totoro.email;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author:totoro
 * @createDate:2023/2/10
 * @description:
 */
@SpringBootTest
class EmailTest {

    @Resource
    private Email email;

    @Test
    void send(){
        String receiver = "634581304@qq.com";
        String subject = "测试邮件";
        String text = "hello world!";
        email.send(receiver,subject,text);
    }

}