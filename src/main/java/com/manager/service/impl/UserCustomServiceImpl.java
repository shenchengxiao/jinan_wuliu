package com.manager.service.impl;

import com.manager.mapper.UserCustomMapper;
import com.manager.pojo.UserCustom;
import com.manager.service.UserCustomService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by shencx on 2017/4/10.
 */
@Service
public class UserCustomServiceImpl implements UserCustomService{

    @Resource
    private UserCustomMapper userCustomMapper;
    @Override
    public Integer addUserCustomInfo(UserCustom userCustom) {
        return null;
    }
}
