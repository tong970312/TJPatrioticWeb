package com.example.demo.biz.impl;

import com.common.ResultMessage;
import com.enums.SysConstant;
import com.example.demo.biz.BaseAreaService;
import com.example.demo.dao.entity.BaseDetail;
import com.example.demo.dao.entity.BaseDetailExample;
import com.example.demo.dao.repository.BaseDetailRepository;
import com.example.demo.dto.BaseDetailReqDTO;
import com.util.BeanMapperUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BaseAreaServiceImpl implements BaseAreaService {

    /**
     * 日志信息
     */
    protected static final Logger logger = LoggerFactory.getLogger(BaseAreaServiceImpl.class);
    @Autowired
    BaseDetailRepository baseDetailRepository;

    /**
     * 基地信息新增
     * @param baseDetailReqDTO
     * @return resultMessage
     */
    @Override
    public ResultMessage insertBaseArea(BaseDetailReqDTO baseDetailReqDTO) {
        if (StringUtils.isEmpty(baseDetailReqDTO.getBaseName())){
            logger.error("基地名称为空");
            return new ResultMessage(SysConstant.FAIL_CODE,"基地名称为空");
        }
        if (StringUtils.isEmpty(baseDetailReqDTO.getBaseIntroduction())){
            logger.error("基地介绍为空");
            return new ResultMessage(SysConstant.FAIL_CODE,"基地介绍为空");
        }
        if (StringUtils.isEmpty(baseDetailReqDTO.getBaseLocation())){
            logger.error("基地位置为空");
            return new ResultMessage(SysConstant.FAIL_CODE,"基地位置为空");
        }
        if (StringUtils.isEmpty(baseDetailReqDTO.getZipCode())){
            logger.error("传真号码为空");
            return new ResultMessage(SysConstant.FAIL_CODE,"传真号码为空");
        }
        if (StringUtils.isEmpty(baseDetailReqDTO.getOpenTime())){
            logger.error("基地开放时间为空");
            return new ResultMessage(SysConstant.FAIL_CODE,"基地开放时间为空");
        }
        if (StringUtils.isEmpty(baseDetailReqDTO.getPublicTransportation())){
            logger.error("公共交通为空");
            return new ResultMessage(SysConstant.FAIL_CODE,"公共交通为空");
        }
        //校验当前基地数据是否录入
        BaseDetail checkBean = baseDetailRepository.checkBaseDetail(baseDetailReqDTO.getBaseName());
        if (checkBean != null) {
            return new ResultMessage(SysConstant.FAIL_CODE,"基地数据已存在，请勿重复录入");
        }
        BaseDetail baseDetail = BeanMapperUtils.map(baseDetailReqDTO,BaseDetail.class);
        baseDetail.setCreateDate(new Date());
        baseDetail.setUpdateDate(new Date());
        baseDetail.setDelFlag(0);
        try {
            baseDetailRepository.insertSelective(baseDetail);
        } catch (Exception e) {
            logger.error("新增基地数据失败");
            return new ResultMessage(SysConstant.FAIL_CODE,SysConstant.FAIL_MESSAGE);
        }
        return new ResultMessage(SysConstant.SUCCESS_CODE,SysConstant.SUCCESS_MESSAGE);
    }

    /**
     * 获取基地详情
     * @param baseId
     * @return
     */
    @Override
    public ResultMessage getBaseDetails(Integer baseId) {
        if (baseId == null) {
            logger.error("基地id为空，获取失败");
            return new ResultMessage(SysConstant.FAIL_CODE,"基地id为空，获取失败");
        }
        BaseDetail baseDetail = baseDetailRepository.selectByPrimaryKey(baseId);
        if (baseDetail == null) {
            return new ResultMessage(SysConstant.FAIL_CODE,"查询结果为空");
        }
        BaseDetailReqDTO baseDetailReqDTO = BeanMapperUtils.map(baseDetail,BaseDetailReqDTO.class);

        return new ResultMessage(SysConstant.SUCCESS_CODE,"成功",baseDetailReqDTO);
    }

    /**
     * 获取市内基地列表
     * @param parentId
     * @return
     */
    @Override
    public ResultMessage getBaseList(Integer parentId) {
        if (parentId == null) {
            logger.error("获取列表失败，参数错误");
            return new ResultMessage(SysConstant.FAIL_CODE,"获取列表失败，参数错误");
        }
        BaseDetailExample baseDetailExample = new BaseDetailExample();
        baseDetailExample.createCriteria().andParentIdEqualTo(String.valueOf(parentId));
        List<BaseDetail> baseDetailList = baseDetailRepository.selectByExample(baseDetailExample);
        if (baseDetailList != null && !baseDetailList.isEmpty()) {
            List<BaseDetailReqDTO> baseDetailReqDTOList = new ArrayList<>();
            try {
                 baseDetailReqDTOList = BeanMapperUtils.mapList(baseDetailList,BaseDetailReqDTO.class);
            } catch (Exception e) {
                logger.error("对象转换失败");
                return null;
            }
            return new ResultMessage(SysConstant.SUCCESS_CODE,"成功",baseDetailReqDTOList);
        }
        return new ResultMessage(SysConstant.FAIL_CODE,"获取列表失败，市区数据为空");
    }

    /**
     * 修改基地内容
     * @param baseDetailReqDTO
     * @return
     */
    @Override
    public ResultMessage modifyBase(BaseDetailReqDTO baseDetailReqDTO) {
        if (baseDetailReqDTO == null) {
            logger.error("参数为空，修改失败");
            return new ResultMessage(SysConstant.FAIL_CODE,"参数为空，修改失败");
        }
        if (baseDetailReqDTO.getId() == null) {
            logger.error("基地id为空，修改失败");
            return new ResultMessage(SysConstant.FAIL_CODE,"请确认信息是否正确");
        }
        if (StringUtils.isEmpty(baseDetailReqDTO.getBaseName())) {
            logger.error("基地名称为空，修改失败");
            return new ResultMessage(SysConstant.FAIL_CODE,"请确认基地名称是否满足条件");
        }
        if (StringUtils.isEmpty(baseDetailReqDTO.getBaseLocation())) {
            logger.error("基地介绍为空，修改失败");
            return new ResultMessage(SysConstant.FAIL_CODE,"请确认基地介绍是否满足条件");
        }
        if (StringUtils.isEmpty(baseDetailReqDTO.getOpenTime())) {
            logger.error("基地开放为空，修改失败");
            return new ResultMessage(SysConstant.FAIL_CODE,"请确认基地开放时间是否满足条件");
        }
        if (StringUtils.isEmpty(baseDetailReqDTO.getPublicTransportation())) {
            logger.error("公共交通为空，修改失败");
            return new ResultMessage(SysConstant.FAIL_CODE,"请确认公共交通是否满足条件");
        }
        BaseDetail baseDetail = null;
        try {
             baseDetail = BeanMapperUtils.map(baseDetailReqDTO,BaseDetail.class);
             baseDetailRepository.updateByPrimaryKey(baseDetail);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(SysConstant.FAIL_CODE,"修改基地详情失败");
        }
        return new ResultMessage(SysConstant.SUCCESS_CODE,"修改基地详情成功");
    }


}
