package com.tidc.parttimemonarch.dao;

import com.tidc.parttimemonarch.pojo.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: 李家宝
 * @Date: 2018/10/12 17:16
 */
@Repository
@Mapper
public interface PartTimeDAO {

    @Select(value = "select * from part_time_type")
    List<PartTimeType> selectPartTimeTypes();

    @Select(value = "select * from part_time_species")
    List<PartTimeSpecies> selectPartTimeSpecies();

    @Select(value = "select * from settlement_type")
    List<SettlementType> selectSettlementType();

    @Select(value = "select * from calculation_type")
    List<CalculationType> selectCalculationType();

    @Insert(value = "insert INTO part_time " +
            "(name, province_id, city_id, area_id, detail_address, recruitment_cont, price, calculation_type_id, settlement_type_id," +
            "enterprise_user_id, job_description, contact_people, contact_phone, working_date_start, working_date_end, working_time_week," +
            "working_time, price_description, part_time_type_id, part_time_species_id) " +
            "VALUES (#{name},#{provinceId},#{cityId},#{areaId},#{detailAddress},#{recruitmentCont},#{price},#{calculationTypeId},#{settlementTypeId}," +
            "#{enterpriseUserId},#{jobDescription},#{contactPeople},#{contactPhone},#{workingDateStart},#{workingDateEnd},#{workingTimeWeek},#{workingTime},#{priceDescription},#{partTimeTypeId},#{partTimeSpeciesId})")
    int insertPartTime(PartTime partTime);
}
