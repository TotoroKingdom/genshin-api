package com.totoro.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    @Bean("jacksonConverter")
    public Jackson2JsonMessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }


    //交换机Bean，可以多个
    @Bean("directExchange")
    public Exchange exchange(){
        return ExchangeBuilder.directExchange("amq.direct").build();
    }

    //消息队列
    @Bean("yydsQueue")
    public Queue queue(){
        return QueueBuilder
                .nonDurable("yyds")
                .deadLetterExchange("dlx.direct")
                .deadLetterRoutingKey("dl-yyds")
                .ttl(5000)
                .maxLength(3)
                .build();
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

    //-------------------------------
    //死信队列

    //创建死信交换机
    @Bean("directDlExchange")
    public Exchange dlExchange(){

        return ExchangeBuilder.directExchange("dlx.direct").build();
    }

    //创建死信队列
    @Bean("yydsDlQueue")
    public Queue dlQueue(){
        return QueueBuilder
                .nonDurable("dl-yyds").build();
    }

    //绑定死信队列和交互机
    @Bean("dlBinding")
    public Binding dlBinding(@Qualifier("directDlExchange") Exchange exchange
            , @Qualifier("yydsDlQueue") Queue queue ) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("dl-yyds")
                .noargs();
    }





}
