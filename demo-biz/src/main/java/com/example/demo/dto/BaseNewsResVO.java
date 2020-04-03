package com.example.demo.dto;

import lombok.Data;

@Data
public class BaseNewsResVO {

    private Integer id;

    private Integer parentId;

    private String title;

    private String content;

    private Integer delFlag;

}
