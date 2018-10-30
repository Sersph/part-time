package com.tidc.parttimemonarch.service.impl;

import com.tidc.parttimemonarch.domain.PartTime;
import com.tidc.parttimemonarch.domain.User;
import com.tidc.parttimemonarch.mapper.PartTimeMapper;
import com.tidc.parttimemonarch.service.PartTimeService;
import com.tidc.parttimemonarch.vo.PartTimeRequesResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Autowired
    private RedisTemplate redisTemplate;

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


    /**
     * 添加兼职
     * @param partTime
     * @param token
     */
    @Override
    public void addPartTime(PartTime partTime, String token) {
        User user = (User) this.redisTemplate.opsForValue().get(token);
        partTime.setUserId(user.getId());

    }
}
