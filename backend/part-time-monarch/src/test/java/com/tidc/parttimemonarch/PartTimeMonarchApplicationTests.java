package com.tidc.parttimemonarch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PartTimeMonarchApplicationTests {

    @Test
    public void contextLoads() {
        HashMap<String, String> seek = new HashMap<>();

        seek.put("1", "123");
    }

}
