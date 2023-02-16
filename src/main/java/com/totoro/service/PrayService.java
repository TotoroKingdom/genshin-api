package com.totoro.service;

import com.totoro.pojo.Card;
import com.totoro.pojo.Pray;
import com.totoro.pojo.Wishes;

/**
 * @author: totoro
 * @createDate: 2023 02 12 22 54
 * @description:
 **/
public interface PrayService {

    /**
     * 祈愿
     */
    Card push(Wishes wishes);

    /**
     * 根据用户ID查询抽奖信息
     * @param userId
     * @return
     */
    Pray findByUserId(Long userId);
}
