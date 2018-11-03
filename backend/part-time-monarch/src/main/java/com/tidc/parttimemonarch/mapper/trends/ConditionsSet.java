package com.tidc.parttimemonarch.mapper.trends;

import lombok.Data;

import java.util.Map;

/**
 * @Author: 李家宝
 * @Date: 2018/11/1 20:40
 */
@Deprecated
@Data
public class ConditionsSet {


    private String tableName;

    private Map<String, Object> condition;

    public ConditionsSet(String tableName, Map<String, Object> condition) {
        this.tableName = tableName;
        this.condition = condition;
    }

}
