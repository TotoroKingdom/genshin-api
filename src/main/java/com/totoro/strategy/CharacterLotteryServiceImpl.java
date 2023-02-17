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

/**
 * @author:totoro
 * @createDate:2023/2/16
 * @description: 角色抽奖实现
 */
@Slf4j
@Service("CharacterLotteryService")
public class CharacterLotteryServiceImpl extends LotteryService {

    @Resource
    private CardService cardService;
    @Resource
    private PrayMapper prayMapper;


    @Override
    public boolean fiveStarLottery(Pray pray) {
        double probabilityCalculate = fiveStarProbabilityCalculate(pray.getFiveStarCharacterNum());
        double random = Math.random();
        if (random <= probabilityCalculate){
            log.info("抽中五星角色");
            return true;
        }
        log.info("没抽中五星角色  累计值+1");
        pray.setFiveStarCharacterNum(pray.getFiveStarCharacterNum() + 1);
        prayMapper.updateById(pray);
        return false;
    }

    @Override
    public Card chooseFiveStarCard(Pray pray, Long wishId){
        //判断是否保底
        if (PrayConstants.GUARANTEED.equals(pray.getFiveStarCharacterUp())){
            log.info("抽中五星up角色（保底）");
            return findFiveStarUpCharacter(pray, wishId);
        }

        if (RandomUtils.coinToss()){
            log.info("抽中五星up角色");
            return findFiveStarUpCharacter(pray, wishId);
        }

        log.info("抽中常驻五星角色----------------");
        return randomIndefiniteFiveStar(pray);
    }

    /**
     * 锁定五星up角色
     * @param wishId
     * @return
     */
    private Card findFiveStarUpCharacter(Pray pray,Long wishId){
        Card card = new Card();
        card.setCardType(CardConstants.CHARACTER);
        card.setStars(5);
        card.setUp(CardConstants.UP);
        card.setIndefinite(CardConstants.DEFINITE);
        List<Card> cards = cardService.findByWishesIdAndCard(wishId, card);
        clearFiveStarCharacterUp(pray);
        return cards.get(0);
    }

    /**
     * 抽中up奖品后初始累计值
     * @param pray
     * @return
     */
    private int clearFiveStarCharacterUp(Pray pray){
        pray.setFiveStarCharacterUp(PrayConstants.UN_GUARANTEED);
        pray.setFiveStarCharacterNum(0);
        return prayMapper.updateById(pray);
    }

    /**
     * 随机选中一个五星常驻
     * @param pray
     * @return
     */
    private Card randomIndefiniteFiveStar(Pray pray){
       //查询所有五星常驻角色
        Card card = new Card();
        card.setCardType(CardConstants.CHARACTER);
        card.setStars(5);
        card.setIndefinite(CardConstants.INDEFINITE);
        List<Card> cards = cardService.findByCard(card);
        //随机选一个
        card = cards.get(RandomUtils.randomList(cards.size()));

        //增加保底值
        pray.setFiveStarCharacterNum(0);
        pray.setFiveStarCharacterUp(PrayConstants.GUARANTEED);
        prayMapper.updateById(pray);

        return card;
    }

    @Override
    public boolean fourStarLottery(Pray pray){
        double probabilityCalculate = fourStarProbabilityCalculate(pray.getFourStarCharacterNum());
        double random = Math.random();
        if (random <= probabilityCalculate){
            log.info("抽中四星角色了");
            return true;
        }
        log.info("没抽中四星 四星累计值+1");
        pray.setFourStarCharacterNum(pray.getFourStarIndefiniteNum() + 1);
        prayMapper.updateById(pray);
        return false;
    }

    @Override
    public Card chooseFourStarCard(Pray pray, Long wishId){
        //判断是否保底
        Integer fourStarCharacterUp = pray.getFourStarCharacterUp();
        if (PrayConstants.GUARANTEED.equals(fourStarCharacterUp)){
            log.info("抽中四星up角色(保底)");

            return findFourUpStarUpCharacter(pray, wishId);
        }

        if (RandomUtils.coinToss()){
            log.info("抽中四星up角色");

            return findFourUpStarUpCharacter(pray, wishId);
        }

        log.info("抽中常驻四星物品----------------");
        return randomIndefiniteFourStar(pray);
    }

    /**
     * 随机选一个四星up角色
     * @param wishId
     * @return
     */
    private Card findFourUpStarUpCharacter(Pray pray,Long wishId){
        Card card = new Card();
        card.setCardType(CardConstants.CHARACTER);
        card.setStars(4);
        card.setUp(CardConstants.UP);
        List<Card> cards = cardService.findByWishesIdAndCard(wishId, card);
        card = cards.get(RandomUtils.randomList(cards.size()));

        clearFourStarCharacterUp(pray);
        return card;
    }

    /**
     * 抽中up四星奖品后初始累计值
     * @param pray
     * @return
     */
    private int clearFourStarCharacterUp(Pray pray){
        pray.setFourStarCharacterUp(PrayConstants.UN_GUARANTEED);
        pray.setFourStarCharacterNum(0);
        return prayMapper.updateById(pray);
    }

    /**
     * 选中一个四星常驻物品
     * @param pray
     * @return
     */
    private Card randomIndefiniteFourStar(Pray pray){
        //抽角色或者武器
        Card card = new Card();
        if (RandomUtils.coinToss()){
            log.info("选择四星角色");
            card.setCardType(CardConstants.CHARACTER);
        } else {
            log.info("选择四星角色");
            card.setCardType(CardConstants.WEAPON);
        }
        card.setStars(4);
        card.setIndefinite(CardConstants.INDEFINITE);
        List<Card> cards = cardService.findByCard(card);
        //随机选一个
        card = cards.get(RandomUtils.randomList(cards.size()));

        //增加保底值
        pray.setFourStarCharacterNum(0);
        pray.setFourStarCharacterUp(PrayConstants.GUARANTEED);
        prayMapper.updateById(pray);
        return card;
    }




}
