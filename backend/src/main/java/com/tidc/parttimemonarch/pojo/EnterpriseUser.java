package com.tidc.parttimemonarch.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tidc.parttimemonarch.exceptions.ResultExceptions;
import com.tidc.parttimemonarch.util.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.sql.Date;

/**
 * 2 * @Author: 李家宝
 * 3 * @Date: 2018/10/11 11:10
 * 4
 */
@ApiModel
@Component
public class EnterpriseUser implements Serializable {

    @ApiModelProperty(hidden = true)
    //主键, 自增
    private int id;

    @Pattern(regexp = "^.{2,20}$", message = "企业名称长度必须大于2小于20")
    @ApiModelProperty(hidden = true)
    //企业名称
    private String enterpriseName;
    @Pattern(regexp = "^.{6,20}$", message = "密码长度必须大于6小于20")
    //密码
    private String password;


    @Email(message = "邮箱格式不正确")
    //邮箱
    private String email;
    @ApiModelProperty(hidden = true)
    //企业所在城市
    private int cityId;




    @ApiModelProperty(hidden = true)
    //企业所在地址
    private String address;
    @ApiModelProperty(hidden = true)
    //状态
    private int status;
    @ApiModelProperty(hidden = true)
    //企业认证状态
    private int certification;
    //最后一次登陆的时间
    @ApiModelProperty(hidden = true)
    private Date lastSignInAt;
    //创建时间 created_at
    @ApiModelProperty(hidden = true)
    private Date createdAt;
    //最后一次修改日期 updated_at
    @ApiModelProperty(hidden = true)
    private Date updatedAt;
    @ApiModelProperty(hidden = true)
    private int type = 2;


    public void setDate(){
        Date date = DateUtil.getDate();
        this.lastSignInAt = date;
        this.createdAt = date;
        this.updatedAt = date;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public String getPassword() {
        return DigestUtils.md5DigestAsHex(this.password.getBytes());
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {

        if ((cityId + "").length() != 6){
            throw new ResultExceptions(1006, "城市id错误");
        }


        this.cityId = cityId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @JsonIgnore
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @JsonIgnore
    public int getCertification() {
        return certification;
    }

    public void setCertification(int certification) {
        this.certification = certification;
    }

    @JsonIgnore
    public Date getLastSignInAt() {
        return lastSignInAt;
    }

    public void setLastSignInAt(Date lastSignInAt) {
        this.lastSignInAt = lastSignInAt;
    }

    @JsonIgnore
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @JsonIgnore
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
