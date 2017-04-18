package com.manager.mapper.manual;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.manager.pojo.manual.ItemResponse;
import com.manager.request.item.ItemRequest;
import com.manager.request.userloginlog.UserLoginLogRequest;
import com.manager.response.UserLoginlogResponse;

@Repository
public interface UserLoginLogResponseMapper {
	List<UserLoginlogResponse> selectByParams(UserLoginLogRequest example);
}
