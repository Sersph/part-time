package com.tidc.part.time.monarch.domain;

import com.tidc.part.time.monarch.verify.EnterpriseVerifyUserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @Author: 李家宝
 * @Date: 2018/10/27 14:41
 */

@ApiModel("用户model")
@Data
public class User implements Serializable {

    @ApiModelProperty(hidden = true)
    private int id;
//    用户名是唯一的

    @ApiModelProperty(value = "用户名由6-20个字符组成")
    @Size(min = 6, max = 20, message = "用户名由6-20个字符组成")
    @Email(message = "邮箱格式不正确", groups = EnterpriseVerifyUserInfo.class)
    private String username;



    @ApiModelProperty(value = "密码由6-20个字符组成")
    @Size(min = 6, max = 20, message = "密码由6-20个字符组成")
    private String password;
}
