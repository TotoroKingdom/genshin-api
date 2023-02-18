package com.totoro.strategy;

import cn.hutool.core.collection.CollUtil;
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
 * @description: 武器抽奖实现
 */
@Slf4j
@Service("WeaponLotteryService")
public class WeaponLotteryServiceImpl extends LotteryService {

    @Resource
    private CardService cardService;
    @Resource
    private PrayMapper prayMapper;

    @Override
    public boolean fiveStarLottery(Pray pray) {
        double probabilityCalculate = fiveStarProbabilityCalculate(pray.getFiveStarWeaponNum());
        double random = Math.random();
        if (random <= probabilityCalculate){
            log.info("抽中五星武器");
            return true;
        }
        log.info("没抽中五星武器  累计值+1");
        pray.setFiveStarWeaponNum(pray.getFiveStarWeaponNum() + 1);
        prayMapper.updateById(pray);
        return false;
    }

    @Override
    public Card chooseFiveStarCard(Pray pray, Long wishId) {

        //查出当期up五星武器
        List<Card> currentUpWeapons = getCurrentFiveStarUpWeapon(wishId);
        if (CollUtil.isEmpty(currentUpWeapons)){
            //如果找不到，说明没有定轨当前活动武器，清零定轨值
            pray.setEpitomizedPathUp(PrayConstants.ORDAINED_INIT);
            prayMapper.updateById(pray);
        }

        //判断命定值
        if (PrayConstants.ORDAINED.equals(pray.getEpitomizedPathUp())){
            log.info("抽中命定武器 命定值："+pray.getEpitomizedPathUp());
            //清零
            clearFiveStarWeapon(pray);
            for (Card card : currentUpWeapons) {
                if (card.getId().equals(pray.getEpitomizedPathCardId())){
                    return card;
                }
            }
        }

        //判断是否保底
        if (PrayConstants.GUARANTEED.equals(pray.getFiveStarWeaponUp())){
            log.info("抽中五星武器（保底）");
            return getFiveStarUpWeapon(pray,currentUpWeapons);
        }

        //没保底 75概率武器， 25概率常驻
        if (RandomUtils.weaponProbability()){
            log.info("抽中五星up武器");
            return getFiveStarUpWeapon(pray,currentUpWeapons);
        }

        log.info("抽中常驻五星武器----------------");
        return randomIndefiniteFiveWeaponStar(pray);

    }

    /**
     * 定轨值+1 抽奖累计数和保底清零
     * @param pray
     */
    private void upEpitomized(Pray pray){
        pray.setEpitomizedPathUp(pray.getEpitomizedPathUp() + 1);
        pray.setFiveStarWeaponUp(PrayConstants.UN_GUARANTEED);
        pray.setFiveStarWeaponNum(0);
        prayMapper.updateById(pray);
    }

    /**
     * 清零定轨数值、抽奖累计值和保底清零
     * @param pray
     */
    private void clearFiveStarWeapon(Pray pray){
        pray.setEpitomizedPathUp(PrayConstants.ORDAINED_INIT);
        pray.setFiveStarWeaponUp(PrayConstants.UN_GUARANTEED);
        pray.setFiveStarWeaponNum(0);
        prayMapper.updateById(pray);
    }

    /**
     * 判断是否定轨，未定轨将武器命定值改为0
     */
    private List<Card> getCurrentFiveStarUpWeapon(Long wishId){
        //判断是否定轨
        Card card = new Card();
        card.setCardType(CardConstants.WEAPON);
        card.setStars(5);
        card.setUp(CardConstants.UP);
        card.setIndefinite(CardConstants.DEFINITE);
        return cardService.findByWishesIdAndCard(wishId, card);
    }

    /**
     * 选择五星up武器
     * @param pray
     * @return
     */
    private Card getFiveStarUpWeapon(Pray pray,List<Card> currentUpWeapons){

        int index = RandomUtils.randomList(currentUpWeapons.size());
        Card card = currentUpWeapons.get(index);
        if (card.getId().equals(pray.getEpitomizedPathCardId())){
            log.info("抽中定轨武器了");
            clearFiveStarWeapon(pray);
        }else {
            log.info("没抽中定轨武器");
            upEpitomized(pray);
        }
        return card;
    }

