package com.totoro.pojo.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.totoro.pojo.Permission;
import com.totoro.pojo.Role;
import com.totoro.pojo.User;
import lombok.Data;

import java.util.List;

/**
 * @author:totoro
 * @createDate:2023/2/13
 * @description:
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserVo extends User {

    /**
     * 角色列表
     */
    private List<Role> roles;

    private List<Permission> permissions;
}
