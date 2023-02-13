package com.totoro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.totoro.mapper.AppleMapper;
import com.totoro.pojo.Apple;
import com.totoro.service.AppleService;
import com.totoro.service.UserService;
import com.totoro.utils.ParamsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author:totoro
 * @createDate:2023/2/13
 * @description:
 */
@Slf4j
@Service
public class AppleServiceImpl extends ServiceImpl<AppleMapper, Apple> implements AppleService {

    @Resource
    private AppleMapper appleMapper;
    @Resource
    private UserService userService;


    @Override
    public Page<Apple> page() {

        LambdaQueryWrapper<Apple> wrapper = new LambdaQueryWrapper<>();
        Page list = appleMapper.selectPage(ParamsUtils.getPage(), wrapper);

        return list;
    }

}
