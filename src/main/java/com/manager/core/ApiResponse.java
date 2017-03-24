package com.manager.core;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * Created by shencx on 2017/3/24.
 */
public class ApiResponse <T> implements Serializable {
    private String message;
    private int status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public ApiResponse() {
        this.message = "";
        this.status = 0;
    }

    public static ApiResponse Create(int status,String message){
        ApiResponse resp = new ApiResponse();
        resp.setStatus(status);
        resp.setMessage(message);
        return resp;
    }

    public static ApiResponse Create(Object data){
        ApiResponse resp = new ApiResponse();
        resp.setData(data);
        return resp;
    }

    public String getMessage() {
        return StringUtils.hasText(this.message) ? this.message : "";
    }

    public void setMessage(String msg) {
        this.message = msg;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean ifSuccess() {
        return this.status == 0;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
