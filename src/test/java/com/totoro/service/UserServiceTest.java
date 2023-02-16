package com.totoro.service;

import com.totoro.mapper.UserMapper;
import com.totoro.pojo.User;
import com.totoro.pojo.vo.UserVo;
import com.totoro.strategy.LotteryService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author:totoro
 * @createDate:2023/2/10
 * @description:
 */
@SpringBootTest
class UserServiceTest {

    @Resource
    Map<String, LotteryService> map = new ConcurrentHashMap<>();

    @Test
    public void getService(){

        for (Map.Entry<String, LotteryService> entry : map.entrySet()) {
            System.out.println("String" + entry.getKey());

        }
    }

}