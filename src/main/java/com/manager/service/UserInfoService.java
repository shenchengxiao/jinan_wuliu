package com.manager.service;

import java.util.List;

import com.manager.exception.DatabaseException;
import com.manager.pojo.User;

/**
 * Created by shencx on 2017/4/9.
 */
public interface UserInfoService {

    Integer addUser(User user) throws DatabaseException;

    boolean updateUser(User user) throws DatabaseException;
    
    List<User> queryUser(User user)throws DatabaseException;
}
