package com.tidc.parttimemonarch.pojo;

/**
 * 兼职类型
 *
 * 寒假工
 * 暑假工
 *
 * @Author: 李家宝
 * @Date: 2018/10/12 14:32
 */
public class PartTimeType {
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
        return "PartTimeType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
