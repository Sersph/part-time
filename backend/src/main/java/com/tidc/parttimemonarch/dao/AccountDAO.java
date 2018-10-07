package com.tidc.parttimemonarch.dao;

import com.tidc.parttimemonarch.model.User;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
@Mapper
public interface AccountDAO{

    @Select("SELECT id,username,password,email,phone,status,last_sign_in_at,updated_at,created_at FROM user WHERE username = #{username}")
    User findByUsername(@Param("username") String username);

    @Insert("INSERT INTO user(username, password, status, created_at, updated_at, last_sign_in_at) VALUES (#{username},#{password},#{status},#{createdAt},#{updatedAt},#{lastSignInAt})")
    int save(User user);

    @Update("UPDATE user SET last_sign_in_at=#{date} WHERE username=#{username}")
    int updateLastSignInAt(@Param("username") String username, @Param("date")Date date);

}
