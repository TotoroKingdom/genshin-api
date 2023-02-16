package com.totoro.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.totoro.annonation.UniqueCheck;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@TableName("t_card")
@UniqueCheck(filedName = "cardName")
public class Card implements Serializable {

  /**
   * id
   */
  @TableId(type = IdType.AUTO)
  private Long id;

  /**
   * 01角色 02武器
   */
  @NotNull
  private String cardType;

  /**
   * 名称
   */
  @NotNull
  @TableField("card_name")
  private String cardName;

  /**
   * 星级
   */
  @NotNull
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
