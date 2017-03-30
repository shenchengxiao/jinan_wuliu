package com.manager.request.advert;

import com.manager.request.BaseQuery;

import java.math.BigDecimal;

/**
 * Created by shencx on 2017/3/30.
 */
public class AdvertInfoRequest extends BaseQuery{

    private BigDecimal price;

    private String linkedName;

    private String phoneNumber;

    private Integer beUsed;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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
}
