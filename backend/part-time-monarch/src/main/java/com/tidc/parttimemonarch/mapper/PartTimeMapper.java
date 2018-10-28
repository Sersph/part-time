package com.tidc.parttimemonarch.mapper;

import com.tidc.parttimemonarch.domain.PartTimeInfo;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: 李家宝
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


}
