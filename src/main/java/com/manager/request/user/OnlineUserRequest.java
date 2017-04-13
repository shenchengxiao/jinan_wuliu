package com.manager.request.user;

import com.manager.request.BaseQuery;

import java.util.List;

/**
 * Created by shencx on 2017/4/12.
 */
public class OnlineUserRequest extends BaseQuery{

    private String userName;

    private String userNum;

    private String phoneNumber;

    private Integer isUsed;

    /** 用户id集合*/
    private List<Integer> idsList;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(Integer isUsed) {
        this.isUsed = isUsed;
    }

    public List<Integer> getIdsList() {
        return idsList;
    }

    public void setIdsList(List<Integer> idsList) {
        this.idsList = idsList;
    }
}
