package com.totoro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.totoro.mapper.WishesMapper;
import com.totoro.pojo.Wishes;
import com.totoro.service.WishesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: totoro
 * @createDate: 2023 02 12 22 58
 * @description:
 **/
@Slf4j
@Service
public class WishesServiceImpl extends ServiceImpl<WishesMapper, Wishes> implements WishesService {

    @Resource
    private WishesMapper wishesMapper;



}
