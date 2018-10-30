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

    @Select(value = "select * from part_time")
    List<PartTime> selectAllPartTime();

    @Insert(value = "INSERT INTO part_time(name, province_id, city_id, area_id, detail_address, recruitment_cont, price, " +
            "calculation_type_id, settlement_type_id, user_id, job_description, contact_people, contact_phone, working_date_start, " +
            "working_date_end, working_time_week, working_time, price_description, part_time_type_id, part_time_species_id)" +
            "VALUES (#{name}, #{province_id}, #{city_id}, #{area_id}, #{detail_address}, #{recruitment_cont}, #{price}," +
            "#{calculation_type_id},#{settlement_type_id},#{user_id},#{job_description},#{contact_people},#{contact_phone},#{working_date_start}," +
            "#{working_date_end}, #{working_time_week}, #{working_time}, #{price_description}, #{part_time_type_id}, #{part_time_species_id}")
    int addPartTime(PartTime partTime);

    @Delete(value = "delete from part_time where id = #{id}")     //通过id删除一条数据
    int deletePartTimeById(@Param(value = "id") int id);
}
