/**
 *
 */
package com.manager.enums;


import com.manager.service.IBusinessStatusEnum;

/**
 * 业务异常状态定义<br/>
 * 定义公共的异常状态,各个业务系统的异常状态由各个系统自行定义.<br/>
 * 但必须实现{@link com.manager.service.IBusinessStatusEnum}接口.<br/>
 * APIResponse包装业务状态时,会接收该接口的对象.<br/>
 *
 * @date Aug 18, 2014
 * @desc 业务状态
 */
public enum BusinessStatusEnum implements IBusinessStatusEnum {
    SUCCESS(                                        0, "操作成功."),
    FAIL(                                           -1,"发生错误."),
    /** 1001XXXXX参数类错误*/
    PARAM_EMPTY(                                    100100000, "参数为空."),
    PARAM_ILLEGAL(                                  100100001, "参数格式不正确."),
    /** 1002XXXXX业务数据验证错误*/
    VALIDATE_ERROR(                                 100200000, "验证错误."),
    USER_FORGETPWD_SMS_ERR(                         100200001, "短信验证码输入错误."),
    /** 100200[1,2,3]XX操作类错误*/
    INSERT_FAIL(                                    100200101, "插入失败."),
    UPDATE_FAIL(                                    100200201, "更新失败."),
    DELETE_FAIL(                                    100200301, "删除失败."),
    /** 1004XXXXX权限、安全、敏感信息类问题*/
    SECURITY_ERROR(                                 100400000, "权限/安全验证出错."),
    USER_LOGIN_STATUS_INVALID(                      100400001, "用户未登录."),
    USER_MODIFY_PASSWORD_OLD_WRONG(                 100400002, "原密码不正确."),
    USER_PASSWORD_WRONG(                            100400003, "密码不正确."),
    LOGIN_ACCOUNT_LOG_ON_OTHER_WEB(                 100400100, "您的账号已经在其他浏览器登录."),
    LOGIN_ACCOUNT_LOG_ON_OTHER_CLIENT(              100400101, "您的账号已经在其他移动设备登录."),
    /** 1005XXXXX系统错误 */
    SYSTEM_ERROR(                                   100500000, "系统错误."),

    /** 1009XXXXX描述无法从系统中获得数据*/
    EMPTY_RESULT(                                   100900000, "数据不存在."),
    ;

    BusinessStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static BusinessStatusEnum create(int code) {
        for (BusinessStatusEnum ase : values()) {
            if (ase.getCode() == code) {
                return ase;
            }
        }
        return null;
    }

    private int code;
    private String desc;
    public int getStatus() {
        return code;
    }
    public void setStatus(int code) {
        this.code = code;
    }
    public String getStatusmsg() {
        return desc;
    }
    public void setStatusmsg(String desc) {
        this.desc = desc;
    }

    public int getCode() {
        return this.code;
    }
    public void setCode(int code) {this.code = code;}

    public String getDesc() {
        return this.desc;
    }
    public void setDesc(String desc) {this.desc = desc;}

}
