package com.manager.handler;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manager.exception.DatabaseException;
import com.manager.exception.YCException;
import com.manager.request.message.UserMessageRequest;
import com.manager.service.UserMessageService;
import com.manager.utils.Validator;
import com.manager.utils.YCSystemStatusEnum;

@Service
public class UserMessageHandler {
		
	
	 @Resource
	 private UserMessageService userMessageService;
	 
	
	 Logger LOG = LoggerFactory.getLogger(ItemHandler.class);
	 //发送用户消息通知
	 public void sendUserMessage(UserMessageRequest userMessageRequest) throws YCException {
	        /** 参数校验 */
	        Validator.isEmpty(userMessageRequest.getUserIds(),YCSystemStatusEnum.USER_ID_EMPTY);
	        Validator.isEmpty(userMessageRequest.getContent(),YCSystemStatusEnum.NULL);
	        Validator.isEmpty(userMessageRequest.getmType(),YCSystemStatusEnum.NULL);
	        try {
	        	List<Integer> list = new ArrayList<>();
	            JSONArray jsonArray = JSON.parseArray(userMessageRequest.getUserIds());
	            for (Object object : jsonArray) {
	                JSONObject jsonObject = JSONObject.parseObject(object.toString());
	                String id =jsonObject.get("id").toString();
	                list.add(Integer.valueOf(id));
	            }
	            if(list != null && list.size()>0){
	            	Validator.isEmpty(list.get(0),YCSystemStatusEnum.USER_ID_EMPTY);
	            	userMessageService.sendUserMessage(list,userMessageRequest.getmType(),userMessageRequest.getContent());
	            }
	        } catch (DatabaseException e) {
	            LOG.error("sendUserMessage exception",userMessageRequest.toString());
	            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
	        }
	    }
	 
	 
	public void sendSysMessage(Integer mType, String content) throws YCException{
		// TODO Auto-generated method stub
		/** 参数校验 */
        Validator.isEmpty(content,YCSystemStatusEnum.NULL);
        try {
            	userMessageService.sendSysMessage(mType,content);
        } catch (DatabaseException e) {
            LOG.error("sendSysMessage exception",content);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
	}
}
