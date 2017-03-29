package com.manager.exception;

/**
 * Created by shencx on 2017/3/29.
 */
public class YCApiException extends YCException {

    public YCApiException() {
        super(1,"Api内部发生未知异常");
    }

    public YCApiException(String message) {
        super(1,message);
    }

    public YCApiException(String message, Throwable cause) {
        super(1, message, cause);
    }
}
