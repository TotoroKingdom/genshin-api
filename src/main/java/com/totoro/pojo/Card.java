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
