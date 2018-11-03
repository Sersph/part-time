package com.tidc.parttimemonarch.mapper;

import com.tidc.parttimemonarch.domain.PartTime;
import com.tidc.parttimemonarch.domain.PartTimeInfo;
import com.tidc.parttimemonarch.domain.SelectPartTimeCondition;
import com.tidc.parttimemonarch.vo.PartTimeVO;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author: 李家宝
 * @Author: 余润凯
 * @Date: 2018/10/28 21:56
 */
@Repository
@Mapper
public interface PartTimeMapper {

    /**
     * 获取兼职类型
     * @return List<PartTimeInfo>
     */
    @Select(value = "select * from part_time_type")
    List<PartTimeInfo> getListPartTimeType();

    /**
     * 获取兼职种类
     * @return List<PartTimeInfo>
     */
    @Select(value = "select * from part_time_species")
    List<PartTimeInfo> partTimeSpecies();

    /**
     * 获取工资计算方式
     * @return List<PartTimeInfo>
     */
    @Select(value = "select * from calculation_type")
    List<PartTimeInfo> calculationTypes();


    /**
     * 获取工资结算方式 日结 周结
     * @return List<PartTimeInfo>
     */
    @Select(value = "select * from settlement_type")
    List<PartTimeInfo> settlementTypes();

    /**
     * 根据 id 获取单个兼职的详细关系
     * @param id
     * @return PartTimeVO
     */
    @Select(value = "SELECT\n" +
            "  `partTime`.`id`,\n" +
            "  `partTime`.`name`,\n" +
            "  `province`.`name` as 'province',\n" +
            "  `city`.`name` as 'city',\n" +
            "  `area`.`name` as 'area',\n" +
            "  `partTime`.`detail_address` as 'address',\n" +
            "  `partTime`.`recruitment_count`,\n" +
            "  `partTime`.`price`,\n" +
            "  `calculation`.`name` as 'calculationType',\n" +
            "  `settlement`.`name` as 'settlementType',\n" +
            "  `user`.`name` as 'enterprise_name',\n" +
            "  `partTime`.`job_description`,\n" +
            "  `partTime`.`contact_people`,\n" +
            "  `partTime`.`contact_phone`,\n" +
            "  `partTime`.`working_date_start`,\n" +
            "  `partTime`.`working_date_end`,\n" +
            "  `partTime`.`working_time_week`,\n" +
            "  `partTime`.`working_time`,\n" +
            "  `partTime`.`price_description`,\n" +
            "  `partTimeType`.`name` as 'partTimeType',\n" +
            "  `partTimeSpecies`.`name` as 'partTimeSpecies'\n" +
            "FROM\n" +
            "  `city` province,\n" +
            "  `city`,\n" +
            "  `city` area,\n" +
            "  `calculation_type` calculation,\n" +
            "  `settlement_type` settlement,\n" +
            "  `user_info` user,\n" +
            "  `part_time_type` partTimeType,\n" +
            "  `part_time_species` partTimeSpecies,\n" +
            "  `part_time` partTime\n" +
            "WHERE\n" +
            "  `partTime`.`id` = #{id}\n" +
            "    AND `province`.`id` = `partTime`.`province_id`\n" +
            "    AND `city`.`id` = `partTime`.`city_id`\n" +
            "    AND `area`.`id` = `partTime`.`area_id`\n" +
            "    AND `calculation`.`id` = `partTime`.`calculation_type_id`\n" +
            "    AND `settlement`.`id` = `partTime`.`settlement_type_id`\n" +
            "    AND `user`.`user_id` = `partTime`.`user_id`\n" +
            "    AND `partTimeType`.`id` = `partTime`.`part_time_type_id`\n" +
            "    AND `partTimeSpecies`.`id` = `partTime`.`part_time_species_id`")
    PartTimeVO findPartTimeById(@Param(value = "id") int id);


    /**
     * 插入兼职
     * @param partTime
     * @return int
     */
    @Insert(value = "INSERT INTO part_time(" +
                "name, province_id, city_id, area_id, detail_address, recruitment_count, price, calculation_type_id, " +
                "settlement_type_id, user_id, job_description, contact_people, contact_phone, working_date_start, " +
                "working_date_end, working_time_week, working_time, price_description, part_time_type_id, part_time_species_id" +
            ")" +
            "VALUES (" +
                "#{name}, #{provinceId}, #{cityId}, #{areaId}, #{detailAddress}, #{recruitmentCount}, #{price}," +
                "#{calculationTypeId},#{settlementTypeId},#{userId},#{jobDescription},#{contactPeople},#{contactPhone}," +
                "#{workingDateStart}, #{workingDateEnd}, #{workingTimeWeek}, #{workingTime}, #{priceDescription}," +
                "#{partTimeTypeId}, #{partTimeSpeciesId}" +
            ")")
    int addPartTime(PartTime partTime);

    /**
     * 根据 id 删除一条数据
     * @param id
     * @return int
     */
    @Delete(value = "delete from part_time where id = #{id}")
    int deletePartTimeById(@Param(value = "id") int id);


    /**
     * 条件搜索兼职
     * @param selectPartTimeCondition
     * @return
     */
    @Select(value = "select " +
            "  pt.id id, " +
            "  pt.name name, " +
            "  pt.price price, " +
            "  pt.recruitment_count recruitment_count, " +
            "  c.name as area, " +
            "  ct.name as calculation_type, " +
            "  pts.name as part_time_species, " +
            "  ptt.name as part_time_type, " +
            "  st.name as settlement_type  " +
            "from" +
            "  part_time pt, " +
            "  city c," +
            "  calculation_type ct, " +
            "  part_time_species pts, " +
            "  part_time_type ptt, " +
            "  settlement_type st " +
            "where " +
            "  pt.city_id = #{cityId} " +
            "and " +
            "  (#{areaId} = 0 or pt.area_id = #{areaId}) " +
            "and " +
            "  (#{partTimeTypeId} = 0 or pt.part_time_type_id = #{partTimeTypeId}) " +
            "and" +
            "  (#{partTimeSpeciesId} = 0 or pt.part_time_species_id = #{partTimeSpeciesId}) " +
            "and " +
            "  (#{keyword} = '' or pt.name like #{keyword}) " +
            "and " +
            "  c.id = pt.area_id " +
            "and " +
            "  ct.id = pt.calculation_type_id " +
            "and" +
            "  pts.id = pt.part_time_species_id " +
            "and " +
            "  ptt.id = pt.part_time_type_id " +
            "and " +
            "  st.id = pt.settlement_type_id ")
    List<PartTimeVO> selectPartTime(SelectPartTimeCondition selectPartTimeCondition);


    /**
     * 根据企业名查询地址
     * @param username
     * @return
     */
    @Select(value = "SELECT\n" +
            "  `p`.`name` 'province', `c`.`name` 'city'\n" +
            "FROM\n" +
            "  `city` p,\n" +
            "  `city` c,\n" +
            "  `user_info` ui\n" +
            "WHERE\n" +
            "  `ui`.`name` = #{username} \n" +
            "  AND `c`.`id` = `ui`.`city_id` \n" +
            "  AND `p`.`id` = `c`.`parent_id`")
    Map<String, String> findSiteByUsername(@Param(value = "username") String username);
}
