package com.tidc.parttimemonarch.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.sql.Date;
import java.text.DecimalFormat;

/**
 * @Author: 李家宝
 * @Date: 2018/10/29 12:04
 */
@ApiModel("兼职信息表")
@Data
public class PartTime {

    @ApiModelProperty(hidden = true)
    private int id;

    @ApiModelProperty("标题5到30个字符")
    @Size(min = 5, max = 30, message = "标题5到30个字符")
    private String name;

    @ApiModelProperty("省份id为六位数")
    @Size( min = 6, max = 6,message = "地区id为六位数")
    private String provinceId;

    @ApiModelProperty("城市id为六位数")
    @Size( min = 6, max = 6,message = "地区id为六位数")
    private String cityId;

    @ApiModelProperty("地区id为六位数")
    @Size( min = 6, max = 6,message = "地区id为六位数")
    private String areaId;

    @ApiModelProperty("详细地址")
    @Size(min = 1, max = 50, message = "详细地址1到50个字符")
    private String detailAddress;

    @ApiModelProperty("招聘人数 1 - 9999人")
    @Min(value = 1, message = "招聘人数 1 - 9999人")
    @Max(value = 9999, message = "招聘人数 1 - 9999人")
    private int recruitmentCount;

    @ApiModelProperty("工资不能大于99999或者小于1")
    @Min(value = 1, message = "工资不能大于99999或者小于1")
    @Max(value = 99999, message = "工资不能大于99999或者小于1")
    private double price;

    @ApiModelProperty("工资计算方式")
    @NotNull(message = "工资计算方式不能为空")
    private int calculationTypeId;

    @ApiModelProperty("工资结算方式")
    @NotNull(message = "工资结算方式不能为空")
    private int settlementTypeId;

    @ApiModelProperty(value = "用户id", hidden = true)
    private int userId;

    @ApiModelProperty("工作描述由1到300个字符组成")
    @Size(min = 1, max = 300, message = "工作描述由1到300个字符组成")
    private String jobDescription;

    @ApiModelProperty("联系人名称由1到10位之间的字符组成")
    @Size(min = 1, max = 10, message = "联系人名称由1到10位之间的字符组成")
    private String contactPeople;

    @ApiModelProperty("联系电话由7到11位之间的字符组成")
    @Size(min = 7, max = 11, message = "联系电话由7到11位之间的字符组成")
    private String contactPhone;

    @ApiModelProperty("工作开始日期")
    @NotNull(message = "工作开始日期错误")
    private Date workingDateStart;

    @ApiModelProperty("工作结束日期")
    @NotNull(message = "工作结束日期错误")
    private Date workingDateEnd;

    @ApiModelProperty("工作时间")
    @NotNull(message = "工作时间错误")
    private String workingTimeWeek;

    @ApiModelProperty("上班时间")
    @NotNull(message = "上班时间错误")
    private String workingTime;

    @ApiModelProperty("工资描述由1到200个字符组成")
    @Size(min = 1, max = 200, message = "工资描述由1到200个字符组成")
    private String priceDescription;

    @ApiModelProperty("兼职类型")
    @NotNull(message = "兼职类型错误")
    private int partTimeTypeId;

    @ApiModelProperty("兼职种类")
    @NotNull(message = "兼职种类错误")
    private int partTimeSpeciesId;


    /**
     * 最多只能存在小数点后两位
     * @param price
     */
    public void setPrice(double price) {
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        this.price = Double.valueOf(decimalFormat.format(price));
    }

    public void setWorkingDateStart(long time) {
        this.workingDateStart = new Date(time);
    }

    public void setWorkingDateEnd(long time) {
        this.workingDateEnd = new Date(time);
    }
}
