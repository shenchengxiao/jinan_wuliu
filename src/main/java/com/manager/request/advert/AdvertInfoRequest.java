package com.manager.request.advert;

import com.manager.request.BaseQuery;


/**
 * Created by shencx on 2017/3/30.
 */
public class AdvertInfoRequest extends BaseQuery{

    private Integer id;

    private String startTime;

    private String endTime;

    private String content;

    private Float price;

    private String linkedName;

    private String phoneNumber;

    private Integer beUsed;

    private String linkUrl;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getLinkedName() {
        return linkedName;
    }

    public void setLinkedName(String linkedName) {
        this.linkedName = linkedName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getBeUsed() {
        return beUsed;
    }

    public void setBeUsed(Integer beUsed) {
        this.beUsed = beUsed;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
}
