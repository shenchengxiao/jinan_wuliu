package com.manager.service;

import java.util.List;

import com.manager.exception.DatabaseException;
import com.manager.pojo.User;
import com.manager.request.user.OnlineUserRequest;
import com.manager.request.user.UserManageRequest;
import com.manager.response.UserMangeResponse;
import com.manager.utils.Page;

/**
 * Created by shencx on 2017/4/9.
 */
public interface UserInfoService {

    Integer addUser(User user) throws DatabaseException;

    boolean updateUser(User user) throws DatabaseException;

    List<User> queryUser(User user)throws DatabaseException;

    UserMangeResponse getUserDetail(UserManageRequest request) throws DatabaseException;

    Page<UserMangeResponse> getUserList(UserManageRequest request) throws DatabaseException;

    boolean modifyStatus(Integer id,Integer enabled) throws DatabaseException;

    Page<UserMangeResponse> getUserByUserIds(OnlineUserRequest request) throws DatabaseException;

    boolean batchUpdateUserStatus(List<Integer> ids) throws DatabaseException;
}
