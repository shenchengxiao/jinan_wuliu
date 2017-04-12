package com.manager.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class SendMessage {
	
	private static final String EXCHANGE_NAME = "JINAN_MESSAGE_EXCHANGE"; 
	
	public static final void replaceIgnoreCase(String flag, String content) {
		
		try {
		// 创建连接和频道  
		ConnectionFactory factory = new ConnectionFactory();  
		factory.setHost("localhost");
        //端口
        factory.setPort(5672);
//        factory.setPort(5670);
        //设置账号信息，用户名、密码、vhost
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");
		Connection connection = factory.newConnection();  
		Channel channel = connection.createChannel();  
		// 声明转发器的类型  
		channel.exchangeDeclare(EXCHANGE_NAME, "topic");
		// 发布消息至转发器，指定routingkey  
        channel.basicPublish(EXCHANGE_NAME, flag, null, content  
                .getBytes());
        
        channel.close();  
        connection.close();  
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
