package com.manager.controller;

import com.manager.handler.AdvertHandler;
import com.manager.request.advert.AdvertInfoRequest;
import com.manager.response.AdvertInfoResponse;
import com.manager.utils.APIResponse;
import com.manager.utils.Page;
import com.manager.utils.YCSystemStatusEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by shencx on 2017/3/30.
 */
@Controller
@RequestMapping(value = "/api/advert")
public class AdvertController {

    @Resource
    private AdvertHandler advertHandler;

    @ResponseBody
    @RequestMapping(value = "/list")
    public APIResponse<Page<AdvertInfoResponse>> list(HttpServletRequest request , AdvertInfoRequest advertInfoRequest){
        APIResponse<Page<AdvertInfoResponse>> apiResponse = new APIResponse<>();
        Page<AdvertInfoResponse> page = null;
        page = advertHandler.fetchAdvertList(advertInfoRequest);
        apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
        apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
        apiResponse.setData(page);
        return apiResponse;
    }
}
