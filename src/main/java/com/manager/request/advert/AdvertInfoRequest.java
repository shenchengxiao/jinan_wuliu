package com.manager.request.advert;

import com.manager.request.BaseQuery;


/**
 * Created by shencx on 2017/3/30.
 */
public class AdvertInfoRequest extends BaseQuery{

    private Integer id;

    /** 开始时间 */
    private String startTime;

    /** 结束时间 */
    private String endTime;

    /** 广告内容 */
    private String content;

    /** 价格 */
    private Float price;

    /** 联系人 */
    private String linkedName;

    /** 联系电话 */
    private String phoneNumber;

    /** 是否有效 */
    private Integer beUsed;

    /** 链接地址 */
    private String linkUrl;

    /** 广告标题 */
    private String advertTitle;

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

    public String getAdvertTitle() {
        return advertTitle;
    }

    public void setAdvertTitle(String advertTitle) {
        this.advertTitle = advertTitle;
    }
}
