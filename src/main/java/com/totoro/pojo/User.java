package com.totoro.pojo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.Date;

@Data
public class User {

  private Long id;
  private String nickname;
  private String username;
  @JsonIgnore
  private String password;
  private String email;
  private String status;
  private String registerCode;
  private String avatar;
  private Date createTime;
  private Date updateTime;
  private Long createBy;
  private Long updateBy;
  private Integer version;
  

}
