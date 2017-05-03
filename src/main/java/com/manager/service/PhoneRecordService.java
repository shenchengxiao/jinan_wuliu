package com.manager.service;

import com.manager.exception.DatabaseException;
import com.manager.pojo.PhoneRecord;
import com.manager.request.phone.PhoneRecordRequest;
import com.manager.response.PhoneRecordResponse;
import com.manager.utils.Page;

public interface PhoneRecordService {
	
	public void updateByRecord(PhoneRecord phoneRecord) throws DatabaseException;

	Page<PhoneRecord> fetchPhoneRecodList(PhoneRecordRequest pRecordRequest) throws DatabaseException;

}
