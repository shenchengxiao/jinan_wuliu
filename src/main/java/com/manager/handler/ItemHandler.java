package com.manager.handler;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manager.exception.DatabaseException;
import com.manager.exception.YCException;
import com.manager.mapper.UserMapper;
import com.manager.request.item.ItemRequest;
import com.manager.response.ItemResponse;
import com.manager.service.ItemService;
import com.manager.utils.Page;
import com.manager.utils.Validator;
import com.manager.utils.YCSystemStatusEnum;

@Service
public class ItemHandler {
	

    @Resource
    private ItemService itemService;
    @Resource
    private UserMapper userMapper;

    Logger LOG = LoggerFactory.getLogger(ItemHandler.class);

	public Page<ItemResponse> fetchItemList(ItemRequest itemRequest) throws YCException{
		// TODO Auto-generated method stub
		/** 参数校验 */
        //Validator.isEmpty(request,YCSystemStatusEnum.PARAM_EMPTY);
        Page<ItemResponse> page = null;
        try {
            page = itemService.fetchItemList(itemRequest);
            
            page.setPagesize(page.getPagesize());
            page.setPageindex(page.getPageindex());
            page.setTotal(page.getTotal());
        } catch (DatabaseException e) {
            LOG.error("fetchItemList exception",itemRequest);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
        return page;
	}

	public void deleteItemById(Integer id) throws YCException{
		// TODO Auto-generated method stub
		/** 参数校验 */
        Validator.isEmpty(id,YCSystemStatusEnum.USER_ID_EMPTY);
        try {
        	itemService.deleteItemById(id);
        } catch (DatabaseException e) {
            LOG.error("deleteUserInfo exception",id);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
	}

	public void deleteItems(String ids) throws YCException{
		// TODO Auto-generated method stub
		/** 参数校验 */
        Validator.isEmpty(ids,YCSystemStatusEnum.USER_ID_EMPTY);
        try {
        	List<Long> list = new ArrayList<>();
            JSONArray jsonArray = JSON.parseArray(ids);
            for (Object object : jsonArray) {
                JSONObject jsonObject = JSONObject.parseObject(object.toString());
                String id =jsonObject.get("id").toString();
                list.add(Long.valueOf(id));
            }

        	itemService.deleteItems(list);
        } catch (DatabaseException e) {
            LOG.error("deleteUserInfo exception",ids);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
	}

	public void updateItemStatue(Integer id) throws YCException{
		// TODO Auto-generated method stub
		/** 参数校验 */
        Validator.isEmpty(id,YCSystemStatusEnum.USER_ID_EMPTY);
        try {
        	itemService.updateItemStatue(id);
        } catch (DatabaseException e) {
            LOG.error("deleteUserInfo exception",id);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
	}

}
