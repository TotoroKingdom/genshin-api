package com.totoro.pojo;


import lombok.Data;

import java.util.Date;

@Data
public class Wishes {

  private Long id;
  private String wishesType;
  private String wishesName;
  private String img;
  private Date beginTime;
  private Date endTime;
  private Date createTime;
  private Date updateTime;
  private Long createBy;
  private Long updateBy;
  private Integer version;
  

}
