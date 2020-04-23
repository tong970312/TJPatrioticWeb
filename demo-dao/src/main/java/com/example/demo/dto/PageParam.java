package com.example.demo.dto;

import lombok.Data;

@Data
public class PageParam<T> {

    /**
     * 页码
     */
    private Integer pageNum;
    /**
     * 条数
     */
    private Integer pageSize;

    private Integer startIndex;
    /**
     * 参数
     */
    private T data;

    public PageParam() {
    }

    public PageParam(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        startIndex = (pageNum - 1) * pageSize;
    }

}
