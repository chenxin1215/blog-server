package com.cx.user.client.dto;

public class BaseResp<T> {

    private String rspMsg;
    private String rspCode;
    private T rspData;

    public String getRspMsg() {
        return rspMsg;
    }

    public void setRspMsg(String rspMsg) {
        this.rspMsg = rspMsg;
    }

    public String getRspCode() {
        return rspCode;
    }

    public void setRspCode(String rspCode) {
        this.rspCode = rspCode;
    }

    public T getRspData() {
        return rspData;
    }

    public void setRspData(T rspData) {
        this.rspData = rspData;
    }

    public BaseResp<T> success() {
        this.rspCode = "0000";
        this.rspMsg = "SUCCESS";
        return this;

    }

    public BaseResp<T> success(T rspData) {
        this.rspCode = "0000";
        this.rspMsg = "SUCCESS";
        this.rspData = rspData;
        return this;

    }

    public BaseResp<T> fail() {
        this.rspCode = "0001";
        this.rspMsg = "FAIL";
        this.rspData = null;
        return this;
    }

    public BaseResp<T> fail(T rspData) {
        this.rspCode = "0001";
        this.rspMsg = "FAIL";
        this.rspData = rspData;
        return this;
    }

    public BaseResp<T> fail(String rspMsg, String rspCode) {
        this.rspCode = rspCode;
        this.rspMsg = rspMsg;
        this.rspData = null;
        return this;
    }

    public BaseResp<T> fail(String rspMsg, String rspCode, T rspData) {
        this.rspCode = rspCode;
        this.rspMsg = rspMsg;
        this.rspData = rspData;
        return this;
    }

}
