package com.totoro.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {


    //交换机Bean，可以多个
    @Bean("directExchange")
    public Exchange exchange(){
        return ExchangeBuilder.directExchange("amq.direct").build();
    }

    //消息队列
    @Bean("yydsQueue")
    public Queue queue(){
        return QueueBuilder.nonDurable("yyds").build();
    }

    @Bean("binding")
    public Binding binding(@Qualifier("directExchange") Exchange exchange
            , @Qualifier("yydsQueue") Queue queue){
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("my-yyds")  //自定义的routingKey
                .noargs();
    }

    @Bean("jacksonConverter")
    public Jackson2JsonMessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }


}
