package com.manager.controller;

import com.manager.annotations.Authentication;
import com.manager.enums.UserRoleEnum;
import com.manager.exception.YCException;
import com.manager.handler.UserManageHandler;
import com.manager.request.user.OnlineUserRequest;
import com.manager.request.user.UserManageRequest;
import com.manager.response.UserMangeResponse;
import com.manager.utils.APIResponse;
import com.manager.utils.Page;
import com.manager.utils.YCSystemStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by shencx on 2017/4/10.
 */
@Controller
@RequestMapping(value = "/api/user_manage")
@Authentication(allow = UserRoleEnum.SuperAdmin)
public class UserManageController {

    Logger LOG = LoggerFactory.getLogger(UserManageController.class);

    @Resource
    private UserManageHandler userManageHandler;

    /**
     * 添加、修改用户信息
     * @param request
     * @param userManageRequest
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/edit_user",method = RequestMethod.POST)
    public APIResponse editUserInfo(HttpServletRequest request, UserManageRequest userManageRequest){
        APIResponse apiResponse = new APIResponse<>();
        try {
            userManageHandler.editUser(userManageRequest);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
        } catch (Throwable e) {
            LOG.error("添加修改用户信息发生异常",userManageRequest);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }

    /**
     * 获取用户详情
     * @param request
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public APIResponse<UserMangeResponse> detail(HttpServletRequest request,Integer id){
        APIResponse<UserMangeResponse> apiResponse = new APIResponse<>();
        UserMangeResponse userMangeResponse = null;
        try {
            userMangeResponse = userManageHandler.fetchUserManageDetail(id);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
            apiResponse.setData(userMangeResponse);
        } catch (Throwable e) {
            LOG.error("获取用户详情信息发生异常",id);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }

    /**
     * 获取用户列表
     * @param request
     * @param userManageRequest
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public APIResponse<Page<UserMangeResponse>> list(HttpServletRequest request, UserManageRequest userManageRequest){
        APIResponse<Page<UserMangeResponse>> apiResponse = new APIResponse<>();
        Page<UserMangeResponse> page = null;
        try {
            page = userManageHandler.fetchUserManageList(userManageRequest);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
            apiResponse.setData(page);
        } catch (Throwable e) {
            LOG.error("获取用户列表发生异常",userManageRequest);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }

    /**
     * 启用、禁用
     * @param request
     * @param id
     * @param status
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/modify_status",method = RequestMethod.POST)
    public APIResponse modify(HttpServletRequest request, Integer id,Integer status){
        APIResponse apiResponse = new APIResponse<>();
        try {
            userManageHandler.modifyUserStatus(id,status);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
        } catch (Throwable e) {
            LOG.error("更新用户状态发生异常",id);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }

    /**
     * 获取在线用户
     * @param request
     * @param onlineUserRequest
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/online_user",method = RequestMethod.GET)
    public APIResponse<Page<UserMangeResponse>> online(HttpServletRequest request, OnlineUserRequest onlineUserRequest){
        APIResponse<Page<UserMangeResponse>> apiResponse = new APIResponse<>();
        Page<UserMangeResponse> page = null;
        try {
            page = userManageHandler.fetchOnlineUserList(onlineUserRequest);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
            apiResponse.setData(page);
        } catch (Throwable e) {
            LOG.error("获取在线用户列表发生异常",onlineUserRequest);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }

    /**
     * 踢出用户
     * @param request
     * @param userIdsArray
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/kick_out",method = RequestMethod.POST)
    public APIResponse kickOut(HttpServletRequest request,String[] userIdsArray){
        APIResponse apiResponse = new APIResponse();
        try {
            userManageHandler.kickOutUser(userIdsArray);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
        } catch (Throwable e) {
            LOG.error("踢出用户发生异常",userIdsArray);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }
}
