package com.example.demo.web.controller;

import com.example.demo.dto.PageParam;
import com.common.ResultMessage;
import com.controller.BaseController;
import com.example.demo.biz.LeaveMsgService;
import com.example.demo.dao.entity.UserInfo;
import com.example.demo.dto.LeaveMsgPageVo;
import com.example.demo.dto.LeaveMsgReqVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/leaveMsg")
public class LeaveMsgController extends BaseController {
    @Autowired
    LeaveMsgService leaveMsgService;

    /**
     * 留言
     * @param request
     * @return
     */
    @PostMapping(value = "/communication")
    public ResultMessage communication(@RequestBody LeaveMsgReqVO leaveMsgReqVO, HttpServletRequest request){
        UserInfo userInfo = getLoginUser(request);
        return leaveMsgService.communication(leaveMsgReqVO,userInfo);
    }

    /**
     * 查看留言 用户、管理员都可
     * @return
     */
    @PostMapping(value = "/getMsg")
    public ResultMessage getMsg(@RequestBody PageParam<LeaveMsgPageVo> page){
        return leaveMsgService.getMsg(page);
    }

    /**
     * 管理员
     * @return
     */
    @PostMapping(value = "/getAdminMsg")
    public ResultMessage getAdminMsg(@RequestBody PageParam<LeaveMsgPageVo> page){
        return leaveMsgService.getAdminMsg(page);
    }


}
