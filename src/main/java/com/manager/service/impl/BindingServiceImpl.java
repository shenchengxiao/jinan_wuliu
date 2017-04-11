package com.manager.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.manager.exception.DatabaseException;
import com.manager.mapper.BlackWordMapper;
import com.manager.mapper.UserBindingMapper;
import com.manager.mapper.UserMapper;
import com.manager.pojo.BlackWord;
import com.manager.pojo.User;
import com.manager.pojo.UserBinding;
import com.manager.pojo.UserBindingExample;
import com.manager.pojo.UserBindingExample.Criteria;
import com.manager.pojo.UserExample;
import com.manager.request.blackword.BlackWordRequest;
import com.manager.response.BlackWordResponse;
import com.manager.service.BindingService;
import com.manager.utils.Page;

@Service
public class BindingServiceImpl implements BindingService{

	@Resource
    private UserBindingMapper userBindingMapper;
	@Resource
    private UserMapper userMapper;

    Logger LOG = LoggerFactory.getLogger(BindingServiceImpl.class);
	
	@Override
	public Page<BlackWordResponse> fetchBlackwordList(BlackWordRequest request) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteBlackword(Integer id) throws DatabaseException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBlackword(BlackWord blackWord) throws DatabaseException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addBinding(UserBinding userBinding) throws DatabaseException {
		try {
			UserExample uexample = new UserExample();
			com.manager.pojo.UserExample.Criteria c = uexample.createCriteria();
			c.andUsernameEqualTo(userBinding.getUserName());
			List<User> user = userMapper.selectByExample(uexample);
			if(user != null && user.size()>0){
				userBinding.setUserId(user.get(0).getId());
				UserBindingExample example = new UserBindingExample();
				Criteria criteria = example.createCriteria();
				criteria.andUserNameEqualTo(userBinding.getUserName());
				List<UserBinding> uBindingList = userBindingMapper.selectByExample(example);
				if(uBindingList != null && uBindingList.size()>0){
					userBindingMapper.updateByExampleSelective(userBinding, example);
				}else{
					userBindingMapper.insertSelective(userBinding);
				}
				return true;
			}else{
				
				return false;
			}
			
		} catch (Throwable e) {
            LOG.error("addBinding 异常",userBinding.getId());
            throw new DatabaseException(e.getMessage());
        }
		
	}

	/**
	 * 根据用户主键ID更新信息
	 * @param userBinding
	 * @return
	 * @throws DatabaseException
	 */
	@Override
	public boolean updateUserBinding(UserBinding userBinding) throws DatabaseException {
		try {
			if (userBinding == null){
				LOG.error("updateUserBinding 信息为空",userBinding);
				return false;
			}
			if (userBinding.getUserId() == null){
				LOG.error("updateUserBinding 用户主键ID为空",userBinding);
				return false;
			}
			UserBindingExample example = new UserBindingExample();
			UserBindingExample.Criteria criteria = example.createCriteria();
			criteria.andUserIdEqualTo(userBinding.getUserId());
			Integer val = userBindingMapper.updateByExampleSelective(userBinding,example);
			return val>0?true:false;
		}catch (Throwable e) {
			LOG.error("updateUserBinding 异常",userBinding.getId());
			throw new DatabaseException(e.getMessage());
		}
	}

	@Override
	public BlackWord fetchBlackWordDetail(Integer id) throws DatabaseException {
		// TODO Auto-generated method stub
		return null;
	}

}
