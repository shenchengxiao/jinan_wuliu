package com.manager.controller;

import com.manager.core.ActionContext;
import com.manager.core.AuthUser;
import com.manager.exception.YCException;
import com.manager.handler.UserInfoHandler;
import com.manager.pojo.UserInfo;
import com.manager.request.user.UserInfoRequest;
import com.manager.utils.APIResponse;
import com.manager.utils.BusinessStatusEnum;
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
    public APIResponse<UserInfo> userLogin(HttpServletRequest request, UserInfoRequest userInfoRequest){
        APIResponse<UserInfo> apiResponse = new APIResponse<UserInfo>();
        UserInfo userInfo = null;
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
        } catch (YCException e) {
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
    public APIResponse addUser(HttpServletRequest request, UserInfo userInfo){
        APIResponse apiResponse = new APIResponse();
        try {
            userInfoHandler.addUserInfo(userInfo);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
        } catch (YCException e) {
            LOG.error("添加用户发生异常",userInfo);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }
}
