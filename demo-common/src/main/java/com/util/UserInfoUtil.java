package com.util;

import com.example.demo.dao.entity.UserInfo;
import com.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Slf4j
@Component
public class UserInfoUtil {
    @Autowired
    RedisUtil redisUtil;
    @Value("${userInfo.key}")
    String userKey;
   public  UserInfo getUserInfoByNo(String userNo){
       if (StringUtils.isEmpty(userNo)) {
           throw  new ServiceException("获取用户信息失败，参数错误");
       }
       Map<String, UserInfo> map = new HashMap<>();
       try {
            map = (Map<String, UserInfo>) redisUtil.get(userKey);
       } catch (Exception e) {
            log.error("获取用户数据错误");
            throw new ServiceException("获取用户数据错误，请联系管理员");
       }
        return map.get(userNo);
   }


}
