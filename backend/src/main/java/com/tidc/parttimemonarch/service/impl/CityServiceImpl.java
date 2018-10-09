package com.tidc.parttimemonarch.service.impl;

import com.tidc.parttimemonarch.dao.CityDAO;
import com.tidc.parttimemonarch.enumerate.Code;
import com.tidc.parttimemonarch.model.City;
import com.tidc.parttimemonarch.model.Region;
import com.tidc.parttimemonarch.result.CityListRequestResult;
import com.tidc.parttimemonarch.result.RequestResult;
import com.tidc.parttimemonarch.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CityServiceImpl implements CityService {

    @Autowired
    private CityDAO cityDAO;

    @Override
    public RequestResult obtainCity() {

        List<City> cityList = this.cityDAO.selectCityAll();

        CityListRequestResult cityListRequestResult = new CityListRequestResult(Code.SUCCEED, this.neaten(cityList));

        City dongguan = this.cityDAO.selectCityByName("东莞市");
        List<City> cities = new ArrayList<>();
        Region region = new Region(dongguan, this.getRegionList(dongguan.getId(), cities, cityList));

        cityListRequestResult.setCurrentCity(region);

        return cityListRequestResult;
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
