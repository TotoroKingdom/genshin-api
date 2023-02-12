package com.totoro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.totoro.pojo.Wishes;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WishesMapper extends BaseMapper<Wishes> {
}
