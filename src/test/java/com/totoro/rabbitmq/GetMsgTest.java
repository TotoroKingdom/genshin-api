package com.totoro.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.nio.charset.StandardCharsets;

public class    GetMsgTest {

    public static void main(String[] args) {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.121.121");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setVirtualHost("test");

        try(Connection connection = factory.newConnection()){
            Channel channel = connection.createChannel();

            channel.basicConsume("javaQueue",false,(s, delivery) -> {
                System.out.println(new String(delivery.getBody()));
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            },s -> {});



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
