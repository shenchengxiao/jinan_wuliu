package com.manager.controller;
import com.manager.exception.YCException;
import com.manager.handler.BlackWordHandler;
import com.manager.pojo.BlackWord;
import com.manager.request.blackword.BlackWordRequest;
import com.manager.response.AdvertInfoResponse;
import com.manager.response.BlackWordResponse;
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
@RequestMapping(value = "/api/blackword")
public class BlackWordController {

    @Resource
    private BlackWordHandler blackWordHandler;
    
    Logger LOG = LoggerFactory.getLogger(BlackWordController.class);

    @ResponseBody
    @RequestMapping(value = "/list")
    @GetMapping(value = "/list")
    public APIResponse<Page<BlackWordResponse>> list(HttpServletRequest request , BlackWordRequest blackWordRequest){
        APIResponse<Page<BlackWordResponse>> apiResponse = new APIResponse<>();
        Page<BlackWordResponse> page = null;
        try {
			page = blackWordHandler.fetchBlackwordList(blackWordRequest);
			apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
	        apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
	        apiResponse.setData(page);
		} catch (YCException e) {
			LOG.error("获取黑词列表发生异常",blackWordRequest);
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
    public APIResponse delete(HttpServletRequest request, Integer id){
        APIResponse apiResponse = new APIResponse<>();
        try {
        	blackWordHandler.deleteBlackword(id);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
        } catch (Throwable e) {
            LOG.error("删除黑词发生异常",id);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }
    
    /**
     * 修改黑词状态
     * @param request
     * @param blackWordRequest
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update_status",method = RequestMethod.POST)
    public APIResponse updateStatus(HttpServletRequest request, BlackWord blackWord){
        APIResponse apiResponse = new APIResponse<>();
        try {
        	blackWordHandler.updateBlackword(blackWord);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
        } catch (Throwable e) {
            LOG.error("修改黑词状态发生异常",blackWord.getbWId());
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }
    
    @ResponseBody
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public APIResponse add(HttpServletRequest request, BlackWord blackWord){
        APIResponse apiResponse = new APIResponse<>();
        try {
        	blackWordHandler.addBlackword(blackWord);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
        } catch (Throwable e) {
            LOG.error("添加黑词状态发生异常",blackWord.toString());
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }
    
    /**
     * 获取详情
     * @param request
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public APIResponse<BlackWord> detail(HttpServletRequest request , Integer id){
        APIResponse<BlackWord> apiResponse = new APIResponse<>();
        BlackWord blackWordResponse = null;
        try {
        	blackWordResponse = blackWordHandler.getBlackwordDetail(id);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
            apiResponse.setData(blackWordResponse);
        } catch (Throwable e) {
            LOG.error("获取广告详情信息发生异常",id);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
        }
        return apiResponse;
    }
}
