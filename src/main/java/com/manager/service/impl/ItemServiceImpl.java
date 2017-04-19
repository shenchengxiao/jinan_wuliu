package com.manager.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.taglibs.standard.lang.jstl.test.Bean1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.manager.exception.DatabaseException;
import com.manager.interceptor.PageMybatisInterceptor;
import com.manager.mapper.ItemsMapper;
import com.manager.mapper.UserMapper;
import com.manager.mapper.manual.ItemResponseMapper;
import com.manager.pojo.Items;
import com.manager.pojo.User;
import com.manager.pojo.UserExample;
import com.manager.request.item.ItemRequest;
import com.manager.response.ItemResponse;
import com.manager.service.ItemService;
import com.manager.utils.Page;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Resource
	private ItemsMapper itemsMapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	private ItemResponseMapper itemResponseMapper;
	
	
	Logger LOG = LoggerFactory.getLogger(ItemServiceImpl.class);
	
	@Override
	public Page<ItemResponse> fetchItemList(ItemRequest itemRequest) throws DatabaseException{
		// TODO Auto-generated method stub
		try {
            /*if (request == null){
                LOG.error("fetchUserInfoList 信息为空",request);
                return null;
            }*/
           
            if (StringUtils.isNotBlank(itemRequest.getUserNum())){
            	User user = selectUserByNum(itemRequest.getUserNum());
            	if(user != null){
            		itemRequest.setUserId(user.getId());
            	}
            }
            PageMybatisInterceptor.startPage(itemRequest.getPageNum(),itemRequest.getPageSize());
            itemResponseMapper.selectByExampleWithBLOBs(itemRequest);
            Page<ItemResponse> page = PageMybatisInterceptor.endPage();
            return page;
        } catch (Throwable e) {
            LOG.error("fetchBlackwordList 异常",itemRequest);
            throw new DatabaseException(e.getMessage());
        }
	}
	
	//根据用户编号查询用户
	public User selectUserByNum(String userNum){
		
		UserExample userExample = new UserExample();
		com.manager.pojo.UserExample.Criteria c = userExample.createCriteria();
		c.andUserNumEqualTo(userNum);
		
		List<User> list = userMapper.selectByExample(userExample);
		if(list != null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public boolean deleteItemById(Integer id) throws DatabaseException{
		// TODO Auto-generated method stub
		try {
            if (id == null){
                LOG.error("deleteItem id为空",id);
                return false;
            }
            int result = itemsMapper.deleteByPrimaryKey(Long.valueOf(id));
    		
    		return result> 0?true:false;
        } catch (Throwable e) {
            LOG.error("deleteUserInfo 异常",id);
            throw new DatabaseException(e.getMessage());
        }
	}

	@Override
	public boolean deleteItems(List<Long> ids_arr) throws DatabaseException{
		// TODO Auto-generated method stub
		try {
			int result = 0;
            if (ids_arr .size() <= 0){
                LOG.error("deleteItems ids为空",ids_arr);
                return false;
            }
            for(int i = 0; i < ids_arr.size(); i++){
            	
            	result = itemsMapper.deleteByPrimaryKey(ids_arr.get(i));
            }
    		
    		return result> 0?true:false;
        } catch (Throwable e) {
            LOG.error("deleteUserInfo 异常",ids_arr);
            throw new DatabaseException(e.getMessage());
        }
	}

	@Override
	public boolean updateItemStatue(Integer id) throws DatabaseException {
		// TODO Auto-generated method stub
		try {
			Byte i = 1;
            if (id == null){
                LOG.error("updateItemStatue id为空",id);
                return false;
            }
            Items items = new Items();
            items.setStatus(i);
            items.setItemId(Long.valueOf(id));
            int result = itemsMapper.updateByPrimaryKeySelective(items);
    		
    		return result> 0?true:false;
        } catch (Throwable e) {
            LOG.error("deleteUserInfo 异常",id);
            throw new DatabaseException(e.getMessage());
        }
	}

	@Override
	public Page<ItemResponse> fetchItemList2(ItemRequest itemRequest) throws DatabaseException {
		try {
            /*if (request == null){
                LOG.error("fetchUserInfoList 信息为空",request);
                return null;
            }*/
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			
            if (StringUtils.isNotBlank(itemRequest.getUserNum())){
            	User user = selectUserByNum(itemRequest.getUserNum());
            	if(user != null){
            		itemRequest.setUserId(user.getId());
            	}
            }
            if(StringUtils.isNotBlank(itemRequest.getCreateTime())){
            	Date date = sdf.parse(itemRequest.getCreateTime());
            	String dateStr = sdf.format(date);
            	itemRequest.setEndTime(dateStr+" 23:59:59");
            }
            PageMybatisInterceptor.startPage(itemRequest.getPageNum(),itemRequest.getPageSize());
            itemResponseMapper.selectPushItemsLogByParams(itemRequest);
            Page<ItemResponse> page = PageMybatisInterceptor.endPage();
            return page;
        } catch (Throwable e) {
            LOG.error("fetchBlackwordList2 异常",itemRequest);
            throw new DatabaseException(e.getMessage());
        }
	}

}
