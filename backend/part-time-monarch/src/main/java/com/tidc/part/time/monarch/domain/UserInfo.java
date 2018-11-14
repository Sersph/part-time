package com.tidc.part.time.monarch.domain;

import com.tidc.part.time.monarch.verify.EnterpriseVerifyUserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;

/**
 * 用户信息表
 *
 * @Author: 李家宝
 * @Date: 2018/10/27 21:30
 */
@ApiModel(description = "用户数据实体类")
@Data
public class UserInfo implements Serializable {


    @ApiModelProperty(hidden = true)
    private int id;

    @ApiModelProperty(value = "用户id", hidden = true)
    private int userId;

    @ApiModelProperty(value = "角色id", hidden = true)
    private int roleId;

    @ApiModelProperty("用户名或者企业名称")
    @Size(min = 6, max = 20, message = "用户名由6-20个字符组成", groups = EnterpriseVerifyUserInfo.class)
    private String name;

    @ApiModelProperty(value = "联系电话", hidden = true)
    private String phone;

    @ApiModelProperty("邮箱")
    @Email(message = "邮箱格式不正确", groups = EnterpriseVerifyUserInfo.class)
    private String email;

    @ApiModelProperty("城市id")
    @Pattern(regexp = "^\\d{6}$", message = "城市id为六位", groups = EnterpriseVerifyUserInfo.class)
    private String cityId;

    @ApiModelProperty(value = "具体地址", hidden = true)
    private String address;

    @ApiModelProperty(value = "状态", hidden = true)
    private int status;

    @ApiModelProperty(value = "认证状态", hidden = true)
    private int certification;

    @ApiModelProperty(value = "最后一次登陆时间", hidden = true)
    private Date lastSignInAt;

    @ApiModelProperty(value = "记录创建日期", hidden = true)
    private Date createdAt;

    @ApiModelProperty(value = "记录最后一次修改日期", hidden = true)
    private Date updatedAt;

    public void setDate(java.util.Date date){
        long time = date.getTime();
        Date sqlDate = new Date(time);
        this.lastSignInAt = sqlDate;
        this.createdAt = sqlDate;
        this.updatedAt = sqlDate;
    }
}
