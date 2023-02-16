package com.totoro.controller;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.totoro.constants.Result;
import com.totoro.exception.ServiceException;
import com.totoro.pojo.Wishes;
import com.totoro.pojo.vo.LeftTimeVo;
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
    public Result<Integer> add(@RequestBody @Valid Wishes wishes){
        if (CollUtil.isEmpty(wishes.getCardIds())){
            throw new ServiceException("必须关联卡池ID");
        }
        return Result.success(wishesService.add(wishes));
    }

    /**
     * 更新祈愿活动
     * @param wishes
     * @return
     */
    @PostMapping("update")
    public Result<Integer> update(@RequestBody Wishes wishes){

        return Result.success(wishesService.renew(wishes));
    }

    /**
     * 根据ID查询祈愿活动
     * @param id
     * @return
     */
    @PostMapping("find")
    public Result<Wishes> find(@RequestParam("id") Long id){

        return Result.success(wishesService.findById(id));
    }

    /**
     * 倒计时
     * @param id
     * @return
     */
    @PostMapping("countdown")
    public Result<LeftTimeVo> countdown(@RequestParam("id") Long id){

        return Result.success(wishesService.countdown(id));
    }

    /**
     * 分页查询
     * @param wishes
     * @return
     */
    @PostMapping("page")
    public Result<Page<Wishes>> page(@RequestBody Wishes wishes){

        return Result.success(wishesService.page(wishes));
    }


    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @PostMapping("delete")
    public Result<Integer> delete(@RequestParam("id") Long id){

        return Result.success(wishesService.deleteById(id));
    }
}
