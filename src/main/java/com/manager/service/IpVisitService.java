package com.manager.service;

import com.manager.exception.DatabaseException;
import com.manager.pojo.Advert;
import com.manager.pojo.BlackWord;
import com.manager.pojo.IpVisit;
import com.manager.request.blackword.BlackWordRequest;
import com.manager.request.ipvisit.IpVisitRequest;
import com.manager.response.BlackWordResponse;
import com.manager.response.IpVisitResponse;
import com.manager.utils.Page;

public interface IpVisitService {
	
    Page<IpVisitResponse> fetchIpVisitList(IpVisitRequest request) throws DatabaseException;

}
