package com.tidc.parttimemonarch;

import com.tidc.parttimemonarch.domain.PartTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PartTimeMonarchApplicationTests {

    @Autowired
    PartTimeMapper partTimeMapper;

    @Test
    public void contextLoads() {
        PartTime partTime = new PartTime();

        partTime.setName("test");
        partTime.setProvinceId(123);
        partTime.setCityId(123);
        partTime.setAreaId(123);
        partTime.setDetailAddress("test");
        partTime.setRecruitmentCont(123);
        partTime.setPrice(123);
        partTime.setCalculationTypeId(123);
        partTime.setSettlementTypeId(123);
        partTime.setUserId(123);
        partTime.setJobDescription("test");
        partTime.setContactPeople("test");
        partTime.setContactPhone(123);
        partTime.setWorkingDateStart(new Date().getTime());
        partTime.setWorkingDateEnd(new Date().getTime());
        partTime.setWorkingTimeWeek("123");
        partTime.setWorkingTime("123");
        partTime.setPriceDescription("123");
        partTime.setPartTimeTypeId(123);
        partTime.setPartTimeSpeciesId(123);

        partTimeMapper.addPartTime(partTime);
    }

}
