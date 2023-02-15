package com.totoro.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: totoro
 * @createDate: 2023 02 13 22 04
 * @description:
 **/
@Data
@TableName("t_permission")
public class Permission implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    //权限编码
    private String permissionKey;

    //菜单名称
    private String menuName;

    //菜单父级名称
    private String parentName;

    //父级ID
    private Long parentId;

    //排序
    private Integer orderNum;

    //路由地址
    private String routerPath;

    //组件
    private String component;

    //1:显示 0：隐藏
    private Integer visible;

    //图标
    private String icon;

    //备注
    private String remark;

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
