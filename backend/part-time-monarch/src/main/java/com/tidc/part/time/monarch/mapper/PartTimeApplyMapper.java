package com.tidc.part.time.monarch.mapper;

import com.tidc.part.time.monarch.domain.PartTimeApply;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: 李家宝
 * @Date: 2018/11/6 18:37
 */
@Repository
@Mapper
public interface PartTimeApplyMapper {

    /**
     *
     * @param partTimeApply
     * @return int
     */
//    @Insert(value = "insert into part_time_apply(user_id, part_time_id, name, phone, create_time) values(#{userId}, #{partTimeId}, #{name}, #{phone}, #{createTime})")
    int addPartTimeApply(PartTimeApply partTimeApply);

}
