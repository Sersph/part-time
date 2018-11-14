package com.tidc.part.time.monarch.service.impl;

import com.tidc.part.time.monarch.domain.*;
import com.tidc.part.time.monarch.exception.ResultException;
import com.tidc.part.time.monarch.service.EmailService;
import com.tidc.part.time.monarch.mapper.CityMapper;
import com.tidc.part.time.monarch.mapper.PartTimeApplyMapper;
import com.tidc.part.time.monarch.mapper.PartTimeMapper;
import com.tidc.part.time.monarch.mapper.UserInfoMapper;
import com.tidc.part.time.monarch.service.PartTimeService;
import com.tidc.part.time.monarch.vo.PartTimeInfoRequestResult;
import com.tidc.part.time.monarch.vo.PartTimeVO;
import com.tidc.part.time.monarch.vo.RequestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
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
    private UserInfoMapper userInfoMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PartTimeApplyMapper partTimeApplyMapper;

    @Autowired
    private CityMapper cityMapper;

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
    public PartTimeVO getPartTimeById(int partTimeId) {

        PartTimeVO partTimeVO = this.partTimeMapper.findPartTimeById(partTimeId);
        if (partTimeVO == null){
            System.out.println("is null part time info");
            throw new ResultException(1005, "该兼职不存在");
        }

        Map<String, String> site = this.partTimeMapper.findSiteByUsername(partTimeVO.getEnterpriseName());
        System.out.println();
        partTimeVO.setEnterpriseSite(site.get("province") + " " + site.get("city"));


        partTimeVO.setRandom(this.partTimeMapper.listRandomDataByCityId(this.cityMapper.findCityIdByName(partTimeVO.getCity()), partTimeId ));
        return partTimeVO;
    }


    @Override
    public RequestResult applyPartTime(PartTimeApply partTimeApply, String token) {



        User user = (User) this.redisTemplate.opsForValue().get(token);

        if (user == null){
            throw new ResultException(1006, "提交失败！");
        }

        partTimeApply.setUserId(user.getId());

        partTimeApply.setCreateTime(new Date(new java.util.Date().getTime()));

        String email = this.userInfoMapper.findUserEmailByPartTimeId(partTimeApply.getPartTimeId());
        String partTimeName = this.partTimeMapper.findPartTimeNameByPartTimeId(partTimeApply.getPartTimeId());

        int sendEmail = this.emailService.informEnterprise(email, partTimeName);

        int addPartTimeApply = this.partTimeApplyMapper.addPartTimeApply(partTimeApply);

        if (sendEmail == 1 || email == null || partTimeName == null || addPartTimeApply == 0){
            throw new ResultException(1006, "提交失败！");
        }

        /**
         * TODO 这个接口是用户申请兼职的接口所以需要验证手机验证码后期有时间再完成
         */
        return new RequestResult().succeed();
    }
}





















