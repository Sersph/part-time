package com.yyccyy.j.authenticationserver.message;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * @author innoyiya
 * @date 2018/9/24.
 */
@JsonSerialize(using = CustomOauthExceptionSerializer.class)
public class CustomOauthException extends OAuth2Exception {


    public CustomOauthException(String msg) {
        super(msg);
    }


}
