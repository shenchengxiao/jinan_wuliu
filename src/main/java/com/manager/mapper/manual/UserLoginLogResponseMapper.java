package com.manager.mapper.manual;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.manager.pojo.manual.UserLoginlogResponse;
import com.manager.request.userloginlog.UserLoginLogRequest;

@Repository
public interface UserLoginLogResponseMapper {
	List<UserLoginlogResponse> selectByParams(UserLoginLogRequest example);

	List<UserLoginlogResponse> selectLoginLogByParams(UserLoginLogRequest request);

}
