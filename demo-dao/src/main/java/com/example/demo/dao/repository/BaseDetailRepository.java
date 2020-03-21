package com.example.demo.dao.repository;

import com.example.demo.dao.entity.BaseDetail;
import com.example.demo.dao.entity.BaseDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseDetailRepository {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BASE_DETAIL
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    long countByExample(BaseDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BASE_DETAIL
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    int deleteByExample(BaseDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BASE_DETAIL
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BASE_DETAIL
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    int insert(BaseDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BASE_DETAIL
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    int insertSelective(BaseDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BASE_DETAIL
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    List<BaseDetail> selectByExample(BaseDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BASE_DETAIL
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    BaseDetail selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BASE_DETAIL
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    int updateByExampleSelective(@Param("record") BaseDetail record, @Param("example") BaseDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BASE_DETAIL
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    int updateByExample(@Param("record") BaseDetail record, @Param("example") BaseDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BASE_DETAIL
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    int updateByPrimaryKeySelective(BaseDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table BASE_DETAIL
     *
     * @mbg.generated Sat Feb 22 19:47:29 GMT+08:00 2020
     */
    int updateByPrimaryKey(BaseDetail record);


    BaseDetail checkBaseDetail(String baseName);
}