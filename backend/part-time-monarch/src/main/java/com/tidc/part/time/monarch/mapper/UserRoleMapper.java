package com.tidc.part.time.monarch.mapper;

import com.tidc.part.time.monarch.domain.UserRole;
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

    /**
     * 注册时添加一条用户角色数据
     * @param userRole
     * @return int
     */
    int addUserRole(UserRole userRole);


}
