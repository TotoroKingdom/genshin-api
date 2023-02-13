package com.totoro.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author:totoro
 * @createDate:2023/2/13
 * @description:
 */
@Data
@TableName("city")
public class Apple {


    @TableId(type = IdType.AUTO)
    @TableField(value = "ID")
    private Long ID;

    @TableField(value = "Name")
    private String Name;

    @TableField(value = "CountryCode")
    private String CountryCode;

    @TableField(value = "District")
    private String District;

    @TableField(value = "Population")
    private String Population;
}
