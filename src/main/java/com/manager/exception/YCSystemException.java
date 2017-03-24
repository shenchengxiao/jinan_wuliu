package com.manager.exception;

public class YCSystemException extends Exception {

    /**
     * 错误编码
     */
    private String code;

    /**
     * 错误信息
     */
    private String message;

    public YCSystemException() {
        super();
    }

    public YCSystemException(String code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public YCSystemException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("RWSystemException{");
        sb.append("code='").append(code).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
