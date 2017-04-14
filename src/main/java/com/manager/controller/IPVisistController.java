package com.manager.controller;
import com.manager.exception.YCException;
import com.manager.handler.BlackWordHandler;
import com.manager.handler.IpVisitHandler;
import com.manager.pojo.BlackWord;
import com.manager.pojo.IpVisit;
import com.manager.request.blackword.BlackWordRequest;
import com.manager.request.ipvisit.IpVisitRequest;
import com.manager.response.AdvertInfoResponse;
import com.manager.response.BlackWordResponse;
import com.manager.response.IpVisitResponse;
import com.manager.utils.APIResponse;
import com.manager.utils.Page;
import com.manager.utils.YCSystemStatusEnum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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
    
    Logger LOG = LoggerFactory.getLogger(IPVisistController.class);

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
			LOG.error("获取IP访问列表发生异常",request);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
		}
        
        return apiResponse;
        
    }
    
}
