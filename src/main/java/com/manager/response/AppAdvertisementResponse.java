package com.manager.response;

import com.manager.pojo.Advert;
import com.manager.pojo.BannerInfo;
import com.manager.utils.Page;

import java.io.Serializable;
import java.util.List;

/**
 * Created by shencx on 2017/4/18.
 */
public class AppAdvertisementResponse implements Serializable{

    /**
     * 轮播图集合列表
     */
    private List<AppBannerResponse> bannerInfoList;

    /**
     * 文字广告分页集合
     */
    private Page<Advert> advertPage;

    public List<AppBannerResponse> getBannerInfoList() {
        return bannerInfoList;
    }

    public void setBannerInfoList(List<AppBannerResponse> bannerInfoList) {
        this.bannerInfoList = bannerInfoList;
    }

    public Page<Advert> getAdvertPage() {
        return advertPage;
    }

    public void setAdvertPage(Page<Advert> advertPage) {
        this.advertPage = advertPage;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AppAdvertisementResponse{");
        sb.append("bannerInfoList=").append(bannerInfoList);
        sb.append(", advertPage=").append(advertPage);
        sb.append('}');
        return sb.toString();
    }
}
