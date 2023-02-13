package com.totoro.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.totoro.pojo.User;
import com.totoro.pojo.vo.UserVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author:totoro
 * @createDate:2023/2/10
 * @description:
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     *
     * @param page
     * @param ${ew.customSqlSegment}
     * @return
     */
    @Select("select * from t_user ${ew.customSqlSegment}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "nickname", column = "nickname"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "status", column = "status"),
            @Result(property = "registerCode", column = "register_code"),
            @Result(property = "avatar", column = "avatar"),
            @Result(property = "roles", column = "id"
                    , javaType = List.class, many = @Many(select = "com.totoro.mapper.RoleMapper.findRoleByUserId"))
    })
    Page<UserVo> findUserVoList(Page page, @Param(Constants.WRAPPER) Wrapper wrapper);
}
