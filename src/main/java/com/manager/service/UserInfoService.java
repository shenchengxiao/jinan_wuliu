package com.manager.service;

import com.manager.exception.DatabaseException;
import com.manager.pojo.UserInfo;
import com.manager.request.user.UserInfoRequest;
import com.manager.utils.Page;


/**
 * Created by shencx on 2017/3/28.
 */
public interface UserInfoService {

    /**
     * 获取用户列表
     * @param request
     * @return
     * @throws DatabaseException
     */
    Page<UserInfo> fetchUserInfoList(UserInfoRequest request) throws DatabaseException;

    /**
     * 添加用户
     * @param userInfo
     * @return
     */
    Integer addUserInfo(UserInfo userInfo) throws DatabaseException;

    /**
     * 用户登录
     * @param userInfoRequest
     * @return
     */
    UserInfo fetchUserByUserNameAndPasswd(UserInfoRequest userInfoRequest) throws DatabaseException;

    boolean updateUserInfo(UserInfo userInfo) throws DatabaseException;

    boolean deleteUserInfo(Integer id) throws DatabaseException;

    UserInfo fetchUserInfoById(Integer id) throws DatabaseException;


}
