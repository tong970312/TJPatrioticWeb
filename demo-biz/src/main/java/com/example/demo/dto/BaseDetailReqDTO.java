package com.example.demo.dto;

import lombok.Data;

/**
 * 基地详情请求dto
 */
@Data
public class BaseDetailReqDTO {

    private Integer id;
    /**
     * 所属市区id
     */
    private String parentId;
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
