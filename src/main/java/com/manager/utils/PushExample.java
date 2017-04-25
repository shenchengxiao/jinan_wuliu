package com.manager.utils;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.manager.pojo.JpushClint.JPushClient;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;

public class PushExample {
	
	protected static final Logger LOG = LoggerFactory.getLogger(PushExample.class);
	//在极光注册上传应用的 appKey 和 masterSecret  
    private static final String APP_KEY ="6777d550be451f27c6ccd778";////必填，例如466f7032ac604e02fb7bda89  
    private static final String MASTER_SECRET = "4284e86e110c91438f862442";//必填，每个应用都对应一个masterSecret  
    public static long sendCount = 0;
    
	/**
	 * 给所有人发送系统消息
	 * @param content
	 */
	public static void SendSysPush(String content) {
		
		ClientConfig clientConfig = ClientConfig.getInstance();
        final JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, clientConfig);
        PushResult result = new PushResult();
        
        try {
			result = jpushClient.sendNotificationAll(content);
			LOG.info("Got result - " + result);
		} catch (APIConnectionException e) {
			LOG.error("Connection error. Should retry later. ", e);
			e.printStackTrace();
		} catch (APIRequestException e) {
			LOG.error("Error response from JPush server. Should review and fix it. ", e);
		    LOG.info("HTTP Status: " + e.getStatus());
		    LOG.info("Error Code: " + e.getErrorCode());
		    LOG.info("Error Message: " + e.getErrorMessage());
		    LOG.info("Msg ID: " + e.getMsgId());
			e.printStackTrace();
		}
        System.out.println(result.getOriginalContent()+"====="+result.getRateLimitQuota()+
				"========"+result.getRateLimitRemaining()+"========"+result.getRateLimitReset());
        
    }
	/**
	 * 发送信息给指定的安卓平台
	 * @param title
	 * @param msgContent
	 * @param registrationID
	 */
	public static void SendUsersPushToAndroid(String title, String msgContent,Map<String, String> map, String... registrationID) {
		ClientConfig clientConfig = ClientConfig.getInstance();
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("type", "1");
        final JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, clientConfig);
        PushResult result = new PushResult();
        
        try {
			result = jpushClient.sendAndroidNotificationWithRegistrationID(title,msgContent,map,registrationID);
			LOG.info("Got result - " + result);
		} catch (APIConnectionException e) {
			LOG.error("Connection error. Should retry later. ", e);
			e.printStackTrace();
		} catch (APIRequestException e) {
			LOG.error("Error response from JPush server. Should review and fix it. ", e);
		    LOG.info("HTTP Status: " + e.getStatus());
		    LOG.info("Error Code: " + e.getErrorCode());
		    LOG.info("Error Message: " + e.getErrorMessage());
		    LOG.info("Msg ID: " + e.getMsgId());
			e.printStackTrace();
		}
        System.out.println(result.getOriginalContent()+"====="+result.getRateLimitQuota()+
				"========"+result.getRateLimitRemaining()+"========"+result.getRateLimitReset());
	}
	/**
	 * 发送信息给指定的ios用户
	 * @param msgContent
	 * @param registrationID
	 */
	public static void SendUsersPushToIOS(String msgContent,Map<String, String> map, String... registrationID) {
		ClientConfig clientConfig = ClientConfig.getInstance();
//		Map<String, String> map = new HashMap<String, String>();
		final JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, clientConfig);
		PushResult result = new PushResult();
		
		try {
			result = jpushClient.sendIosNotificationWithRegistrationID(msgContent,map,registrationID);
			LOG.info("Got result - " + result);
		} catch (APIConnectionException e) {
			LOG.error("Connection error. Should retry later. ", e);
			e.printStackTrace();
		} catch (APIRequestException e) {
			LOG.error("Error response from JPush server. Should review and fix it. ", e);
			LOG.info("HTTP Status: " + e.getStatus());
			LOG.info("Error Code: " + e.getErrorCode());
			LOG.info("Error Message: " + e.getErrorMessage());
			LOG.info("Msg ID: " + e.getMsgId());
			e.printStackTrace();
		}
		System.out.println(result.getOriginalContent()+"====="+result.getRateLimitQuota()+
				"========"+result.getRateLimitRemaining()+"========"+result.getRateLimitReset());
	}
    
    
}
