package com.totoro.strategy;

import org.springframework.stereotype.Service;

/**
 * @author:totoro
 * @createDate:2023/2/16
 * @description: 常驻抽奖实现
 */
@Service("IndefiniteLotteryService")
public class IndefiniteLotteryServiceImpl implements LotteryService {
    @Override
    public void lottery() {
        System.out.println("常驻抽奖");

    }
}
