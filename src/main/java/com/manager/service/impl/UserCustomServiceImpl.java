package com.manager.service.impl;

import com.manager.exception.DatabaseException;
import com.manager.interceptor.PageMybatisInterceptor;
import com.manager.mapper.UserCustomMapper;
import com.manager.pojo.User;
import com.manager.pojo.UserCustom;
import com.manager.pojo.UserCustomExample;

import com.manager.pojo.UserCustomExample.Criteria;
import com.manager.request.custom.UserCustomRequest;
import com.manager.response.ItemResponse;
import com.manager.service.UserCustomService;
import com.manager.utils.Page;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    /**
     * 根据用户主键ID更新　信息
     * @param userCustom
     * @return
     * @throws DatabaseException
     */
    @Override
    public boolean updateUserCustomInfo(UserCustom userCustom) throws DatabaseException {
        try {
            if (userCustom == null){
                LOG.error("updateUserCustomInfo 信息为空",userCustom);
                return false;
            }
            if (userCustom.getUserId() == null){
                LOG.error("updateUserCustomInfo 用户主键ID为空",userCustom);
                return false;
            }
            UserCustomExample example = new UserCustomExample();
            UserCustomExample.Criteria criteria = example.createCriteria();
            criteria.andUserIdEqualTo(userCustom.getUserId());
            int val = userCustomMapper.updateByExampleSelective(userCustom,example);
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

	@Override
	public Page<UserCustom> selectUserCustom(UserCustomRequest userCustomRequest) throws DatabaseException {
		try {
            /*if (request == null){
                LOG.error("fetchUserInfoList 信息为空",request);
                return null;
            }*/
           
//            if (StringUtils.isNotBlank(userCustomRequest.getUsername())){
//            	User user = selectUserByNum(userCustomRequest.getUsername());
//            	if(user != null){
//            		itemRequest.setUserId(user.getId());
//            	}
//            }
			UserCustomExample example = new UserCustomExample();
			example.setOrderByClause("update_time desc");
			Criteria criteria = example.createCriteria();
			if(userCustomRequest.getUsername() != null && userCustomRequest.getUsername() != ""){
				criteria.andUsernameLike("%"+userCustomRequest.getUsername()+"%");
			}
            PageMybatisInterceptor.startPage(userCustomRequest.getPageNum(),userCustomRequest.getPageSize());
            userCustomMapper.selectByExample(example);
            Page<UserCustom> page = PageMybatisInterceptor.endPage();
            return page;
        } catch (Throwable e) {
            LOG.error("selectUserCustom 异常",userCustomRequest);
            throw new DatabaseException(e.getMessage());
        }
	}

	@Override
	public UserCustom getUserCustomById(Integer id) throws DatabaseException {
		// TODO Auto-generated method stub
		UserCustom userCustom = userCustomMapper.selectByPrimaryKey(id);
		
		return userCustom;
	}

	@Override
	public boolean updateUserCustom(UserCustom userCustom) throws DatabaseException {
		// TODO Auto-generated method stub
		userCustom.setUpdateTime(new Date());
		int i = userCustomMapper.updateByPrimaryKeySelective(userCustom);
		if(i > 0){
			return true;
		}
		return false;
	}
}
