package com.tidc.parttimemonarch.dao;

import com.tidc.parttimemonarch.model.PersonalUser;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
@Mapper
public interface AccountDAO{

    @Select("SELECT id,username,password,email,phone,status,type,avatar FROM user WHERE username = #{username}")
    PersonalUser findByUsername(@Param("username") String username);

    @Insert("INSERT INTO user(username, password, status, created_at, updated_at, last_sign_in_at) VALUES (#{username},#{password},#{status},#{createdAt},#{updatedAt},#{lastSignInAt})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int save(PersonalUser personalUser);

    @Update("UPDATE user SET last_sign_in_at=#{date} WHERE username=#{username}")
    int updateLastSignInAt(@Param("username") String username, @Param("date")Date date);

    @Select("SELECT * FROM user WHERE username = #{username} AND password = #{password} || phone = #{username} AND password = #{password}")
    PersonalUser signIn(PersonalUser personalUser);
}
