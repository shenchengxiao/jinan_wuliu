package com.manager.handler;

import com.manager.enums.YesNoEnum;
import com.manager.exception.DatabaseException;
import com.manager.exception.ValidationException;
import com.manager.exception.YCException;
import com.manager.pojo.User;
import com.manager.pojo.UserBinding;
import com.manager.pojo.UserCustom;
import com.manager.request.user.UserManageRequest;
import com.manager.response.UserMangeResponse;
import com.manager.service.BindingService;
import com.manager.service.UserCustomService;
import com.manager.service.UserInfoService;
import com.manager.utils.DateTimeUtil;
import com.manager.utils.Page;
import com.manager.utils.Validator;
import com.manager.utils.YCSystemStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by shencx on 2017/4/10.
 */
@Service
public class UserManageHandler {

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private UserCustomService userCustomService;

    @Resource
    private BindingService bindingService;

    Logger LOG = LoggerFactory.getLogger(UserManageHandler.class);

    /**
     * 用户添加、修改
     * @param request
     * @throws YCException
     */
    public void editUser(UserManageRequest request) throws YCException {
        /** 参数校验 */
        Validator.isEmpty(request, YCSystemStatusEnum.PARAM_EMPTY);
        Validator.isEmpty(request.getUserName(),"用户名称不能为空");
        Validator.isEmpty(request.getUserNum(),"用户编号不能为空");
        Validator.isEmpty(request.getPassword(),"用户密码不能为空");
        Validator.isEmpty(request.getPasswordVerify(),"确认密码不能为空");
        Validator.isEmpty(request.getPhoneNumber(),"电话号码不能为空");

        //用户基本信息
        User user = new User();
        user.setId(request.getId());
        user.setUsername(request.getUserName());
        user.setUserNum(request.getUserNum());
        user.setPassword(request.getPassword());
        user.setPasswordConfirm(request.getPasswordVerify());
        user.setPhones(request.getPhoneNumber());
        user.setProvince(request.getProvince());
        user.setCity(request.getCity());
        user.setDistrict(request.getCounty());
        user.setBeginTime(DateTimeUtil.convertDate(request.getStartTime()));
        user.setEndTime(DateTimeUtil.convertDate(request.getEndTime()));
        user.setStatus(YesNoEnum.create(request.getIsAbled()));
        user.setIsManager(YesNoEnum.create(request.getIsManager()));
        user.setIsSynchro(YesNoEnum.create(request.getIsSync()));
        user.setMailbox(request.getUserEmail());
        user.setPostCode(request.getPostCode());
        user.setCompanyName(request.getCompanyName());
        user.setRegisterIp(request.getRegisterIp());
        user.setLoginIp(request.getLoginIp());
        user.setIdentification(request.getIdentityNum());
        user.setAddress(request.getAddress());
        user.setLastQuitNum(request.getLastQuitNum());
        user.setThisLoadNum(request.getThisLoadNum());
        user.setCheckLimit(request.getCheckLimit());
        user.setCheckNum(request.getCheckNum());

        //是否发布接收信息入库
        UserCustom userCustom = new UserCustom();
        userCustom.setIsSend(YesNoEnum.create(request.getIsSend()));
        userCustom.setIsReceive(YesNoEnum.create(request.getIsReceive()));
        userCustom.setIsReceiveSelf(YesNoEnum.create(request.getIsReceiveSelf()));
        userCustom.setSendProvince(request.getSendProvince());
        userCustom.setSendCity(request.getSendCity());
        userCustom.setReceiveProvince(request.getReceiveProvince());
        userCustom.setReceiveCity(request.getReceiveCity());

        //是否绑定电脑信息入库
        UserBinding userBinding = new UserBinding();
        userBinding.setUserName(request.getUserName());
        userBinding.setHardpanNum(request.getHardNum());
        userBinding.setNetworkCard(request.getNetworkNum());
        userBinding.setTemporaryCard(request.getTemporaryCard());
        userBinding.setIsBinding(request.getIsBinding().byteValue());
        try {
            //判断ID是否为空，是则添加，否则更新
            if (request.getId() == null){
                Integer id = userInfoService.addUser(user);

                //添加定制信息
                userCustom.setUserId(id);
                userCustomService.addUserCustomInfo(userCustom);

                //添加绑定信息
                userBinding.setUserId(id);
                bindingService.addBinding(userBinding);
            }else {
                userInfoService.updateUser(user);

                //修改定制信息
                userCustom.setUserId(request.getId());
                userCustomService.updateUserCustomInfo(userCustom);

                //修改绑定信息
                userBinding.setUserId(request.getId());
                bindingService.updateUserBinding(userBinding);

            }
        } catch (DatabaseException e) {
            LOG.error("editUser exception",request);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }

    /**
     * 根据ID获取详情
     * @param id
     * @return
     * @throws YCException
     */
    public UserMangeResponse fetchUserManageDetail(Integer id) throws YCException {
        /** 参数校验 */
        Validator.isEmpty(id,"用户主键ID不能为空");
        UserManageRequest request = new UserManageRequest();
        request.setId(id);
        UserMangeResponse userMangeResponse = null;
        try {
            userMangeResponse = userInfoService.getUserDetail(request);
            return userMangeResponse;
        } catch (DatabaseException e) {
            LOG.error("fetchUserManageDetail exception",id);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }

    /**
     * 获取列表
     * @param request
     * @return
     * @throws YCException
     */
    public Page<UserMangeResponse> fetchUserManageList(UserManageRequest request) throws YCException {
        /** 参数校验 */
        Validator.isEmpty(request,YCSystemStatusEnum.PARAM_EMPTY);
        Page<UserMangeResponse> userMangeResponsePage = null;
        try {
            userMangeResponsePage = userInfoService.getUserList(request);
            return userMangeResponsePage;
        } catch (DatabaseException e) {
            LOG.error("fetchUserManageList exception",request);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }

    /**
     * 更新用户状态
     * @param id
     * @param enabled
     * @throws YCException
     */
    public void modifyUserStatus(Integer id,Integer enabled) throws YCException {
        /** 参数校验 */
        Validator.isEmpty(id,"主键ID不能为空");
        Validator.isEmpty(enabled,"状态值不能为空");
        try {
            userInfoService.modifyStatus(id,enabled);
        } catch (DatabaseException e) {
            LOG.error("modifyUserStatus exception",id);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }
}
