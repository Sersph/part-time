package com.tidc.part.time.monarch.mapper;

import com.tidc.part.time.monarch.domain.UserInfo;
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

    /**
     * 添加用户信息
     * @param userInfo
     * @return int
     */
//    @Insert("INSERT INTO user_info(name, user_id, role_id, email, city_id, created_at, updated_at, last_sign_in_at) " +
//            "VALUES (#{name}, #{userId}, #{roleId}, #{email}, #{cityId}, #{createdAt}, #{updatedAt}, #{lastSignInAt})")
    int addUserInfo(UserInfo userInfo);


    /**
     * 根据用户 Id 获取用户信息
     * @param userId
     * @return UserInfo
     */
//    @Select(value = "select * from user_info where user_id = #{userId}")
    UserInfo selectUserInfoByUserId(@Param(value = "userId") int userId);

    /**
     * 根据邮箱获取用户名
     * @param email
     * @return String
     */
//    @Select(value = "select username from user where username = #{email}")
    String selectUserInfoByEmail(@Param(value = "email") String email);

    /**
     * 根据兼职 id 获取用户邮箱
     * @param partTimeId
     * @return String
     */
//    @Select(value = "select ui.email from user_info ui, part_time pt where pt.id = #{partTimeId} and ui.user_id = pt.user_id")
    String findUserEmailByPartTimeId(@Param(value = "partTimeId") int partTimeId);
}
