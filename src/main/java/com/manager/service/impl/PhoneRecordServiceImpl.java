package com.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.exception.DatabaseException;
import com.manager.mapper.PhoneRecordMapper;
import com.manager.mapper.UserMapper;
import com.manager.pojo.PhoneRecord;
import com.manager.pojo.PhoneRecordExample;
import com.manager.pojo.User;
import com.manager.pojo.UserExample;
import com.manager.pojo.UserExample.Criteria;
import com.manager.service.PhoneRecordService;

@Service
public class PhoneRecordServiceImpl implements PhoneRecordService{
	
	@Autowired
	private PhoneRecordMapper pMapper;
	
	@Autowired
	private UserMapper uMapper;

	/**
	 * 拨/挂电话记录
	 */
	@Override
	public void updateByRecord(PhoneRecord phoneRecord) throws DatabaseException {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andPhonesLike("%"+phoneRecord.getInPhone()+"%");
		List<User> userList = uMapper.selectByExample(example);
		if(userList != null && userList.size() > 0){
			User user = userList.get(0);
			
			phoneRecord.setCustId(user.getUserNum());
			
		}
		
		PhoneRecordExample pexample = new PhoneRecordExample();
		com.manager.pojo.PhoneRecordExample.Criteria c = pexample.createCriteria();
		c.andInPhoneEqualTo(phoneRecord.getInPhone());
		List<PhoneRecord> prlist = pMapper.selectByExample(pexample);
		if(prlist != null && prlist.size() > 0){
			phoneRecord.setrId(prlist.get(0).getrId());
			pMapper.updateByPrimaryKey(phoneRecord);
			
			return;
		}
		
		pMapper.insertSelective(phoneRecord);
		
		
		
	}

}
