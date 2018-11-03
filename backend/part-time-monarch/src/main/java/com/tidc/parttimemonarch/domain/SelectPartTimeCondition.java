package com.tidc.parttimemonarch.domain;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Author: 李家宝
 * @Date: 2018/11/2 17:08
 */
@Data
public class SelectPartTimeCondition {

    @NotNull
    private int cityId;

    @NotNull
    private int areaId;

    @NotNull
    private int partTimeTypeId;

    @NotNull
    private int partTimeSpeciesId;

    private String keyword = "";

    @Min(1)
    private int pageSize;

    @Min(1)
    private int pageNumber;
}
