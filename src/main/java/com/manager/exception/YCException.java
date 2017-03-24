package com.manager.exception;


import com.manager.service.IBusinessStatusEnum;

/**
 * 异常定义
 *
 */
public class YCException extends Exception {

    private static final long serialVersionUID = 6464791608647205003L;

    /**
     * 错误编码
     */
    private int code;

    /**
     * 错误信息
     */
    private String message;

    public YCException() {
        super();
    }

    public YCException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public YCException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    /**
     * 使用IBusinessStatusEnum构造异常.
     * @param businessStatusEnum
     */
    public YCException(IBusinessStatusEnum businessStatusEnum) {
        this(businessStatusEnum.getCode(), businessStatusEnum.getDesc());
    }

    /**
     * 使用IBusinessStatusEnum构造异常.并使用第二个参数覆盖message
     * @param businessStatusEnum
     * @param message
     */
    public YCException(IBusinessStatusEnum businessStatusEnum, String message) {
        this(businessStatusEnum.getCode(), message);
    }

    public YCException(IBusinessStatusEnum businessStatusEnum, Throwable cause) {
        this(businessStatusEnum.getCode(), businessStatusEnum.getDesc(), cause);
    }

    /**
     * 使用IBusinessStatusEnum构造异常.并使用第二个参数覆盖message
     * @param businessStatusEnum
     * @param message
     * @param cause
     */
    public YCException(IBusinessStatusEnum businessStatusEnum, String message, Throwable cause) {
        this(businessStatusEnum.getCode(), message, cause);
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RWException{");
        sb.append("code=").append(code);
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
