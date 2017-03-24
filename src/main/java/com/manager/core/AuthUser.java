package com.manager.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.manager.utils.CookieUtils;
import com.manager.utils.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;

/**
 * Created by shencx on 2017/3/24.
 */
public class AuthUser implements Serializable {

    private static final String COOKIE_KEY = "AUTH";
    private static final String SIGNATURE_KEY = "78d29a50-5af9-4a18-a026-1ac97dfbfee3";

    /** 日志组件初始化 */
    private static Logger LOG = LoggerFactory.getLogger(AuthUser.class);

    /**
     * 用户标识
     */
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 用户角色
     */
    private Integer userRole;

    /**
     * 签名
     */
    private String sign;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    /**
     * 是否认证用户
     * @return
     */
    @JSONField(serialize=false)
    public boolean isAuthorized(){
        return id > 0;
    }

    /**
     * 获取当前用户
     * @return
     */
    public static AuthUser getCurrentUser(){
        HttpServletRequest request = ActionContext.getActionContext().getHttpRequest();
        if(request!=null) {
            try {
                String json = CookieUtils.getCookieByName(request, COOKIE_KEY);
                json = URLDecoder.decode(json,"utf-8");
//                LogUtils.debug(LOG,"获取用户Cookie信息","AUTH",json);
                if (StringUtils.hasText(json)) {
                    AuthUser user = JSON.parseObject(json, AuthUser.class);
                    if(user.verfiy()) {
                        return user;
                    }
                    else{
//                        LogUtils.debug(LOG,"获取用户AUTH信息签名验证失败","AUTH",json);
                        user.logout();
                    }
                }
            } catch (Throwable e) {
//                LogUtils.debug(LOG,"获取用户Cookie信息失败","ex",e);
            }
        }
        return new AuthUser();
    }

    /**
     * 登录用户
     * @param id 用户标识
     * @param name 用户名
     * @param merchantId 商家用户ID
     * @param businessCode 企业编码
     * @param merchantType 商家类型
     * @param userRole 用户角色
     * @return 登录是否成功
     */
    public boolean login(Long id, String name, Integer merchantId,
                         String businessCode, Integer merchantType, Integer userRole){
        this.setId(id);
        this.setName(name);
        this.setUserRole(userRole);
        try {
            String md5 = createMD5();
            this.setSign(md5);

            HttpServletRequest request = ActionContext.getActionContext().getHttpRequest();
            HttpServletResponse response = ActionContext.getActionContext().getHttpResponse();
            String domain = request.getHeader("host");
            String value = JSON.toJSONString(this);
            value = URLEncoder.encode(value,"utf-8"); //urlencode 编码
            CookieUtils.writeCookie(response,domain,COOKIE_KEY,value,60*60);
        }catch (Throwable e){
            return false;
        }
        return true;
    }

    /**
     * 退出登录
     */
    public void logout(){
        this.id = null;
        HttpServletRequest request = ActionContext.getActionContext().getHttpRequest();
        HttpServletResponse response = ActionContext.getActionContext().getHttpResponse();
        String domain = request.getHeader("host");
        CookieUtils.removeCookie(response,COOKIE_KEY,domain);
    }

    /**
     * 用户验证
     * @return
     */
    private boolean verfiy(){
        try {
            String md5 = createMD5();
            return md5.equals(this.getSign());
        }catch (Throwable e){
            return false;
        }
    }

    /**
     * 生成当前用户的签名信息
     * @return
     * @throws NoSuchAlgorithmException
     */
    private String createMD5() throws NoSuchAlgorithmException {
        String sign = this.getSign();
        this.sign = null;
        String text =  JSON.toJSONString(this);
        String md5 = MD5Util.encryptMD5(String.format("%s&%s&%s",SIGNATURE_KEY,text,SIGNATURE_KEY));
        this.setSign(sign);
        return md5;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
