package com.totoro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.totoro.mapper.CardWishesMapper;
import com.totoro.pojo.CardWishes;
import com.totoro.service.CardWishesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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



}
