package com.totoro.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

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

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色编码
     */
    private String roleKey;

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
