package com.tidc.parttimemonarch.result;

import com.tidc.parttimemonarch.model.City;
import com.tidc.parttimemonarch.model.Region;

import java.util.List;

public interface RequestResult {

    void succeed();

    void succeed(List<City> regionList, Region currentCity);

    void succeed(Object accountInfo);

    void error(int code, String message);
}
