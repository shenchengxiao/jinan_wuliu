package com.manager.handler;

import com.manager.common.SystemParam;
import com.manager.enums.ManagerTypeEnum;
import com.manager.enums.PlatformTypeEnum;
import com.manager.enums.YesNoEnum;
import com.manager.exception.DatabaseException;
import com.manager.exception.YCException;
import com.manager.pojo.User;
import com.manager.pojo.UserBinding;
import com.manager.pojo.UserCustom;
import com.manager.request.user.OnlineUserRequest;
import com.manager.request.user.UserManageRequest;
import com.manager.response.UserMangeResponse;
import com.manager.service.BindingService;
import com.manager.service.UserCustomService;
import com.manager.service.UserInfoService;
import com.manager.utils.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


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

    @Value("${api.kick.out}")
    private String kickOutUser;

    @Value("${jinan.user.url}")
    private String host;

    @Value("${online.list.method}")
    private String onlineUserList;

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
        user.setIsManager(ManagerTypeEnum.create(request.getIsManager()));
        user.setIsSynchro(YesNoEnum.create(request.getIsSync()));
        user.setMailbox(request.getUserEmail());
        user.setPostCode(request.getPostCode());
        user.setCompanyName(request.getCompanyName());
        user.setRegisterIp(request.getRegisterIp());
        user.setLoginIp(request.getLoginIp());
        user.setIdentification(request.getIdentityNum());
        user.setAddress(request.getAddress());
