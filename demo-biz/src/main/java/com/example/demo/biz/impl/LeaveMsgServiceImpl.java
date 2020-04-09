package com.example.demo.biz.impl;

import com.example.demo.dto.PageParam;
import com.common.Result;
import com.common.ResultMessage;
import com.example.demo.biz.LeaveMsgService;
import com.example.demo.dao.entity.LeaveMessage;
import com.example.demo.dao.entity.UserInfo;
import com.example.demo.dao.repository.LeaveMessageRepository;
import com.example.demo.dto.LeaveMsgPageVo;
import com.example.demo.dto.LeaveMsgReqVO;
import com.example.demo.dto.LeaveMsgResVO;
import com.exception.ServiceException;
import com.util.BeanMapperUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class LeaveMsgServiceImpl implements LeaveMsgService {
    /**
     * 日志信息
     */
    protected static final Logger logger = LoggerFactory.getLogger(LeaveMsgServiceImpl.class);
    @Autowired
    LeaveMessageRepository leaveMsgRepository;
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
        if (leaveMsgReqVO.getParentId() == null) {
            leaveMsgReqVO.setWordMasterId("JYJD202011111");
        }else if (StringUtils.isBlank(leaveMsgReqVO.getWordMasterId())) {
            return Result.error("留言目标不能为空");
        }
        if (StringUtils.isBlank(leaveMsgReqVO.getWordAuthorId())) {
            return Result.error("留言作者不能为空");
        }
        if (StringUtils.isBlank(leaveMsgReqVO.getMsgContent())) {
            return Result.error("留言内容不能为空");
        }
        //当前层级是空,则为第一条,设置为默认值
        if (leaveMsgReqVO.getMsgLevel() == null) {
            leaveMsgReqVO.setMsgLevel(1);
        }else{
            //当前层级+1
            leaveMsgReqVO.setMsgLevel(leaveMsgReqVO.getMsgLevel() + 1);
        }
        LeaveMessage leaveMessage = BeanMapperUtils.map(leaveMsgReqVO,LeaveMessage.class);
        leaveMessage.setCreateDate(new Date());
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
        //递归查询留言
        //初始化响应结果集
//        List<LeaveMsgResVO> leaveMsgList = new ArrayList<>();
        page.setStartIndex((page.getPageNum() - 1) * page.getPageSize());
        System.out.println(page);
        List<LeaveMessage> leaveMsgList = leaveMsgRepository.selectByPage(page);  //获取团队列表总数
        int total = leaveMsgRepository.selectCount(page);

        return Result.success(String.valueOf(total),leaveMsgList);
//        //临时存放父级留言集合
//        //查找所有第一层留言,parentId为null
//        List<LeaveMsgResVO> parentMsg = null;
//        List<LeaveMsgResVO> allMsg = null;
//        try {
//            parentMsg = BeanMapperUtils.mapList(leaveMsgRepository.selectAllParent(), LeaveMsgResVO.class);
//            allMsg = BeanMapperUtils.mapList(leaveMsgRepository.selectByExample(null),LeaveMsgResVO.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new ServiceException("对象转换失败");
//        }
//        if (parentMsg.isEmpty()) {
//            return Result.error("当前留言为空");
//        }
//        //根据所有第一层留言，递归
//        for (LeaveMsgResVO msg : parentMsg) {
//               //getChild(msg);
//            getChild2(msg,allMsg);
//        }
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
//    private void getChild2(LeaveMsgResVO msg,List<LeaveMsgResVO> allMsg){
//        for (LeaveMsgResVO leaveMsg : allMsg) {
//            if (leaveMsg.getParentId() == msg.getId()) {
//                msg.setChild(leaveMsg);
//                getChild2(leaveMsg,allMsg);
//            }
//        }
//    }
}
