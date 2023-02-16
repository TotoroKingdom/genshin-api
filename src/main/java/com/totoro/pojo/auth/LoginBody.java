package com.totoro.pojo.auth;

import lombok.Data;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * @author:totoro
 * @createDate:2023/2/8
 * @description:
 */
@Data
public class LoginBody implements Serializable {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    @NotNull
    private String password;

    /**
     * 验证码
     */
    private String code;

    /**
     * 邮箱
     */
    @NotNull
    private String email;

}
