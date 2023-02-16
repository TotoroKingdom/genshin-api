package com.totoro.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.totoro.constants.Result;
import com.totoro.pojo.Card;
import com.totoro.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 卡片人（奖池）API
 * @author:totoro
 * @createDate:2023/2/16
 * @description:
 */
@Slf4j
@RestController
@RequestMapping("card")
public class CardController {

    @Resource
    private CardService cardService;

    /**
     * 新增卡片
     * @param card
     * @return
     */
    @PostMapping("add")
    public Result<Integer> add(@RequestBody @Valid Card card){

        return Result.success(cardService.add(card));
    }

    /**
     * 更新卡片
     * @param card
     * @return
     */
    @PostMapping("update")
    public Result<Integer> renew(@RequestBody Card card){

        return Result.success(cardService.renew(card));
    }

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @PostMapping("find")
    public Result<Card> find(@RequestParam("id") Long id){

        return Result.success(cardService.findById(id));
    }

    /**
     * 分页查询
     * @param card
     * @return
     */
    @PostMapping("page")
    public Result<Page<Card>> page(@RequestBody Card card){

        return Result.success(cardService.page(card));
    }

    /**
     * 根据ID删除
     * @param id
     * @return
     */
    @PostMapping("delete")
    public Result<Integer> delete(@RequestParam("id") Long id){

        return Result.success(cardService.deleteById(id));
    }
}
