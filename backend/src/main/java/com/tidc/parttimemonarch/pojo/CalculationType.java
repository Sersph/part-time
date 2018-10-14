package com.tidc.parttimemonarch.pojo;

/**
 * 计算方式
 * 天
 * 件
 * 次
 *
 * @Author: 李家宝
 * @Date: 2018/10/12 15:20
 */
public class CalculationType {
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
        return "CalculationType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
