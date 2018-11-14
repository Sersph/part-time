package com.tidc.part.time.monarch.vo;

import lombok.Data;

import java.sql.Date;
import java.util.List;

/**
 * @Author: 李家宝
 * @Date: 2018/11/3 12:09
 */
@Data
public class PartTimeVO {
    private Integer id;
    private String name;
    private String province;
    private String city;
    private String area;
    private String address;
    private Integer recruitmentCount;
    private Double price;
    private String calculationType;
    private String settlementType;
    private String enterpriseName;
    private String jobDescription;
    private String contactPeople;
    private Long contactPhone;
    private Date workingDateStart;
    private Date workingDateEnd;
    private String workingTimeWeek;
    private String workingTime;
    private String priceDescription;
    private String partTimeType;
    private String partTimeSpecies;
    private String enterpriseSite;
    private List<PartTimeVO> random;
}
