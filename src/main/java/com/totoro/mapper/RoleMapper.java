package com.totoro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface RoleMapper extends BaseMapper<Role> {

    @Select("select r.* from t_role r right join t_user_role ur on r.id = ur.role_id where ur.user_id = #{userId}")
    List<Role> findRoleByUserId(@Param("userId") Long userId);
}
