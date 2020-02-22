package com.example.demo.dao.entity;

import lombok.Data;

import java.util.Date;
@Data
public class BaseDetail {

    private String id;

    private String parentId;

    private String baseName;

    private String baseIntroduction;

    private String basePriUrl;

    private String baseLocation;

    private String telNo;

    private String zipCode;

    private String fixCode;

    private String website;

    private String openTime;

    private String publicTransportation;

}