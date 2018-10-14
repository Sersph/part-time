package com.tidc.parttimemonarch.pojo;

/**
 * 结算方式
 *
 * 日结
 *
 * 周结
 *
 * @Author: 李家宝
 * @Date: 2018/10/12 15:19
 */
public class SettlementType {
    private int id;
    private String name;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SettlementType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
