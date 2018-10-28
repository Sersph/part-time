package com.tidc.parttimemonarch;

import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AccountDAO {

    @Select(value = "select id from user where username = #{username}")
    int selectUserIdByUsername(@Param(value = "username") String username);
}
