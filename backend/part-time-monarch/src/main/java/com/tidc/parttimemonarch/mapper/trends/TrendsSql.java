package com.tidc.parttimemonarch.mapper.trends;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @Author: 李家宝
 * @Date: 2018/11/1 21:59
 */
@Deprecated
public class TrendsSql {

    private StringBuffer sqlBuffer = new StringBuffer();

    private final String sql = "select * from ";

    private final String and = " and ";

    public String trendsSql(ConditionsSet conditionsSet){

        sqlBuffer.append(sql);

        String tableName = conditionsSet.getTableName();

        Map<String, Object> condition = conditionsSet.getCondition();

        boolean isSeek = false;

        String seek = "seek";

        ArrayList<String> seekList = null;

        if (condition.get(seek) != null){
            isSeek = true;
            seekList = (ArrayList<String>) condition.get(seek);
            condition.remove(seek);
        }


        List<String> keys = new ArrayList<>();
        condition.forEach((key, value)->{
            keys.add(key);
        });


        for (int i = 0; i < keys.size(); i ++){
            sqlBuffer.append(tableName + " where ");
            sqlBuffer.append(keys.get(i) + "=" + condition.get(keys.get(i)));
            if (i + 1 != keys.size()){
                sqlBuffer.append(and);
            }
        }

        if (isSeek){
            if (keys.size() != 0){
                this.sqlBuffer.append(this.and);
            }else {
                sqlBuffer.append(tableName + " where ");
            }
            this.like(seekList.get(0), seekList.get(1));
        }

        if (!isSeek && keys.size() == 0){
            return "select * from " + tableName;
        }

        return this.sqlBuffer.toString();
    }

    public void like(String key, String value){
        sqlBuffer.append(key +" like '%" + value + "%'");
    }
}

