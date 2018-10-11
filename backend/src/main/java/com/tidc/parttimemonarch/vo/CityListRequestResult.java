package com.tidc.parttimemonarch.vo;


import com.tidc.parttimemonarch.pojo.City;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CityListRequestResult extends RequestResult {
    private List<City> regionList;

    private Region currentCity;



    public void succeed(List<City> regionList, Region currentCity){
        this.succeed();
        this.regionList = regionList;
        this.currentCity = currentCity;
    }

    public List<City> getRegionList() {
        return regionList;
    }

    public void setRegionList(List<City> regionList) {
        this.regionList = regionList;
    }

    public Region getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(Region currentCity) {
        this.currentCity = currentCity;
    }

    @Override
    public String toString() {
        return "CityListRequestResult{" +
                "regionList=" + regionList +
                ", currentCity=" + currentCity +
                '}';
    }
}
