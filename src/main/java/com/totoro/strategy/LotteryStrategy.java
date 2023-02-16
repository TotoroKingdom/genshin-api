package com.totoro.strategy;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author:totoro
 * @createDate:2023/2/16
 * @description:
 */
@Service
public class LotteryStrategy {

    @Resource
    Map<String, LotteryService> map = new ConcurrentHashMap<>();

    public LotteryService getLotteryService(String code){
        String strategy = LotteryEnum.getStrategy(code);
        return map.get(strategy);
    }
}
