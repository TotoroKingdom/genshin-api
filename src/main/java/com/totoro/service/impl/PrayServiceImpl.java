package com.totoro.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.totoro.constants.PrayConstants;
import com.totoro.mapper.PrayMapper;
import com.totoro.pojo.Card;
import com.totoro.pojo.Pray;
import com.totoro.pojo.PrayRecord;
import com.totoro.pojo.Wishes;
import com.totoro.service.PrayRecordService;
import com.totoro.service.PrayService;
import com.totoro.strategy.LotteryService;
import com.totoro.strategy.LotteryStrategy;
import com.totoro.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @Resource
    private LotteryStrategy lotteryStrategy;

    @Resource
    private PrayRecordService prayRecordService;

    @Override
    public int renew(Pray pray) {
        return prayMapper.updateById(pray);
    }

    @Override
    public Card push(Wishes wishes) {

        Long userId = SecurityUtils.getUserId();
        Pray pray = findByUserId(userId);

        Card card = null;

        //选择抽奖策略
        LotteryService lotteryService = lotteryStrategy.getLotteryService(wishes.getWishesType());
        log.info("开始抽奖");
        boolean winFiveStar = lotteryService.fiveStarLottery(pray);
        boolean winFourStar = lotteryService.fourStarLottery(pray);

        //先判断三星，三星概率较大
        if (!winFiveStar && !winFourStar){
            log.info("抽中三星物品了");
            card = lotteryService.chooseThreeStarCard();
        } else if (winFourStar){
            card = lotteryService.chooseFourStarCard(pray, wishes.getId());
        } else if (winFiveStar){
            card = lotteryService.chooseFiveStarCard(pray, wishes.getId());
        }

        //记录抽中的物品
        PrayRecord prayRecord = new PrayRecord();
        prayRecord.setUserId(userId);
        prayRecord.setWishesId(wishes.getId());
        prayRecord.setWishesType(wishes.getWishesType());
        prayRecord.setCardName(card.getCardName());
        prayRecord.setCardId(card.getId());
        prayRecord.setWishTime(LocalDateTime.now());

        prayRecordService.record(prayRecord);

        return card;
    }

    @Override
    public List<Card> pushTen(Wishes wishes) {
        ArrayList<Card> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Card push = push(wishes);
            list.add(push);
        }
        return list;
    }

    @Override
    public Long epitomized(Long cardId) {
        Long userId = SecurityUtils.getUserId();
        Pray pray = findByUserId(userId);

        if (pray.getEpitomizedPathCardId().equals(cardId)){
            log.info("已经定轨");
            return cardId;
        }
        pray.setEpitomizedPathCardId(cardId);
        pray.setEpitomizedPathUp(PrayConstants.ORDAINED_INIT);
        prayMapper.updateById(pray);
        return cardId;
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






}
