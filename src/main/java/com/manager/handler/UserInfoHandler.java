package com.manager.handler;

import com.manager.core.PasswordEncrypt;
import com.manager.exception.DatabaseException;
import com.manager.exception.YCException;
import com.manager.pojo.Admin;
import com.manager.pojo.User;
import com.manager.request.user.UserInfoRequest;
import com.manager.service.AdminInfoService;
import com.manager.utils.Page;
import com.manager.utils.Validator;
import com.manager.utils.YCSystemStatusEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * Created by shencx on 2017/3/28.
 */
@Service
public class UserInfoHandler {

    @Resource
    private AdminInfoService adminInfoService;

    Logger LOG = LoggerFactory.getLogger(UserInfoHandler.class);

    /**
     * 用户登录
     * @param request
     * @return
     * @throws YCException
     */
    public Admin getUserInfoByNameAndPasswd(UserInfoRequest request) throws YCException {
        /** 参数校验 */
        Validator.isEmpty(request, YCSystemStatusEnum.PARAM_EMPTY);
        Validator.isEmpty(request.getUserName(), YCSystemStatusEnum.USER_NAME);
        Validator.isEmpty(request.getPasswd(), YCSystemStatusEnum.USER_PASSWD);
        //密码加密（用户名和密码联接后MD5）
        String pwd = PasswordEncrypt.encrypt(request.getUserName(),request.getPasswd());
        request.setPasswd(pwd);
        try {
            Admin admin = adminInfoService.fetchUserByUserNameAndPasswd(request);
            return admin;
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
    public void addUserInfo(Admin userInfo,String roleArr) throws YCException {

        Integer role = 0;
        if (StringUtils.isNotBlank(roleArr)) {
            String[] roles = roleArr.split(",");
            for (String r : roles) {
                //角色值或运算
                role = role | Integer.valueOf(r);
            }
        }
        userInfo.setRole(role);
        /** 参数校验 */
        Validator.isEmpty(userInfo, YCSystemStatusEnum.PARAM_EMPTY);
        Validator.isEmpty(userInfo.getUserName(), YCSystemStatusEnum.USER_NAME);
        Validator.isEmpty(userInfo.getPasswd(), YCSystemStatusEnum.USER_PASSWD);
        Validator.isEmpty(userInfo.getRole(), YCSystemStatusEnum.USER_ROLE);
        UserInfoRequest userInfoRequest = new UserInfoRequest();
        userInfoRequest.setPasswd(userInfo.getPasswd());

        try {
            //ID为空则添加，否则更新
            if (userInfo.getId() == null){
                userInfo.setPasswd(PasswordEncrypt.encrypt(userInfo.getUserName(),userInfo.getPasswd()));
                userInfo.setBeUsed(1);
                adminInfoService.addUserInfo(userInfo);
            }else {
                Admin admin = adminInfoService.fetchUserInfoById(userInfoRequest);
                if (admin == null){
                    userInfo.setPasswd(PasswordEncrypt.encrypt(userInfo.getUserName(),userInfo.getPasswd()));
                }
                adminInfoService.updateUserInfo(userInfo);
            }
        } catch (DatabaseException e) {
            LOG.error("add userInfo exception",userInfo);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }

    /**
     * 获取用户列表信息
     * @param request
     * @return
     * @throws YCException
     */
    public Page<Admin> fetchUserInfoList(UserInfoRequest request) throws YCException {
        /** 参数校验 */
        Validator.isEmpty(request,YCSystemStatusEnum.PARAM_EMPTY);
        Page<Admin> page = null;
        try {
            page = adminInfoService.fetchUserInfoList(request);
        } catch (DatabaseException e) {
            LOG.error("fetchUserInfoList exception",request);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
        return page;
    }

    /**
     * 根据主键获取用户详情
     * @param id
     * @return
     * @throws YCException
     */
    public Admin fetchUserInfoDetail(Integer id) throws YCException {
        /** 参数校验 */
        Validator.isEmpty(id,YCSystemStatusEnum.USER_ID_EMPTY);
        UserInfoRequest userInfoRequest = new UserInfoRequest();
        userInfoRequest.setId(id);
        Admin userInfo = null;
        try {
            userInfo = adminInfoService.fetchUserInfoById(userInfoRequest);
        } catch (DatabaseException e) {
            LOG.error("fetchUserInfoDetail exception",id);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
        return userInfo;
    }

    public void deleteUserInfo(Integer id) throws YCException {
        /** 参数校验 */
        Validator.isEmpty(id,YCSystemStatusEnum.USER_ID_EMPTY);
        try {
            adminInfoService.deleteUserInfo(id);
        } catch (DatabaseException e) {
            LOG.error("deleteUserInfo exception",id);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }

    /**
     * 禁用启用
     * @param id
     * @param status
     * @throws YCException
     */
    public void modifyStatus(Integer id,Integer status) throws YCException {
        /** 参数校验 */
        Validator.isEmpty(id,YCSystemStatusEnum.USER_ID_EMPTY);
        Validator.isEmpty(status,YCSystemStatusEnum.USER_ID_EMPTY);
        Admin userInfo = new Admin();
        userInfo.setId(id);
        userInfo.setBeUsed(status);
        try {
            adminInfoService.updateUserInfo(userInfo);
        } catch (DatabaseException e) {
            LOG.error("modifyStatus exception",id);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }
    
}
