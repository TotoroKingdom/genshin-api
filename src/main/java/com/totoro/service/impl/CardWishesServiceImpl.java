package com.totoro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.totoro.mapper.CardWishesMapper;
import com.totoro.pojo.CardWishes;
import com.totoro.service.CardWishesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: totoro
 * @createDate: 2023 02 12 22 53
 * @description:
 **/
@Slf4j
@Service
public class CardWishesServiceImpl extends ServiceImpl<CardWishesMapper, CardWishes> implements CardWishesService {

    @Resource
    private CardWishesMapper cardWishesMapper;


    @Override
    public boolean associationCardAndWishes(List<Long> cardIds, Long wishesId) {

        List<CardWishes> list = new ArrayList<>();
        for (Long cardId : cardIds) {
            CardWishes cw = new CardWishes();
            cw.setCardId(cardId);
            cw.setWishesId(wishesId);
            list.add(cw);
        }
        return this.saveBatch(list);
    }

    @Override
    public int removeAssociationCardAndWishes(Long wishedId) {

        LambdaQueryWrapper<CardWishes> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CardWishes::getWishesId,wishedId);

        return cardWishesMapper.delete(wrapper);
    }
}
