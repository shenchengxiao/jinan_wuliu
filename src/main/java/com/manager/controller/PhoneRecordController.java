package com.manager.controller;

import com.manager.annotations.Authentication;
import com.manager.core.ActionContext;
import com.manager.core.AuthUser;
import com.manager.enums.BusinessStatusEnum;
import com.manager.enums.UserRoleEnum;
import com.manager.handler.PhoneRecordHandler;
import com.manager.handler.UserInfoHandler;
import com.manager.pojo.Admin;
import com.manager.pojo.PhoneRecord;
import com.manager.request.phone.PhoneRecordRequest;
import com.manager.request.user.UserInfoRequest;
import com.manager.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by shencx on 2017/3/24.
 */
@Controller
@RequestMapping(value = "/api/phone")
public class PhoneRecordController {

    @Resource
    private PhoneRecordHandler phoneRecordHandler;

    Logger LOG = LoggerFactory.getLogger(PhoneRecordController.class);

    /**
     * 添加话务信息
     * @param request
     * @param phoneRecord
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    public APIResponse addPhoneRecord(HttpServletRequest request, PhoneRecord phoneRecord){
        APIResponse apiResponse = new APIResponse();
        try {
        	AuthUser user = ActionContext.getActionContext().currentUser();
        	phoneRecord.setServiceNum(user.getName());
        	phoneRecordHandler.updatePhoneRecord(phoneRecord);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
        } catch (Throwable e) {
            LOG.error("添加话务信息发生异常",phoneRecord);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }

    /**
     * 获取话务列表
     * @param request
     * @param pRecordRequest
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @Authentication(allow = UserRoleEnum.SuperAdmin)
    public APIResponse<Page<PhoneRecord>> list(HttpServletRequest request,PhoneRecordRequest pRecordRequest){
        APIResponse<Page<PhoneRecord>> apiResponse = new APIResponse<>();
        Page<PhoneRecord> page = null;
        try {
            page = phoneRecordHandler.fetchPhoneRecodList(pRecordRequest);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
            apiResponse.setData(page);
        } catch (Throwable e) {
            LOG.error("获取话务列表发生异常",pRecordRequest);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }

    
}
