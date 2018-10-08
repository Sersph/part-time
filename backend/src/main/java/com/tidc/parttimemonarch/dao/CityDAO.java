package com.tidc.parttimemonarch.dao;


import com.tidc.parttimemonarch.model.city.City;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CityDAO {


    @Select(value = "select * from city where id > 0")
    List<City> selectCityAll();


    @Select(value = "select * from city where name = #{name}")
    City selectCityByName(@Param(value = "name") String name);
}
