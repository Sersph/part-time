package com.tidc.parttimemonarch.pojo;

/**
 * 兼职种类
 *
 * 传单派发
 * 打包分拣
 *
 * @Author: 李家宝
 * @Date: 2018/10/12 14:28
 */
public class PartTimeSpecies {

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
        return "PartTimeSpecies{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
