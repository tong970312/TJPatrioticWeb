package com.example.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageModelReq <T> {

    private Integer pageNum;

    private Long total;

    private T data;
}
