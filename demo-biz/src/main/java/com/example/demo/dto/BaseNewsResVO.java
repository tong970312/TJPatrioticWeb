package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class BaseNewsResVO {

    private Integer id;

    private Integer parentId;

    private String title;

    private String content;

    private Integer delFlag;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createDate;

}
