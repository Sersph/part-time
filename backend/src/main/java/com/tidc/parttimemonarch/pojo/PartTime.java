package com.tidc.parttimemonarch.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tidc.parttimemonarch.util.NumberUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;

/**
 * 2 * @Author: 李家宝
 * 3 * @Date: 2018/10/12 8:58
 * 4
 */
@ApiModel
public class PartTime {

    @ApiModelProperty(hidden = true)
    private int id;
    //兼职标题
    @Pattern(regexp = "^.{1,30}$", message = "标题长度不正确")
    private String name;

    //省份id
    @NotNull(message = "省份id不能为空")
    private int provinceId;
    //城市id
    @NotNull(message = "城市id不能为空")
    private int cityId;
    //地区id
    @NotNull(message = "地区id不能为空")
    private int areaId;

    //详细地址
    @Pattern(regexp = "^.{1,50}$", message = "详细地址长度不正确")
    private String detailAddress;
    //招聘人数
    @NotNull(message = "招聘人数不能为空")
    private int recruitmentCount;
    //工资
    @NotNull(message = "工资不能为空")
    private float price;
    //工资计算方式
    @NotNull(message = "工资计算方式不能为空")
    private int calculationTypeId;
    //工资结算方式
    @NotNull(message = "工资结算方式不能为空")
    private int settlementTypeId;

    @ApiModelProperty(hidden = true)
    //发布企业id 取 session
    private int enterpriseUserId;
    //工作描述
//    @Pattern(regexp = "^.{1,500}$", message = "工作描述长度不正确")
    @Size(min = 1, max = 500)
    private String jobDescription;
    //联系人
    @Pattern(regexp = "^.{1,20}$", message = "联系人格式不正确")
    private String contactPeople;
    //联系电话
    @NotNull(message = "联系电话不能为空")
    private long contactPhone;
    //工作开始日期
    @NotNull(message = "工作开始日期不能为空")
    private Date workingDateStart;
    //工作结束日期
    @NotNull(message = "工作结束日期不能为空")
    private Date workingDateEnd;
    //工作时间 周几
    @NotNull(message = "工作时间不能为空")
    private String workingTimeWeek;
    //上班时间
    @NotNull(message = "上班时间不能为空")
    private String workingTime;
    //薪资描述
    private String priceDescription;
    //兼职类型
    @NotNull(message = "兼职类型不能为空")
    private int partTimeTypeId;
    //兼职种类
    @NotNull(message = "兼职种类不能为空")
    private int partTimeSpeciesId;


    @JsonIgnore
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        NumberUtil.validNumberLength(provinceId,6, 6, "省份id格式不正确");
        this.provinceId = provinceId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        NumberUtil.validNumberLength(cityId,6, 6, "城市id格式不正确");
        this.cityId = cityId;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        NumberUtil.validNumberLength(areaId,6, 6, "地区id格式不正确");
        this.areaId = areaId;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public int getRecruitmentCount() {
        return recruitmentCount;
    }

    public void setRecruitmentCount(int recruitmentCount) {
        NumberUtil.validNumberLength(recruitmentCount, 1, 5, "招聘人数不符合规范");
        this.recruitmentCount = recruitmentCount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        NumberUtil.validNumberLength(Math.round(price), 1, 20, "工资不符合规范");
        this.price = price;
    }

    public int getCalculationTypeId() {
        return calculationTypeId;
    }

    public void setCalculationTypeId(int calculationTypeId) {
        this.calculationTypeId = calculationTypeId;
    }

    public int getSettlementTypeId() {
        return settlementTypeId;
    }

    public void setSettlementTypeId(int settlementTypeId) {
        this.settlementTypeId = settlementTypeId;
    }

    public int getEnterpriseUserId() {
        return enterpriseUserId;
    }

    public void setEnterpriseUserId(int enterpriseUserId) {
        this.enterpriseUserId = enterpriseUserId;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getContactPeople() {
        return contactPeople;
    }

    public void setContactPeople(String contactPeople) {
        this.contactPeople = contactPeople;
    }

    public long getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(long contactPhone) {
        NumberUtil.validNumberLength(contactPhone, 5, 20, "电话格式不正确");
        this.contactPhone = contactPhone;
    }

    public Date getWorkingDateStart() {
        return workingDateStart;
    }

    public void setWorkingDateStart(long workingDateStart) {
        this.workingDateStart = new Date(workingDateStart);
    }

    public Date getWorkingDateEnd() {
        return workingDateEnd;
    }

    public void setWorkingDateEnd(long workingDateEnd) {
        this.workingDateEnd = new Date(workingDateEnd);
    }

    public String getWorkingTimeWeek() {
        return workingTimeWeek;
    }

    public void setWorkingTimeWeek(String workingTimeWeek) {
        this.workingTimeWeek = workingTimeWeek;
    }

    public String getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(String workingTime) {
        this.workingTime = workingTime;
    }

    public String getPriceDescription() {
        return priceDescription;
    }

    public void setPriceDescription(String priceDescription) {
        this.priceDescription = priceDescription;
    }

    public int getPartTimeTypeId() {
        return partTimeTypeId;
    }

    public void setPartTimeTypeId(int partTimeTypeId) {
        this.partTimeTypeId = partTimeTypeId;
    }

    public int getPartTimeSpeciesId() {
        return partTimeSpeciesId;
    }

    public void setPartTimeSpeciesId(int partTimeSpeciesId) {
        this.partTimeSpeciesId = partTimeSpeciesId;
    }

    @Override
    public String toString() {
        return "PartTime{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", provinceId=" + provinceId +
                ", cityId=" + cityId +
                ", areaId=" + areaId +
                ", detailAddress='" + detailAddress + '\'' +
                ", recruitmentCount=" + recruitmentCount +
                ", price=" + price +
                ", calculationTypeId=" + calculationTypeId +
                ", settlementTypeId=" + settlementTypeId +
                ", enterpriseUserId=" + enterpriseUserId +
                ", jobDescription='" + jobDescription + '\'' +
                ", contactPeople='" + contactPeople + '\'' +
                ", contactPhone=" + contactPhone +
                ", workingDateStart=" + workingDateStart +
                ", workingDateEnd=" + workingDateEnd +
                ", workingTimeWeek='" + workingTimeWeek + '\'' +
                ", workingTime='" + workingTime + '\'' +
                ", priceDescription='" + priceDescription + '\'' +
                ", partTimeTypeId=" + partTimeTypeId +
                ", partTimeSpeciesId=" + partTimeSpeciesId +
                '}';
    }
}
