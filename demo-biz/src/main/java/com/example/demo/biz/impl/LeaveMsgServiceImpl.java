package com.example.demo.biz.impl;

import com.example.demo.dao.entity.LeaveMessageExample;
import com.example.demo.dto.*;
import com.common.Result;
import com.common.ResultMessage;
import com.example.demo.biz.LeaveMsgService;
import com.example.demo.dao.entity.LeaveMessage;
import com.example.demo.dao.entity.UserInfo;
import com.example.demo.dao.repository.LeaveMessageRepository;
import com.exception.ServiceException;
import com.util.BeanMapperUtils;
import com.util.JsonUtil;
import com.util.UserInfoUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class LeaveMsgServiceImpl implements LeaveMsgService {
    /**
     * 日志信息
     */
    protected static final Logger logger = LoggerFactory.getLogger(LeaveMsgServiceImpl.class);
    @Autowired
    LeaveMessageRepository leaveMsgRepository;
    @Autowired
    UserInfoUtil userInfoUtil;
    /**
     * 留言
     * @param leaveMsgReqVO
     * @param userInfo
     * @return
     */
    @Override
    @Transactional
    public ResultMessage communication(LeaveMsgReqVO leaveMsgReqVO, UserInfo userInfo) {
        //如果parentId为空。当前为第一条留言，此时设置留言目标为管理员
        if (StringUtils.isBlank(leaveMsgReqVO.getWordMasterId())) {
            leaveMsgReqVO.setWordMasterId("JYJD202011111");
        }
        if (StringUtils.isBlank(leaveMsgReqVO.getWordAuthorId())) {
            return Result.error("留言作者不能为空");
        }
        if (StringUtils.isBlank(leaveMsgReqVO.getMsgContent())) {
            return Result.error("留言内容不能为空");
        }

        LeaveMessage leaveMessage = BeanMapperUtils.map(leaveMsgReqVO,LeaveMessage.class);
        leaveMessage.setCreateDate(new Date());
        leaveMessage.setCreateUid(userInfo.getUserNo());
        Integer result = leaveMsgRepository.insertSelective(leaveMessage);
        if (result <= 0) {
            throw new ServiceException("留言失败");
        }
        return Result.success();
    }

    /**
     * 查看留言
     * @return
     * @param page
     */
    @Override
    public ResultMessage getMsg(PageParam<LeaveMsgPageVo> page) {
        PageModelReq pageModelReq = new PageModelReq();
        if (page.getPageNum() <=0) page.setPageNum(1);
        if (page.getPageSize()<=0) page.setPageSize(5);
        page.setPageNum((page.getPageNum() - 1) * page.getPageSize());
        //临时存放父级留言集合
        //查找所有第一层留言,parentId为null
        LeaveMessageExample leaveMessageExample = new LeaveMessageExample();
        LeaveMessageExample.Criteria criteria =leaveMessageExample.createCriteria();
        criteria.andParentIdIsNull().andDelFlagEqualTo(0);
        leaveMessageExample.setLimit(page.getPageSize());
        leaveMessageExample.setOffset(page.getPageNum());
        //区县编码不为空
        if (StringUtils.isNotBlank(page.getData().getCityCode())){
            criteria.andCityCodeEqualTo(page.getData().getCityCode());
        }
        List<LeaveMessage> messageList = leaveMsgRepository.selectByExample(leaveMessageExample);
        Integer total = leaveMsgRepository.countByExample(leaveMessageExample);
        //非空限制父节点就可以
        if (CollectionUtils.isEmpty(messageList)) {
            return Result.error("当前还没有留言");
        }
        List<LeaveMsgResVO> parentMsg = BeanMapperUtils.mapList(messageList, LeaveMsgResVO.class);
        List<LeaveMsgResVO> allMsg = BeanMapperUtils.mapList(leaveMsgRepository.selectByExample(null),LeaveMsgResVO.class);
//        //根据所有第一层留言，递归
        Map<String,LeaveResultVO> resultMap = new HashMap<>();
        for (LeaveMsgResVO msg : parentMsg) {
            LeaveResultVO resultVO = new LeaveResultVO();
            List<LeaveMsgResVO> result = new ArrayList<>();
            List<LeaveMsgResVO> child = getChild2(msg,allMsg);
            result.add(msg);
            msg.setList(child);
            for (LeaveMsgResVO leaveMsg:result) {
                leaveMsg.setWordAuthorName(userInfoUtil.getUserInfoByNo(leaveMsg.getWordAuthorId()).getUserName());
                leaveMsg.setWordMasterName(userInfoUtil.getUserInfoByNo(leaveMsg.getWordMasterId()).getUserName());
            }
            resultVO.setCount(result.size()+child.size());
            resultVO.setResult(result);
           resultMap.put(result.get(0).getCityCode(),resultVO);
        }
        pageModelReq.setData(resultMap);
        pageModelReq.setTotal(Long.valueOf(total));
        return Result.success(pageModelReq);
    }

    //递归子回复方法
//    private void getChild(LeaveMsgResVO msg){
//        //查找当前留言层级的下一层
//         LeaveMsgResVO leaveMsgResVO = BeanMapperUtils.map(leaveMsgRepository.selectByParentId(msg.getId()),LeaveMsgResVO.class);
//         if (leaveMsgResVO == null) {
//             return;
//         }
//         msg.setChild(leaveMsgResVO);
//         getChild(leaveMsgResVO);
//    }
    private List<LeaveMsgResVO> getChild2(LeaveMsgResVO msg,List<LeaveMsgResVO> allMsg){
        List<LeaveMsgResVO> list = new ArrayList<>();
        for (LeaveMsgResVO leaveMsg : allMsg) {
            if (leaveMsg.getParentId() == msg.getId()) {
               list.add(leaveMsg);
            }
        }
        return list;
    }
}
