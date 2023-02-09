package com.totoro.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private Long id;

    private String nickname;

    private String username;

    @JsonIgnore
    private String password;
}
