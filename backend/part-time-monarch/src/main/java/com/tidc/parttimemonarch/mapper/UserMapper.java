package com.tidc.parttimemonarch.mapper;

import com.tidc.parttimemonarch.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: 李家宝
 * @Date: 2018/10/27 18:05
 */
@Repository
@Mapper
public interface UserMapper {


    /**
     * 根据用户名获取用户 id
     * @param username
     * @return int
     */
    @Select(value = "select id from user where username = #{username}")
    int selectUserIdByUsername(@Param(value = "username") String username);

    @Insert(value = "INSERT INTO user(username, password) VALUES (#{username},#{password})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int addUser(User user);



















}
