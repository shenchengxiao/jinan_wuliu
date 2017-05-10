package com.manager.request.advert;

import com.manager.enums.YesNoEnum;

/**
 * Created by shencx on 2017/5/8.
 */
public class BannerInfoRequest {

    private Integer id;

    /** 图片名称 */
    private String bannerName;

    /** 图片地址 */
    private String imageUrl;

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
}
