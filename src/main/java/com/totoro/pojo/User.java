package com.totoro.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.totoro.annonation.UniqueCheck;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("t_user")
@UniqueCheck(filedName = "username")
public class User implements Serializable {

  @TableId(type = IdType.AUTO)
  private Long id;


  private String nickname;

  @TableField("username")
  private String username;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;

  @TableField("email")
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
