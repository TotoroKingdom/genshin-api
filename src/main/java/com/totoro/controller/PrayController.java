package com.totoro.controller;

import com.totoro.constants.Result;
import com.totoro.pojo.Card;
import com.totoro.service.PrayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 抽奖API
 * @author: totoro
 * @createDate: 2023 02 14 22 42
 * @description:
 **/
@Slf4j
@RestController
@RequestMapping("pray")
public class PrayController {

    @Resource
    private PrayService prayService;

    /**
     * 单抽
     * @return
     */
    @PostMapping("push")
    public Result push(){
        Card card = prayService.push();
        return Result.success(card);
    }

}
