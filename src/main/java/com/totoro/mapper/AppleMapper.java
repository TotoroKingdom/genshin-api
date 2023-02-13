package com.totoro.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.totoro.pojo.Apple;
import com.totoro.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author:totoro
 * @createDate:2023/2/13
 * @description:
 */
@Mapper
public interface AppleMapper extends BaseMapper<Apple> {


}
