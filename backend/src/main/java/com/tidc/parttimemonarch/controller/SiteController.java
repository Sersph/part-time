package com.tidc.parttimemonarch.controller;

import com.tidc.parttimemonarch.message.RequestState;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/site")
public class SiteController {


    @GetMapping(value = "/getUserInfo")
    public RequestState getUserInfo(HttpServletRequest httpServletRequest){
        return null;
    }
}
