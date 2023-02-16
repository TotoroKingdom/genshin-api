package com.totoro.strategy;

import org.springframework.stereotype.Service;

/**
 * @author:totoro
 * @createDate:2023/2/16
 * @description: 角色抽奖实现
 */
@Service("CharacterLotteryService")
public class CharacterLotteryServiceImpl implements LotteryService {
    @Override
    public void lottery() {
        System.out.println("角色池抽奖");
    }
}
