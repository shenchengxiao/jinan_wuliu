package com.manager.service;

import com.manager.exception.DatabaseException;
import com.manager.pojo.manual.UserLoginlogResponse;
import com.manager.request.ipvisit.IpVisitRequest;
import com.manager.request.userloginlog.UserLoginLogRequest;
import com.manager.response.IpVisitResponse;
import com.manager.utils.Page;

public interface IpVisitService {
	
    Page<IpVisitResponse> fetchIpVisitList(IpVisitRequest request) throws DatabaseException;

	Page<UserLoginlogResponse> fetchIpVisitList2(UserLoginLogRequest request) throws DatabaseException;

}
