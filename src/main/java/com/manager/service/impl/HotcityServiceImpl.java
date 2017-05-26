package com.manager.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.manager.exception.DatabaseException;
import com.manager.mapper.HotCityMapper;
import com.manager.pojo.HotCity;
import com.manager.pojo.HotCityExample;
import com.manager.service.HotcityService;

@Service
public class HotcityServiceImpl implements HotcityService{
	
	Logger LOG = LoggerFactory.getLogger(HotcityServiceImpl.class);
	
	@Resource
	private HotCityMapper hotcityMapper;

	@Override
	public void insertCity(HotCity hotCity) throws DatabaseException{
		try{
			HotCityExample example = new HotCityExample();
			List<HotCity> hotcityList = hotcityMapper.selectByExample(example);
			if(hotcityList != null && hotcityList.size()>0){
				hotCity.setUpdateTime(new Date());
				hotcityMapper.updateByPrimaryKeySelective(hotCity);
			}else{
				hotCity.setCreateTime(new Date());
				hotcityMapper.insert(hotCity);
			}
		
		} catch (Throwable e) {
	        LOG.error("fetchBlackwordList 异常",hotCity);
	        throw new DatabaseException(e.getMessage());
	    }

	}

	@Override
	public List<HotCity> selectCity() throws DatabaseException {
		HotCityExample example = new HotCityExample();
		return hotcityMapper.selectByExampleWithBLOBs(example);
	}

}
