package com.manager.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.manager.exception.YCException;
import com.manager.handler.ItemHandler;
import com.manager.request.blackword.BlackWordRequest;
import com.manager.request.item.ItemRequest;
import com.manager.response.BlackWordResponse;
import com.manager.response.ItemResponse;
import com.manager.utils.APIResponse;
import com.manager.utils.Page;
import com.manager.utils.YCSystemStatusEnum;

@Controller
@RequestMapping(value = "/api/item")
public class ItemController {
	
	@Resource
	private ItemHandler itemHandler;
	
	Logger LOG = LoggerFactory.getLogger(ItemController.class);
	
	@ResponseBody
    @RequestMapping(value = "/list")
    @GetMapping(value = "/list")
    public APIResponse<Page<ItemResponse>> list(HttpServletRequest request , ItemRequest itemRequest){
        APIResponse<Page<ItemResponse>> apiResponse = new APIResponse<>();
        Page<ItemResponse> page = null;
        try {
			page = itemHandler.fetchItemList(itemRequest);
			apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
	        apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
	        apiResponse.setData(page);
		} catch (YCException e) {
			LOG.error("获取信息列表发生异常",itemRequest);
            apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
		}
        
        return apiResponse;
        
    }
	
	/**
     * 删除多条
     * @param request
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteItems",method = RequestMethod.POST)
    public APIResponse deleteItems(HttpServletRequest request, String ids){
        APIResponse apiResponse = new APIResponse<>();
        try {
        	itemHandler.deleteItems(ids);
            apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
            apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
        } catch (Throwable e) {
            LOG.error("删除信息发生异常",ids);
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
    		itemHandler.deleteItemById(id);
    		apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
    		apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
    	} catch (Throwable e) {
    		LOG.error("删除信息发生异常",id);
    		apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
    		apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
    	}
    	return apiResponse;
    }
    /**
     * 修改状态为已完成
     * @param request
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public APIResponse updateItemStatue(HttpServletRequest request, Integer id){
    	APIResponse apiResponse = new APIResponse<>();
    	try {
    		itemHandler.updateItemStatue(id);
    		apiResponse.setStatus(YCSystemStatusEnum.SUCCESS.getCode());
    		apiResponse.setMsg(YCSystemStatusEnum.SUCCESS.getDesc());
    	} catch (Throwable e) {
    		LOG.error("修改信息状态发生异常",id);
    		apiResponse.setStatus(YCSystemStatusEnum.SYSTEM_ERROR.getCode());
    		apiResponse.setMsg(YCSystemStatusEnum.SYSTEM_ERROR.getDesc());
    	}
    	return apiResponse;
    }
	
}
