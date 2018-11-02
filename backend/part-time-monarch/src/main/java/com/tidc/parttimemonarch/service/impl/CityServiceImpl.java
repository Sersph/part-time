package com.tidc.parttimemonarch.service.impl;

import com.tidc.parttimemonarch.mapper.CityDAO;
import com.tidc.parttimemonarch.domain.City;
import com.tidc.parttimemonarch.domain.Region;
import com.tidc.parttimemonarch.service.CityService;
import com.tidc.parttimemonarch.vo.CityListRequestResult;
import com.tidc.parttimemonarch.vo.RequestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDAO cityDAO;

    @Autowired
    private CityListRequestResult result;

    @Override
    public RequestResult obtainCity() {

        List<City> cityList = this.cityDAO.selectCityAll();

        City dongGuan = this.cityDAO.selectCityByName("东莞市");
        List<City> cities = new ArrayList<>();
        Region region = new Region(dongGuan, this.getRegionList(dongGuan.getId(), cities, cityList));

        this.result.succeed(this.neaten(cityList), region);
        return this.result;
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
