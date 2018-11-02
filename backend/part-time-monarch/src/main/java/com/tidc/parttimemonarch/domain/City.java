package com.tidc.parttimemonarch.domain;

import lombok.Data;

@Data
public class City {

    private int id;

    private String name;

    private int parentId;
}
