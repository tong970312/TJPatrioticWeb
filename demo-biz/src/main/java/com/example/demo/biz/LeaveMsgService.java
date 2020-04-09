package com.example.demo.biz;

import com.example.demo.dto.PageParam;
import com.common.ResultMessage;
import com.example.demo.dao.entity.UserInfo;
import com.example.demo.dto.LeaveMsgPageVo;
import com.example.demo.dto.LeaveMsgReqVO;

public interface LeaveMsgService {
    /**
     * 留言
     * @param leaveMsgReqVO
     * @param userInfo
     * @return
     */
    ResultMessage communication(LeaveMsgReqVO leaveMsgReqVO, UserInfo userInfo);

    /**
     * 查看留言
     * @return
     * @param page
     */
    ResultMessage getMsg(PageParam<LeaveMsgPageVo> page);
}
