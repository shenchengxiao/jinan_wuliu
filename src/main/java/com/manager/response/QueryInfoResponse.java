package com.manager.response;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by shencx on 2017/4/19.
 */
public class QueryInfoResponse implements Serializable{

    private Long id;

    /** 搜索名称 */
    private String searchContent;

    /** 用户名称 */
    private String userName;

    /** 创建时间 */
    private Date createTime;

    /** 类型 */
    private Integer type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("QueryInfoResponse{");
        sb.append("id=").append(id);
        sb.append(", searchContent='").append(searchContent).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append(", type=").append(type);
        sb.append('}');
        return sb.toString();
    }
}
