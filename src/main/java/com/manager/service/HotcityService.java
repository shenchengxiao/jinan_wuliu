package com.manager.service;

import java.util.List;

import com.manager.exception.DatabaseException;
import com.manager.pojo.HotCity;

public interface HotcityService {
	
	public void insertCity(HotCity hotCity)throws DatabaseException;
	
	public List<HotCity> selectCity()throws DatabaseException;

}
