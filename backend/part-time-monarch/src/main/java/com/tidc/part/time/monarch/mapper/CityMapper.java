package com.tidc.part.time.monarch.mapper;


import com.tidc.part.time.monarch.domain.City;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CityMapper {


    List<City> selectCityAll();


//    @Select(value = "select * from city where name = #{name}")
    City findCityByName(@Param(value = "name") String name);

//    @Select(value = "select id from city where name = #{name}")
    int findCityIdByName(@Param(value = "name") String name);
}
