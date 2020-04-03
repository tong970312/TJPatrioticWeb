package com.example.demo.biz;

import com.common.ResultMessage;
import com.example.demo.dao.entity.UserInfo;
import com.example.demo.dto.BaseNewsReqDTO;
import com.example.demo.dto.BaseNewsResVO;

public interface BaseNewsService {
    /**
     * 查询基地的资讯
     * @param baseNewsReqDTO
     * @return
     */
    ResultMessage getAllNews(BaseNewsReqDTO baseNewsReqDTO);

    /**
     * 资讯新增
     * @param baseNewsReqDTO
     * @param userInfo
     * @return
     */
    ResultMessage insertNews(BaseNewsResVO baseNewsReqDTO, UserInfo userInfo);

    /**
     * 修改资讯
     * @param baseNewsReqDTO
     * @param userInfo
     * @return
     */
    ResultMessage modifyNews(BaseNewsResVO baseNewsReqDTO, UserInfo userInfo);

    /**
     * 资讯删除
     * @param id
     * @return
     */
    ResultMessage delNews(String id);
}
