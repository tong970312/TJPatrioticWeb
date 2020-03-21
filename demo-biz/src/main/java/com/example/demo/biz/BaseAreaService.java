package com.example.demo.biz;

import com.common.ResultMessage;
import com.example.demo.dto.BaseDetailReqDTO;

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
     * @param parentId
     * @return
     */
    ResultMessage getBaseList(Integer parentId);

    /**
     * 修改基地内容
     * @param baseDetailReqDTO
     * @return
     */
    ResultMessage modifyBase(BaseDetailReqDTO baseDetailReqDTO);
}
