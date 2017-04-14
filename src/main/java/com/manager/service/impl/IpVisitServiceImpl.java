package com.manager.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.manager.exception.DatabaseException;
import com.manager.interceptor.PageMybatisInterceptor;
import com.manager.mapper.IpVisitMapper;
import com.manager.pojo.IpVisit;
import com.manager.pojo.IpVisitExample;
import com.manager.request.ipvisit.IpVisitRequest;
import com.manager.response.BlackWordResponse;
import com.manager.response.IpVisitResponse;
import com.manager.service.IpVisitService;
import com.manager.utils.Page;

@Service
public class IpVisitServiceImpl implements IpVisitService {
	
	@Resource
	private IpVisitMapper mapper;
	
	 Logger LOG = LoggerFactory.getLogger(IpVisitServiceImpl.class);

	@Override
	public Page<IpVisitResponse> fetchIpVisitList(IpVisitRequest request) throws DatabaseException {
		try{
			IpVisitExample example = new IpVisitExample();
			PageMybatisInterceptor.startPage(request.getPageNum(),request.getPageSize());
			mapper.selectByExample(example);
			Page<IpVisitResponse> page = PageMybatisInterceptor.endPage();
			
			return page;
		} catch (Throwable e) {
	        LOG.error("fetchBlackwordList 异常",request);
	        throw new DatabaseException(e.getMessage());
	    }
		
	}

}
