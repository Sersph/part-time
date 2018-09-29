package com.tidc.parttimemonarch;


import org.springframework.stereotype.Component;

@Component
public class TestPojo {
    public String name = "hah";
    public String message = "test pojo";

    public TestPojo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
