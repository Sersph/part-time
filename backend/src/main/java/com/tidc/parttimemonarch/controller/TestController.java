package com.tidc.parttimemonarch.controller;

import com.tidc.parttimemonarch.service.EMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private EMailService eMailService;


    @GetMapping(value = "/mail")
    public String send(){
        try {
            this.eMailService.send();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "1";
    }
}
