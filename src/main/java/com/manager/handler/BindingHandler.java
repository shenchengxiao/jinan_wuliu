package com.manager.handler;

import com.manager.enums.YesNoEnum;
import com.manager.exception.DatabaseException;
import com.manager.exception.YCException;
import com.manager.pojo.BlackWord;
import com.manager.pojo.UserBinding;
import com.manager.pojo.UserCustom;
import com.manager.request.blackword.BlackWordRequest;
import com.manager.response.BlackWordResponse;
import com.manager.service.BindingService;
import com.manager.service.BlackWordService;
import com.manager.service.UserCustomService;
import com.manager.utils.Page;
import com.manager.utils.Validator;
import com.manager.utils.YCSystemStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by shencx on 2017/3/28.
 */
@Service
public class BindingHandler {

    @Resource
    private BindingService bindingService;

    @Resource
    private UserCustomService userCustomService;

    Logger LOG = LoggerFactory.getLogger(BindingHandler.class);

    
    public boolean addBinding(UserBinding userBinding) throws YCException {
        /** 参数校验 */
        Validator.isEmpty(userBinding.getUserName(),YCSystemStatusEnum.USER_ID_EMPTY);
        try {
        	return bindingService.addBinding(userBinding);
        } catch (DatabaseException e) {
            LOG.error("addBinding exception",userBinding.getUserName());
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }

    /**
     * 设备解绑
     * @param userBinding
     * @throws YCException
     */
    public void userUnbind(UserBinding userBinding) throws YCException {
        /** 参数校验 */
        Validator.isEmpty(userBinding,YCSystemStatusEnum.USER_ID_EMPTY);
        Validator.isEmpty(userBinding.getUserId(),"用户主键ID不能为空");

        Integer is_bind = 0;
        userBinding.setIsBinding(is_bind.byteValue());
        userBinding.setHardpanNum("");

        try {
            bindingService.updateUserBinding(userBinding);

            //更新用户权限表
            UserCustom userCustom = new UserCustom();
            userCustom.setUserId(userBinding.getUserId());
            userCustom.setIsBinding(YesNoEnum.create(is_bind));
            userCustomService.updateUserCustomInfo(userCustom);

        }catch (DatabaseException e) {
            LOG.error("userUnbind exception",userBinding);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }
}
