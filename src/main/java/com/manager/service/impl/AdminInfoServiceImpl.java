package com.manager.service.impl;

import com.manager.exception.DatabaseException;
import com.manager.interceptor.PageMybatisInterceptor;
import com.manager.mapper.AdminMapper;
import com.manager.pojo.Admin;
import com.manager.pojo.AdminExample;
import com.manager.request.user.UserInfoRequest;
import com.manager.service.AdminInfoService;
import com.manager.utils.Page;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by shencx on 2017/3/28.
 */
@Service
public class AdminInfoServiceImpl implements AdminInfoService {

    @Resource
    private AdminMapper userInfoMapper;

    Logger LOG = LoggerFactory.getLogger(AdminInfoServiceImpl.class);


    /**
     * 获取用户列表
     * @param request
     * @return
     * @throws DatabaseException
     */
    @Override
    public Page<Admin> fetchUserInfoList(UserInfoRequest request) throws DatabaseException {

        try {
            if (request == null){
                LOG.error("fetchUserInfoList 信息为空",request);
                return null;
            }
            AdminExample example = new AdminExample();
            AdminExample.Criteria criteria = example.createCriteria();
            if (StringUtils.isNotBlank(request.getUserName())){
                StringBuffer sb = new StringBuffer();
                String userName = request.getUserName();
                sb.append("%");
                sb.append(userName);
                sb.append("%");
                criteria.andUserNameLike(sb.toString());
            }
            if (request.getStatus() != null){
                criteria.andBeUsedEqualTo(request.getStatus());
            }
            example.setOrderByClause("create_time desc");
            PageMybatisInterceptor.startPage(request.getPageNum(),request.getPageSize());
            userInfoMapper.selectByExample(example);
            Page<Admin> page = PageMybatisInterceptor.endPage();
            return page;
        } catch (Throwable e) {
            LOG.error("fetchUserInfoListm 异常",request);
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * 添加用户信息
     * @param userInfo
     * @return
     * @throws DatabaseException
     */
    @Override
    public Integer addUserInfo(Admin userInfo) throws DatabaseException {
        try {
            if (userInfo == null){
                LOG.error("addUserInfo 信息为空",userInfo);
                return Integer.valueOf(0);
            }
            userInfo.setCreateTime(new Date());
            int val = userInfoMapper.insert(userInfo);
            if (val > 0){
                return userInfo.getId();
            }else {
                return Integer.valueOf(0);
            }
        } catch (Throwable e) {
            LOG.error("addUserInfo 异常",userInfo);
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * 用户信息登录
     * @param userInfoRequest
     * @return
     * @throws DatabaseException
     */
    @Override
    public Admin fetchUserByUserNameAndPasswd(UserInfoRequest userInfoRequest) throws DatabaseException {
        try {
            if (userInfoRequest == null){
                LOG.error("fetchUserByUserNameAndPasswd 信息为空",userInfoRequest);
                return null;
            }
            AdminExample example = new AdminExample();
            AdminExample.Criteria criteria = example.createCriteria();
            criteria.andUserNameEqualTo(userInfoRequest.getUserName());
            criteria.andPasswdEqualTo(userInfoRequest.getPasswd());
            criteria.andBeUsedEqualTo(1);
            List<Admin> list = userInfoMapper.selectByExample(example);
            if (list == null || list.size() == 0){
                return null;
            }else {
                return list.get(0);
            }

        } catch (Throwable e) {
            LOG.error("fetchUserByUserNameAndPasswd 异常",userInfoRequest);
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public boolean updateUserInfo(Admin userInfo) throws DatabaseException {
        try {
            if (userInfo == null){
                LOG.error("updateUserInfo 信息为空",userInfo);
                return false;
            }
           int val = userInfoMapper.updateByPrimaryKeySelective(userInfo);
            return (val > 0)?true:false;
        } catch (Throwable e) {
            LOG.error("updateUserInfo 异常",userInfo);
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public boolean deleteUserInfo(Integer id) throws DatabaseException {
        try {
            if (id == null){
                LOG.error("deleteUserInfo id为空",id);
                return false;
            }
            int val = userInfoMapper.deleteByPrimaryKey(id);
            return (val>0)?true:false;
        } catch (Throwable e) {
            LOG.error("deleteUserInfo 异常",id);
            throw new DatabaseException(e.getMessage());
        }
    }

    @Override
    public Admin fetchUserInfoById(UserInfoRequest request) throws DatabaseException {
        try {
            if (request == null){
                LOG.error("fetchUserInfoById 为空",request);
                return null;
            }
            AdminExample example = new AdminExample();
            AdminExample.Criteria criteria = example.createCriteria();
            if (request.getId() != null){
                criteria.andIdEqualTo(request.getId());
            }
            if (StringUtils.isNoneBlank(request.getPasswd())){
                criteria.andPasswdEqualTo(request.getPasswd());
            }
            List<Admin> list = userInfoMapper.selectByExample(example);
            if (list != null && list.size() > 0){
                return list.get(0);
            }else {
                return null;
            }

        } catch (Throwable e) {
            LOG.error("fetchUserInfoById 异常",request);
            throw new DatabaseException(e.getMessage());
        }
    }
}
