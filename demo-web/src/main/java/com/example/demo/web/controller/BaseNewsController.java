package com.example.demo.web.controller;

import com.common.Result;
import com.common.ResultMessage;
import com.example.demo.biz.BaseNewsService;
import com.example.demo.dao.entity.UserInfo;
import com.example.demo.dto.BaseNewsReqDTO;
import com.example.demo.dto.BaseNewsResVO;
import com.util.LoginCurrentInfoUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/baseNews")
public class BaseNewsController {
    @Autowired
    BaseNewsService baseNewsService;
    @Autowired
    LoginCurrentInfoUtils loginCurrentInfoUtils;
    /**
     * 查询基地的资讯
     * @param baseNewsReqDTO
     * @return
     */
    @PostMapping(value = "/getAllNews")
    public ResultMessage getAllNews(@RequestBody BaseNewsReqDTO baseNewsReqDTO){
      return baseNewsService.getAllNews(baseNewsReqDTO);
    }

    @PostMapping(value = "/insertNews")
    public ResultMessage insertNews(@RequestBody BaseNewsResVO baseNewsReqDTO, HttpServletRequest request){
        UserInfo userInfo = loginCurrentInfoUtils.getCurrentInfo(request);
      return baseNewsService.insertNews(baseNewsReqDTO,userInfo);
    }

    @PostMapping(value = "/modifyNews")
    public ResultMessage modifyNews(@RequestBody BaseNewsResVO baseNewsReqDTO, HttpServletRequest request){
        UserInfo userInfo = loginCurrentInfoUtils.getCurrentInfo(request);
      return baseNewsService.modifyNews(baseNewsReqDTO,userInfo);
    }

    @GetMapping(value = "/delNews")
    public ResultMessage delNews(@Param("id") String id){
      return baseNewsService.delNews(id);
    }

    @GetMapping(value = "/getNewsDetail")
    public ResultMessage getNewsDetail(@Param("id") Integer id){
      return baseNewsService.getNewsDetail(id);
    }


    @GetMapping(value = "/test")
    public ResultMessage test(){
      return Result.success("测试jenkins");
    }


}
