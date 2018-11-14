package com.tidc.part.time.monarch.mapper;

import com.tidc.part.time.monarch.domain.PartTime;
import com.tidc.part.time.monarch.domain.PartTimeInfo;
import com.tidc.part.time.monarch.domain.SelectPartTimeCondition;
import com.tidc.part.time.monarch.vo.PartTimeVO;
import org.apache.ibatis.annotations.*;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 将 sql 与 mapper 接口分离 sql 写到 xml 文件里
 * @Author: 李家宝
 * @Author: 余润凯
 * @Date: 2018/10/28 21:56
 */
@Repository
@Mapper
public interface PartTimeMapper {

    /**
     * 获取兼职类型
     * @return List<PartTimeInfo>
     */
    List<PartTimeInfo> getListPartTimeType();

    /**
     * 获取兼职种类
     * @return List<PartTimeInfo>
     */
    List<PartTimeInfo> partTimeSpecies();

    /**
     * 获取工资计算方式
     * @return List<PartTimeInfo>
     */
    List<PartTimeInfo> calculationTypes();


    /**
     * 获取工资结算方式 日结 周结
     * @return List<PartTimeInfo>
     */
    List<PartTimeInfo> settlementTypes();

    /**
     * 根据 id 获取单个兼职的详细关系
     * @param id
     * @return PartTimeVO
     */
    PartTimeVO findPartTimeById(@Param(value = "id") int id);


    /**
     * 插入兼职
     * @param partTime
     * @return int
     */
    int addPartTime(PartTime partTime);

    /**
     * 根据 id 删除一条数据
     * @param id
     * @return int
     */
    int deletePartTimeById(@Param(value = "id") int id);


    /**
     * 条件搜索兼职
     * @param selectPartTimeCondition
     * @return List<PartTimeVO>
     */
    List<PartTimeVO> selectPartTime(SelectPartTimeCondition selectPartTimeCondition);


    /**
     * 根据企业名查询地址
     * @param username
     * @return Map<String, String>
     */
    @Select(value = "SELECT\n" +
            "  `p`.`name` 'province', `c`.`name` 'city'\n" +
            "FROM\n" +
            "  `city` p,\n" +
            "  `city` c,\n" +
            "  `user_info` ui\n" +
            "WHERE\n" +
            "  `ui`.`name` = #{username} \n" +
            "  AND `c`.`id` = `ui`.`city_id` \n" +
            "  AND `p`.`id` = `c`.`parent_id`")
    Map<String, String> findSiteByUsername(@Param(value = "username") String username);


    /**
     * 根据兼职id查询兼职名
     * @param partTimeId
     * @return String
     */
    String findPartTimeNameByPartTimeId(@Param(value = "partTimeId") int partTimeId);

    /**
     * 获取相同城市的其他兼职
     * @param cityId
     * @param partTimeId
     * @return List<PartTimeVO>
     */
    List<PartTimeVO> listRandomDataByCityId(@Param(value = "cityId") int cityId, @Param(value = "partTimeId") int partTimeId);
}
