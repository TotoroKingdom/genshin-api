package com.totoro.pojo;


import lombok.Data;

import java.util.Date;

@Data
public class PrayRecord {

  private Long id;
  private Long userId;
  private Long wishesId;
  private String wishesType;
  private String cardName;
  private Long cardId;
  private String cardType;
  private Date wishTime;
  private Date createTime;
  private Date updateTime;
  private Long createBy;
  private Long updateBy;
  private Integer version;


}
