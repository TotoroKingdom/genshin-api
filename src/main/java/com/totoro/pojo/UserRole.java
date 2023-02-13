package com.totoro.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author:totoro
 * @createDate:2023/2/13
 * @description:
 */
@Data
@TableName("t_user_role")
public class UserRole implements Serializable {

    private Long id;

    private Long userId;

    private Long roleId;
}
