package com.tidc.parttimemonarch.domain;

import lombok.Data;

import java.util.List;

@Data
public class Region {

    private int id;

    private String name;

    private int parentId;

    private List children;

    public Region() {
    }

    public Region(City city){
        this.id = city.getId();
        this.name = city.getName();
        this.parentId = city.getParentId();
    }

    public Region(City city, List children) {
        this.id = city.getId();
        this.name = city.getName();
        this.parentId = city.getParentId();
        this.children = children;
    }

    public List<City> getChildren() {
        if (this.children.size() == 0){
            return null;
        }
        return children;
    }
}
