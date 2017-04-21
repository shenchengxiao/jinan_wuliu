package com.manager.handler;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.manager.enums.ManagerTypeEnum;
import com.manager.enums.PlatformTypeEnum;
import com.manager.enums.YesNoEnum;
import com.manager.exception.DatabaseException;
import com.manager.exception.YCException;
import com.manager.pojo.User;
import com.manager.pojo.UserCustom;
import com.manager.request.custom.UserCustomRequest;
import com.manager.response.UserCustomResponse;
import com.manager.service.UserCustomService;
import com.manager.service.UserInfoService;
import com.manager.utils.Page;
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
        Validator.isEmpty(user.getUsername(),YCSystemStatusEnum.USER_ID_EMPTY);
        try {
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


	public Page<UserCustom> selectUserCustom(UserCustomRequest userCustomRequest) throws YCException {
		Page<UserCustom> page = null;
        try {
            page = userCustomService.selectUserCustom(userCustomRequest);

            page.setPagesize(page.getPagesize());
            page.setPageindex(page.getPageindex());
            page.setTotal(page.getTotal());
        } catch (DatabaseException e) {
            LOG.error("selectUserCustom exception",userCustomRequest);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
        return page;
	}


	public UserCustomResponse getUserCustomById(Integer id) throws YCException {
		 /** 参数校验 */
        Validator.isEmpty(id,"用户权限ID不能为空");
        UserCustomResponse userCustomResponse = new UserCustomResponse();
        try {
        	UserCustom userCustom = userCustomService.getUserCustomById(id);
        	if(userCustom != null){
        		userCustomResponse.setId(userCustom.getId());
        		userCustomResponse.setUsername(userCustom.getUsername());
        		userCustomResponse.setIsManager(userCustom.getIsManager().getValue());
        		userCustomResponse.setPlatformType(userCustom.getPlatformType());
        		userCustomResponse.setIsBinding(userCustom.getIsBinding().getValue());
        		userCustomResponse.setIsPhoneLimit(userCustom.getIsPhoneLimit().getValue());
        		userCustomResponse.setIsReceiveCar(userCustom.getIsReceiveCar().getValue());
        		userCustomResponse.setIsReceiveGoods(userCustom.getIsReceiveGoods().getValue());
        		userCustomResponse.setIsSendCar(userCustom.getIsSendCar().getValue());
        		userCustomResponse.setIsSendGoods(userCustom.getIsSendGoods().getValue());
        		userCustomResponse.setIsLookPhone(userCustom.getIsLookPhone().getValue());
        	}
        	
        	
            return userCustomResponse;
        } catch (DatabaseException e) {
            LOG.error("getUserCustomById exception",id);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
	}


	public void updateUserCustom(UserCustomRequest request) throws YCException {
		// TODO Auto-generated method stub
		Validator.isEmpty(request, YCSystemStatusEnum.PARAM_EMPTY);
        Validator.isEmpty(request.getUsername(),"用户名称不能为空");
        Validator.isEmpty(request.getPlatformType(),"用户平台类型不能为空");
        Integer integer = 0;
        String type = request.getPlatformType();
        if(type .equals("iOS")){
        	integer = 1;
        }else if(type .equals("Android")){
        	integer = 2;
        }
        
        UserCustom userCustom = new UserCustom();
        userCustom.setId(request.getId());
        userCustom.setPlatformType(PlatformTypeEnum.create(integer));
        userCustom.setIsManager(ManagerTypeEnum.create(request.getIsManager()));
        userCustom.setIsBinding(YesNoEnum.create(request.getIsBinding()));
        userCustom.setIsPhoneLimit(YesNoEnum.create(request.getIsPhoneLimit()));
        userCustom.setIsReceiveCar(YesNoEnum.create(request.getIsReceiveCar()));
        userCustom.setIsReceiveGoods(YesNoEnum.create(request.getIsReceiveGoods()));
        userCustom.setIsSendCar(YesNoEnum.create(request.getIsSendCar()));
        userCustom.setIsSendGoods(YesNoEnum.create(request.getIsSendGoods()));
        userCustom.setIsLookPhone(YesNoEnum.create(request.getIsLookPhone()));
        try {
            //判断ID是否为空，是则添加，否则更新
        	if(request.getId() != null){
            	userCustomService.updateUserCustom(userCustom);
            }
        } catch (DatabaseException e) {
            LOG.error("updateUserCustom exception",request);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
        
	}
	
	

}
