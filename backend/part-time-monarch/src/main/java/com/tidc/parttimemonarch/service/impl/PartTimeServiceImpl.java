package com.tidc.parttimemonarch.service.impl;

import com.tidc.parttimemonarch.domain.PageResultBean;
import com.tidc.parttimemonarch.domain.PartTime;
import com.tidc.parttimemonarch.domain.SelectPartTimeCondition;
import com.tidc.parttimemonarch.domain.User;
import com.tidc.parttimemonarch.exception.ResultException;
import com.tidc.parttimemonarch.mapper.PartTimeMapper;
import com.tidc.parttimemonarch.service.PartTimeService;
import com.tidc.parttimemonarch.vo.PartTimeInfoRequestResult;
import com.tidc.parttimemonarch.vo.PartTimeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: 李家宝
 * @Date: 2018/10/28 21:59
 */
@Service
public class PartTimeServiceImpl implements PartTimeService {

    @Autowired
    private PartTimeMapper partTimeMapper;


    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public PartTimeInfoRequestResult getBaseInfo() {

        return new PartTimeInfoRequestResult().succeed(
                this.partTimeMapper.getListPartTimeType(),
                this.partTimeMapper.partTimeSpecies(),
                this.partTimeMapper.calculationTypes(),
                this.partTimeMapper.settlementTypes()
        );
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
        this.partTimeMapper.addPartTime(partTime);
    }


    @Override
    public PageResultBean<PartTimeVO> getPartTimeList(SelectPartTimeCondition selectPartTimeCondition) {
        List<PartTimeVO> partTimeVOS = this.partTimeMapper.selectPartTime(selectPartTimeCondition);

        return new PageResultBean<PartTimeVO>().setPage(partTimeVOS, selectPartTimeCondition.getPageSize(),
                selectPartTimeCondition.getPageNumber());
    }

    @Override
    public PartTimeVO getPartTimeById(int id) {

        PartTimeVO partTimeVO = this.partTimeMapper.findPartTimeById(id);
        if (partTimeVO == null){
            System.out.println("is null part time info");
            throw new ResultException(1005, "该兼职不存在");
        }

        Map<String, String> site = this.partTimeMapper.findSiteByUsername(partTimeVO.getEnterpriseName());
        System.out.println();
        partTimeVO.setEnterpriseSite(site.get("province") + " " + site.get("city"));
        return partTimeVO;
    }
}
