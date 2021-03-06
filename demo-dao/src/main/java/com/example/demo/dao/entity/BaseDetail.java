package com.example.demo.dao.entity;

import java.util.Date;

public class BaseDetail {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BASE_DETAIL.ID
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BASE_DETAIL.PARENT_ID
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    private String parentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BASE_DETAIL.BASE_NAME
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    private String baseName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BASE_DETAIL.BASE_INTRODUCTION
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    private String baseIntroduction;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BASE_DETAIL.BASE_PRI_URL
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    private String basePriUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BASE_DETAIL.BASE_LOCATION
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    private String baseLocation;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BASE_DETAIL.TEL_NO
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    private String telNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BASE_DETAIL.ZIP_CODE
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    private String zipCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BASE_DETAIL.FIX_CODE
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    private String fixCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BASE_DETAIL.WEBSITE
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    private String website;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BASE_DETAIL.OPEN_TIME
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    private String openTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BASE_DETAIL.PUBLIC_TRANSPORTATION
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    private String publicTransportation;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BASE_DETAIL.OTHER_INFO1
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    private String otherInfo1;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BASE_DETAIL.OTHER_INFO2
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    private String otherInfo2;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BASE_DETAIL.OTHER_INFO3
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    private String otherInfo3;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BASE_DETAIL.CREATE_DATE
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    private Date createDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BASE_DETAIL.CREATE_UID
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    private String createUid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BASE_DETAIL.UPDATE_DATE
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    private Date updateDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BASE_DETAIL.UPDATE_UID
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    private String updateUid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column BASE_DETAIL.DEL_FLAG
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    private Integer delFlag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BASE_DETAIL.ID
     *
     * @return the value of BASE_DETAIL.ID
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BASE_DETAIL.ID
     *
     * @param id the value for BASE_DETAIL.ID
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BASE_DETAIL.PARENT_ID
     *
     * @return the value of BASE_DETAIL.PARENT_ID
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BASE_DETAIL.PARENT_ID
     *
     * @param parentId the value for BASE_DETAIL.PARENT_ID
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BASE_DETAIL.BASE_NAME
     *
     * @return the value of BASE_DETAIL.BASE_NAME
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public String getBaseName() {
        return baseName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BASE_DETAIL.BASE_NAME
     *
     * @param baseName the value for BASE_DETAIL.BASE_NAME
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public void setBaseName(String baseName) {
        this.baseName = baseName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BASE_DETAIL.BASE_INTRODUCTION
     *
     * @return the value of BASE_DETAIL.BASE_INTRODUCTION
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public String getBaseIntroduction() {
        return baseIntroduction;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BASE_DETAIL.BASE_INTRODUCTION
     *
     * @param baseIntroduction the value for BASE_DETAIL.BASE_INTRODUCTION
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public void setBaseIntroduction(String baseIntroduction) {
        this.baseIntroduction = baseIntroduction;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BASE_DETAIL.BASE_PRI_URL
     *
     * @return the value of BASE_DETAIL.BASE_PRI_URL
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public String getBasePriUrl() {
        return basePriUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BASE_DETAIL.BASE_PRI_URL
     *
     * @param basePriUrl the value for BASE_DETAIL.BASE_PRI_URL
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public void setBasePriUrl(String basePriUrl) {
        this.basePriUrl = basePriUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BASE_DETAIL.BASE_LOCATION
     *
     * @return the value of BASE_DETAIL.BASE_LOCATION
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public String getBaseLocation() {
        return baseLocation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BASE_DETAIL.BASE_LOCATION
     *
     * @param baseLocation the value for BASE_DETAIL.BASE_LOCATION
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public void setBaseLocation(String baseLocation) {
        this.baseLocation = baseLocation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BASE_DETAIL.TEL_NO
     *
     * @return the value of BASE_DETAIL.TEL_NO
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public String getTelNo() {
        return telNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BASE_DETAIL.TEL_NO
     *
     * @param telNo the value for BASE_DETAIL.TEL_NO
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BASE_DETAIL.ZIP_CODE
     *
     * @return the value of BASE_DETAIL.ZIP_CODE
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BASE_DETAIL.ZIP_CODE
     *
     * @param zipCode the value for BASE_DETAIL.ZIP_CODE
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BASE_DETAIL.FIX_CODE
     *
     * @return the value of BASE_DETAIL.FIX_CODE
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public String getFixCode() {
        return fixCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BASE_DETAIL.FIX_CODE
     *
     * @param fixCode the value for BASE_DETAIL.FIX_CODE
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public void setFixCode(String fixCode) {
        this.fixCode = fixCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BASE_DETAIL.WEBSITE
     *
     * @return the value of BASE_DETAIL.WEBSITE
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public String getWebsite() {
        return website;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BASE_DETAIL.WEBSITE
     *
     * @param website the value for BASE_DETAIL.WEBSITE
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BASE_DETAIL.OPEN_TIME
     *
     * @return the value of BASE_DETAIL.OPEN_TIME
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public String getOpenTime() {
        return openTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BASE_DETAIL.OPEN_TIME
     *
     * @param openTime the value for BASE_DETAIL.OPEN_TIME
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BASE_DETAIL.PUBLIC_TRANSPORTATION
     *
     * @return the value of BASE_DETAIL.PUBLIC_TRANSPORTATION
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public String getPublicTransportation() {
        return publicTransportation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BASE_DETAIL.PUBLIC_TRANSPORTATION
     *
     * @param publicTransportation the value for BASE_DETAIL.PUBLIC_TRANSPORTATION
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public void setPublicTransportation(String publicTransportation) {
        this.publicTransportation = publicTransportation;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BASE_DETAIL.OTHER_INFO1
     *
     * @return the value of BASE_DETAIL.OTHER_INFO1
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public String getOtherInfo1() {
        return otherInfo1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BASE_DETAIL.OTHER_INFO1
     *
     * @param otherInfo1 the value for BASE_DETAIL.OTHER_INFO1
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public void setOtherInfo1(String otherInfo1) {
        this.otherInfo1 = otherInfo1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BASE_DETAIL.OTHER_INFO2
     *
     * @return the value of BASE_DETAIL.OTHER_INFO2
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public String getOtherInfo2() {
        return otherInfo2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BASE_DETAIL.OTHER_INFO2
     *
     * @param otherInfo2 the value for BASE_DETAIL.OTHER_INFO2
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public void setOtherInfo2(String otherInfo2) {
        this.otherInfo2 = otherInfo2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BASE_DETAIL.OTHER_INFO3
     *
     * @return the value of BASE_DETAIL.OTHER_INFO3
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public String getOtherInfo3() {
        return otherInfo3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BASE_DETAIL.OTHER_INFO3
     *
     * @param otherInfo3 the value for BASE_DETAIL.OTHER_INFO3
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public void setOtherInfo3(String otherInfo3) {
        this.otherInfo3 = otherInfo3;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BASE_DETAIL.CREATE_DATE
     *
     * @return the value of BASE_DETAIL.CREATE_DATE
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BASE_DETAIL.CREATE_DATE
     *
     * @param createDate the value for BASE_DETAIL.CREATE_DATE
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BASE_DETAIL.CREATE_UID
     *
     * @return the value of BASE_DETAIL.CREATE_UID
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public String getCreateUid() {
        return createUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BASE_DETAIL.CREATE_UID
     *
     * @param createUid the value for BASE_DETAIL.CREATE_UID
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public void setCreateUid(String createUid) {
        this.createUid = createUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BASE_DETAIL.UPDATE_DATE
     *
     * @return the value of BASE_DETAIL.UPDATE_DATE
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BASE_DETAIL.UPDATE_DATE
     *
     * @param updateDate the value for BASE_DETAIL.UPDATE_DATE
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BASE_DETAIL.UPDATE_UID
     *
     * @return the value of BASE_DETAIL.UPDATE_UID
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public String getUpdateUid() {
        return updateUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BASE_DETAIL.UPDATE_UID
     *
     * @param updateUid the value for BASE_DETAIL.UPDATE_UID
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public void setUpdateUid(String updateUid) {
        this.updateUid = updateUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column BASE_DETAIL.DEL_FLAG
     *
     * @return the value of BASE_DETAIL.DEL_FLAG
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column BASE_DETAIL.DEL_FLAG
     *
     * @param delFlag the value for BASE_DETAIL.DEL_FLAG
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}