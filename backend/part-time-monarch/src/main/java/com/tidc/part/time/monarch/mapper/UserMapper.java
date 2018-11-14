package com.tidc.part.time.monarch.mapper;

import com.tidc.part.time.monarch.domain.User;
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
    int selectUserIdByUsername(@Param(value = "username") String username);

    /**
     * 注册用户
     * @param user
     * @return int
     */
    int addUser(User user);



















}
