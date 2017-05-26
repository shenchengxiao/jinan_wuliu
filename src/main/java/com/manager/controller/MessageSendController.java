package com.manager.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manager.handler.ItemHandler;
import com.manager.handler.UserMessageHandler;
import com.manager.pojo.BlackWord;
import com.manager.pojo.UserMessage;
import com.manager.request.message.UserMessageRequest;
import com.manager.utils.APIResponse;
import com.manager.utils.YCSystemStatusEnum;

@Controller
@RequestMapping(value = "/api/message")
public class MessageSendController {
	
	@Resource
	private UserMessageHandler userMessageHandler;
	
	Logger LOG = LoggerFactory.getLogger(ItemHandler.class);
	
	@ResponseBody
    @RequestMapping(value = "/send",method = RequestMethod.POST)
    public APIResponse sendUserMessage(HttpServletRequest request, UserMessageRequest userMessageRequest){
        APIResponse apiResponse = new APIResponse<>();
        try {
        	userMessageHandler.sendUserMessage(userMessageRequest);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
        } catch (Throwable e) {
            LOG.error("发送消息通知出现异常",userMessageRequest.toString());
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }
	
	@ResponseBody
	@RequestMapping(value = "/sendsys",method = RequestMethod.POST)
	public APIResponse sendSysMessage(HttpServletRequest request, UserMessageRequest userMessageRequest){
		APIResponse apiResponse = new APIResponse<>();
		try {
			userMessageHandler.sendSysMessage(userMessageRequest.getmType(),userMessageRequest.getContent());
			apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
			apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
		} catch (Throwable e) {
			LOG.error("发送消息通知出现异常",userMessageRequest.getContent());
			apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
			apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
		}
		return apiResponse;
	}

	/**
	 * 多设备帐号登陆下线通知
	 * @param request
	 * @param registerId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/off_line",method = RequestMethod.POST)
	public APIResponse offLine(HttpServletRequest request, String registerId){
		APIResponse apiResponse = new APIResponse<>();
		try {
			userMessageHandler.sendOnlyOnline(registerId);
			apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
			apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
		} catch (Throwable e) {
			LOG.error("下线通知发生异常",registerId);
			apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
			apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
		}
		return apiResponse;
	}
}
