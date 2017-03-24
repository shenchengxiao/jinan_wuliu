package com.manager.pojo.manual;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by shencx on 2017/3/17.
 */
public class UserResponse implements Serializable{

    private String userName;

    private String passwd;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "userName='" + userName + '\'' +
                ", passwd='" + passwd + '\'' +
                '}';
    }
}
