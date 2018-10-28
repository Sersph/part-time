package com.tidc.parttimemonarch.security;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "用户的令牌，用于鉴权和认证")
public class UserTokenDTO implements Serializable {

    private static final long serialVersionUID = 5802485028976042797L;

    @ApiModelProperty("状态码")
    private int code;

    @ApiModelProperty("消息")
    private String message;

    @ApiModelProperty("令牌")
    private String access_token;

    @ApiModelProperty(value = "密码的模式", hidden = true)
    private String token_type;

//    @ApiModelProperty("用于刷新的令牌")
//    private String refresh_token;

    @ApiModelProperty("token持续时间")
    private Integer expires_in;

    @ApiModelProperty(value = "范围", hidden = true)
    private String scope;

}