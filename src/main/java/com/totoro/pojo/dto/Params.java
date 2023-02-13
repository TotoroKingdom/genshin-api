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

    private String beginTime;

    private String endTime;
}
