package com.tidc.parttimemonarch.service.impl;

import com.tidc.parttimemonarch.dao.PartTimeDAO;
import com.tidc.parttimemonarch.pojo.PartTime;
import com.tidc.parttimemonarch.service.PartTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 兼职 service
 *
 * 发布兼职
 * 修改兼职
 * 删除兼职
 * 查询兼职数据等操作
 *
 * @Author: 李家宝
 * @Date: 2018/10/12 22:03
 */
@Component
public class PartTimeServiceImpl implements PartTimeService {

    @Autowired
    private PartTimeDAO partTimeDAO;

    @Override
    public void addPartTime(PartTime partTime) {
        this.partTimeDAO.insertPartTime(partTime);
    }
}
