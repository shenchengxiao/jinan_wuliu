package com.manager.mapper.manual;

import java.util.List;

import com.manager.response.ItemResponse;
import org.springframework.stereotype.Repository;

import com.manager.request.item.ItemRequest;

@Repository
public interface ItemResponseMapper {
	List<ItemResponse> selectByExampleWithBLOBs(ItemRequest example);

	List<ItemResponse> selectPushItemsLogByParams(ItemRequest itemRequest);

	List<ItemResponse> findItemsLogPage(ItemRequest itemRequest);

	int deleteItemLogByPrimaryKey(Long itemId);
}
