package com.manager.controller;

import com.manager.pojo.manual.UserResponse;
import com.manager.utils.APIResponse;
import com.manager.utils.BusinessStatusEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by shencx on 2017/3/24.
 */
@Controller
@RequestMapping(value = "/api/user")
public class UserController {

    @ResponseBody
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public APIResponse<UserResponse> userLogin(HttpServletRequest request, String userName, String passwd){
        APIResponse<UserResponse> apiResponse = new APIResponse<UserResponse>();
        UserResponse userResponse = new UserResponse();
        if (userName.equals("yuzhibo")&&passwd.equals("123456")){
            userResponse.setUserName(userName);
            userResponse.setPasswd(passwd);
            apiResponse.setStatus(BusinessStatusEnum.SUCCESS.getStatus());
            apiResponse.setMsg(BusinessStatusEnum.SUCCESS.getDesc());
            apiResponse.setData(userResponse);
        }
        return apiResponse;
    }
}
