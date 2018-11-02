package com.tidc.parttimemonarch.mapper;

import com.tidc.parttimemonarch.domain.UserRole;
import org.apache.ibatis.annotations.Insert;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: 李家宝
 * @Date: 2018/10/28 14:46
 */
@Repository
@Mapper
public interface UserRoleMapper {


    @Insert(value = "INSERT INTO user_role(user_id, role_id) VALUES (#{userId},#{roleId})")
    int addUserRole(UserRole userRole);


}
