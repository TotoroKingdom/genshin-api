package com.totoro.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: totoro
 * @createDate: 2023 02 16 02 31
 * @description:
 **/
@Data
public class LeftTimeVo implements Serializable {

    /**
     * 剩余分钟
     */
    private long minute;

    /**
     * 剩余小时
     */
    private long hour;

    /**
     * 剩余天数
     */
    private long day;
}
