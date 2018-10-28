package com.tidc.parttimemonarch;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PartTimeMonarchApplicationTests {

    @Autowired
    private AccountDAO accountDAO;

    @Test
    public void contextLoads() {

        System.out.println(accountDAO.selectUserIdByUsername("baoeraa"));

    }

}
