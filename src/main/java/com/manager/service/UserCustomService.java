package com.manager.service;

import com.manager.exception.DatabaseException;
import com.manager.pojo.UserCustom;
import org.springframework.stereotype.Service;

/**
 * Created by shencx on 2017/4/10.
 */
@Service
public interface UserCustomService {

    Integer addUserCustomInfo(UserCustom userCustom) throws DatabaseException;

    boolean updateUserCustomInfo(UserCustom userCustom) throws DatabaseException;
}