//        user.setLastQuitNum(request.getLastQuitNum());
//        user.setThisLoadNum(request.getThisLoadNum());
        user.setCheckLimit(request.getCheckLimit());
        user.setCheckNum(request.getCheckNum());
        user.setPlatformType(PlatformTypeEnum.create(request.getPlatformType()));

        //是否发布接收信息入库
        UserCustom userCustom = new UserCustom();
        userCustom.setIsSend(YesNoEnum.create(request.getIsSend()));
        userCustom.setIsReceive(YesNoEnum.create(request.getIsReceive()));
        userCustom.setIsReceiveSelf(YesNoEnum.create(request.getIsReceiveSelf()));
        userCustom.setSendProvince(request.getSendProvince());
        userCustom.setSendCity(request.getSendCity());
        userCustom.setReceiveProvince(request.getReceiveProvince());
        userCustom.setReceiveCity(request.getReceiveCity());

        userCustom.setPlatformType(PlatformTypeEnum.create(request.getPlatformType()));
        userCustom.setIsManager(ManagerTypeEnum.create(request.getIsManager()));
        userCustom.setUsername(request.getUserName());
        userCustom.setIsBinding(YesNoEnum.create(request.getIsBinding()));

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
            	if(request.getInLine1() != null && request.getInLine1() != "" && (request.getInLine2() == null || request.getInLine2() == "")){
            		user.setInLine("内线:"+request.getInLine1());
            	}
            	if(request.getInLine2() != null && request.getInLine2() != "" && (request.getInLine1() == null || request.getInLine1() == "")){
            		user.setInLine("电信内线:"+request.getInLine2());
            	}
            	if(request.getInLine2() != null && request.getInLine2() != "" && request.getInLine1() != null && request.getInLine1() != ""){
            		user.setInLine("内线:"+request.getInLine1()+"#"+"电信内线:"+request.getInLine2());
            	}
            	
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
            if(userMangeResponse != null && userMangeResponse.getInLine() != null){
            	String[] strArray = userMangeResponse.getInLine().split("#");
//            	System.out.println(strArray.length);
            	if(strArray.length == 1){
            		if(userMangeResponse.getInLine().startsWith("内线")){
            			String[] strArray1 = userMangeResponse.getInLine().split(":");
                		userMangeResponse.setInLine1(strArray1[1]);
            		}else {
            			String[] strArray2 = userMangeResponse.getInLine().split(":");
                		userMangeResponse.setInLine2(strArray2[1]);
					}
            	}else if(strArray.length == 2){
            		String[] strArray1 = strArray[0].split(":");
            		userMangeResponse.setInLine1(strArray1[1]);
            		String[] strArray2 = strArray[1].split(":");
            		userMangeResponse.setInLine2(strArray2[1]);
            	}
            	
            }//内线:11111#电信内线:22222
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

    /**
     * 更新密码、到期时间
     * @param request
     * @throws YCException
     */
    public void modifyPasswdOrDate(UserManageRequest request) throws YCException {
        /** 参数校验 */
        Validator.isEmpty(request,YCSystemStatusEnum.PARAM_EMPTY);
        Validator.isEmpty(request.getId(),"用户主键ID不能为空");
        User user = new User();
        user.setId(request.getId());
        user.setPassword(request.getPassword());
        user.setPasswordConfirm(request.getPasswordVerify());
        if (StringUtils.isNoneBlank(request.getStartTime())) {
            user.setBeginTime(DateTimeUtil.convertDate(request.getStartTime()));
        }
        if (StringUtils.isNoneBlank(request.getEndTime())) {
            user.setEndTime(DateTimeUtil.convertDate(request.getEndTime()));
        }
        try {
            userInfoService.updateUser(user);
        }  catch (DatabaseException e) {
            LOG.error("modifyPasswdOrDate exception",request);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }

    /**
     * 获取在线用户列表
     * @param request
     * @return
     * @throws YCException
     */
    public Page<UserMangeResponse> fetchOnlineUserList(OnlineUserRequest request) throws YCException {
        List<Integer> list = new ArrayList<>();
        String onlineUserUrl= host+onlineUserList;
        String resultData = fetchUserIds(onlineUserUrl);
        if (resultData != null) {
            JSONObject jsonObject = JSONObject.fromObject(resultData);
            JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("data"));
            for (Object obj : jsonArray) {
                JSONObject jObject = JSONObject.fromObject(obj.toString());
                String name = jObject.getString("name");
                String userid ="";
                //先处理
                if (!name.contains("_")) {
                    userid = name;
                    list.add(Integer.valueOf(userid));
                }
            }

            //ID去重
            if (list != null && list.size() > 0) {
                HashSet idset = new HashSet();
                for (Integer id : list) {
                    idset.add(id);
                }
                List<Integer> integerList = new ArrayList<>();
                for (Object object : idset) {
                    integerList.add((Integer) object);
                }
                request.setIdsList(integerList);
            }
        }
        Page<UserMangeResponse> userMangeResponsePage = null;
        try {
            userMangeResponsePage = userInfoService.getUserByUserIds(request);
            return userMangeResponsePage;
        } catch (DatabaseException e) {
            LOG.error("fetchOnlineUserList exception",request);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }

    /**
     * 踢出用户
     * @param userIds
     * @throws YCException
     */
    public void kickOutUser(String[] userIds) throws YCException {
        String kickoutUrl = host + kickOutUser;
        List<Integer> list = new ArrayList<>();
        List<Integer> listAll = new ArrayList<>();
        //int type = 0;//1.代表给单独或多个用户发送消息通知;2.踢出用户;0.代表广播系统消息
        Map<String, String> map = new HashMap<>();
        map.put("type", "2");
        for (String ids : userIds) {
            Integer id = Integer.valueOf(ids);
        	User user = userInfoService.selectById(id);
        	if(user != null && user.getPlatformType() != null){
        		if(user.getPlatformType().getValue() == 0){
        			list.add(Integer.valueOf(ids));
                    //踢出用户(pc客户端)
                    kickOutByUserId(list,kickoutUrl);
        		}else if(user.getPlatformType().getValue() == 1  && user.getRegistrationid() != null){
        			//踢出ios用户
        			String msgContent = "被物流网客服强迫下线";
        			PushExample.SendUsersPushToIOS(msgContent,map, user.getRegistrationid());
        		}else if(user.getPlatformType().getValue() == 2  && user.getRegistrationid() != null){
        			//踢出安卓用户
        			String msgContent = "被物流网客服强迫下线";
        			PushExample.SendUsersPushToAndroid("济南网通知:", msgContent,map, user.getRegistrationid());
        		}
        	}
        	listAll.add(Integer.valueOf(ids));
        }
        try {

            //更新用户状态
            userInfoService.batchUpdateUserStatus(listAll);
        }  catch (DatabaseException e) {
            LOG.error("kickOutUser exception",userIds);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }

    }

    /**
     * 查询在线用户
     * @param url
     * @return
     */
    public String fetchUserIds(String url){
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Content-Type", "text/plain;charset=utf-8"));
        params.add(new BasicNameValuePair("Content-Encoding", "utf-8"));
        String userIds =  URLConnUtil.doPost(url,params);
        return userIds;
    }

    /**
     * 踢出在线用户
     * @param userIds
     * @param action
     * @return
     * @throws YCException
     */
    public boolean kickOutByUserId(List<Integer> userIds,String action) throws YCException {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Content-Type", "text/plain;charset=utf-8"));
        params.add(new BasicNameValuePair("Content-Encoding", "utf-8"));
//        JSONObject reqJson = new JSONObject();
//        reqJson.put("userids",userIds);
//        reqJson.put("type",3);
        List<NameValuePair> params2 = new ArrayList<NameValuePair>();
        String[] arr=new String[userIds.size()];
        for (int i=0;i<userIds.size();i++){
            arr[i] = String.valueOf(userIds.get(i));
        }

        params2.add(new BasicNameValuePair("userids", "arr"));
        params2.add(new BasicNameValuePair("type", "3"));
//        String result =  URLConnUtil.doPost(action, reqJson.toString(), params);
        String result =  URLConnUtil.doGet(action, params2, params);
        JSONObject jsonObject = JSONObject.fromObject(result);
        if(jsonObject.getString("status") .equals("0")){
            return true;
        }else {
            LOG.error("kickOutByUserId exception",userIds);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }
    
    /**
     * 返回联想账号
     * @param user
     * @return
     * @throws YCException
     */
	public List<String> selectByParam(User user) throws YCException {
		
		List<String> usernames = new ArrayList<>();
		try{
			List<User> userList = userInfoService.selectByParam(user);
			
			if(userList != null && userList.size()>0){
				for(User u : userList){
					usernames.add(u.getUsername());
				}
			}
		}  catch (DatabaseException e) {
	        LOG.error("selectByParam exception",user);
	        throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
	    }
		return usernames;
	    	
	}

    public List<User> verifyNameExist(String userName) throws YCException {
        User user = new User();
        user.setUsername(userName);
        try {
            List<User> list = userInfoService.queryUser(user);
            return list;
        } catch (DatabaseException e) {
            LOG.error("verifyNameExist exception",userName);
            throw new YCException(YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getCode(), YCSystemStatusEnum.INVOKE_API_RETURN_EXCEPTION.getDesc());
        }
    }
}
