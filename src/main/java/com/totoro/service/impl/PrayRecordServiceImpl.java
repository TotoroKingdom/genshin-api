package com.totoro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.totoro.mapper.PrayRecordMapper;
import com.totoro.pojo.PrayRecord;
import com.totoro.service.PrayRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: totoro
 * @createDate: 2023 02 12 22 55
 * @description:
 **/
@Slf4j
@Service
public class PrayRecordServiceImpl extends ServiceImpl<PrayRecordMapper, PrayRecord> implements PrayRecordService {

    @Resource
    private PrayRecordMapper prayRecordMapper;


}
