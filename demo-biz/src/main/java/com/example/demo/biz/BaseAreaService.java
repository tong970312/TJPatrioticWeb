package com.example.demo.biz;

import com.common.ResultMessage;
import com.example.demo.dto.BaseListReqVO;
import com.example.demo.dto.BaseDetailReqDTO;
import org.springframework.web.multipart.MultipartFile;

public interface BaseAreaService {
    /**
     * 基地信息新增
     * @param baseDetailReqDTO
     * @return resultMessage
     */
    ResultMessage insertBaseArea(BaseDetailReqDTO baseDetailReqDTO);
    /**
     * 获取基地详情
     * @param baseId
     * @return
     */
    ResultMessage getBaseDetails(Integer baseId);
    /**
     * 获取市内基地列表
     * @param baseListReqVO
     * @return
     */
    ResultMessage getBaseList(BaseListReqVO baseListReqVO);

    /**
     * 修改基地内容
     * @param baseDetailReqDTO
     * @return
     */
    ResultMessage modifyBase(BaseDetailReqDTO baseDetailReqDTO);

    ResultMessage upload(MultipartFile file);
}
