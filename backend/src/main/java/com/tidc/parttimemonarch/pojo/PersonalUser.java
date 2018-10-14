package com.tidc.parttimemonarch.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tidc.parttimemonarch.util.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.sql.Date;

@ApiModel
@Component
public class PersonalUser implements Serializable {

    @ApiModelProperty(hidden = true)
    private int id;

    @Pattern(regexp = "^.{6,20}$", message = "用户名长度必须大于6小于20")
    private String username;

    @Pattern(regexp = "^.{6,20}$", message = "密码长度必须大于6小于20")
    private String password;

    @ApiModelProperty(hidden = true)
    @Email(message = "邮箱格式错误")
    private String email;
    @ApiModelProperty(hidden = true)
    private String phone;

    @ApiModelProperty(hidden = true)
    private int status = 0;

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
    //1普通用户 2企业用户
    private int type = 1;

    //头像
    @ApiModelProperty(hidden = true)
    private String avatar = "default.jpg";


    public PersonalUser() {}


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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore
    public String getPassword() {
        return DigestUtils.md5DigestAsHex(this.password.getBytes());
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonIgnore
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                ", lastSignInAt=" + lastSignInAt +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", type=" + type +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
