package com.totoro.pojo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.totoro.annonation.UniqueCheck;
import com.totoro.service.UserService;
import lombok.Data;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@TableName("t_user")
@UniqueCheck(filedName = "username",groups = {Insert.class, Update.class})
public class User implements Serializable {

  @TableId(type = IdType.AUTO)
  private Long id;

  /**
   * 昵称
   */
  private String nickname;

  /**
   * 用户名
   */
  @NotNull(groups = {Insert.class})
  @TableField("username")
  private String username;

  /**
   * 密码
   */
  @NotNull(groups = {Insert.class})
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;

  /**
   * 邮箱
   */
  @NotNull(groups = {Insert.class})
  @TableField("email")
  private String email;

  /**
   * 状态 00未激活 01激活 02停用
   */
  private String status;

  /**
   * 注册码
   */
  private String registerCode;

  /**
   * 图片
   */
  private String avatar;

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
