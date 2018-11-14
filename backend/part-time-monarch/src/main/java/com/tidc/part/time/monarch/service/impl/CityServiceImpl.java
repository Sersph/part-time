package com.tidc.part.time.monarch.service.impl;

import com.tidc.part.time.monarch.mapper.CityMapper;
import com.tidc.part.time.monarch.domain.City;
import com.tidc.part.time.monarch.domain.Region;
import com.tidc.part.time.monarch.service.CityService;
import com.tidc.part.time.monarch.vo.CityListRequestResult;
import com.tidc.part.time.monarch.vo.RequestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * 获取城市列表
 * @Author 李家宝
 */
@Component
public class CityServiceImpl implements CityService {

    @Autowired
    private CityMapper cityMapper;

    @Override
    public RequestResult obtainCity() {

        List<City> cityList = this.cityMapper.selectCityAll();

        City dongGuan = this.cityMapper.findCityByName("东莞市");
        List<City> cities = new ArrayList<>();
        Region region = new Region(dongGuan, this.getRegionList(dongGuan.getId(), cities, cityList));

        return new CityListRequestResult().succeed(this.neaten(cityList), region);
    }

    private List neaten(List cityList) {
        List<Region> regions = new ArrayList<>();
        regions = getRegionList(0, regions, cityList);
        return regions;
    }


    private List<Region> getRegionList(int parentId,List container, List<City> cityList){

        for (City city : cityList){
            if (city.getParentId() == parentId){
                List regions = this.getRegionList(city.getId(),new ArrayList<Region>(), cityList);

                container.add(new Region(city, regions));
            }

        }
        return container;
    }

}
