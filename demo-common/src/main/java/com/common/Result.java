package com.common;

import com.enums.SysConstant;

public class Result {
    public Result() {
    }

    private static <T> ResultMessage<T> error(String code, String errorMessage, T data) {
        return new ResultMessage(code, errorMessage, data);
    }


    public static <T> ResultMessage<T> error(String code, T data) {
        return error(code, data);
    }

    public static <T> ResultMessage<T> error(String code, String errorMessage) {
        return error(SysConstant.FAIL_CODE, errorMessage,null);
    }


    public static <T> ResultMessage<T> success() {
        return new ResultMessage(SysConstant.SUCCESS_CODE,"");
    }
     public static <T> ResultMessage<T> success(T t) {
        return new ResultMessage(SysConstant.SUCCESS_CODE,"",t);
    }

    public static <T> ResultMessage<T> success(String message,T t) {
        return new ResultMessage(SysConstant.SUCCESS_CODE, message,  t);
    }

    public static ResultMessage error() {
        return error("System_error");
    }

    public static ResultMessage error(String message) {
        return error(SysConstant.FAIL_CODE, message);
    }
    public static ResultMessage success(String message) {
        return new ResultMessage(SysConstant.SUCCESS_CODE, message);
    }


}
