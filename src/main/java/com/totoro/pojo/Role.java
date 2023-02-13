package com.totoro.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author:totoro
 * @createDate:2023/2/13
 * @description:
 */
@Data
@TableName("t_role")
public class Role implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    //角色名称
    private String roleName;

    //角色编码
    private String roleKey;

    private Date creatTime;

    private Date updateTime;

    private Long createBy;

    private Long updateBy;

    private Integer version;
}
