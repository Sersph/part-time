package com.yyccyy.j.authenticationserver.mapper;

import com.yyccyy.j.authenticationserver.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Author: 李家宝
 * @Date: 2018/10/27 11:46
 */
@Repository
@Mapper
public interface RoleMapper {

    @Select(value = "select * from role where id = #{roleId}")
    Role selectRoleById(@Param(value = "roleId") long roleId);
}
