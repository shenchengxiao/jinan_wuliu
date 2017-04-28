package com.manager.controller;
import com.manager.exception.YCException;
import com.manager.handler.IpVisitHandler;
import com.manager.handler.UserLoginLogHandler;
import com.manager.pojo.manual.UserLoginlogResponse;
import com.manager.request.advert.AdvertInfoRequest;
import com.manager.request.ipvisit.IpVisitRequest;
import com.manager.request.userloginlog.UserLoginLogRequest;
import com.manager.response.IpVisitResponse;
import com.manager.utils.APIResponse;
import com.manager.utils.Page;
import com.manager.utils.YCSystemStatusEnum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
/**
 * Created by zcy on 2017/3/31.
 */
@Controller
@RequestMapping(value = "/api/ipvisit")
public class IPVisistController {

    @Resource
    private IpVisitHandler ipVisitHandler;
    @Resource
    private UserLoginLogHandler userLoginLogHandler;
    
    Logger LOG = LoggerFactory.getLogger(IPVisistController.class);

    /**
     * 服务端服务器列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list")
    @GetMapping(value = "/list")
    public APIResponse<Page<IpVisitResponse>> list(IpVisitRequest request){
        APIResponse<Page<IpVisitResponse>> apiResponse = new APIResponse<>();
        Page<IpVisitResponse> page = null;
        try {
			page = ipVisitHandler.fetchIpVisitList(request);
			apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
	        apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
	        apiResponse.setData(page);
		} catch (YCException e) {
			LOG.error("获取服务器列表发生异常",request);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
		}
        
        return apiResponse;
        
    }
    /**
     * 用户登录 访问列表
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list2")
    @GetMapping(value = "/list2")
    public APIResponse<Page<UserLoginlogResponse>> list2(UserLoginLogRequest request){
    	APIResponse<Page<UserLoginlogResponse>> apiResponse = new APIResponse<>();
    	Page<UserLoginlogResponse> page = null;
    	try {
    		page = userLoginLogHandler.fetchIpVisitList2(request);
    		apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
    		apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
    		apiResponse.setData(page);
    	} catch (YCException e) {
    		LOG.error("获取访问服务器列表发生异常",request);
    		apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
    		apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
    	}
    	
    	return apiResponse;
    	
    }
    /**
     * 用户登录日志
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/loginLog")
    @GetMapping(value = "/loginLog")
    public APIResponse<Page<UserLoginlogResponse>> loginLog(UserLoginLogRequest request){
    	APIResponse<Page<UserLoginlogResponse>> apiResponse = new APIResponse<>();
    	Page<UserLoginlogResponse> page = null;
    	try {
    		page = userLoginLogHandler.fetchIpVisitList3(request);
    		apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
    		apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
    		apiResponse.setData(page);
    	} catch (YCException e) {
    		LOG.error("获取访问服务器列表发生异常",request);
    		apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
    		apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
    	}
    	
    	return apiResponse;
    	
    }
    
    /**
     * 添加/修改服务器
     * @param request
     * @param advertInfoRequest
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public APIResponse addServer(HttpServletRequest request , IpVisitRequest ipVisitRequest){
        APIResponse apiResponse = new APIResponse<>();
        try {
        	ipVisitHandler.addServer(ipVisitRequest);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
        } catch (Throwable e) {
            LOG.error("添加服务器信息发生异常",ipVisitRequest);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }
    
    /**
     * 删除
     * @param request
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public APIResponse deleteServer(HttpServletRequest request , Integer id){
        APIResponse apiResponse = new APIResponse<>();
        try {
        	ipVisitHandler.deleteServer(id);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
        } catch (Throwable e) {
            LOG.error("删除服务器信息发生异常",id);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }
}
