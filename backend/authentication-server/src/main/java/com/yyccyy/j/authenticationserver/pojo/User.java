package com.yyccyy.j.authenticationserver.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: 李家宝
 * @Date: 2018/10/27 11:50
 */

@Data
public class User implements Serializable {
    private long id;
    private String username;
    private String password;
}
