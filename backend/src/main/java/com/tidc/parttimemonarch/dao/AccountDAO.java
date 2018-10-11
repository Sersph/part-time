package com.tidc.parttimemonarch.dao;

import com.tidc.parttimemonarch.pojo.EnterpriseUser;
import com.tidc.parttimemonarch.pojo.PersonalUser;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
@Mapper
public interface AccountDAO{

    /**
     * 企业用户持久层操作
     *
     * @Author: 李家宝
    */

    //注册
    @Insert("INSERT INTO enterprise_user(enterprise_name, password, email, city_id, created_at, updated_at, last_sign_in_at) VALUES (#{enterpriseName},#{password},#{email}, #{cityId}, #{createdAt},#{updatedAt},#{lastSignInAt})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int addEnterpriseUser(EnterpriseUser enterpriseUser);

    //登陆
    @Select("SELECT id,enterprise_name,email,phone,address FROM enterprise_user WHERE email = #{email} AND password = #{password}")
    EnterpriseUser enterpriseSignIn(@Param("email")String email, @Param("password")String password);

    //更新用户最后登陆时间
    @Update("UPDATE enterprise_user SET last_sign_in_at=#{date} WHERE email=#{email}")
    int updateLastSignInAtInEnterprise(@Param("email") String email, @Param("date")Date date);

    //查询邮箱是否存在
    @Select("SELECT email FROM enterprise_user WHERE email = #{email}")
    String findEnterpriseByeEmail(@Param("email") String email);



    /**
     * 普通用户持久层操作
     *
     * @Author: 李家宝
    */
//    @Select("SELECT id,username,password,email,phone,status,type,avatar FROM personal_user WHERE username = #{username}")
//    PersonalUser findByUsername(@Param("username") String username);

    //注册
    @Insert("INSERT INTO personal_user(username, password, created_at, updated_at, last_sign_in_at) VALUES (#{username},#{password},#{createdAt},#{updatedAt},#{lastSignInAt})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    int addPersonalUser(PersonalUser personalUser);

    //更新用户最后登陆时间
    @Update("UPDATE personal_user SET last_sign_in_at=#{date} WHERE username=#{username}")
    int updateLastSignInAtInPersonal(@Param("username") String username, @Param("date")Date date);

    //登陆
    @Select("SELECT id,username,email,phone FROM personal_user WHERE username = #{username} AND password = #{password} || phone = #{username} AND password = #{password}")
    PersonalUser signIn(PersonalUser personalUser);
}
