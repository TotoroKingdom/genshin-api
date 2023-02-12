package com.totoro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.totoro.pojo.Card;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CardMapper extends BaseMapper<Card> {
}
