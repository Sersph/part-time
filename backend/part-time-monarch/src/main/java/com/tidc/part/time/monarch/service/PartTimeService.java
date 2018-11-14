package com.tidc.part.time.monarch.service;

import com.tidc.part.time.monarch.domain.PageResultBean;
import com.tidc.part.time.monarch.domain.PartTime;
import com.tidc.part.time.monarch.domain.PartTimeApply;
import com.tidc.part.time.monarch.domain.SelectPartTimeCondition;
import com.tidc.part.time.monarch.vo.PartTimeInfoRequestResult;
import com.tidc.part.time.monarch.vo.PartTimeVO;
import com.tidc.part.time.monarch.vo.RequestResult;

/**
 * @Author: 李家宝
 * @Date: 2018/10/28 21:59
 */
public interface PartTimeService {

    /**
     * 用于获取首页初始化数据
     * @return PartTimeInfoRequestResult
     */
    PartTimeInfoRequestResult getBaseInfo();

    /**
     * 用于添加一条兼职数据
     * @param partTime
     * @param token
     */
    void addPartTime(PartTime partTime, String token);


    /**
     * 条件搜索兼职数据
     * @param selectPartTimeCondition
     * @return PageResultBean<PartTime>
     */
    PageResultBean<PartTimeVO> getPartTimeList(SelectPartTimeCondition selectPartTimeCondition);


    /**
     * 根据 id 获取单条兼职数据
     * @param id
     * @return PartTimeVO
     */
    PartTimeVO getPartTimeById(int id);


    /**
     * 申请兼职
     * @param partTimeApply
     * @param token
     * @return RequestResult
     */
    RequestResult applyPartTime(PartTimeApply partTimeApply, String token);


}
