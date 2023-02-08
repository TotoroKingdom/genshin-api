package com.totoro.pojo.auth;

import lombok.Data;

import java.io.Serializable;

/**
 * @author:totoro
 * @createDate:2023/2/8
 * @description:
 */
@Data
public class LoginBody implements Serializable {

    private String username;

    private String password;

    private String code;

}
