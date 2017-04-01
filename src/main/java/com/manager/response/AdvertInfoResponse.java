package com.manager.response;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by shencx on 2017/3/30.
 */
public class AdvertInfoResponse implements Serializable{

    private Integer id;

    private String startTime;

    private String endTime;

    private Float price;

    private String linkedName;

    private String phoneNumber;

    private Integer beUsed;

    private String content;

    private String linkUrl;

    public Integer getId() {
        return id;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AdvertInfoResponse{");
        sb.append("id=").append(id);
        sb.append(", startTime='").append(startTime).append('\'');
        sb.append(", endTime='").append(endTime).append('\'');
        sb.append(", price=").append(price);
        sb.append(", linkedName='").append(linkedName).append('\'');
        sb.append(", phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", beUsed=").append(beUsed);
        sb.append(", content='").append(content).append('\'');
        sb.append(", linkUrl='").append(linkUrl).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
