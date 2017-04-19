package com.manager.service;

import java.util.List;

import com.manager.exception.DatabaseException;
import com.manager.request.item.ItemRequest;
import com.manager.response.ItemResponse;
import com.manager.utils.Page;

public interface ItemService {

	Page<ItemResponse> fetchItemList(ItemRequest itemRequest) throws DatabaseException;

	boolean deleteItemById(Integer id) throws DatabaseException;

	boolean deleteItems(List<Long> ids_arr) throws DatabaseException;

	boolean updateItemStatue(Integer id) throws DatabaseException;

	Page<ItemResponse> fetchItemList2(ItemRequest itemRequest) throws DatabaseException;

}
