package com.tidc.parttimemonarch.mapper;

import com.tidc.parttimemonarch.domain.User;
import com.tidc.parttimemonarch.domain.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Author: 李家宝
 * @Date: 2018/10/28 14:25
 */
@Repository
@Mapper
public interface UserInfoMapper {

    @Insert("INSERT INTO user_info(name, user_id, role_id, email, city_id, created_at, updated_at, last_sign_in_at) " +
            "VALUES (#{name}, #{userId}, #{roleId}, #{email}, #{cityId}, #{createdAt}, #{updatedAt}, #{lastSignInAt})")
    int addUserInfo(UserInfo userInfo);


    /**
     * 根据用户 Id 获取用户信息
     * @param userId
     * @return
     */
    @Select(value = "select * from user_info where user_id = #{userId}")
    UserInfo selectUserInfoByUserId(@Param(value = "userId") int userId);

    @Select(value = "select username from user where username = #{email}")
    String selectUserInfoByEmail(@Param(value = "email") String email);
}
