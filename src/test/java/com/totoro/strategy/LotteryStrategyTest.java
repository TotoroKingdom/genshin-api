package com.totoro.strategy;

import com.totoro.pojo.Card;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

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

        Card card = lotteryService1.chooseThreeStarCard();

    }

}