    /**
     * 选择一个五星常驻武器
     */
    private Card randomIndefiniteFiveWeaponStar(Pray pray){
        //查询所有五星常驻武器
        Card card = new Card();
        card.setCardType(CardConstants.WEAPON);
        card.setStars(5);
        card.setIndefinite(CardConstants.INDEFINITE);
        List<Card> cards = cardService.findByCard(card);
        //随机选一个
        card = cards.get(RandomUtils.randomList(cards.size()));

        //清零累计数 增加保底值 增加命定值
        pray.setFiveStarWeaponNum(0);
        pray.setFiveStarWeaponUp(PrayConstants.GUARANTEED);
        pray.setEpitomizedPathUp(pray.getEpitomizedPathUp() + 1);
        prayMapper.updateById(pray);

        return card;

    }

    @Override
    public boolean fourStarLottery(Pray pray) {
        double probabilityCalculate = fourStarProbabilityCalculate(pray.getFourStarWeaponNum());
        double random = Math.random();
        if (random <= probabilityCalculate){
            log.info("抽中四星武器了");
            return true;
        }
        log.info("没抽中四星 四星累计值+1");
        pray.setFourStarWeaponNum(pray.getFourStarWeaponNum() + 1);
        prayMapper.updateById(pray);
        return false;
    }

    @Override
    public Card chooseFourStarCard(Pray pray, Long wishId) {

        //判断是否保底
        Integer fourStarWeaponUp = pray.getFourStarWeaponUp();
        if (PrayConstants.GUARANTEED.equals(fourStarWeaponUp)){
            log.info("抽中四星up武器(保底)");

            return findFourUpStarUpWeapon(pray, wishId);
        }

        if (RandomUtils.weaponProbability()){
            log.info("抽中四星up武器");

            return findFourUpStarUpWeapon(pray, wishId);
        }

        log.info("抽中常驻四星物品----------------");
        return randomIndefiniteFourStar(pray);
    }

    /**
     * 随机选一个四星up武器
     * @param pray
     * @param wishId
     * @return
     */
    private Card findFourUpStarUpWeapon(Pray pray,Long wishId){

        Card card = new Card();
        card.setCardType(CardConstants.WEAPON);
        card.setStars(4);
        card.setUp(CardConstants.UP);
        List<Card> cards = cardService.findByWishesIdAndCard(wishId, card);
        card = cards.get(RandomUtils.randomList(cards.size()));
        clearFourStarWeaponUp(pray);
        return card;

    }

    /**
     * 抽中up四星奖品后初始累计值
     * @param pray
     * @return
     */
    private int clearFourStarWeaponUp(Pray pray){
        pray.setFourStarWeaponUp(PrayConstants.UN_GUARANTEED);
        pray.setFourStarWeaponNum(0);
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
        pray.setFourStarWeaponNum(0);
        pray.setFourStarWeaponUp(PrayConstants.GUARANTEED);
        prayMapper.updateById(pray);
        return card;
    }

    @Override
    public double fiveStarProbabilityCalculate(Integer currentPush) {
        //五星概率
        if (currentPush <= PrayConstants.five_star_weapon_probability_rise){
            return PrayConstants.five_star_weapon_base_probability;
        }

        double fiveStarWeaponProbability = PrayConstants.five_star_weapon_base_probability
                + (currentPush - PrayConstants.five_star_weapon_probability_rise)
                * PrayConstants.five_star_weapon_rise_probability;

        if (fiveStarWeaponProbability >= 1){
            return 1;
        }
        return fiveStarWeaponProbability;
    }

    @Override
    public double fourStarProbabilityCalculate(Integer currentPush) {
        //四星概率
        if (currentPush <= PrayConstants.four_star_weapon_probability_rise){
            return PrayConstants.four_star_weapon_base_probability;
        }

        double fourStarWeaponProbability = PrayConstants.four_star_weapon_base_probability
                + (currentPush - PrayConstants.four_star_weapon_probability_rise)
                * PrayConstants.four_star_weapon_rise_probability;

        if (fourStarWeaponProbability >= 1){
            return 1;
        }
        return fourStarWeaponProbability;
    }


}
