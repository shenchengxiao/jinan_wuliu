package com.manager.service.impl;

import java.util.*;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
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
	public boolean sendUserMessage(List<Integer> list,Integer mType, String content) throws DatabaseException {
		try {
			int val = -1;
//			int andriod = 0;
//			int ios = 0;
//			String StrAndriod[]=new String[]{};
//			String StrIOS[]=new String[]{};
			
            if (mType == null){
                LOG.error("sendUserMessage 没有选择信息类型",content);
                return false;
            }
            Map<String, String> map = new HashMap<>();
            map.put("type", String.valueOf(mType));
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
				userMessage.setmType(mType);
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
            SendUsersPushToPC(list_pc,mType,content,host+kickOutUser);
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
	public boolean sendSysMessage(Integer mType,String content) throws DatabaseException {
		try {
			int val = -1;
            if (content == null || mType == null){
                LOG.error("sendUserMessage 未选择信息类型或信息为空",content);
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
            	userMessage.setmType(mType);
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
            		SendUsersPushToPC(null,mType,content,host+kickOutUser);
            	}
//            channel.close();  
//            connection.close();  
            
           return (val > 0)?true:false;
        } catch (Throwable e) {
            LOG.error("sendUserMessage 异常",content);
            throw new DatabaseException(e.getMessage());
        }
	}

	/**
	 * 多设备登陆帐号下线通知
	 * @param registerId
	 * @return
	 * @throws DatabaseException
	 */
	@Override
	public boolean sendOnlineMeanWhile(String registerId) throws DatabaseException {
		try {
			Map<String, String> map = new HashMap<>();
			map.put("type", "103");
			String content = "您的帐号在另外一台设备上登陆，请重新登录";
			UserMessage userMessage = new UserMessage();
			userMessage.setmType(103);
			userMessage.setContent(content);
			userMessage.setCreateTime(new Date());
			int val = userMessageMapper.insert(userMessage);
			if(val > 0){
				if(StringUtils.isNoneBlank(registerId)){
					PushExample.SendUsersPushToAndroid("济南网通知",content,map,registerId);
					PushExample.SendUsersPushToIOS(content,map,registerId);
				}
			}
			return (val > 0)?true:false;
		} catch (Throwable e) {
			LOG.error("sendOnlineMeanWhile 异常",registerId);
			throw new DatabaseException(e.getMessage());
		}
	}

	public boolean SendUsersPushToPC(List<Integer> userIds,Integer type,String content,String action) throws YCException {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Content-Type", "text/plain;charset=utf-8"));
        params.add(new BasicNameValuePair("Content-Encoding", "utf-8"));

		List<NameValuePair> params2 = new ArrayList<NameValuePair>();
		String[] arr =null;
		if (userIds != null) {
			arr = new String[userIds.size()];
			for (int i = 0; i < userIds.size(); i++) {
				arr[i] = String.valueOf(userIds.get(i));
			}
		}
		params2.add(new BasicNameValuePair("userids", arr == null?null:Arrays.toString(arr)));
		params2.add(new BasicNameValuePair("type", type.toString()));
		params2.add(new BasicNameValuePair("content", content));

		String result = URLConnUtil.doGet(action, params2, params);
		JSONObject jsonObject = JSONObject.fromObject(result);
		if (jsonObject.getString("status").equals("0")) {
			return true;
		} else {
			LOG.error("SendUsersPushToPC exception", userIds);
			throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
		}
    }

}
