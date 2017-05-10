package com.manager.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.manager.exception.DatabaseException;
import com.manager.interceptor.PageMybatisInterceptor;
import com.manager.mapper.IpVisitMapper;
import com.manager.mapper.UserLoginlogMapper;
import com.manager.mapper.manual.UserLoginLogResponseMapper;
import com.manager.pojo.IpVisit;
import com.manager.pojo.IpVisitExample;
import com.manager.pojo.manual.UserLoginlogResponse;
import com.manager.request.ipvisit.IpVisitRequest;
import com.manager.request.userloginlog.UserLoginLogRequest;
import com.manager.response.IpVisitResponse;
import com.manager.service.IpVisitService;
import com.manager.utils.Page;

@Service
public class IpVisitServiceImpl implements IpVisitService {
	
	@Resource
	private IpVisitMapper mapper;
	@Resource
	private UserLoginLogResponseMapper userLoginLogResponseMapper;
	@Resource
	private UserLoginlogMapper userLoginlogMapper;
	
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

	@Override
	public Page<UserLoginlogResponse> fetchIpVisitList2(UserLoginLogRequest request) throws DatabaseException {
		try{
			PageMybatisInterceptor.startPage(request.getPageNum(),request.getPageSize());
			userLoginLogResponseMapper.selectByParams(request);
			Page<UserLoginlogResponse> page = PageMybatisInterceptor.endPage();
			
			return page;
		} catch (Throwable e) {
	        LOG.error("fetchIpVisitList2 异常",request);
	        throw new DatabaseException(e.getMessage());
	    }
	}

	@Override
	public Page<UserLoginlogResponse> fetchIpVisitList3(UserLoginLogRequest request) throws DatabaseException {
		try{
			PageMybatisInterceptor.startPage(request.getPageNum(),request.getPageSize());
			userLoginLogResponseMapper.selectLoginLogByParams(request);
			Page<UserLoginlogResponse> page = PageMybatisInterceptor.endPage();
			
			return page;
		} catch (Throwable e) {
	        LOG.error("fetchIpVisitList3 异常",request);
	        throw new DatabaseException(e.getMessage());
	    }
	}

	@Override
	public void addServer(IpVisit ipVisit) throws DatabaseException {
		// TODO Auto-generated method stub
		mapper.insert(ipVisit);
		
	}

	@Override
	public void updateServer(IpVisit ipVisit) throws DatabaseException {
		// TODO Auto-generated method stub
		mapper.updateByPrimaryKeySelective(ipVisit);
	}

	@Override
	public void deleteServer(Integer id) throws DatabaseException {
		// TODO Auto-generated method stub
		mapper.deleteByPrimaryKey(id);
	}

	@Override
	public boolean delLoginLogs(List<Integer> ids_arr) throws DatabaseException {
		// TODO Auto-generated method stub
		try {
			int result = 0;
            if (ids_arr .size() <= 0){
                LOG.error("delLoginLogs ids为空",ids_arr);
                return false;
            }
            for(int i = 0; i < ids_arr.size(); i++){
            	
            	result = userLoginlogMapper.deleteByPrimaryKey(ids_arr.get(i));
            }
    		
    		return result> 0?true:false;
        } catch (Throwable e) {
            LOG.error("deleteUserInfo 异常",ids_arr);
            throw new DatabaseException(e.getMessage());
        }
	}

}
