package com.util;

import com.example.demo.dao.entity.UserInfo;
import com.exception.ServiceException;
import com.google.gson.Gson;
import org.apache.catalina.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisSystemException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
@Component
public class LoginCurrentInfoUtils {
    @Autowired
    RedisUtil redisUtil;

    public UserInfo getCurrentInfo(HttpServletRequest request){
        String userNo = request.getHeader("userNo");
        //当前userNo为空
        if (StringUtils.isEmpty(userNo)) {
            return null;
        }
        String userJson;
        try {
            userJson = String.valueOf(redisUtil.get(userNo));
        } catch (RedisSystemException e) {
            e.printStackTrace();
            throw new ServiceException("Redis访问超时,请重新操作");
        }
        //如果当前登录信息不存在
        if (StringUtils.isBlank(userJson) || StringUtils.equals("null",userJson)) {
            throw new ServiceException("登录失效,请重新登陆");
        }
        UserInfo user = JsonUtil.strToObj(userJson, UserInfo.class);
        return user;
    };
}
