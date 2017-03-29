package com.manager.exception;

/**
 * Created by shencx on 2017/3/29.
 */
public class YCIllegalAccessException extends YCApiException {
    public YCIllegalAccessException() {
        super("非法访问");
    }

    public YCIllegalAccessException(String message) {
        super(message);
    }

    public YCIllegalAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
