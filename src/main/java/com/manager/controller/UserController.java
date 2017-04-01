package com.manager.controller;

import com.manager.annotations.Authentication;
import com.manager.core.ActionContext;
import com.manager.core.AuthUser;
import com.manager.enums.BusinessStatusEnum;
import com.manager.enums.UserRoleEnum;
import com.manager.handler.UserInfoHandler;
import com.manager.pojo.Admin;
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
@RequestMapping(value = "/api/user")
public class UserController {

    @Resource
    private UserInfoHandler userInfoHandler;

    Logger LOG = LoggerFactory.getLogger(UserController.class);

    /**
     * 用户登录
     * @param request
     * @param userInfoRequest
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public APIResponse<Admin> userLogin(HttpServletRequest request, UserInfoRequest userInfoRequest){
        APIResponse<Admin> apiResponse = new APIResponse<Admin>();
        Admin userInfo = null;
        try {
            userInfo = userInfoHandler.getUserInfoByNameAndPasswd(userInfoRequest);
            if (userInfo != null){
                AuthUser user = ActionContext.getActionContext().currentUser();
                user.login(Long.valueOf(userInfo.getId()),userInfo.getUserName(),userInfo.getRole());
                apiResponse.setStatus(BusinessStatusEnum.SUCCESS.getStatus());
                apiResponse.setMsg(BusinessStatusEnum.SUCCESS.getDesc());
                apiResponse.setData(userInfo);
            }else {
                apiResponse.setStatus(BusinessStatusEnum.EMPTY_RESULT.getCode());
                apiResponse.setMsg(BusinessStatusEnum.EMPTY_RESULT.getDesc());
            }
        } catch (Throwable e) {
            LOG.error("用户登录发生异常",userInfoRequest);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }

    /**
     * 退出登录
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public APIResponse login(HttpServletRequest request){
        APIResponse response = new APIResponse();

        AuthUser user = ActionContext.getActionContext().currentUser();
        user.logout();

        response.setStatus(0);
        return response;
    }

    /**
     * 添加用户信息
     * @param request
     * @param userInfo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add" ,method = RequestMethod.POST)
    @Authentication(allow = UserRoleEnum.SuperAdmin)
    public APIResponse addUser(HttpServletRequest request, Admin userInfo,String roleArr){
        APIResponse apiResponse = new APIResponse();
        try {
            userInfoHandler.addUserInfo(userInfo,roleArr);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
        } catch (Throwable e) {
            LOG.error("添加用户发生异常",userInfo);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }

    /**
     * 获取用户列表
     * @param request
     * @param userInfoRequest
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @Authentication(allow = UserRoleEnum.SuperAdmin)
    public APIResponse<Page<Admin>> list(HttpServletRequest request,UserInfoRequest userInfoRequest){
        APIResponse<Page<Admin>> apiResponse = new APIResponse<>();
        Page<Admin> page = null;
        try {
            page = userInfoHandler.fetchUserInfoList(userInfoRequest);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
            apiResponse.setData(page);
        } catch (Throwable e) {
            LOG.error("获取用户列表发生异常",userInfoRequest);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }

    /**
     * 获取用户详情信息
     * @param request
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    @Authentication(allow = UserRoleEnum.SuperAdmin)
    public APIResponse<Admin> detail(HttpServletRequest request,Integer id){
        APIResponse<Admin> apiResponse = new APIResponse<>();
        Admin userInfo = null;
        try {
            userInfo = userInfoHandler.fetchUserInfoDetail(id);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
            apiResponse.setData(userInfo);
        } catch (Throwable e) {
            LOG.error("获取用户详情信息发生异常",id);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }

    /**
     * 删除
     * @param request
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @Authentication(allow = UserRoleEnum.SuperAdmin)
    public APIResponse delete(HttpServletRequest request, Integer id){
        APIResponse apiResponse = new APIResponse<>();
        try {
            userInfoHandler.deleteUserInfo(id);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
        } catch (Throwable e) {
            LOG.error("删除用户信息发生异常",id);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/modify_status",method = RequestMethod.POST)
    @Authentication(allow = UserRoleEnum.SuperAdmin)
    public APIResponse modifyUserStatus(HttpServletRequest request, Integer id,Integer status){
        APIResponse apiResponse = new APIResponse<>();
        try {
            userInfoHandler.modifyStatus(id,status);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
        } catch (Throwable e) {
            LOG.error("禁用启用发生异常",id);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }
}
