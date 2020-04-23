package com.util;

import com.example.demo.dao.entity.UserInfo;
import com.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Slf4j
@Component
public class BaseUtil {

    @Autowired
    RedisUtil redisUtil;

    public String getBaseInfoByNo(String id){
        if (StringUtils.isEmpty(id)) {
            throw  new ServiceException("获取基地名称失败，参数错误");
        }
        Map<String, String> map = new HashMap<>();
        try {
            map = (Map<String, String>) redisUtil.get("baseInfos");
        } catch (Exception e) {
            log.error("获取基地名称失败");
            throw new ServiceException("获取基地名称失败，请联系管理员");
        }
        return map.get(id);
    }
}
