package com.manager.response;

import java.io.Serializable;

/**
 * Created by shencx on 2017/4/27.
 */
public class AdvertContentResponse implements Serializable{

    private String advertContent;

    public String getAdvertContent() {
        return advertContent;
    }

    public void setAdvertContent(String advertContent) {
        this.advertContent = advertContent;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AdvertContentResponse{");
        sb.append("advertContent='").append(advertContent).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
