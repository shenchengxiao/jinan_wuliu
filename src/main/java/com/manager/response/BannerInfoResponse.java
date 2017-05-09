package com.manager.response;

import java.io.Serializable;

/**
 * Created by shencx on 2017/4/1.
 */
public class BannerInfoResponse implements Serializable{

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 图片名称
     */
    private String bannerName;

    /**
     * 图片地址
     */
    private String imageUrl;

    /**
     * 状态
     */
    private Integer status;

    /** 开始时间 */
    private String startTime;

    /** 结束时间 */
    private String endTime;

    /** 链接地址 */
    private String linkUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBannerName() {
        return bannerName;
    }

    public void setBannerName(String bannerName) {
        this.bannerName = bannerName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BannerInfoResponse{");
        sb.append("id=").append(id);
        sb.append(", bannerName='").append(bannerName).append('\'');
        sb.append(", imageUrl='").append(imageUrl).append('\'');
        sb.append(", status=").append(status);
        sb.append(", startTime='").append(startTime).append('\'');
        sb.append(", endTime='").append(endTime).append('\'');
        sb.append(", linkUrl='").append(linkUrl).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
