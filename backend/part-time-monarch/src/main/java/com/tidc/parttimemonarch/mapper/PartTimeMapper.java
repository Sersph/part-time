package com.tidc.parttimemonarch.mapper;

import com.tidc.parttimemonarch.domain.PartTime;
import com.tidc.parttimemonarch.domain.PartTimeInfo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import javax.mail.Part;
import java.util.List;

/**
 * @Author: 李家宝
 * @Author: 余润凯
 * @Date: 2018/10/28 21:56
 */
@Repository
@Mapper
public interface PartTimeMapper {

    @Select(value = "select * from part_time_type")
    List<PartTimeInfo> getListPartTimeType();

    @Select(value = "select * from part_time_species")
    List<PartTimeInfo> partTimeSpecies();

    @Select(value = "select * from calculation_type")
    List<PartTimeInfo> calculationTypes();

    @Select(value = "select * from settlement_type")
    List<PartTimeInfo> settlementTypes();

    @Select(value = "")     //查询所有数据
    List<PartTime> selectAllPartTime();

//     @Insert("INSERT INTO user_info(name, user_id, role_id, email, city_id, created_at, updated_at, last_sign_in_at) " +
//            "VALUES (#{name}, #{userId}, #{roleId}, #{email}, #{cityId}, #{createdAt}, #{updatedAt}, #{lastSignInAt})")


    @Insert(value = "")     //插入一条数据
    int addPartTime(PartTime partTime);

    @Delete(value = "")     //通过id删除一条数据
    int deletePartTimeById(@Param(value = "id") int id);
}
