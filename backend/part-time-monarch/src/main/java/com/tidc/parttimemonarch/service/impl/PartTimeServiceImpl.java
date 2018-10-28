package com.tidc.parttimemonarch.service.impl;

import com.tidc.parttimemonarch.mapper.PartTimeMapper;
import com.tidc.parttimemonarch.service.PartTimeService;
import com.tidc.parttimemonarch.vo.PartTimeRequesResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: 李家宝
 * @Date: 2018/10/28 21:59
 */
@Service
public class PartTimeServiceImpl implements PartTimeService {

    @Autowired
    private PartTimeMapper partTimeMapper;

    @Autowired
    private PartTimeRequesResult partTimeRequesResult;

    @Override
    public PartTimeRequesResult getBaseInfo() {

        this.partTimeRequesResult.succeed(
                this.partTimeMapper.getListPartTimeType(),
                this.partTimeMapper.partTimeSpecies(),
                this.partTimeMapper.calculationTypes(),
                this.partTimeMapper.settlementTypes()
        );

        return this.partTimeRequesResult;
    }
}
