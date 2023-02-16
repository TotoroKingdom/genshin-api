package com.totoro.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.totoro.constants.Result;
import com.totoro.pojo.PrayRecord;
import com.totoro.service.PrayRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 抽奖记录接口
 * @author:totoro
 * @createDate:2023/2/16
 * @description:
 */
@Slf4j
@RequestMapping("record")
@RestController
public class PrayRecordController {

    @Resource
    private PrayRecordService prayRecordService;

    /**
     * 查询个人的抽奖记录
     * @param prayRecord
     * @return
     */
    @PostMapping("self")
    public Result<Page<PrayRecord>> self(@RequestBody PrayRecord prayRecord){

        return null;
    }

    /**
     * 查询所有人的抽奖记录
     * @param prayRecord
     * @return
     */
    @PostMapping("page")
    public Result<Page<PrayRecord>> page(@RequestBody PrayRecord prayRecord){

        return null;
    }
}
