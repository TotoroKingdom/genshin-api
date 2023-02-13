package com.totoro.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_pray_record")
public class PrayRecord implements Serializable {

  @TableId(type = IdType.AUTO)
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
