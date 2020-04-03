package com.util;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonUtil <T>{

    public static String ObjToStr(Object o){
        if (o != null){
            return  new Gson().toJson(o);
        }
        return "";
    }

    public static  <T> T strToObj(String json,Class<T> tClass){
        try {
            return  new Gson().fromJson(json,tClass);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            log.error("转换当前登录人信息错误");
        }
        return null;
    }
}
