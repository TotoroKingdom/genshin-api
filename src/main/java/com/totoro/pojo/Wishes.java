package com.totoro.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@TableName("t_wishes")
public class Wishes implements Serializable {

  /**
   * id
   */
  @TableId(type = IdType.AUTO)
  private Long id;

  /**
   * 00-常驻 01-角色活动1 02-角色活动2 03-武器活动
   */
  @NotNull
  private String wishesType;

  /**
   * 祈愿活动名称
   */
  @NotNull
  private String wishesName;

  /**
   * 活动图片
   */
  private String img;

  /**
   * 状态 01激活 02停用
   */
  @TableField(exist = false)
  private String status;

  /**
   * 活动开始时间
   */
  @NotNull
  private LocalDateTime beginTime;

  /**
   * 活动结束时间
   */
  @NotNull
  private LocalDateTime endTime;

  /**
   * 关联的卡片人ID
   */
  @NotEmpty
  @TableField(exist = false)
  private List<Long> cardIds;

  /**
   * 关联的卡片人信息
   */
  @TableField(exist = false)
  private List<Card> cards;

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
