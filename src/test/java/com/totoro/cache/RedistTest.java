package com.totoro.cache;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @author:totoro
 * @createDate:2023/2/9
 * @description:
 */
@SpringBootTest
public class RedistTest {

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    void connect(){
        redisTemplate.opsForValue().set("testKey","testValue");
        Object testKey = redisTemplate.opsForValue().get("testKey");
        System.out.println(testKey);
    }

    @Test
    void Serial(){
        User user = new User();
        user.setNickname("小李飞刀");
        user.setUsername("xiaolifeidao");
        redisTemplate.opsForValue().set(user,user);
        Object o = redisTemplate.opsForValue().get(user);
        System.out.println(o);
    }
}
