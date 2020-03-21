package com.controller;

import com.example.demo.dao.entity.UserInfo;
import com.google.gson.Gson;
import com.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
    /**
     * 日志信息
     */
    protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    RedisUtil redisUtil;

    /**
     * 获取登录信息
     * @param request
     * @return
     */
    public final UserInfo getLoginUser(javax.servlet.http.HttpServletRequest request){
        String paramNo = request.getHeader("userNo");
        String userJson = String.valueOf(redisUtil.get(paramNo));
        UserInfo currentInfo = null;
        try {
            currentInfo = new Gson().fromJson(userJson, UserInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("转换对象失败");
        }
        return currentInfo;

    }
}
