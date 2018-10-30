package com.tidc.parttimemonarch.service;

import com.tidc.parttimemonarch.domain.PartTime;
import com.tidc.parttimemonarch.vo.PartTimeRequesResult;

/**
 * @Author: 李家宝
 * @Date: 2018/10/28 21:59
 */
public interface PartTimeService {

    PartTimeRequesResult getBaseInfo();

    void addPartTime(PartTime partTime, String token);
}
