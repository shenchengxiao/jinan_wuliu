package com.manager.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

/**
 * Created by shencx on 2017/3/27.
 */
public class MenuInfoResponse implements Serializable{
    private Integer id;

    private String name;

    private String url;

    private String cssClass;

    private List<MenuInfoResponse> childNodes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<MenuInfoResponse> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<MenuInfoResponse> childNodes) {
        this.childNodes = childNodes;
    }

    @Override
    public boolean equals(Object obj) {
        return this.getId().equals(((MenuInfoResponse)obj).getId());
    }
}
