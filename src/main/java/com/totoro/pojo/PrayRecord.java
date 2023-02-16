package com.totoro.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("t_pray_record")
public class PrayRecord implements Serializable {

  @TableId(type = IdType.AUTO)
  private Long id;

  /**
   * 用户ID
   */
  private Long userId;

  /**
   * 祈愿ID
   */
  private Long wishesId;

  /**
   * 祈愿类型 00常驻 01新手 02角色 03武器
   */
  private String wishesType;

  /**
   * 卡片名称
   */
  private String cardName;

  /**
   * 卡片ID
   */
  private Long cardId;

  /**
   * 卡片类型 01角色 02武器
   */
  private String cardType;

  /**
   * 祈愿时间
   */
  private LocalDateTime wishTime;

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
