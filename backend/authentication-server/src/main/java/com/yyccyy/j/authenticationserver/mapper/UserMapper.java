package com.yyccyy.j.authenticationserver.mapper;

import com.yyccyy.j.authenticationserver.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @Author: 李家宝
 * @Date: 2018/10/27 11:46
 */
@Repository
@Mapper
public interface UserMapper {

    @Select(value = "select * from user where username = #{username}")
    User selectUserByUsername(@Param(value = "username") String username);

    @Update(value = "update user set password = #{password} where username = #{username}")
    int updatePasswordByUsername(@Param(value = "password") String password, @Param(value = "username") String username);
}
