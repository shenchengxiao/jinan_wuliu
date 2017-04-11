package com.manager.service.impl;

import com.manager.exception.DatabaseException;
import com.manager.mapper.UserCustomMapper;
import com.manager.pojo.UserCustom;
import com.manager.pojo.UserCustomExample;
import com.manager.pojo.UserCustomExample.Criteria;
import com.manager.service.UserCustomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * Created by shencx on 2017/4/10.
 */
@Service
public class UserCustomServiceImpl implements UserCustomService{

    @Resource
    private UserCustomMapper userCustomMapper;


    Logger LOG = LoggerFactory.getLogger(UserCustomServiceImpl.class);

    @Override
    public Integer addUserCustomInfo(UserCustom userCustom) throws DatabaseException {
        try {
            if (userCustom == null){
                LOG.error("addUserCustomInfo 信息为空",userCustom);
                return Integer.valueOf(0);
            }
            int val = userCustomMapper.insertSelective(userCustom);
            if (val > 0){
                return userCustom.getId();
            }else {
                return Integer.valueOf(0);
            }
        } catch (Throwable e) {
            LOG.error("addUserCustomInfo 异常",userCustom);
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public boolean updateUserCustomInfo(UserCustom userCustom) throws DatabaseException {
        try {
            if (userCustom == null){
                LOG.error("updateUserCustomInfo 信息为空",userCustom);
                return false;
            }
            int val = userCustomMapper.updateByPrimaryKeySelective(userCustom);
            return (val>0)?true:false;
        } catch (Throwable e) {
            LOG.error("updateUserCustomInfo 异常",userCustom);
            throw new DatabaseException(e.getMessage());
        }
    }

	@Override
	public boolean updateUserCustomInfoByParam(UserCustom userCustom) throws DatabaseException {
		try {
            if (userCustom == null){
                LOG.error("updateUserCustomInfoByUserId 信息为空",userCustom);
                return false;
            }
            UserCustomExample example = new UserCustomExample();
            Criteria criteria = example.createCriteria();
            if(userCustom.getUserId() != null){
            	criteria.andUserIdEqualTo(userCustom.getUserId());
            }
            int val = userCustomMapper.updateByExampleSelective(userCustom, example);
            return (val>0)?true:false;
        } catch (Throwable e) {
            LOG.error("updateUserCustomInfoByUserId 异常",userCustom);
            throw new DatabaseException(e.getMessage());
        }
	}

	@Override
	public List<UserCustom> queryCustomInfo(UserCustom userCustom) throws DatabaseException {
		try {
            if (userCustom == null){
                LOG.error("queryCustomInfo 信息为空",userCustom);
                return null;
            }
            UserCustomExample example = new UserCustomExample();
            Criteria criteria = example.createCriteria();
            if(userCustom.getUserId() != null){
            	criteria.andUserIdEqualTo(userCustom.getUserId());
            }
            
            return userCustomMapper.selectByExample(example);
        } catch (Throwable e) {
            LOG.error("updateUserCustomInfoByUserId 异常",userCustom);
            throw new DatabaseException(e.getMessage());
        }
	}
}
