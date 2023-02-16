package com.totoro.strategy;

import com.totoro.pojo.Card;
import com.totoro.pojo.Pray;
import org.springframework.stereotype.Service;

/**
 * @author:totoro
 * @createDate:2023/2/16
 * @description: 武器抽奖实现
 */
@Service("WeaponLotteryService")
public class WeaponLotteryServiceImpl extends LotteryService {


    @Override
    public boolean fiveStarLottery(Pray pray) {
        return false;
    }

    @Override
    public Card chooseFiveStarCard(Pray fiveStarCharacterUp, Long wishId) {
        return null;
    }

    @Override
    public boolean fourStarLottery(Pray pray) {
        return false;
    }

    @Override
    public Card chooseFourStarCard(Pray pray, Long wishId) {
        return null;
    }


}
