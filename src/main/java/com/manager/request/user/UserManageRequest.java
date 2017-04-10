package com.manager.request.user;

import com.manager.request.BaseQuery;

/**
 * Created by shencx on 2017/4/10.
 */
public class UserManageRequest extends BaseQuery{

    private Integer id;

    /** 用户名称 */
    private String userName;

    /** 用户编号 */
    private String userNum;

    /** 密码 */
    private String password;

    /** 确认密码 */
    private String passwordVerify;

    /** 联系号码 */
    private String phoneNumber;

    /** 省份 */
    private String province;

    /** 城市 */
    private String city;

    /** 县 */
    private String county;

    /** 是否可用（0、否 1、是） */
    private Integer isAbled;

    /** 是否是管理员（0、否 1、是） */
    private Integer isManager;

    /** 是否同步（0、否 1、是） */
    private Integer isSync;

    /** 邮箱 */
    private String userEmail;

    /** 邮编号码 */
    private String postCode;

    /** 公司名称 */
    private String companyName;

    /** 注册IP */
    private String registerIp;

    /** 最后登录IP */
    private String loginIp;

    /** 服务开始时间 */
    private String startTime;

    /** 服务结束时间 */
    private String endTime;

    /** 身份证号码 */
    private String identityNum;

    /** 地址 */
    private String address;

    /** 上次退出编号 */
    private String lastQuitNum;

    /** 本次下载编号 */
    private String thisLoadNum;

    /** 硬盘号 */
    private String hardNum;

    /** 网卡号 */
    private String networkNum;

    /** 临时硬盘网卡号 */
    private String temporaryCard;

    /** 限额 */
    private Integer checkLimit;

    /** 次数 */
    private Integer checkNum;

    /** 能否发布信息 */
    private Integer isSend;

    /** 能否接受信息 */
    private Integer isReceive;

    /** 能否接受自己发布信息 */
    private Integer isReceiveSelf;

    /** 是否绑定电脑 */
    private Integer isBinding;

    /** 可发布省份 */
    private String sendProvince;

    /** 可发布城市 */
    private String sendCity;

    /** 可接收省份 */
    private String receiveProvince;

    /** 可接收城市 */
    private String receiveCity;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserNum() {
        return userNum;
    }

    public void setUserNum(String userNum) {
        this.userNum = userNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordVerify() {
        return passwordVerify;
    }

    public void setPasswordVerify(String passwordVerify) {
        this.passwordVerify = passwordVerify;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public Integer getIsAbled() {
        return isAbled;
    }

    public void setIsAbled(Integer isAbled) {
        this.isAbled = isAbled;
    }

    public Integer getIsManager() {
        return isManager;
    }

    public void setIsManager(Integer isManager) {
        this.isManager = isManager;
    }

    public Integer getIsSync() {
        return isSync;
    }

    public void setIsSync(Integer isSync) {
        this.isSync = isSync;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getIdentityNum() {
        return identityNum;
    }

    public void setIdentityNum(String identityNum) {
        this.identityNum = identityNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLastQuitNum() {
        return lastQuitNum;
    }

    public void setLastQuitNum(String lastQuitNum) {
        this.lastQuitNum = lastQuitNum;
    }

    public String getThisLoadNum() {
        return thisLoadNum;
    }

    public void setThisLoadNum(String thisLoadNum) {
        this.thisLoadNum = thisLoadNum;
    }

    public String getHardNum() {
        return hardNum;
    }

    public void setHardNum(String hardNum) {
        this.hardNum = hardNum;
    }

    public String getNetworkNum() {
        return networkNum;
    }

    public void setNetworkNum(String networkNum) {
        this.networkNum = networkNum;
    }

    public String getTemporaryCard() {
        return temporaryCard;
    }

    public void setTemporaryCard(String temporaryCard) {
        this.temporaryCard = temporaryCard;
    }

    public Integer getCheckLimit() {
        return checkLimit;
    }

    public void setCheckLimit(Integer checkLimit) {
        this.checkLimit = checkLimit;
    }

    public Integer getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(Integer checkNum) {
        this.checkNum = checkNum;
    }

    public Integer getIsSend() {
        return isSend;
    }

    public void setIsSend(Integer isSend) {
        this.isSend = isSend;
    }

    public Integer getIsReceive() {
        return isReceive;
    }

    public void setIsReceive(Integer isReceive) {
        this.isReceive = isReceive;
    }

    public Integer getIsReceiveSelf() {
        return isReceiveSelf;
    }

    public void setIsReceiveSelf(Integer isReceiveSelf) {
        this.isReceiveSelf = isReceiveSelf;
    }

    public Integer getIsBinding() {
        return isBinding;
    }

    public void setIsBinding(Integer isBinding) {
        this.isBinding = isBinding;
    }

    public String getSendProvince() {
        return sendProvince;
    }

    public void setSendProvince(String sendProvince) {
        this.sendProvince = sendProvince;
    }

    public String getSendCity() {
        return sendCity;
    }

    public void setSendCity(String sendCity) {
        this.sendCity = sendCity;
    }

    public String getReceiveProvince() {
        return receiveProvince;
    }

    public void setReceiveProvince(String receiveProvince) {
        this.receiveProvince = receiveProvince;
    }

    public String getReceiveCity() {
        return receiveCity;
    }

    public void setReceiveCity(String receiveCity) {
        this.receiveCity = receiveCity;
    }
}
