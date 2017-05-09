package com.manager.service;

import com.manager.exception.DatabaseException;
import com.manager.pojo.Admin;
import com.manager.request.user.UserInfoRequest;
import com.manager.utils.Page;


/**
 * Created by shencx on 2017/3/28.
 */
public interface AdminInfoService {

    /**
     * 获取用户列表
     * @param request
     * @return
     * @throws DatabaseException
     */
    Page<Admin> fetchUserInfoList(UserInfoRequest request) throws DatabaseException;

    /**
     * 添加用户
     * @param userInfo
     * @return
     */
    Integer addUserInfo(Admin userInfo) throws DatabaseException;

    /**
     * 用户登录
     * @param userInfoRequest
     * @return
     */
    Admin fetchUserByUserNameAndPasswd(UserInfoRequest userInfoRequest) throws DatabaseException;

    boolean updateUserInfo(Admin userInfo) throws DatabaseException;

    boolean deleteUserInfo(Integer id) throws DatabaseException;

    Admin fetchUserInfoById(UserInfoRequest request) throws DatabaseException;


}
