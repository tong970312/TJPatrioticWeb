package com.example.demo.biz.impl;

import com.common.Result;
import com.common.ResultMessage;
import com.example.demo.biz.BaseAreaService;
import com.example.demo.dao.entity.BaseDetail;
import com.example.demo.dao.entity.BaseDetailExample;
import com.example.demo.dto.BaseListReqVO;
import com.example.demo.dto.PageModelReq;
import com.example.demo.dao.repository.BaseDetailRepository;
import com.example.demo.dto.BaseDetailReqDTO;
import com.util.BeanMapperUtils;
import com.util.FTPUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class BaseAreaServiceImpl implements BaseAreaService {

    /**
     * 日志信息
     */
    protected static final Logger logger = LoggerFactory.getLogger(BaseAreaServiceImpl.class);
    @Autowired
    BaseDetailRepository baseDetailRepository;
    @Autowired
    FTPUtils ftpUtils;
    @Value("${image.url}")
    String imageUrl;
    @Value("${local.url}")
    String localUrl;

    /**
     * 基地信息新增
     * @param baseDetailReqDTO
     * @return resultMessage
     */
    @Override
    @Transactional
    public ResultMessage insertBaseArea(BaseDetailReqDTO baseDetailReqDTO) {
        if (StringUtils.isEmpty(baseDetailReqDTO.getBaseName())){
            logger.error("基地名称为空");
            return Result.error("基地名称为空");
        }
        if (StringUtils.isEmpty(baseDetailReqDTO.getBaseIntroduction())){
            logger.error("基地介绍为空");
            return Result.error("基地介绍为空");
        }
        if (StringUtils.isEmpty(baseDetailReqDTO.getParentId())){
            logger.error("所属区县为空");
            return Result.error("所属区县为空");
        }

        if (StringUtils.isEmpty(baseDetailReqDTO.getBaseLocation())){
            logger.error("基地位置为空");
            return Result.error("基地位置为空");
        }
        if (StringUtils.isEmpty(baseDetailReqDTO.getOpenTime())){
            logger.error("基地开放时间为空");
            return Result.error("基地开放时间为空");
        }
        if (StringUtils.isEmpty(baseDetailReqDTO.getPublicTransportation())){
            logger.error("公共交通为空");
            return Result.error("公共交通为空");
        }
        //校验当前基地数据是否录入
        String check = baseDetailRepository.checkBaseDetail(baseDetailReqDTO.getBaseName());
        if (StringUtils.isNotEmpty(check)) {
            return Result.error("基地数据已存在，请勿重复录入");
        }
        BaseDetail baseDetail = BeanMapperUtils.map(baseDetailReqDTO,BaseDetail.class);
        baseDetail.setCreateDate(new Date());
        baseDetail.setUpdateDate(new Date());
        baseDetail.setDelFlag(0);
        try {
            baseDetailRepository.insertSelective(baseDetail);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("新增基地数据失败");
            return Result.error("新增基地数据失败");
        }

        return Result.success("新增基地成功");
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
            return Result.error("基地id为空，获取失败");
        }
        BaseDetail baseDetail = baseDetailRepository.selectByPrimaryKey(baseId);
        if (baseDetail == null) {
            return Result.error("查询结果为空");
        }
        BaseDetailReqDTO baseDetailReqDTO = BeanMapperUtils.map(baseDetail,BaseDetailReqDTO.class);

        return Result.success("成功",baseDetailReqDTO);
    }

    /**
     * 获取市内基地列表
     * @param baseListReqVO
     * @return
     */
    @Override
    public ResultMessage getBaseList(BaseListReqVO baseListReqVO) {
        if (baseListReqVO == null) {
            logger.error("获取列表失败，参数错误");
            return Result.error("获取列表失败，参数错误");
        }
        if (StringUtils.isBlank(baseListReqVO.getParentId())) {
            return Result.error("查询参数错误，请检查");
        }
        BaseDetailExample baseDetailExample = new BaseDetailExample();
        baseDetailExample.createCriteria().andParentIdEqualTo(baseListReqVO.getParentId());
        Integer limit = baseListReqVO.getPageSize();
        //未设置默认每页10条
        if (limit == null) limit = 10;
        Integer currentPage = baseListReqVO.getPageNum();
        //未设置默认从第一页查询
        if (currentPage == null) currentPage = 1;
        Integer offset = (currentPage - 1) * limit;
        baseDetailExample.setLimit(limit);
        baseDetailExample.setOffset(offset);
        List<BaseDetail> baseDetailList = baseDetailRepository.selectByExample(baseDetailExample);
        Long count = baseDetailRepository.countByExample(baseDetailExample);
        PageModelReq pageModelReq = new PageModelReq();
        if (baseDetailList != null && !baseDetailList.isEmpty()) {
            List<BaseDetailReqDTO> baseDetailReqDTOList = new ArrayList<>();
            try {
                 baseDetailReqDTOList = BeanMapperUtils.mapList(baseDetailList,BaseDetailReqDTO.class);
            } catch (Exception e) {
                logger.error("对象转换失败");
                return null;
            }
            pageModelReq.setData(baseDetailReqDTOList);
            pageModelReq.setTotal(count);
            return Result.success("成功",pageModelReq);
        }
        return Result.error("获取列表失败，市区数据为空");
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
            return Result.error("参数为空，修改失败");
        }
        if (baseDetailReqDTO.getId() == null) {
            logger.error("基地id为空，修改失败");
            return Result.error("请确认信息是否正确");
        }
        if (StringUtils.isEmpty(baseDetailReqDTO.getBaseName())) {
            logger.error("基地名称为空，修改失败");
            return Result.error("请确认基地名称是否满足条件");
        }
        if (StringUtils.isEmpty(baseDetailReqDTO.getBaseLocation())) {
            logger.error("基地介绍为空，修改失败");
            return Result.error("请确认基地介绍是否满足条件");
        }
        if (StringUtils.isEmpty(baseDetailReqDTO.getOpenTime())) {
            logger.error("基地开放为空，修改失败");
            return Result.error("请确认基地开放时间是否满足条件");
        }
        if (StringUtils.isEmpty(baseDetailReqDTO.getPublicTransportation())) {
            logger.error("公共交通为空，修改失败");
            return Result.error("请确认公共交通是否满足条件");
        }
        String checkName = baseDetailRepository.checkBaseDetail2(baseDetailReqDTO.getBaseName(),baseDetailReqDTO.getId());
        if (!StringUtils.isEmpty(checkName)) {
            return Result.error("基地名称重，请更改");
        }
        BaseDetail baseDetail = null;
        try {
             baseDetail = BeanMapperUtils.map(baseDetailReqDTO,BaseDetail.class);
             baseDetailRepository.updateByPrimaryKey(baseDetail);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("修改基地详情失败");
        }
        return Result.success("修改基地详情成功");
    }

    /**
     *
     * @return
     */
    @Override
    public ResultMessage upload(MultipartFile file) {
        //获取图片名称
        String originalFileName = file.getOriginalFilename();
        //获取扩展名
        String extensionName = originalFileName.substring(originalFileName.lastIndexOf("."));
        //生成唯一名字
        String newName = UUID.randomUUID().toString() + extensionName;
        File pathfile = new File(localUrl);
        //如果不存在
        if (!pathfile.exists()) {
            //设置可写
            pathfile.setWritable(true);
            pathfile.mkdir();
        }
        File file1 = new File(localUrl, newName);
        try {
            file.transferTo(file1);
            Map<String, String> map = new HashMap<>();
            map.put("URL", imageUrl + newName);
            return Result.success(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
