package com.example.demo.dto;

import lombok.Data;

@Data
public class BaseDetailRepDTO {
    private String id;
    /**
     * 基地名称
     */
    private String baseName;
    /**
     * 基地介绍
     */
    private String baseIntroduction;
    /**
     * 基地图片地址
     */
    private String basePriUrl;
    /**
     * 基地位置
     */
    private String baseLocation;
    /**
     * 基地电话
     */
    private String telNo;
    /**
     * 邮编
     */
    private String zipCode;
    /**
     * 传真号码
     */
    private String fixCode;
    /**
     * 官网网址
     */
    private String website;
    /**
     * 开放时间
     */
    private String openTime;
    /**
     * 公共交通
     */
    private String publicTransportation;
}
