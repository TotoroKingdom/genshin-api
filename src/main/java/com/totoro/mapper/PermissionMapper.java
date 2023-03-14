package com.totoro.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.totoro.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author: totoro
 * @createDate: 2023 02 13 22 10
 * @description:
 **/
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 查询用户拥有的权限key
     * @param wrapper
     * @return
     */
    @Select("SELECT p.permission_key \n" +
            "FROM t_user u \n" +
            "LEFT JOIN t_user_role ur on u.id = ur.user_id\n" +
            "LEFT JOIN t_role r on ur.role_id = r.id\n" +
            "LEFT JOIN t_role_permission rp on r.id = rp.role_id\n" +
            "LEFT JOIN t_permission p on rp.permission_id = p.id ${ew.customSqlSegment}\n")
    List<String> findKeyByUsername(@Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 根据用户查询菜单
     * @param wrapper
     * @return
     */
    @Select("SELECT p.* \n" +
            "FROM t_user u \n" +
            "LEFT JOIN t_user_role ur on u.id = ur.user_id\n" +
            "LEFT JOIN t_role r on ur.role_id = r.id\n" +
            "LEFT JOIN t_role_permission rp on r.id = rp.role_id\n" +
            "LEFT JOIN t_permission p on rp.permission_id = p.id ${ew.customSqlSegment}\n")
    List<Permission> findMenuByUserId(@Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * 查询所有菜单
     * @return
     */
    @Select("select * from t_permission order by order_num")
    List<Permission> findAllMenu();
}
