package com.tidc.part.time.monarch.vo;


import com.tidc.part.time.monarch.domain.City;
import com.tidc.part.time.monarch.domain.Region;
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
