package com.common;

public class ResultMessage<T> {

    private String respCode;
    private String respMsg;
    private T data;


    public ResultMessage() {
    }

    public ResultMessage(String respCode, String message) {
        this.respCode = respCode;
        this.respMsg = message;
    }

    public ResultMessage(String respCode, String message,  T data) {
        this.respCode = respCode;
        this.respMsg = message;
        this.data = data;
    }

    public ResultMessage(String respCode) {
        this.respCode = respCode;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
