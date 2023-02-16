package com.totoro.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.totoro.mapper.CardMapper;
import com.totoro.pojo.Card;
import com.totoro.pojo.User;
import com.totoro.service.CardService;
import com.totoro.utils.ParamsUtils;
import com.totoro.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: totoro
 * @createDate: 2023 02 12 22 52
 * @description:
 **/
@Slf4j
@Service
public class CardServiceImpl extends ServiceImpl<CardMapper, Card> implements CardService {

    @Resource
    private CardMapper cardMapper;


    @Override
    public int add(Card card) {
        card.setCreateBy(SecurityUtils.getUserId());

        return cardMapper.insert(card);
    }

    @Override
    public int renew(Card card) {

        card.setUpdateBy(SecurityUtils.getUserId());
        return cardMapper.updateById(card);
    }

    @Override
    public Card findById(Long id) {
        return cardMapper.selectById(id);
    }

    @Override
    public Page<Card> page(Card card) {
        Page page = ParamsUtils.getPage();
        LambdaQueryWrapper<Card> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ObjectUtil.isNotNull(card.getCardType()), Card::getCardType, card.getCardType());
        wrapper.like(ObjectUtil.isNotNull(card.getCardName()), Card::getCardName, card.getCardName());
        wrapper.eq(ObjectUtil.isNotNull(card.getStars()), Card::getStars, card.getStars());
        wrapper.eq(ObjectUtil.isNotNull(card.getUp()), Card::getUp, card.getUp());
        wrapper.eq(ObjectUtil.isNotNull(card.getIndefinite()), Card::getIndefinite, card.getIndefinite());
        wrapper.eq(ObjectUtil.isNotNull(card.getDeleted()),Card::getDeleted,card.getDeleted());

        Page<Card> pageList = cardMapper.selectPage(page, wrapper);

        return pageList;
    }

    @Override
    public int deleteById(Long id) {

        return cardMapper.deleteById(id);
    }
}
