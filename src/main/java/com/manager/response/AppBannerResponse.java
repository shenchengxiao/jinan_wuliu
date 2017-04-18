package com.manager.response;

import java.io.Serializable;

/**
 * Created by shencx on 2017/4/18.
 */
public class AppBannerResponse implements Serializable{

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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AppBannerResponse{");
        sb.append("id=").append(id);
        sb.append(", bannerName='").append(bannerName).append('\'');
        sb.append(", imageUrl='").append(imageUrl).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
