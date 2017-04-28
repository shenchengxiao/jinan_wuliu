package com.manager.service;

import com.manager.exception.DatabaseException;
import com.manager.pojo.PhoneRecord;

public interface PhoneRecordService {
	
	public void updateByRecord(PhoneRecord phoneRecord) throws DatabaseException;

}
