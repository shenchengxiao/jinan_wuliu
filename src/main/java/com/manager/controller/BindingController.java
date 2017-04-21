package com.manager.controller;
import com.manager.exception.YCException;
import com.manager.handler.BindingHandler;
import com.manager.handler.BlackWordHandler;
import com.manager.pojo.BlackWord;
import com.manager.pojo.UserBinding;
import com.manager.request.blackword.BlackWordRequest;
import com.manager.response.AdvertInfoResponse;
import com.manager.response.BlackWordResponse;
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
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
/**
 * Created by zcy on 2017/3/31.
 */
@Controller
@RequestMapping(value = "/api/binding")
public class BindingController {

    @Resource
    private BindingHandler bindingHandler;
    
    Logger LOG = LoggerFactory.getLogger(BindingController.class);


    
    @ResponseBody
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public APIResponse add(HttpServletRequest request, UserBinding userBinding){
        APIResponse apiResponse = new APIResponse<>();
        try {
        	if(bindingHandler.addBinding(userBinding)){
        		apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
                apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
        	}else{
        		apiResponse.setStatus(YCSystemStatusEnum.NOT_FOUND_USER.getCode());
                apiResponse.setMsg(YCSystemStatusEnum.NOT_FOUND_USER.getDesc());
        	}
            
        } catch (Throwable e) {
            LOG.error("修改解绑电脑发生异常",userBinding.toString());
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }

    /**
     * 用户解绑
     * @param request
     * @param userBinding
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/unbind",method = RequestMethod.POST)
    public APIResponse unbind(HttpServletRequest request , UserBinding userBinding){
        APIResponse apiResponse = new APIResponse();
        try {
            bindingHandler.userUnbind(userBinding);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
        } catch (Throwable e) {
            LOG.error("用户设备解绑发生异常",userBinding);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }
}
