package com.manager.handler;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manager.exception.DatabaseException;
import com.manager.exception.YCException;
import com.manager.pojo.BlackWord;
import com.manager.pojo.IpVisit;
import com.manager.request.blackword.BlackWordRequest;
import com.manager.request.ipvisit.IpVisitRequest;
import com.manager.response.BlackWordResponse;
import com.manager.response.IpVisitResponse;
import com.manager.service.BlackWordService;
import com.manager.service.IpVisitService;
import com.manager.utils.DateTimeUtil;
import com.manager.utils.Page;
import com.manager.utils.Validator;
import com.manager.utils.YCSystemStatusEnum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

/**
 * 
 * @author zcy
 *
 */
@Service
public class IpVisitHandler {

    @Resource
    private IpVisitService ipVisitService;

    Logger LOG = LoggerFactory.getLogger(IpVisitHandler.class);


    /**
     * 获取IP访问列表信息
     * @param request
     * @return
     * @throws YCException
     */
    public Page<IpVisitResponse> fetchIpVisitList(IpVisitRequest request) throws YCException {
        /** 参数校验 */
        //Validator.isEmpty(request,YCSystemStatusEnum.PARAM_EMPTY);
        Page<IpVisitResponse> page = null;
        try {
            page = ipVisitService.fetchIpVisitList(request);
            page.setPagesize(page.getPagesize());
            page.setPageindex(page.getPageindex());
            page.setTotal(page.getTotal());
        } catch (DatabaseException e) {
            LOG.error("fetchIpVisitList exception",request);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
        return page;
    }


	public void addServer(IpVisitRequest request) throws YCException{
		/** 参数校验 */
        Validator.isEmpty(request,YCSystemStatusEnum.PARAM_EMPTY);
        Validator.isEmpty(request.getIp(),"服务器ip不能为空");
        Validator.isEmpty(request.getPort(),"服务器端口不能为空");
        Validator.isEmpty(request.getCreateTime(),"添加时间不能为空");
        
        IpVisit ipVisit = new IpVisit();
        ipVisit.setId(request.getId());
        ipVisit.setIp(request.getIp());
        ipVisit.setPort(request.getPort());
        ipVisit.setCreateTime(DateTimeUtil.convertDate(request.getCreateTime()));
        ipVisit.setDomain(request.getDomain());
        ipVisit.setFunctionDesc(request.getFunctionDesc());
        ipVisit.setStatus(request.getStatus());
        
        try {
            //id为空则添加，否则为修改
            if (ipVisit.getId() == null){
            	ipVisitService.addServer(ipVisit);
            }else {
            	ipVisitService.updateServer(ipVisit);
            }
        }catch (DatabaseException e) {
            LOG.error("addServer exception",ipVisit);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
        
        
        
		
	}


	public void deleteServer(Integer id) throws YCException{
		 /** 参数校验 */
        Validator.isEmpty(id,"删除服务器的id为空");
        try {
        	ipVisitService.deleteServer(id);
        } catch (DatabaseException e) {
            LOG.error("deleteAdvertInfo exception",id);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
		
	}


	public void delLoginLogs(String ids) throws YCException{
		// TODO Auto-generated method stub
		 /** 参数校验 */
        Validator.isEmpty(ids,YCSystemStatusEnum.USER_ID_EMPTY);
        try {
            List<Integer> list = new ArrayList<>();
            JSONArray jsonArray = JSON.parseArray(ids);
            for (Object object : jsonArray) {
                JSONObject jsonObject = JSONObject.parseObject(object.toString());
                String id =jsonObject.get("id").toString();
                list.add(Integer.valueOf(id));
            }

            ipVisitService.delLoginLogs(list);
        } catch (DatabaseException e) {
            LOG.error("deleteUserInfo exception",ids);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
	}
    
}
