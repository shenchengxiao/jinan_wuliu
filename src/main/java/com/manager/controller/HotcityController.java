package com.manager.controller;
import com.manager.exception.YCException;
import com.manager.handler.BindingHandler;
import com.manager.handler.BlackWordHandler;
import com.manager.handler.UserCustomHandler;
import com.manager.pojo.BlackWord;
import com.manager.pojo.HotCity;
import com.manager.pojo.User;
import com.manager.pojo.UserBinding;
import com.manager.pojo.UserCustom;
import com.manager.request.blackword.BlackWordRequest;
import com.manager.response.AdvertInfoResponse;
import com.manager.response.BlackWordResponse;
import com.manager.service.HotcityService;
import com.manager.utils.APIResponse;
import com.manager.utils.Page;
import com.manager.utils.YCSystemStatusEnum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
/**
 * Created by zcy on 2017/3/31.
 */
@Controller
@RequestMapping(value = "/api/hotcity")
public class HotcityController {

    @Resource
    private HotcityService hotcityService;
    
    Logger LOG = LoggerFactory.getLogger(HotcityController.class);
    
    @ResponseBody
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public APIResponse add(HttpServletRequest request,HotCity hotCity){
        APIResponse apiResponse = new APIResponse<>();
        try {
        	hotcityService.insertCity(hotCity);
    		apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
            
        } catch (Throwable e) {
            LOG.error("添加热门城市发生异常",hotCity.toString());
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }
    
    /**
     * 获取详情
     * @param request
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public APIResponse<List<HotCity>> detail(HttpServletRequest request){
        APIResponse<List<HotCity>> apiResponse = new APIResponse<>();
        try {
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
            apiResponse.setData(hotcityService.selectCity());
        } catch (Throwable e) {
            LOG.error("获取热门城市详情信息发生异常",request);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }
}
