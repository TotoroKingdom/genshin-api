package com.totoro.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_user")
public class User {

  @TableId(type = IdType.AUTO)
  private Long id;
  private String nickname;
  private String username;
  @JsonIgnore
  private String password;
  private String email;

  //状态 00未激活 01激活 02停用
  private String status;
  private String registerCode;
  private String avatar;
  private Date createTime;
  private Date updateTime;
  private Long createBy;
  private Long updateBy;
  private Integer version;
  

}
