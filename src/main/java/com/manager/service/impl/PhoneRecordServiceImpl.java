package com.manager.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manager.exception.DatabaseException;
import com.manager.interceptor.PageMybatisInterceptor;
import com.manager.mapper.PhoneRecordMapper;
import com.manager.mapper.UserMapper;
import com.manager.pojo.PhoneRecord;
import com.manager.pojo.PhoneRecordExample;
import com.manager.pojo.User;
import com.manager.pojo.UserExample;
import com.manager.pojo.UserExample.Criteria;
import com.manager.request.item.ItemRequest;
import com.manager.request.phone.PhoneRecordRequest;
import com.manager.response.ItemResponse;
import com.manager.response.PhoneRecordResponse;
import com.manager.service.PhoneRecordService;
import com.manager.utils.Page;
import com.manager.utils.StringUtils;

@Service
public class PhoneRecordServiceImpl implements PhoneRecordService{
	
	Logger LOG = LoggerFactory.getLogger(PhoneRecordServiceImpl.class);
	
	@Autowired
	private PhoneRecordMapper pMapper;
	
	@Autowired
	private UserMapper uMapper;

	/**
	 * 拨/挂电话记录
	 */
	@Override
	public void updateByRecord(PhoneRecord phoneRecord) throws DatabaseException {
		try{	
			UserExample example = new UserExample();
			Criteria criteria = example.createCriteria();
			criteria.andPhonesLike("%"+phoneRecord.getInPhone()+"%");
			List<User> userList = uMapper.selectByExample(example);
			if(userList != null && userList.size() > 0){
				User user = userList.get(0);
				
				phoneRecord.setCustId(user.getUserNum());
				
			}
			
			if(!org.springframework.util.StringUtils.isEmpty(phoneRecord.getIsAnswer())){
				if(phoneRecord.getIsAnswer() == 1){
					phoneRecord.setInTime(new Date());
				}
			}else if(!org.springframework.util.StringUtils.isEmpty(phoneRecord.getIsHang())){
				if(phoneRecord.getIsHang() == 1){
					phoneRecord.setOutTime(new Date());
				}
				
			}
			
			PhoneRecordExample pexample = new PhoneRecordExample();
			pexample.setOrderByClause("create_time desc");
			com.manager.pojo.PhoneRecordExample.Criteria c = pexample.createCriteria();
			c.andInPhoneEqualTo(phoneRecord.getInPhone());
			List<PhoneRecord> prlist = pMapper.selectByExample(pexample);
			if(prlist != null && prlist.size() > 0){
				PhoneRecord phoneR = prlist.get(0);
				if(phoneR.getIsAnswer() == 1 && phoneR.getIsHang() == 1){
					phoneRecord.setCreateTime(new Date());
					pMapper.insertSelective(phoneRecord);
					return;
				}
				phoneRecord.setrId(phoneR.getrId());
				phoneRecord.setUpdateTime(new Date());
				pMapper.updateByPrimaryKeySelective(phoneRecord);
				
				return;
			}
			phoneRecord.setCreateTime(new Date());
			pMapper.insertSelective(phoneRecord);
		} catch (Throwable e) {
	        LOG.error("updateByRecord 异常",e,phoneRecord);
	        throw new DatabaseException(e.getMessage());
	    }
		
		
	}
	
	@Override
	public Page<PhoneRecord> fetchPhoneRecodList(PhoneRecordRequest pRecordRequest) throws DatabaseException{
		try {
           
			PhoneRecordExample pexample = new PhoneRecordExample();
			pexample.setOrderByClause("create_time desc");
			com.manager.pojo.PhoneRecordExample.Criteria c = pexample.createCriteria();
			if(pRecordRequest != null){
				if (org.apache.commons.lang3.StringUtils.isNotBlank(pRecordRequest.getCustId())){
	            	c.andCustIdEqualTo(pRecordRequest.getCustId());
	            }
				if (org.apache.commons.lang3.StringUtils.isNotBlank(pRecordRequest.getInPhone())){
	            	c.andInPhoneLike("%"+pRecordRequest.getInPhone()+"%");
	            }
				if (org.apache.commons.lang3.StringUtils.isNotBlank(pRecordRequest.getServiceNum())){
	            	c.andServiceNumEqualTo(pRecordRequest.getServiceNum());
	            }
				if (pRecordRequest.getInTime() != null){
	            	c.andInTimeGreaterThanOrEqualTo(pRecordRequest.getInTime());
	            }
				if (pRecordRequest.getOutTime() != null){
	            	c.andOutTimeLessThanOrEqualTo(pRecordRequest.getOutTime());
	            }
			}
			
            PageMybatisInterceptor.startPage(pRecordRequest.getPageNum(),pRecordRequest.getPageSize());
            List<PhoneRecord> pList = pMapper.selectByExample(pexample);
            if(pList != null && pList.size()>0){
            	for(PhoneRecord pr : pList){
            		if(pr.getIsAnswer() == 1 && pr.getIsHang() == 1){
            			long between = pr.getOutTime().getTime() - pr.getInTime().getTime();
            			long min = between/(1000); 
                		pr.setDuration(min);
            		}
            	}
            }
            Page<PhoneRecord> page = PageMybatisInterceptor.endPage();
            int count = page.getTotal();
            List<PhoneRecord> prList = pMapper.selectByExample(pexample);
            long sumduration = 0;
            if(prList != null && prList.size()>0){
            	for(PhoneRecord pr : prList){
            		if(pr.getIsAnswer() == 1 && pr.getIsHang() == 1){
            			long between = pr.getOutTime().getTime() - pr.getInTime().getTime();
            			long min = between/(1000);   
                		pr.setDuration(min);
                		sumduration += min;
            		}
            	}
            }
            
            if(page.getResult() != null && page.getResult().size()>0){
            	PhoneRecord result = page.getResult().get(0);
                result.setSumduration(sumduration);
                result.setCount(count);
                
                PhoneRecordExample prexample = new PhoneRecordExample();
                
                List<PhoneRecord> prCountList = pMapper.selectByExample(prexample);
                long totaltime = 0;
                if(prCountList != null && prCountList.size()>0){
                	for(PhoneRecord pr : prCountList){
                		if(pr.getIsAnswer() == 1 && pr.getIsHang() == 1){
                			long between = pr.getOutTime().getTime() - pr.getInTime().getTime();
                			long min = between/(1000); 
                    		pr.setDuration(min);
                    		totaltime += min;
                		}
                	}
                }
                result.setTotaltime(totaltime);
                result.setTotal(prCountList.size());
            }
            
            return page;
        } catch (Throwable e) {
            LOG.error("fetchPhoneRecodList 异常",pRecordRequest);
            throw new DatabaseException(e.getMessage());
        }
	}

}
