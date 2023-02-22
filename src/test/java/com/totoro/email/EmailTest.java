package com.totoro.email;

import com.totoro.constants.EmailConstants;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

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

    @Test
    void build(){
        String receiver = "634581304@qq.com";
        String subject = "测试邮件";
        String text = "baidu.com";
        String url = EmailConstants.EMAIL_REGISTER_TEMPLATE;
        email.send(receiver,subject,text,url);

    }

}