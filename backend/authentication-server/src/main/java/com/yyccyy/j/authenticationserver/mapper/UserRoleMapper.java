package com.yyccyy.j.authenticationserver.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Author: 李家宝
 * @Date: 2018/10/27 11:54
 */
@Repository
@Mapper
public interface UserRoleMapper {

    @Select(value = "select role_id from user_role where user_id = #{userId}")
    long selectUserRoleIdByUserId(@Param(value = "userId") long userId);

}
