package com.totoro.pojo;


import lombok.Data;

import java.util.Date;

@Data
public class Card {

  private Long id;
  private String cardType;
  private String cardName;
  private Integer stars;
  private Integer up;
  private Integer indefinite;
  private String img;
  private String bigImg;
  private Integer deleted;
  private Date createTime;
  private Date updateTime;
  private Long createBy;
  private Long updateBy;
  private Integer version;
  

}
