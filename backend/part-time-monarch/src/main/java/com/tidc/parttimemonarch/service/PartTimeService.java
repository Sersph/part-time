package com.tidc.parttimemonarch.service;

import com.tidc.parttimemonarch.domain.PageResultBean;
import com.tidc.parttimemonarch.domain.PartTime;
import com.tidc.parttimemonarch.domain.SelectPartTimeCondition;
import com.tidc.parttimemonarch.vo.PartTimeInfoRequestResult;
import com.tidc.parttimemonarch.vo.PartTimeVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

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

}
