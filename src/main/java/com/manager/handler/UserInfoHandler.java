package com.manager.handler;

import com.manager.core.PasswordEncrypt;
import com.manager.exception.DatabaseException;
import com.manager.exception.YCException;
import com.manager.pojo.UserInfo;
import com.manager.request.user.UserInfoRequest;
import com.manager.service.UserInfoService;
import com.manager.utils.Validator;
import com.manager.utils.YCSystemStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by shencx on 2017/3/28.
 */
@Service
public class UserInfoHandler {

    @Resource
    private UserInfoService userInfoService;

    Logger LOG = LoggerFactory.getLogger(UserInfoHandler.class);

    public UserInfo getUserInfoByNameAndPasswd(UserInfoRequest request) throws YCException {
        /** 参数校验 */
        Validator.isEmpty(request, YCSystemStatusEnum.PARAM_EMPTY);
        Validator.isEmpty(request.getUserName(), YCSystemStatusEnum.USER_NAME);
        Validator.isEmpty(request.getPasswd(), YCSystemStatusEnum.USER_PASSWD);
        //用户名和密码联接后MD5
        String pwd = PasswordEncrypt.encrypt(request.getUserName(),request.getPasswd());
        request.setPasswd(pwd);
        try {
            UserInfo userInfo = userInfoService.fetchUserByUserNameAndPasswd(request);
            return userInfo;
        } catch (DatabaseException e) {
            LOG.error("getUserInfoByNameAndPasswd exception",request);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }


    /**
     * 添加用户信息
     * @param userInfo
     * @throws YCException
     */
    public void addUserInfo(UserInfo userInfo) throws YCException {

        /** 参数校验 */
        Validator.isEmpty(userInfo, YCSystemStatusEnum.PARAM_EMPTY);
        Validator.isEmpty(userInfo.getUserName(), YCSystemStatusEnum.USER_NAME);
        Validator.isEmpty(userInfo.getPasswd(), YCSystemStatusEnum.USER_PASSWD);
        Validator.isEmpty(userInfo.getRole(), YCSystemStatusEnum.USER_ROLE);
        try {
            userInfoService.addUserInfo(userInfo);
        } catch (DatabaseException e) {
            LOG.error("add userInfo exception",userInfo);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }
}
