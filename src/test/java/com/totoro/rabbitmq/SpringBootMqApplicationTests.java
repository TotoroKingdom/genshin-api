package com.totoro.rabbitmq;

import com.totoro.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class SpringBootMqApplicationTests {

    @Resource
    RabbitTemplate rabbitTemplate;

    @Test
    void publisher(){
        rabbitTemplate.convertAndSend("amq.direct","my-yyds","Hello World!");
    }

    @Test
    void publisherAndReceive(){
        Object o = rabbitTemplate.convertSendAndReceive("amq.direct", "my-yyds", "Hello World##");
        System.out.println("消费者响应"+o);
    }

    @Test
    void sendUser(){
        User user = new User();
        user.setName("鹌鹑蛋");
        rabbitTemplate.convertAndSend("amq.direct","my-yyds",user);
    }
}
