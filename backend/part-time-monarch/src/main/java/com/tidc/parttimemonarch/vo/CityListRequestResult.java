package com.tidc.parttimemonarch.vo;


import com.tidc.parttimemonarch.domain.City;
import com.tidc.parttimemonarch.domain.Region;
import lombok.Data;

import java.util.List;

@Data
public class CityListRequestResult extends RequestResult {
    private List<City> regionList;

    private Region currentCity;



    public RequestResult succeed(List<City> regionList, Region currentCity){
        this.succeed();
        this.regionList = regionList;
        this.currentCity = currentCity;
        return this;
    }
}
