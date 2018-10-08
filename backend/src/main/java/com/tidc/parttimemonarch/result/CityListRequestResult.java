package com.tidc.parttimemonarch.result;

import com.tidc.parttimemonarch.enumerate.Code;
import com.tidc.parttimemonarch.model.city.City;

import java.util.List;

public class CityListRequestResult extends RequestResult{


    private List<City> regionList;

    private City currentCity;

    public CityListRequestResult(Code code, List regionList){
        super(code);
        this.regionList = regionList;
    }

    public List<City> getRegionList() {
        return regionList;
    }

    public void setRegionList(List<City> regionList) {
        this.regionList = regionList;
    }

    public City getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(City currentCity) {
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
