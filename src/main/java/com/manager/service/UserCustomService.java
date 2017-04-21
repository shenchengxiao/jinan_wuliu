package com.manager.service;

import com.manager.exception.DatabaseException;
import com.manager.pojo.UserCustom;
import com.manager.request.custom.UserCustomRequest;
import com.manager.utils.Page;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * Created by shencx on 2017/4/10.
 */
@Service
public interface UserCustomService {

    Integer addUserCustomInfo(UserCustom userCustom) throws DatabaseException;

    boolean updateUserCustomInfo(UserCustom userCustom) throws DatabaseException;
    
    boolean updateUserCustomInfoByParam(UserCustom userCustom) throws DatabaseException;
    
    List<UserCustom> queryCustomInfo(UserCustom userCustom) throws DatabaseException;

	Page<UserCustom> selectUserCustom(UserCustomRequest userCustomRequest) throws DatabaseException;

	UserCustom getUserCustomById(Integer id) throws DatabaseException;

	boolean updateUserCustom(UserCustom userCustom) throws DatabaseException;
}
