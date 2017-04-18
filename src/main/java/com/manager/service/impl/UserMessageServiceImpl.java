package com.manager.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.manager.exception.DatabaseException;
import com.manager.mapper.UserMapper;
import com.manager.mapper.UserMessageMapper;
import com.manager.pojo.User;
import com.manager.pojo.UserMessage;
import com.manager.service.UserMessageService;
import com.manager.utils.PushExample;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Service
public class UserMessageServiceImpl implements UserMessageService {
	
//	 private static final String EXCHANGE_NAME = "JINAN_MESSAGE_EXCHANGE"; 
	 
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
            int type = 1;//1.代表给单独或多个用户发送消息通知;2.代表广播系统消息;0.代表提出单个或多个用户
            String json = "{\"type\":\"" + type + "\",\"content\":" + content+ "}";
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
            				PushExample.SendUsersPushToAndroid("济南网通知",json,user.getRegistrationid());
//            				StrAndriod[andriod] = user.getRegistrationid();
//            				andriod ++;
            			}else if(user.getPlatformType() != null && user.getPlatformType().getValue() == 1 && user.getRegistrationid() != null){
            				PushExample.SendUsersPushToIOS(json, user.getRegistrationid());
//							StrIOS[ios] = user.getRegistrationid();
//							ios ++;
						}
            		}
            	}
			}
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
            int type = 2;//1.代表给单独或多个用户发送消息通知;2.代表广播系统消息;0.代表提出单个或多个用户
            String json = "{\"type\":\"" + type + "\",\"content\":" + content+ "}";
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
            		PushExample.SendSysPush(json);
            	}
//            channel.close();  
//            connection.close();  
            
           return (val > 0)?true:false;
        } catch (Throwable e) {
            LOG.error("sendUserMessage 异常",content);
            throw new DatabaseException(e.getMessage());
        }
	}

}
