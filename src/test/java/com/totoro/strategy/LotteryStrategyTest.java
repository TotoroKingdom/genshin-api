package com.totoro.strategy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author:totoro
 * @createDate:2023/2/16
 * @description:
 */
@SpringBootTest
class LotteryStrategyTest {

    @Resource
    private LotteryStrategy lotteryStrategy;

    @Test
    void strategy(){
        LotteryService lotteryService1 = lotteryStrategy.getLotteryService("01");
        LotteryService lotteryService2 = lotteryStrategy.getLotteryService("02");
        LotteryService lotteryService3 = lotteryStrategy.getLotteryService("03");
        LotteryService lotteryService4 = lotteryStrategy.getLotteryService("00");

        lotteryService1.lottery();
        lotteryService2.lottery();
        lotteryService3.lottery();
        lotteryService4.lottery();
    }

}