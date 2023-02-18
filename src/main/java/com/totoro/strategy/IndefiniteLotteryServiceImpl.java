package com.totoro.strategy;

import com.totoro.constants.CardConstants;
import com.totoro.constants.PrayConstants;
import com.totoro.mapper.PrayMapper;
import com.totoro.pojo.Card;
import com.totoro.pojo.Pray;
import com.totoro.service.CardService;
import com.totoro.utils.RandomUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

/**
 * @author:totoro
 * @createDate:2023/2/16
 * @description: 常驻抽奖实现
 */
@Slf4j
@Service("IndefiniteLotteryService")
public class IndefiniteLotteryServiceImpl extends LotteryService {

    @Resource
    private CardService cardService;
    @Resource
    private PrayMapper prayMapper;

    @Override
    public boolean fiveStarLottery(Pray pray) {
        double probabilityCalculate = fiveStarProbabilityCalculate(pray.getFiveStarIndefiniteNum());
        double random = Math.random();
        if (random <= probabilityCalculate){
            log.info("抽中五星常驻");
            return true;
        }
        log.info("没抽中五星常驻 五星常驻累计值+1");
        pray.setFiveStarIndefiniteNum(pray.getFourStarIndefiniteNum() + 1);
        prayMapper.updateById(pray);
        return false;
    }

    @Override
    public Card chooseFiveStarCard(Pray pray, Long wishId) {

        log.info("抽中五星常驻");

        Card card = new Card();
        card.setStars(5);
        card.setIndefinite(CardConstants.INDEFINITE);

        boolean coinToss = RandomUtils.coinToss();
        if (coinToss){
            log.info("抽中角色");
            card.setCardType(CardConstants.CHARACTER);
        } else {
            log.info("抽中武器");
            card.setCardType(CardConstants.WEAPON);
        }

        List<Card> cards = cardService.findByCard(card);
        card = cards.get(RandomUtils.randomList(cards.size()));

        //清零累计次数
        pray.setFiveStarIndefiniteNum(0);
        prayMapper.updateById(pray);

        return card;
    }

    @Override
    public boolean fourStarLottery(Pray pray) {
        double v = fourStarProbabilityCalculate(pray.getFourStarIndefiniteNum());
        double random = Math.random();
        if (random <= v){
            log.info("抽中四星常驻了");
            return true;
        }
        log.info("没抽中四星常驻 累计值+1");
        pray.setFourStarIndefiniteNum(pray.getFourStarIndefiniteNum() + 1);
        prayMapper.updateById(pray);
        return false;
    }

    @Override
    public Card chooseFourStarCard(Pray pray, Long wishId) {
        Card card = new Card();
        card.setStars(4);
        card.setIndefinite(CardConstants.INDEFINITE);

        boolean coinToss = RandomUtils.coinToss();
        if (coinToss){
            log.info("抽中角色");
            card.setCardType(CardConstants.CHARACTER);
        } else {
            log.info("抽中武器");
            card.setCardType(CardConstants.WEAPON);
        }

        List<Card> cards = cardService.findByCard(card);
        card = cards.get(RandomUtils.randomList(cards.size()));

        //清零累计次数
        pray.setFourStarIndefiniteNum(0);
        prayMapper.updateById(pray);
        return card;
    }


}
