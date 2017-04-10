package com.manager.service.impl;

import com.manager.exception.DatabaseException;
import com.manager.mapper.UserMapper;
import com.manager.pojo.User;
import com.manager.pojo.UserExample;
import com.manager.service.UserInfoService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by shencx on 2017/4/10.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService{

    @Resource
    private UserMapper userMapper;

    Logger LOG = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    /**
     * 添加用户信息
     * @param user
     * @return
     * @throws DatabaseException
     */
    @Override
    public Integer addUser(User user) throws DatabaseException {
        try {
            if (user == null){
                LOG.error("addUserInfo 信息为空",user);
                return Integer.valueOf(0);
            }
            int val = userMapper.insertSelective(user);
            if (val > 0){
                return user.getId();
            }else {
                return Integer.valueOf(0);
            }
        } catch (Throwable e) {
            LOG.error("addUserInfo 异常",user);
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public boolean updateUser(User user) throws DatabaseException {
        try {
            if (user == null){
                LOG.error("updateUser 信息为空",user);
                return false;
            }
            Integer val = userMapper.updateByPrimaryKeySelective(user);
            return val>0?true:false;
        } catch (Throwable e) {
            LOG.error("updateUser 异常",user);
            throw new DatabaseException(e.getMessage());
        }
    }
}
