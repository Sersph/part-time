package com.tidc.parttimemonarch.result;

import com.tidc.parttimemonarch.enumerate.Code;
import com.tidc.parttimemonarch.model.city.City;
import com.tidc.parttimemonarch.model.city.Region;

import java.util.List;

public class CityListRequestResult extends RequestResult{


    private List<City> regionList;

    private Region currentCity;

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
