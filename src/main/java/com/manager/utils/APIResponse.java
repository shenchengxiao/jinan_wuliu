package com.manager.utils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.manager.service.IBusinessStatusEnum;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * 接口返回类
 */
public class APIResponse<T> implements Serializable {

    private String msg;
    private int status;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public String getMsg() {
        if(StringUtils.hasText(msg)) {
            return msg;
        }
        return "";
    }

    public APIResponse setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public APIResponse setStatus(int status) {
        this.status = status;
        return this;
    }

    public T getData() {
        if(null == data) {
            return (T)new BaseResp();
        }
        return data;
    }

    public APIResponse setData(T data) {
        this.data = data;
        return this;
    }

    public boolean ifSuccess() {
        return status == 0;
    }

    public static APIResponse build() {
        return new APIResponse();
    }

    public static APIResponse build(IBusinessStatusEnum businessStatusEnum) {
        return build()
                .setMsg(businessStatusEnum.getDesc())
                .setStatus(businessStatusEnum.getCode());
    }

    @Deprecated
    public static <T> APIResponse build(IBusinessStatusEnum businessStatusEnum, T data) {
        return build(businessStatusEnum).setData(data);
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("APIResponse{");
        sb.append("msg='").append(msg).append('\'');
        sb.append(", status=").append(status);
        sb.append(", data=").append(data);
        sb.append('}');
        return sb.toString();
    }
}
