package com.example.demo.dto;

import lombok.Data;

@Data
public class LeaveMsgPageVo {
    /**
     * 用户的id
     */
    private String wordAuthorId;
    /**
     * 基地对应的市区id
     */
    private String cityCode;
}
