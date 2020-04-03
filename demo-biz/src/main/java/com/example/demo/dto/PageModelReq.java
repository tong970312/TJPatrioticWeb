package com.example.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageModelReq <T> {
    private Long total;

    private List<T> data;
}
