package com.example.demo.dao.repository;

import com.example.demo.dao.entity.UserInfo;
import com.example.demo.dao.entity.UserInfoExample;
import lombok.Data;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoRepository {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_INFO
     *
     * @mbg.generated Tue Jan 21 13:16:13 GMT+08:00 2020
     */
    long countByExample(UserInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_INFO
     *
     * @mbg.generated Tue Jan 21 13:16:13 GMT+08:00 2020
     */
    int deleteByExample(UserInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_INFO
     *
     * @mbg.generated Tue Jan 21 13:16:13 GMT+08:00 2020
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_INFO
     *
     * @mbg.generated Tue Jan 21 13:16:13 GMT+08:00 2020
     */
    int insert(UserInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_INFO
     *
     * @mbg.generated Tue Jan 21 13:16:13 GMT+08:00 2020
     */
    int insertSelective(UserInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_INFO
     *
     * @mbg.generated Tue Jan 21 13:16:13 GMT+08:00 2020
     */
    List<UserInfo> selectByExample(UserInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_INFO
     *
     * @mbg.generated Tue Jan 21 13:16:13 GMT+08:00 2020
     */
    UserInfo selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_INFO
     *
     * @mbg.generated Tue Jan 21 13:16:13 GMT+08:00 2020
     */
    int updateByExampleSelective(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_INFO
     *
     * @mbg.generated Tue Jan 21 13:16:13 GMT+08:00 2020
     */
    int updateByExample(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_INFO
     *
     * @mbg.generated Tue Jan 21 13:16:13 GMT+08:00 2020
     */
    int updateByPrimaryKeySelective(UserInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER_INFO
     *
     * @mbg.generated Tue Jan 21 13:16:13 GMT+08:00 2020
     */
    int updateByPrimaryKey(UserInfo record);

    /**
     * 登录校验
     * @param userName
     * @param password
     * @return
     */
    UserInfo checkUser(@Param("username") String userName, @Param("password") String password);

    /**
     * 注册校验
     * @param userName
     * @return
     */
    String checkName(String userName);

    /**
     * 查询最后一个用户编号
     * @param code
     * @return
     */
    String getUserNoCode(String code);
}