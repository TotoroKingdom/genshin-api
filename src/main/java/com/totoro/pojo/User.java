package com.totoro.pojo;

import lombok.Data;

@Data
public class User {
    private Long id;

    private String nickname;

    private String username;

    private String password;
}
