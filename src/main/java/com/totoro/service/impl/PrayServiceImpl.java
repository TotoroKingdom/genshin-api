package com.totoro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.totoro.mapper.PrayMapper;
import com.totoro.pojo.Pray;
import com.totoro.service.PrayService;
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



}
