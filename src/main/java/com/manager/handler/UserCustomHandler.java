package com.manager.handler;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.manager.exception.DatabaseException;
import com.manager.exception.YCException;
import com.manager.pojo.User;
import com.manager.pojo.UserCustom;
import com.manager.service.UserCustomService;
import com.manager.service.UserInfoService;
import com.manager.utils.Validator;
import com.manager.utils.YCSystemStatusEnum;

@Service
public class UserCustomHandler {
	
	@Resource
	private UserCustomService userCustomService;
	@Resource
	private UserInfoService userInfoService;
	
	Logger LOG = LoggerFactory.getLogger(UserCustomHandler.class);
	
	
	public boolean addUserCustom(User user,UserCustom userCustom) throws YCException {
        /** 参数校验 */
        Validator.isEmpty(user.getUserNum(),YCSystemStatusEnum.USER_ID_EMPTY);
        try {
        	String rc = userCustom.getReceiveCity();
        	rc = rc.replaceAll(",", " ");
        	userCustom.setReceiveCity(rc);
        	List<User> users = userInfoService.queryUser(user);
        	
        	if(users != null && users.size()>0){
        		
        		User u = users.get(0);
        		userCustom.setUserId(u.getId());
        		
        		List<UserCustom> userCustoms = userCustomService.queryCustomInfo(userCustom);
        		if(userCustoms != null && userCustoms.size()>0){
        			userCustomService.updateUserCustomInfoByParam(userCustom);
        		}else{
        			userCustomService.addUserCustomInfo(userCustom);
        		}
        		
        		return true;
        	}else{
        		return false;
        	}
        	
        	
        } catch (DatabaseException e) {
            LOG.error("addUserCustom exception",user.getUsername());
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }
	
	

}
