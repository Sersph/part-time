package com.tidc.part.time.monarch.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Date;

/**
 * @Author: 李家宝
 * @Date: 2018/11/5 18:53
 */
@Data
@ApiModel(value = "申请兼职的mode类")
public class PartTimeApply {
    @ApiModelProperty(hidden = true)
    private int id;

    @ApiModelProperty(hidden = true)
    private int userId;
    @NotNull(message = "兼职 id 不能为空")
    private int partTimeId;
    @Size(min = 1, max = 10, message = "姓名只能是 1 到 10 位字符组成")
    private String name;


    @Pattern(regexp = "^1[3|4|5|7|8][0-9]\\d{8}$", message = "手机号格式不正确")
    private String phone;

    @ApiModelProperty(hidden = true)
    private Date createTime;
    @ApiModelProperty(hidden = true)
    private int state;
}
