package com.yyccyy.j.authenticationserver.controller;

import com.yyccyy.j.authenticationserver.pojo.User;
import com.yyccyy.j.authenticationserver.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author innoyiya
 * @date 2018/10/10.
 */
@RestController
public class AlterPasswordController {


    @Autowired
    private IUserService userService;

    private static final String ENCODINGID = "{bcrypt}";
    private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    @PreAuthorize(value = "principal.username.equals(#userId)")
    @PutMapping("/alter/password/{userId}")
    public Map<String,Object> alterPassword(@PathVariable String userId, User user){
        String encodePassword = ENCODINGID + bCryptPasswordEncoder.encode(user.getPassword());
        Map result = new HashMap(8);
        if (userService.alterPassword(userId, encodePassword)){
            result.put("status", "成功");
            result.put("data", 1);
        }else {
            result.put("status", "失败");
            result.put("data", 0);
        }
        return result;
    }


    @ResponseBody
    @GetMapping("/alter/hello")
    @PreAuthorize(value = "hasAnyAuthority('root','enterpise')")
    public String sayHello(){
        return "hello world ";
    }

}
