package com.totoro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.totoro.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author:totoro
 * @createDate:2023/2/10
 * @description:
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
