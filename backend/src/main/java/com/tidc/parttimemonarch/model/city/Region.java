package com.tidc.parttimemonarch.model.city;

import java.util.List;

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

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public List<City> getChildren() {
        if (this.children.size() == 0){
            return null;
        }
        return children;
    }

    public void setChildren(List<City> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", children=" + children +
                '}';
    }
}
