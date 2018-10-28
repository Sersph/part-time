package com.tidc.parttimemonarch.mapper;

import com.tidc.parttimemonarch.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
@Mapper
public interface AccountMapper {




    /**
     *
     * @Author: 李家宝
    */

    //注册
    @Insert("INSERT INTO enterprise_user(enterprise_name, password, email, city_id, created_at, updated_at, last_sign_in_at) VALUES (#{enterpriseName},#{password},#{email}, #{cityId}, #{createdAt},#{updatedAt},#{lastSignInAt})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int addEnterpriseUser(UserInfo UserInfo);


    //登陆
    @Select("SELECT id,enterprise_name,email,phone,address FROM enterprise_user WHERE email = #{email} AND password = #{password}")
    UserInfo enterpriseSignIn(@Param("email")String email, @Param("password")String password);

    @Update("UPDATE enterprise_user SET last_sign_in_at=#{date} WHERE email=#{email}")
    int updateLastSignInAtInEnterprise(@Param("email") String email, @Param("date")Date date);


    @Select("SELECT email FROM enterprise_user WHERE email = #{email}")
    String findEnterpriseByeEmail(@Param("email") String email);



    /**
     * 普通用户持久层操作
     *
     * @Author: 李家宝
    */


//    @Select("SELECT id,username,password,email,phone,status,type,avatar FROM personal_user WHERE username = #{username}")
//    PersonalUser findByUsername(@Param("username") String username);

    @Insert("INSERT INTO personal_user(username, password, created_at, updated_at, last_sign_in_at) VALUES (#{username},#{password},#{createdAt},#{updatedAt},#{lastSignInAt})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int addPersonalUser(UserInfo personalUser);

    @Select("SELECT id,username,email,phone FROM personal_user WHERE username = #{username} AND password = #{password} || phone = #{username} AND password = #{password}")
    UserInfo signIn(UserInfo personalUser);





    /**
     * 更新用户最后登陆时间
     *
     * @param username
     * @param date
     * @return
     */
    @Update("UPDATE user_info SET last_sign_in_at=#{date} WHERE username=#{username}")
    int updateLastSignInAtInPersonal(@Param("username") String username, @Param("date")Date date);

    /**
     * 根据用户名获取用户 id
     * @param username
     * @return int
     */
    @Select(value = "select id from user where username = #{username}")
    int selectUserIdByUsername(@Param(value = "username") String username);
}
