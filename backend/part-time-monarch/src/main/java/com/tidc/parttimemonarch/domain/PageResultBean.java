package com.tidc.parttimemonarch.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 李家宝
 * @Date: 2018/11/1 20:31
 */
@Data
public class PageResultBean<T> implements Serializable {

    /**
     * 序列化id
     */
    private static final long serialVersionUID = -6477868212171605239L;

    /**
     * 总记录数
     */
    private int total;

    /**
     * 结果集
     */
    private List<T> rows;

    /**
     * 页数
     */
    private int pageNumber;

    /**
     * 每页记录数
     */
    private int pageSize;

    /**
     * 总页数
     */
    private Integer pages;

    public PageResultBean<T> setPage(List<T> rows, int pageSize, int pageNum) {
        this.rows = rows;
        this.total = this.rows.size();
        this.pageSize = pageSize;
        this.pageNumber = pageNum;
        this.pages = this.total % this.pageSize == 0 ? this.total / this.pageSize : (this.total / this.pageSize) + 1;
        return this;
    }


    public List<T> getRows() {

        List<T> pageRows = new ArrayList<>();

        int start = (this.pageSize * this.pageNumber) - this.pageSize;
        int finish = (this.pageSize * this.pageNumber);
        if (this.pageSize > this.total){
            start = 0;
        }
        if (finish + 1 >= this.total){
            finish = this.total;
        }

        while (start < finish){
            pageRows.add(this.rows.get(start));
            start ++;
        }

        return pageRows;
    }
}












