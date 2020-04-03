package com.example.demo.web.controller;

import com.common.ResultMessage;
import com.controller.BaseController;
import com.example.demo.biz.BaseAreaService;
import com.example.demo.dto.BaseListReqVO;
import com.example.demo.dto.BaseDetailReqDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/base")
public class BaseAreaController extends BaseController {
    @Autowired
    BaseAreaService baseAreaService;

    /**
     * 基地信息新增
     * @param baseDetailReqDTO
     * @return resultMessage
     */
    @PostMapping(value = "/insert")
    public ResultMessage insertBaseArea(@RequestBody BaseDetailReqDTO baseDetailReqDTO){
        return baseAreaService.insertBaseArea(baseDetailReqDTO);
    }

    /**
     * 获取基地详情
     * @param baseId
     * @return
     */
    @GetMapping(value = "/details")
    public ResultMessage getBaseDetails(Integer baseId){

        return baseAreaService.getBaseDetails(baseId);
    }

    /**
     * 获取市内基地列表
     * @param baseListReqVO
     * @return
     */
    @PostMapping(value = "/baseList")
    public ResultMessage getBaseList(@RequestBody BaseListReqVO baseListReqVO){
        return baseAreaService.getBaseList(baseListReqVO);
    }

    /**
     * 修改基地内容
     * @param baseDetailReqDTO
     * @return
     */
    @PostMapping(value = "/modifyBase")
    public ResultMessage modifyBase(@RequestBody BaseDetailReqDTO baseDetailReqDTO){

        return baseAreaService.modifyBase(baseDetailReqDTO);
    }

    /**
     * 上传图片
     * @param file
     * @return
     */
    @PostMapping(value = "/upload")
    public ResultMessage upload(@RequestParam(value="file", required=false)MultipartFile file){
        return baseAreaService.upload(file);
    }




}
