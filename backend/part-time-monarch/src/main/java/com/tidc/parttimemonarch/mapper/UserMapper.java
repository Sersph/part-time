package com.tidc.parttimemonarch.mapper;

import com.tidc.parttimemonarch.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: 李家宝
 * @Date: 2018/10/27 18:05
 */
@Repository
@Mapper
public interface UserMapper {


    @Insert(value = "INSERT INTO user(username, password) VALUES (#{username},#{password})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int addUser(User user);



















}
