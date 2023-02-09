package com.totoro.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private Long id;

    private String nickname;

    private String username;

    private String password;
}
