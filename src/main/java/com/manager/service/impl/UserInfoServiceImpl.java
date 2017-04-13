package com.manager.service.impl;

import com.manager.enums.YesNoEnum;
import com.manager.exception.DatabaseException;
import com.manager.interceptor.PageMybatisInterceptor;
import com.manager.mapper.UserMapper;
import com.manager.mapper.manual.ICustomizedUserManageMapper;
import com.manager.pojo.User;
import com.manager.pojo.UserExample;
import com.manager.pojo.UserExample.Criteria;
import com.manager.request.user.OnlineUserRequest;
import com.manager.request.user.UserManageRequest;
import com.manager.response.UserMangeResponse;


import com.manager.service.UserInfoService;
import com.manager.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * Created by shencx on 2017/4/10.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService{

    @Resource
    private UserMapper userMapper;

    @Resource
    private ICustomizedUserManageMapper customizedUserManageMapper;

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
            int val = userMapper.updateByPrimaryKeySelective(user);
            return val>0?true:false;
        } catch (Throwable e) {
            LOG.error("updateUser 异常",user);
            throw new DatabaseException(e.getMessage());
        }
    }


	@Override
	public List<User> queryUser(User user) throws DatabaseException {
		try {
			UserExample example = new UserExample();
			Criteria criteria = example.createCriteria();
			if(StringUtils.isNoneEmpty(user.getUserNum())){
				criteria.andUserNumEqualTo(user.getUserNum());
			}
			if(StringUtils.isNoneEmpty(user.getUsername())){
				criteria.andUsernameEqualTo(user.getUsername());
			}

			return userMapper.selectByExample(example);

		} catch (Throwable e) {
	        LOG.error("queryUser 异常",user);
	        throw new DatabaseException(e.getMessage());
	    }
	}


    /**
     * 获取用户详情信息
     * @param request
     * @return
     * @throws DatabaseException
     */
    public UserMangeResponse getUserDetail(UserManageRequest request) throws DatabaseException {
        try {
            if (request == null){
                LOG.error("getUserDetail 信息为空",request);
                return null;
            }
            if (request.getId() == null){
                LOG.error("getUserDetail 主键ID为空",request);
                return null;
            }
            UserMangeResponse userMangeResponse = customizedUserManageMapper.findUserInfoDetail(request);
            return userMangeResponse;
        }  catch (Throwable e) {
            LOG.error("getUserDetail 异常",request);
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * 获取用户列表
     * @param request
     * @return
     * @throws DatabaseException
     */
    public Page<UserMangeResponse> getUserList(UserManageRequest request) throws DatabaseException {
        try {
            if (request == null){
                LOG.error("getUserList 信息为空",request);
                return null;
            }
            PageMybatisInterceptor.startPage(request.getPageNum(),request.getPageSize());
            customizedUserManageMapper.findUserInfoPage(request);
            Page<UserMangeResponse> page = PageMybatisInterceptor.endPage();
            return page;
        }  catch (Throwable e) {
            LOG.error("getUserList 异常",request);
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public boolean modifyStatus(Integer id, Integer enabled) throws DatabaseException {
        try {
            if (id == null){
                LOG.error("modifyStatus id为空",id);
                return false;
            }
            User record = new User();
            record.setId(id);
            record.setStatus(YesNoEnum.create(enabled));
            UserExample example = new UserExample();
            UserExample.Criteria criteria = example.createCriteria();
            criteria.andIdEqualTo(id);
            int val = userMapper.updateByExampleSelective(record,example);
            return val>0?true:false;
        } catch (Throwable e) {
            LOG.error("modifyStatus 异常",id);
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * 批量查询用户
     * @param request
     * @return
     */
    @Override
    public Page<UserMangeResponse> getUserByUserIds(OnlineUserRequest request) throws DatabaseException {
        try {
            if (request == null){
                LOG.error("getUserByUserIds 信息为空",request);
                return null;
            }
            if (request.getIdsList() == null || request.getIdsList().size() ==0){
                LOG.error("getUserByUserIds id为空",request);
                return null;
            }
            PageMybatisInterceptor.startPage(request.getPageNum(),request.getPageSize());
            customizedUserManageMapper.findUserByUserIds(request);
            Page<UserMangeResponse> page = PageMybatisInterceptor.endPage();
            return page;
        } catch (Throwable e) {
            LOG.error("getUserByUserIds 异常",request);
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * 批量更新用户状态
     * @param ids
     * @return
     * @throws DatabaseException
     */
    @Override
    public boolean batchUpdateUserStatus(List<Integer> ids) throws DatabaseException {
        try {
            if (ids == null){
                LOG.error("batchUpdateUserStatus 信息为空",ids);
                return false;
            }
            Integer val = customizedUserManageMapper.batchUpdateStatus(ids);
            return (val>0)?true:false;
        } catch (Throwable e) {
            LOG.error("batchUpdateUserStatus 异常",ids);
            throw new DatabaseException(e.getMessage());
        }
    }
}
