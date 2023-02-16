package com.totoro.pojo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author:totoro
 * @createDate:2023/2/13
 * @description:
 */
@Data
public class Params implements Serializable {

    /**
     * 开始时间
     */
    private String beginTime;

    /**
     * 结束时间
     */
    private String endTime;
}
