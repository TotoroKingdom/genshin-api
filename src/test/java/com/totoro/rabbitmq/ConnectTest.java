package com.totoro.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

public class ConnectTest {

    public static void main(String[] args) {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.121.121");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setVirtualHost("test");

        try(Connection connection = factory.newConnection()){
            Channel channel = connection.createChannel();

            channel.queueDeclare("java1Queue",false,false,false,null);

            channel.queueBind("java1Queue","amq.direct","testKey");

            channel.basicPublish("amq.direct","java1Queue",null,"HelloWorld".getBytes());


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
