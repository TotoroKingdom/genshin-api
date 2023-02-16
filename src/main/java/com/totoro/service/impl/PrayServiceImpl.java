package com.totoro.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.totoro.constants.PrayConstants;
import com.totoro.mapper.PrayMapper;
import com.totoro.pojo.Card;
import com.totoro.pojo.Pray;
import com.totoro.pojo.Wishes;
import com.totoro.service.PrayService;
import com.totoro.utils.RandomUtils;
import com.totoro.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: totoro
 * @createDate: 2023 02 12 22 54
 * @description:
 **/
@Slf4j
@Service
public class PrayServiceImpl extends ServiceImpl<PrayMapper, Pray> implements PrayService {

    @Resource
    private PrayMapper prayMapper;

    @Override
    public Card push(Wishes wishes) {

        Long userId = SecurityUtils.getUserId();
        Pray pray = findByUserId(userId);
        //抽五星
        if (fiveStarLottery(pray)){

            return chooseFiveStarCard(pray);
        }
        if (fourStarLottery(pray)){
            return chooseFourStarCard(pray);
        }
        Card card = new Card();
        card.setCardName("三星物品");
        return card;
    }

    @Override
    public Pray findByUserId(Long userId) {
        LambdaQueryWrapper<Pray> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Pray::getUserId,userId);
        Pray pray = prayMapper.selectOne(wrapper);
        if (ObjectUtil.isNull(pray)){
            Pray p = new Pray();
            p.setUserId(userId);
            return initPray(p);
        }
        return pray;
    }

    //初始化抽奖信息
    public Pray initPray(Pray pray){
        prayMapper.insert(pray);
        return prayMapper.selectById(pray.getId());
    }

    //计算五星概率
    public double fiveStarProbabilityCalculate(Pray pray){

        //五星概率
        Integer fiveStarCharacterNum = pray.getFiveStarCharacterNum();
        if (fiveStarCharacterNum <= PrayConstants.five_star_character_probability_rise){
            return PrayConstants.five_star_character_base_probability;
        }

        double fiveStarCharacterProbability = PrayConstants.five_star_character_base_probability + (fiveStarCharacterNum - PrayConstants.five_star_character_probability_rise) * PrayConstants.five_star_character_rise_probability;

        if (fiveStarCharacterProbability >= 1){
            return 1;
        }
        return fiveStarCharacterProbability;
    }

    //计算四星概率
    public double fourStarProbabilityCalculate(Pray pray){

        //五星概率
        Integer fourStarCharacterNum = pray.getFourStarCharacterNum();
        if (fourStarCharacterNum <= PrayConstants.four_star_character_probability_rise){
            return PrayConstants.four_star_character_base_probability;
        }

        double fourStarCharacterProbability = PrayConstants.four_star_character_base_probability + (fourStarCharacterNum - PrayConstants.four_star_character_probability_rise) * PrayConstants.four_star_character_rise_probability;

        if (fourStarCharacterProbability >= 1){
            return 1;
        }
        return fourStarCharacterProbability;
    }

    public boolean fiveStarLottery(Pray pray){
        double probabilityCalculate = fiveStarProbabilityCalculate(pray);
        double random = Math.random();
        if (random <= probabilityCalculate){
            log.info("抽中五星了");
            return true;
        }
        return false;
    }

    public boolean fourStarLottery(Pray pray){
        double probabilityCalculate = fourStarProbabilityCalculate(pray);
        double random = Math.random();
        if (random <= probabilityCalculate){
            log.info("抽中四星了");
            return true;
        }
        return false;
    }

    public Card chooseFiveStarCard(Pray pray){
        //判断是否保底
        Integer fiveStarCharacterUp = pray.getFiveStarCharacterUp();
        if (PrayConstants.GUARANTEED.equals(fiveStarCharacterUp)){
            log.info("保底了");
            Card card = new Card();
            card.setCardName("五星up角色（保底）");
            return card;
        }

        boolean guaranteed = RandomUtils.guaranteed();
        if (guaranteed){
            log.info("没歪");
            Card card = new Card();
            card.setCardName("五星up角色");
            return card;
        }

        log.info("歪了----------------");
        Card card = new Card();
        card.setCardName("五星常驻");
        return card;
    }

    public Card chooseFourStarCard(Pray pray){
        //判断是否保底
        Integer fourStarCharacterUp = pray.getFourStarCharacterUp();
        if (PrayConstants.GUARANTEED.equals(fourStarCharacterUp)){
            log.info("保底了");
            Card card = new Card();
            card.setCardName("四星up角色（保底）");
            return card;
        }

        boolean guaranteed = RandomUtils.guaranteed();
        if (guaranteed){
            log.info("没歪");
            Card card = new Card();
            card.setCardName("四星up角色");
            return card;
        }

        log.info("歪了----------------");
        Card card = new Card();
        card.setCardName("四星常驻");
        return card;
    }


}
