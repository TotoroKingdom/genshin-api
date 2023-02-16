package com.totoro.service;

import java.util.List;

/**
 * @author: totoro
 * @createDate: 2023 02 12 22 53
 * @description:
 **/
public interface CardWishesService {

    /**
     * 关联卡池和活动
     * @param cardIds
     * @param wishesId
     */
    boolean associationCardAndWishes(List<Long> cardIds, Long wishesId);

    /**
     * 移除卡池和活动的关联
     * @param wishedId
     * @return
     */
    int removeAssociationCardAndWishes(Long wishedId);
}
