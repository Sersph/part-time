package com.yyccyy.j.authenticationserver.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author innoyiya
 * @date 2018/9/27.
 */
@FrameworkEndpoint
public class LogoutController {

    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    @ResponseBody
    public Map logoutPage (@Param("token") String token) {
        Map result = new HashMap(16);
        if (consumerTokenServices.revokeToken(token)){
            result.put("status", "成功");
            result.put("data", 1);
        } else {
            result.put("status", "失败");
            result.put("data", 1);
            result.put("code", 1008);
        }
        return result;
    }

}
