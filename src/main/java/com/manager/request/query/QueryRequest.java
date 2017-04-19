package com.manager.request.query;

import com.manager.request.BaseQuery;

/**
 * Created by shencx on 2017/4/19.
 */
public class QueryRequest extends BaseQuery{

    /** 用户名称 */
    private String userName;

    /** 指定日期 */
    private String appointedDay;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAppointedDay() {
        return appointedDay;
    }

    public void setAppointedDay(String appointedDay) {
        this.appointedDay = appointedDay;
    }
}
