package com.manager.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.manager.exception.DatabaseException;
import com.manager.mapper.UserMessageMapper;
import com.manager.pojo.UserMessage;
import com.manager.service.UserMessageService;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Service
public class UserMessageServiceImpl implements UserMessageService {
	
	 private static final String EXCHANGE_NAME = "JINAN_MESSAGE_EXCHANGE"; 
	 
	 @Resource
	 private UserMessageMapper userMessageMapper;
	 Logger LOG = LoggerFactory.getLogger(BlackWordServiceImpl.class);
	 
	@Override
	public boolean sendUserMessage(List<Integer> list, String content) throws DatabaseException {
		try {
			int val = -1;
            if (content == null){
                LOG.error("sendUserMessage 信息为空",content);
                return false;
            }
            // 创建连接和频道  
    		ConnectionFactory factory = new ConnectionFactory();  
    		factory.setHost("localhost");
            //端口
            factory.setPort(5672);
//            factory.setPort(5670);
            //设置账号信息，用户名、密码、vhost
            factory.setUsername("guest");
            factory.setPassword("guest");
            factory.setVirtualHost("/");
    		Connection connection = factory.newConnection();  
    		Channel channel = connection.createChannel();  
    		// 声明转发器的类型  
    		channel.exchangeDeclare(EXCHANGE_NAME, "topic");
            for (Integer userid : list) {
            	UserMessage userMessage = new UserMessage();
				userMessage.setUserId(userid);
				userMessage.setContent(content);
				userMessage.setCreateTime(new Date());
            	
            	val = userMessageMapper.insert(userMessage);
            	if(val > 0){
            		//MQ发送消息
            		// 发布消息至转发器，指定routingkey  
                    channel.basicPublish(EXCHANGE_NAME, userid+"", null, content  
                            .getBytes());  
                    System.out.println(" [x] Sent '" + content + "'");
            	}
			}
            channel.close();  
            connection.close();  
            
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
            // 创建连接和频道  
    		ConnectionFactory factory = new ConnectionFactory();  
    		factory.setHost("localhost");
            //端口
            factory.setPort(5672);
//            factory.setPort(5670);
            //设置账号信息，用户名、密码、vhost
            factory.setUsername("guest");
            factory.setPassword("guest");
            factory.setVirtualHost("/");
    		Connection connection = factory.newConnection();  
    		Channel channel = connection.createChannel();  
    		// 声明转发器的类型  
    		channel.exchangeDeclare(EXCHANGE_NAME, "topic");
            	UserMessage userMessage = new UserMessage();
				userMessage.setContent(content);
				userMessage.setCreateTime(new Date());
            	
            	val = userMessageMapper.insert(userMessage);
            	if(val > 0){
            		//MQ发送消息
            		// 发布消息至转发器，指定routingkey  
                    channel.basicPublish(EXCHANGE_NAME, "ALL", null, content  
                            .getBytes());  
                    System.out.println(" [x] Sent '" + content + "'");
            	}
            channel.close();  
            connection.close();  
            
           return (val > 0)?true:false;
        } catch (Throwable e) {
            LOG.error("sendUserMessage 异常",content);
            throw new DatabaseException(e.getMessage());
        }
	}

}
