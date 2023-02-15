package com.totoro.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.totoro.constants.WishConstants;
import com.totoro.mapper.WishesMapper;
import com.totoro.pojo.Wishes;
import com.totoro.pojo.vo.LeftTimeVo;
import com.totoro.service.WishesService;
import com.totoro.utils.ParamsUtils;
import com.totoro.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

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


    @Override
    public int add(Wishes wishes) {
        wishes.setCreateBy(SecurityUtils.getUserId());

        return wishesMapper.insert(wishes);
    }

    @Override
    public int renew(Wishes wishes) {
        wishes.setUpdateBy(SecurityUtils.getUserId());

        return wishesMapper.updateById(wishes);
    }

    @Override
    public Wishes findById(Long id) {
        return wishesMapper.selectById(id);
    }

    @Override
    public LeftTimeVo countdown(Long id) {
        Wishes wishes = wishesMapper.selectById(id);
        LocalDateTime endTime = wishes.getEndTime();
        LocalDateTime now = LocalDateTime.now();

        Duration between = Duration.between(now, endTime);

        long minutes = between.toMinutes();
        long minute = minutes % 60;
        long hour = minutes / 60;
        long day = minutes /  (60 * 24);
        LeftTimeVo vo = new LeftTimeVo();
        vo.setMinute(minute);
        vo.setHour(hour);
        vo.setDay(day);
        return vo;
    }

    @Override
    public Page<Wishes> page(Wishes wishes) {

        Page page = ParamsUtils.getPage();
        LambdaQueryWrapper<Wishes> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(ObjectUtil.isNotNull(wishes.getWishesName()),Wishes::getWishesName,wishes.getWishesName());
        wrapper.orderByDesc(Wishes::getId);
        Page<Wishes> wishesPage = wishesMapper.selectPage(page, wrapper);
        LocalDateTime now = LocalDateTime.now();
        //设定状态
        wishesPage.getRecords().stream().forEach( wish -> {
            boolean before = wish.getBeginTime().isBefore(now);
            boolean after = wish.getEndTime().isAfter(now);
            if (before && after){
                wish.setStatus(WishConstants.ACTIVATED);
            } else {
                wish.setStatus(WishConstants.DISABLED);
            }
        });
        //过滤状态
        Optional.ofNullable(wishes.getStatus()).ifPresent( w ->{
            wishesPage.getRecords().stream()
                    .filter( wish -> wish.getStatus().equals(WishConstants.ACTIVATED));
        });
        return wishesPage;
    }

    @Override
    public int deleteById(Long id) {

        return wishesMapper.deleteById(id);
    }
}
