package com.tidc.parttimemonarch.result;


import com.tidc.parttimemonarch.model.City;
import com.tidc.parttimemonarch.model.Region;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RequestResultImpl implements RequestResult {
    private int code;
    private String message;

    private List<City> regionList;

    private Region currentCity;

    private Object accountInfo;

    @Override
    public void succeed(){
        this.code = 0;
        this.message = "成功";
    }

    public void succeed(List<City> regionList, Region currentCity){
        this.succeed();
        this.regionList = regionList;
        this.currentCity = currentCity;
    }

    public void succeed(Object accountInfo){
        this.succeed();
        this.accountInfo = accountInfo;
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

    public Object getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(Object accountInfo) {
        this.accountInfo = accountInfo;
    }

    @Override
    public void error(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "RequestResult{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
