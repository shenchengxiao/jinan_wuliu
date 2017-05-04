package com.manager.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.manager.exception.DatabaseException;
import com.manager.exception.YCException;
import com.manager.mapper.UserMapper;
import com.manager.mapper.UserMessageMapper;
import com.manager.pojo.User;
import com.manager.pojo.UserMessage;
import com.manager.service.UserMessageService;
import com.manager.utils.PushExample;
import com.manager.utils.URLConnUtil;
import com.manager.utils.YCSystemStatusEnum;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import net.sf.json.JSONObject;

@Service
public class UserMessageServiceImpl implements UserMessageService {
	
//	 private static final String EXCHANGE_NAME = "JINAN_MESSAGE_EXCHANGE"; 
	
	 @Value("${api.kick.out}")
     private String kickOutUser;

     @Value("${jinan.user.url}")
     private String host;
	 
	 @Resource
	 private UserMessageMapper userMessageMapper;
	 @Resource
	 private UserMapper userMapper;
	 Logger LOG = LoggerFactory.getLogger(BlackWordServiceImpl.class);
	 
	@Override
	public boolean sendUserMessage(List<Integer> list, String content) throws DatabaseException {
		try {
			int val = -1;
//			int andriod = 0;
//			int ios = 0;
//			String StrAndriod[]=new String[]{};
//			String StrIOS[]=new String[]{};
			
            if (content == null){
                LOG.error("sendUserMessage 信息为空",content);
                return false;
            }
            Map<String, String> map = new HashMap<>();
            map.put("type", "1");
//            int type = 1;//1.代表给单独或多个用户发送消息通知;2.代表广播系统消息;0.代表提出单个或多个用户
//            String json = "{\"type\":\"" + type + "\",\"content\":" + content+ "}";
            // 创建连接和频道  
//    		ConnectionFactory factory = new ConnectionFactory();  
//    		factory.setHost("139.129.232.78");
            //端口
//            factory.setPort(5672);
//            factory.setPort(5670);
            //设置账号信息，用户名、密码、vhost
//            factory.setUsername("admin");
//            factory.setPassword("adminjinan");
//            factory.setVirtualHost("/jinan");
//    		Connection connection = factory.newConnection();  
//    		Channel channel = connection.createChannel();  
    		// 声明转发器的类型  
//    		channel.exchangeDeclare(EXCHANGE_NAME, "topic");
            List<Integer> list_pc = new ArrayList<>();
            for (Integer userid : list) {
            	UserMessage userMessage = new UserMessage();
				userMessage.setUserId(userid);
				userMessage.setContent(content);
				userMessage.setCreateTime(new Date());
            	
            	val = userMessageMapper.insert(userMessage);
            	if(val > 0){
            		//MQ发送消息
            		// 发布消息至转发器，指定routingkey  
//                    channel.basicPublish(EXCHANGE_NAME, userid+"", null, content  
//                            .getBytes());  
//                    System.out.println(" [x] Sent '" + content + "'");
            		User user = userMapper.selectByPrimaryKey(userid);
            		if(user != null){
            			if(user.getPlatformType() != null && user.getPlatformType().getValue() == 2 && user.getRegistrationid() != null){
            				PushExample.SendUsersPushToAndroid("济南网通知",content,map,user.getRegistrationid());
//            				StrAndriod[andriod] = user.getRegistrationid();
//            				andriod ++;
            			}else if(user.getPlatformType() != null && user.getPlatformType().getValue() == 1 && user.getRegistrationid() != null){
            				PushExample.SendUsersPushToIOS(content,map, user.getRegistrationid());
//							StrIOS[ios] = user.getRegistrationid();
//							ios ++;
						}else if(user.getPlatformType() != null && user.getPlatformType().getValue() == 0){
							list_pc.add(userid);
						}
            		}
            	}
			}
            //消息通知给pc客户端,type为4
            SendUsersPushToPC(list_pc,4,content,host+kickOutUser);
//            if(StrAndriod.length > 0){
//            	PushExample.SendUsersPushToAndroid("济南网通知",content,StrAndriod);
//            }
//            if(StrIOS.length > 0){
//            	PushExample.SendUsersPushToIOS(content, StrIOS);
//            }
//            channel.close();  
//            connection.close();  
            
           return (val > 0)?true:false;
        } catch (Throwable e) {
            LOG.error("sendUserMessage 异常",content);
            throw new DatabaseException(e.getMessage());
        }
	}

	@Override
	public boolean sendSysMessage(String content) throws DatabaseException {
		try {
			int val = -1;
            if (content == null){
                LOG.error("sendUserMessage 信息为空",content);
                return false;
            }
//            int type = 2;//1.代表给单独或多个用户发送消息通知;2.代表广播系统消息;0.代表提出单个或多个用户
//            String json = "{\"type\":\"" + type + "\",\"content\":" + content+ "}";
            // 创建连接和频道  
//    		ConnectionFactory factory = new ConnectionFactory();  
//    		factory.setHost("localhost");
            //端口
//            factory.setPort(5672);
//            factory.setPort(5670);
            //设置账号信息，用户名、密码、vhost
//            factory.setUsername("guest");
//            factory.setPassword("guest");
//            factory.setVirtualHost("/");
//    		Connection connection = factory.newConnection();  
//    		Channel channel = connection.createChannel();  
    		// 声明转发器的类型  
//    		channel.exchangeDeclare(EXCHANGE_NAME, "topic");
            	UserMessage userMessage = new UserMessage();
				userMessage.setContent(content);
				userMessage.setCreateTime(new Date());
            	
            	val = userMessageMapper.insert(userMessage);
            	if(val > 0){
            		//MQ发送消息
            		// 发布消息至转发器，指定routingkey  
//                    channel.basicPublish(EXCHANGE_NAME, "ALL", null, content  
//                            .getBytes());  
//                    System.out.println(" [x] Sent '" + content + "'");
            		PushExample.SendSysPush(content);
            		//type为5是广播消息给所有用户
            		SendUsersPushToPC(null,5,content,host+kickOutUser);
            	}
//            channel.close();  
//            connection.close();  
            
           return (val > 0)?true:false;
        } catch (Throwable e) {
            LOG.error("sendUserMessage 异常",content);
            throw new DatabaseException(e.getMessage());
        }
	}
	
	public boolean SendUsersPushToPC(List<Integer> userIds,Integer type,String content,String action) throws YCException {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Content-Type", "text/plain;charset=utf-8"));
        params.add(new BasicNameValuePair("Content-Encoding", "utf-8"));
        JSONObject reqJson = new JSONObject();
        reqJson.put("userids",userIds);
        reqJson.put("type",type);
        reqJson.put("content",content);
        String result =  URLConnUtil.doPost(action,reqJson.toString(),params);
        JSONObject jsonObject = JSONObject.fromObject(result);
        if(jsonObject.getString("status") .equals("0")){
            return true;
        }else {
            LOG.error("SendUsersPushToPC exception",userIds);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }

}
