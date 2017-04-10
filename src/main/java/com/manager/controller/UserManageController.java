package com.manager.controller;

import com.manager.annotations.Authentication;
import com.manager.enums.UserRoleEnum;
import com.manager.handler.UserManageHandler;
import com.manager.request.user.UserManageRequest;
import com.manager.utils.APIResponse;
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
}
