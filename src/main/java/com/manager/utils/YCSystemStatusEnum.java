package com.manager.utils;


import com.manager.service.IBusinessStatusEnum;

/**
 * api状态、错误定义.
 * 请保留000000、100000、200000、300000、400000、500000、600000、700000、800000、900000
 * @author Jess.zou 2016-04-04
 */
public enum YCSystemStatusEnum implements IBusinessStatusEnum {
    SUCCESS(                            0, "成功"),

    /** 1XXXXX 参数类错误. */
    PARAM_EMPTY(                        100001, "参数为空."),
    PHONE_NUMBER(                       100008,  "手机号不能为空"),


    /** 2XXXXX 数据验证错误. */
    USER_ID_EMPTY(                      200001, "用户ID不能为空."),
    VERIFYCODE(                         200002, "验证码不能为空"),
    NUB_AND_VERIFY(                     200003, "手机号与验证码不匹配"),
    USER_NAME_PASSWORD_EMPTY(           200004,  "用户名或密码错误"),
    LOGIN_FAIL(                         200005, "登录失败."),
    API_SIGN_FAIL(                      200006, "Api接口签名失败."),
    API_SIGN_TIMESTAMP_EXPIRE(          200007, "接口请求已过期."),
    API_SIGN_VERIFY_EXCEPTION(          200008, "接口签名验证发生异常."),


    /** 3XXXXX 数据逻辑错误(偏业务层) */
    NOT_FOUND_AVALIABLE_CHANNEL_ERROR(  300001, "为查找有效通道."),
    REQUEST_IP_NOT_WITHE_LIST(          300002, "无效的IP请求."),
    SEND_SMS_FAIL(                      300003, "发送短信失败."),
    UPGRADE_NON_NEW_VERSION(            300004, "当前已是最新版本."),

    /** 5XXXXX 数据不存在. */
    NOT_FOUND_USER(                     500002, "注册的用户不存在"),
    NULL(                               500003, "数据为空"),
    VERSION_NULL(                       500004, "版本数据为空"),

    /** 9XXXXX 系统类错误.*/
    SYSTEM_ERROR(                       -1, "系统异常."),
    INVOKE_API_RETURN_EXCEPTION(        900001, "调用接口响应失败状态."),
    NETWORK_ERROR(                      900002, "网络异常"),
    ;

    YCSystemStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    /** 系统状态编码. */
    private int code;

    /** 系统状态描述. */
    private String desc;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}