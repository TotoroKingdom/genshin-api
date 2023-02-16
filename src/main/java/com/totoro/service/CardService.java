package com.totoro.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.totoro.pojo.Card;

import java.util.List;

/**
 * @author: totoro
 * @createDate: 2023 02 12 22 52
 * @description:
 **/
public interface CardService {

    /**
     * 根据祈愿活动ID查询
     * @param wishId
     * @return
     */
    List<Card> findByWishesId(Long wishId);

    /**
     * 新增
     * @param card
     * @return
     */
    int add(Card card);

    /**
     * 更新
     * @param card
     * @return
     */
    int renew(Card card);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    Card findById(Long id);

    /**
     * 分页查询
     * @param card
     * @return
     */
    Page<Card> page(Card card);

    /**
     * 根据ID删除
     * @param id
     * @return
     */
    int deleteById(Long id);
}
