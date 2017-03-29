package com.manager.request.user;

import com.manager.request.BaseQuery;

/**
 * Created by shencx on 2017/3/28.
 */
public class UserInfoRequest extends BaseQuery {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String passwd;

    /**
     * 用户手机号
     */
    private String phoneNumber;

    /**
     * 用户状态
     */
    private Integer status;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
