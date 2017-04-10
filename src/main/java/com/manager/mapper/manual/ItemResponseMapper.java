package com.manager.mapper.manual;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.manager.pojo.manual.ItemResponse;
import com.manager.request.item.ItemRequest;

@Repository
public interface ItemResponseMapper {
	List<ItemResponse> selectByExampleWithBLOBs(ItemRequest example);
}
