package com.totoro.service;

import com.totoro.pojo.PrayRecord;

/**
 * @author: totoro
 * @createDate: 2023 02 12 22 55
 * @description:
 **/
public interface PrayRecordService {

    /**
     * 记录抽奖记录
     * @param prayRecord
     * @return
     */
    int record(PrayRecord prayRecord);
}
