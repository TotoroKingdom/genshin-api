package com.totoro.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_card")
public class Card implements Serializable {

  @TableId(type = IdType.AUTO)
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
