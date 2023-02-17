package com.totoro.controller;

import com.totoro.constants.Result;
import com.totoro.pojo.Card;
import com.totoro.pojo.Pray;
import com.totoro.pojo.Wishes;
import com.totoro.service.PrayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    public Result<Card> push(@RequestBody Wishes wishes){
        Card card = prayService.push(wishes);
        return Result.success(card);
    }

    /**
     * 十连
     * @return
     */
    @PostMapping("push/ten")
    public Result<List<Card>> pushTen(@RequestBody Wishes wishes){
        Card card = prayService.push(wishes);
        return Result.success(null);
    }

    /**
     * 定轨
     * @return 定轨的武器ID
     */
    @PostMapping("epitomized")
    public Result<Long> push(@RequestParam Long cardId){
        Long epitomized = prayService.epitomized(cardId);
        return Result.success(epitomized);
    }

}
