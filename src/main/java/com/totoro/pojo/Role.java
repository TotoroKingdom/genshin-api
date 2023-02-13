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
@TableName("t_role")
public class Role implements Serializable {

    private Long id;

    private String name;
}
