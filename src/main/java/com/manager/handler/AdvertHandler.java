package com.manager.handler;

import com.manager.request.advert.AdvertInfoRequest;
import com.manager.response.AdvertInfoResponse;
import com.manager.utils.Page;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shencx on 2017/3/30.
 */
@Service
public class AdvertHandler {


    public Page<AdvertInfoResponse> fetchAdvertList(AdvertInfoRequest advertInfoRequest){

        List<AdvertInfoResponse> list = new ArrayList<>();
        AdvertInfoResponse advertInfoResponse = new AdvertInfoResponse();
        advertInfoResponse.setStartTime("2017-03-28");
        advertInfoResponse.setEndTime("2017-03-29");
        advertInfoResponse.setPrice(new BigDecimal(222));
        advertInfoResponse.setLinkedName("张三");
        advertInfoResponse.setPhoneNumber("13114142525");
        advertInfoResponse.setBeUsed(1);
        advertInfoResponse.setContent("这是广告的内容信息都是浪费了很多离婚了阿斯蒂芬乖哈考虑后果卡卡了撒的谎公开售楼电话风力可达可更换斯达康两个阿萨德个快乐哈撒大公开");
        list.add(advertInfoResponse);
        Page<AdvertInfoResponse> page = new Page<>();
        page.setPageindex(0);
        page.setPagesize(10);
        page.setResult(list);
        page.setTotal(1);
        return page;
    }
}
