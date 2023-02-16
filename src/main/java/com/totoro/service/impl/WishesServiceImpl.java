package com.totoro.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.totoro.constants.WishConstants;
import com.totoro.exception.ServiceException;
import com.totoro.mapper.WishesMapper;
import com.totoro.pojo.Card;
import com.totoro.pojo.CardWishes;
import com.totoro.pojo.Wishes;
import com.totoro.pojo.dto.Params;
import com.totoro.pojo.vo.LeftTimeVo;
import com.totoro.service.CardService;
import com.totoro.service.CardWishesService;
import com.totoro.service.WishesService;
import com.totoro.utils.ParamsUtils;
import com.totoro.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
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
    @Resource
    private CardWishesService cardWishesService;
    @Resource
    private CardService cardService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int add(Wishes wishes) {

        wishes.setCreateBy(SecurityUtils.getUserId());
        int insert = wishesMapper.insert(wishes);

        //关联卡片人ID
        cardWishesService.associationCardAndWishes(wishes.getCardIds(), wishes.getId());

        return insert;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int renew(Wishes wishes) {
        wishes.setUpdateBy(SecurityUtils.getUserId());

        int i = wishesMapper.updateById(wishes);

        Optional.ofNullable(wishes.getCardIds()).ifPresent( w ->{
            cardWishesService.removeAssociationCardAndWishes(wishes.getId());
            cardWishesService.associationCardAndWishes(wishes.getCardIds(), wishes.getId());
        });

        return i;
    }

    @Override
    public Wishes findById(Long id) {
        Wishes wishes = wishesMapper.selectById(id);
        List<Card> cards = cardService.findByWishesId(id);
        wishes.setCards(cards);
        return wishes;
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
        Params params = ParamsUtils.getParams();
        LambdaQueryWrapper<Wishes> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(ObjectUtil.isNotNull(wishes.getWishesType()), Wishes::getWishesType, wishes.getWishesType());
        wrapper.like(ObjectUtil.isNotNull(wishes.getWishesName()), Wishes::getWishesName, wishes.getWishesName());
        wrapper.eq(ObjectUtil.isNotNull(wishes.getStatus()), Wishes::getStatus, wishes.getStatus());

        if (ObjectUtil.isNotNull(params.getBeginTime()) && ObjectUtil.isNotNull(params.getEndTime())){
            wrapper.and(w -> w.between(Wishes::getBeginTime,params.getBeginTime(),params.getEndTime())
                    .or()
                    .between(Wishes::getEndTime,params.getBeginTime(),params.getEndTime()));
        }
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteById(Long id) {
        int i = wishesMapper.deleteById(id);

        //删除联系
        cardWishesService.removeAssociationCardAndWishes(id);

        return i;
    }
}
