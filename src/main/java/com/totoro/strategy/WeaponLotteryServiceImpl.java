package com.totoro.strategy;

import org.springframework.stereotype.Service;

/**
 * @author:totoro
 * @createDate:2023/2/16
 * @description: 武器抽奖实现
 */
@Service("WeaponLotteryService")
public class WeaponLotteryServiceImpl implements LotteryService {
    @Override
    public void lottery() {
        System.out.println("武器池抽奖");
    }
}
