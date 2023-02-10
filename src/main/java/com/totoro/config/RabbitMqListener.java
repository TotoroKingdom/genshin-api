package com.totoro.config;

import com.totoro.pojo.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqListener {

//    @RabbitListener(queues = "yyds")
//    public void test(Message message){
//        System.out.println(new String(message.getBody()));
//    }
//
//    @RabbitListener(queues = "yyds")
//    public String receiver(Message message){
//        System.out.println("二号消息队列监听器"+new String(message.getBody()));
//        return "收到";
//    }

    @RabbitListener(queues = "yyds", messageConverter = "jacksonConverter")
    public void receiver3(User user){
        System.out.println("转换器监听器"+user);
    }

    @RabbitListener(queues = "dl-yyds",messageConverter = "jacksonConverter")
    public void receiverFromDeadLetter(User user){
        System.out.println("死信队列监听器"+user);
    }
}
