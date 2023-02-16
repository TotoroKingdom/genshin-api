package com.totoro.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("t_card")
public class Card implements Serializable {

  /**
   * id
   */
  @TableId(type = IdType.AUTO)
  private Long id;

  /**
   * 01角色 02武器
   */
  private String cardType;

  /**
   * 名称
   */
  private String cardName;

  /**
   * 星级
   */
  private Integer stars;

  /**
   * 0-未up  1-up
   */
  private Integer up;

  /**
   * 0-限定 1-常驻
   */
  private Integer indefinite;

  /**
   * 十连图片
   */
  private String img;

  /**
   * 单抽大图
   */
  private String bigImg;

  /**
   * 1有效  0删除
   */
  private Integer deleted;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private LocalDateTime createTime;
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private LocalDateTime updateTime;
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Long createBy;
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Long updateBy;
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private Integer version;
  

}
