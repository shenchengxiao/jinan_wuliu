package com.manager.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manager.exception.YCException;
import com.manager.handler.UserCustomHandler;
import com.manager.pojo.UserCustom;
import com.manager.request.custom.UserCustomRequest;
import com.manager.request.item.ItemRequest;
import com.manager.request.user.UserManageRequest;
import com.manager.response.ItemResponse;
import com.manager.response.UserCustomResponse;
import com.manager.response.UserMangeResponse;
import com.manager.utils.APIResponse;
import com.manager.utils.Page;
import com.manager.utils.YCSystemStatusEnum;

@Controller
@RequestMapping(value = "/api/custom")
public class UserCustomController {
	
	@Resource
	private UserCustomHandler userCustomHandler;
	
	Logger LOG = LoggerFactory.getLogger(ItemController.class);
	
	/**
	 * 获取用户权限列表
	 * @param request
	 * @param userCustomRequest
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "/userCustomList")
    @GetMapping(value = "/userCustomList")
    public APIResponse<Page<UserCustom>> userCustomList(HttpServletRequest request , UserCustomRequest userCustomRequest){
        APIResponse<Page<UserCustom>> apiResponse = new APIResponse<>();
        Page<UserCustom> page = null;
        try {
			page = userCustomHandler.selectUserCustom(userCustomRequest);
			apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
	        apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
	        apiResponse.setData(page);
		} catch (YCException e) {
			LOG.error("获取用户权限列表发生异常",userCustomRequest);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
		}
        
        return apiResponse;
        
    }
	
	/**
	 * 根据id获取用户权限
	 * @param request
	 * @param id
	 * @return
	 */
	@ResponseBody
    @RequestMapping(value = "/getById",method = RequestMethod.GET)
    public APIResponse<UserCustomResponse> getUserCustomById(HttpServletRequest request,Integer id){
        APIResponse<UserCustomResponse> apiResponse = new APIResponse<>();
        UserCustomResponse userCustom = null;
        try {
        	userCustom = userCustomHandler.getUserCustomById(id);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
            apiResponse.setData(userCustom);
        } catch (Throwable e) {
            LOG.error("getUserCustomById",id);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }
	
	@ResponseBody
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public APIResponse updateUserCustom(HttpServletRequest request, UserCustomRequest userCustomRequest){
        APIResponse apiResponse = new APIResponse<>();
        try {
        	userCustomHandler.updateUserCustom(userCustomRequest);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
        } catch (Throwable e) {
            LOG.error("修改用户权限发生异常",userCustomRequest);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }
}
