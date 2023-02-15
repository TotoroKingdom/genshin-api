package com.totoro.controller;

import com.totoro.constants.Result;
import com.totoro.pojo.Wishes;
import com.totoro.service.WishesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 祈愿活动
 * @author: totoro
 * @createDate: 2023 02 16 01 20
 * @description:
 **/
@Slf4j
@RestController
@RequestMapping("wish")
public class WishesController {

    @Resource
    private WishesService wishesService;

    /**
     * 新增祈愿活动
     * @param wishes
     * @return
     */
    @PostMapping("add")
    public Result add(@RequestBody @Valid Wishes wishes){

        return Result.success(wishesService.add(wishes));
    }

    /**
     * 更新祈愿活动
     * @param wishes
     * @return
     */
    @PostMapping("update")
    public Result update(@RequestBody Wishes wishes){

        return Result.success(wishesService.renew(wishes));
    }

    /**
     * 根据ID查询祈愿活动
     * @param id
     * @return
     */
    @PostMapping("find")
    public Result find(@RequestParam("id") Long id){

        return Result.success(wishesService.findById(id));
    }

    /**
     * 倒计时
     * @param id
     * @return
     */
    @PostMapping("countdown")
    public Result countdown(@RequestParam("id") Long id){

        return Result.success(wishesService.countdown(id));
    }

    /**
     * 分页查询
     * @param wishes
     * @return
     */
    @PostMapping("page")
    public Result page(@RequestBody Wishes wishes){

        return Result.success(wishesService.page(wishes));
    }


    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @PostMapping("delete")
    public Result delete(@RequestParam("id") Long id){

        return Result.success(wishesService.deleteById(id));
    }
}
