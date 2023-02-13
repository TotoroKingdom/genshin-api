package com.totoro.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_wishes")
public class Wishes implements Serializable {

  @TableId(type = IdType.AUTO)
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
