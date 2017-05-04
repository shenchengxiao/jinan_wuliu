package com.manager.handler;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.manager.exception.DatabaseException;
import com.manager.exception.YCException;
import com.manager.pojo.PhoneRecord;
import com.manager.request.phone.PhoneRecordRequest;
import com.manager.service.PhoneRecordService;
import com.manager.utils.Page;
import com.manager.utils.YCSystemStatusEnum;

@Service
public class PhoneRecordHandler {
	
	Logger LOG = LoggerFactory.getLogger(PhoneRecordHandler.class);

	@Resource
	private PhoneRecordService phoneRecordService;
	
	public void updatePhoneRecord(PhoneRecord phoneRecord) throws YCException{
        try {
        	
        	phoneRecordService.updateByRecord(phoneRecord);
        	
        } catch (DatabaseException e) {
            LOG.error("updatePhoneRecord exception",phoneRecord.getInPhone());
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }
	
	public Page<PhoneRecord> fetchPhoneRecodList(PhoneRecordRequest pRecordRequest) throws YCException{
		try {	
		
			return phoneRecordService.fetchPhoneRecodList(pRecordRequest);
			
		} catch (DatabaseException e) {
	        LOG.error("fetchPhoneRecodList exception",pRecordRequest.getInPhone());
	        throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
	    }
	}

}
