package com.totoro.strategy;

import com.totoro.constants.PrayConstants;
import com.totoro.pojo.Card;
import com.totoro.pojo.Pray;
import com.totoro.service.CardService;
import com.totoro.utils.RandomUtils;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:totoro
 * @createDate:2023/2/16
 * @description:
 */
@Slf4j
public abstract class LotteryService {

    @Resource
    private CardService cardService;

    /**
     * 抽五星
     */
    abstract public boolean fiveStarLottery(Pray pray);

    /**
     * 选择五星奖品
     * @param pray                 五星角色是否保底 0：未保底 1：保底
     * @param wishId               祈愿活动ID
     * @return
     */
    abstract public Card chooseFiveStarCard(Pray pray, Long wishId);

    /**
     * 抽四星
     * @param pray
     * @return
     */
    abstract public boolean fourStarLottery(Pray pray);

    /**
     * 选择四星奖品
     * @param pray
     * @param wishId               祈愿活动ID
     * @return
     */
    abstract public Card chooseFourStarCard(Pray pray, Long wishId);

    /**
     * 选择三星奖品
     * @return
     */
    public Card chooseThreeStarCard(){
        log.info("选择三星奖品");
        Card card = new Card();
        card.setStars(3);
        List<Card> cards = cardService.findByCard(card);
        card = cards.get(RandomUtils.randomList(cards.size()));
        return card;
    }

    /**
     * 计算五星概率
     * @param currentPush
     * @return
     */
    public double fiveStarProbabilityCalculate(Integer currentPush){
        //五星概率
        Integer fiveStarCharacterNum = currentPush;
        if (fiveStarCharacterNum <= PrayConstants.five_star_character_probability_rise){
            return PrayConstants.five_star_character_base_probability;
        }

        double fiveStarCharacterProbability = PrayConstants.five_star_character_base_probability + (fiveStarCharacterNum - PrayConstants.five_star_character_probability_rise) * PrayConstants.five_star_character_rise_probability;

        if (fiveStarCharacterProbability >= 1){
            return 1;
        }
        return fiveStarCharacterProbability;
    }

    /**
     * 计算四星概率
     * @param currentPush
     * @return
     */
    public double fourStarProbabilityCalculate(Integer currentPush){

        //四星概率
        if (currentPush <= PrayConstants.four_star_character_probability_rise){
            return PrayConstants.four_star_character_base_probability;
        }

        double fourStarCharacterProbability = PrayConstants.four_star_character_base_probability
                + (currentPush - PrayConstants.four_star_character_probability_rise)
                * PrayConstants.four_star_character_rise_probability;

        if (fourStarCharacterProbability >= 1){
            return 1;
        }
        return fourStarCharacterProbability;
    }
}
