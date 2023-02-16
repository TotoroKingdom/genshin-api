package com.totoro.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.totoro.pojo.Card;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CardMapper extends BaseMapper<Card> {

    /**
     * 根据祈愿活动ID查询
     * @param wrapper
     * @return
     */
    @Select("select c.* from t_card c join t_card_wishes cw on c.id = cw.card_id ${ew.customSqlSegment}")
    List<Card> findByWishesId(@Param(Constants.WRAPPER) Wrapper wrapper);

}
