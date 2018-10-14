package com.tidc.parttimemonarch;

import junit.framework.Assert;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PartTimeMonarchApplicationTests {


    @Autowired
    StringEncryptor encryptor;

    @Test
    public void getPass() {
        System.out.println(encryptor.encrypt("mhh"));
    }


}
