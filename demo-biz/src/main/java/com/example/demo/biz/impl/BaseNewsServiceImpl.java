package com.example.demo.biz.impl;

import com.common.Result;
import com.common.ResultMessage;
import com.example.demo.biz.BaseNewsService;
import com.example.demo.dao.entity.BaseNews;
import com.example.demo.dao.entity.BaseNewsExample;
import com.example.demo.dao.entity.UserInfo;
import com.example.demo.dao.repository.BaseNewsRepository;
import com.example.demo.dto.*;
import com.util.BeanMapperUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BaseNewsServiceImpl implements BaseNewsService {

    protected static final Logger logger = LoggerFactory.getLogger(BaseAreaServiceImpl.class);

    @Autowired
    BaseNewsRepository baseNewsRepository;
    /**
     * 查询基地的资讯
     * @param baseNewsReqDTO
     * @return
     */
    @Override
    public ResultMessage getAllNews(BaseNewsReqDTO baseNewsReqDTO) {

        BaseNewsExample baseNewsExample = new BaseNewsExample();
        baseNewsExample.createCriteria().andDelFlagEqualTo(0);
        Integer limit = baseNewsReqDTO.getPageSize();
        //未设置默认每页10条
        if (limit == null) limit = 10;
        Integer currentPage = baseNewsReqDTO.getPageNum();
        //未设置默认从第一页查询
        if (currentPage == null) currentPage = 1;
        Integer offset = (currentPage - 1) * limit;
        baseNewsExample.setLimit(limit);
        baseNewsExample.setOffset(offset);
        List<BaseNews> list = baseNewsRepository.selectByExample(baseNewsExample);
        Long count = baseNewsRepository.countByExample(baseNewsExample);
        PageModelReq pageModelReq = new PageModelReq();
        if (list != null && !list.isEmpty()) {
            List<BaseNewsResVO> baseNewsResVOList = new ArrayList<>();
            try {
                baseNewsResVOList = BeanMapperUtils.mapList(list,BaseNewsResVO.class);
            } catch (Exception e) {
                logger.error("对象转换失败");
                return null;
            }
            pageModelReq.setData(baseNewsResVOList);
            pageModelReq.setTotal(count);
            return Result.success("成功",pageModelReq);
        }
        return Result.error("获取基地资讯失败");
    }

    /**
     * 资讯新增
     * @param baseNewsReqDTO
     * @param userInfo
     * @return
     */
    @Transactional
    @Override
    public ResultMessage insertNews(BaseNewsResVO baseNewsReqDTO, UserInfo userInfo) {
        if (baseNewsReqDTO.getParentId() == null) {
            logger.error("所属区县为空");
            return Result.error("请选择所属区县");
        }
        if (StringUtils.isEmpty(baseNewsReqDTO.getTitle())) {
            logger.error("资讯标题为空");
            return Result.error("资讯标题不能为空");
        }
        if (StringUtils.isEmpty(baseNewsReqDTO.getContent())) {
            logger.error("资讯内容为空");
            return Result.error("资讯内容不能为空");
        }
        String checkName = baseNewsRepository.checkName(baseNewsReqDTO.getTitle());
        if (StringUtils.isNotEmpty(checkName)) {
            return Result.error("当前标题已存在");
        }
        BaseNews baseNews = BeanMapperUtils.map(baseNewsReqDTO,BaseNews.class);
        baseNews.setCreateDate(new Date());
        baseNews.setCreateUid(userInfo.getUserName()+"/"+userInfo.getUserNo());
        baseNews.setUpdateDate(new Date());
        baseNews.setDelFlag(0);
        try {
            baseNewsRepository.insertSelective(baseNews);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("新增资讯内容失败");
            return Result.error("新增资讯内容失败");
        }
        return Result.success("新增资讯内容成功");
    }

    /**
     * 资讯修改
     * @param baseNewsReqDTO
     * @param userInfo
     * @return
     */
    @Override
    @Transactional
    public ResultMessage modifyNews(BaseNewsResVO baseNewsReqDTO, UserInfo userInfo) {
        if (baseNewsReqDTO.getId() == null) {
            logger.error("当前id为空");
            return Result.error("更新参数错误");
        }
        if (baseNewsReqDTO.getParentId() == null) {
            logger.error("所属区县为空");
            return Result.error("请选择所属区县");
        }
        if (StringUtils.isEmpty(baseNewsReqDTO.getTitle())) {
            logger.error("资讯标题为空");
            return Result.error("资讯标题不能为空");
        }
        if (StringUtils.isEmpty(baseNewsReqDTO.getContent())) {
            logger.error("资讯内容为空");
            return Result.error("资讯内容不能为空");
        }
        String checkName = baseNewsRepository.checkName2(baseNewsReqDTO.getTitle(),baseNewsReqDTO.getId());
        if (StringUtils.isNotEmpty(checkName)) {
            return Result.error("当前标题已存在");
        }
        BaseNews baseNews = BeanMapperUtils.map(baseNewsReqDTO,BaseNews.class);
        baseNews.setUpdateUid(userInfo.getUserName()+"/"+userInfo.getUserNo());
        baseNews.setUpdateDate(new Date());
        try {
            baseNewsRepository.updateByPrimaryKey(baseNews);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("修改资讯内容失败");
            return Result.error("修改资讯内容失败");
        }
        return Result.success("修改资讯内容成功");
    }

    /**
     * 资讯删除
     * @param id
     * @return
     */
    @Override
    @Transactional
    public ResultMessage delNews(String id) {
        if (StringUtils.isEmpty(id)) {
            logger.error("删除参数为空");
            return Result.error("删除参数为空");
        }
       Integer del = baseNewsRepository.updateByKey(id);
        if (del <= 0) {
            return Result.error("删除失败");
        }
        return  Result.success("删除成功");
    }

    @Override
    public ResultMessage getNewsDetail(String id) {
        if (StringUtils.isEmpty(id)) {
            logger.error("查询参数为空");
            return Result.error("查询参数为空");
        }
        BaseNews baseNews = baseNewsRepository.selectByPrimaryKey(Integer.valueOf(id));
        if (baseNews == null) {
            return Result.error("查询失败");
        }
        return  Result.success(baseNews);
    }

//    @Override
//    public ResultMessage test() {
//        //初始化
//        RestTemplate restTemplate = new RestTemplate();
//        LoginInfoReqDTO loginInfoReqDTO = new LoginInfoReqDTO();
//        loginInfoReqDTO.setPassword("123456");
//        loginInfoReqDTO.setUserName("李通");
//        RestTemplateTestVo restTemplateTestVo =restTemplate.postForObject("http://123.57.133.58:8318/admin/login",loginInfoReqDTO,RestTemplateTestVo.class);
//        return Result.success(restTemplateTestVo);
//    }
}
