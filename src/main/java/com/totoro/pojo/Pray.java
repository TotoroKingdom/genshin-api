package com.totoro.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_pray")
public class Pray implements Serializable {

  //id
  @TableId(type = IdType.AUTO)
  private Long id;

  //用户ID
  private Long userId;

  //原石数量
  private Integer primogem;

  //纠缠之缘数量
  private Integer acquainFate;

  //相遇之缘数量
  private Integer interwinedFate;

  //无主星辉
  private Integer masterlessStarglitter;

  //无主星尘
  private Integer masterlessStardust;

  //五星角色抽奖次数
  private Integer fiveStarCharacterNum;

  //五星角色是否保底 0：未保底 1：保底
  private Integer fiveStarCharacterUp;

  //四星角色抽奖次数
  private Integer fourStarCharacterNum;

  //四星角色是否保底 0：未保底 1：保底
  private Integer fourStarCharacterUp;

  //五星武器抽奖次数
  private Integer fiveStarWeaponNum;

  //五星武器是否保底 0：未保底 1：保底
  private Integer fiveStarWeaponUp;

  //定轨武器ID 0：未定轨
  private Long epitomizedPathCardId;

  //命定值 0：。。 1：。。 2：必中
  private Integer epitomizedPathUp;

  //4星武器抽奖次数
  private Integer fourStarWeaponNum;

  //4星武器是否保底 0：未保底 1：保底
  private Integer fourStarWeaponUp;

  //常驻五星抽奖次数
  private Integer fiveStarIndefiniteNum;

  //常驻五星up 0：未保底 1:保底
  private Integer fiveStarIndefiniteUp;

  //常驻4星抽奖次数
  private Integer fourStarIndefiniteNum;

  //常驻4星up 0：未保底 1:保底
  private Integer fourStarIndefiniteUp;
  private Date createTime;
  private Date updateTime;
  private Long createBy;
  private Long updateBy;
  private Integer version;




}
