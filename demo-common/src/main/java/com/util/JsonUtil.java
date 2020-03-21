package com.util;

import com.google.gson.Gson;

public class JsonUtil <T>{

    public static String ObjToStr(Object o){
        if (o != null){
            return  new Gson().toJson(o);
        }
        return "";
    }
}
