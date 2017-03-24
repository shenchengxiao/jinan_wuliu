package com.manager.exception;


import com.manager.service.IBusinessStatusEnum;

/**
 * 验证类异常定义
 *
 */
public class ValidationException extends YCException {

    private static final long serialVersionUID = 6758656137317251975L;

    public ValidationException(int code, String message) {
        super(code, message);
    }

    public ValidationException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public ValidationException(IBusinessStatusEnum businessStatusEnum) {
        super(businessStatusEnum.getCode(), businessStatusEnum.getDesc());
    }

    public ValidationException(IBusinessStatusEnum businessStatusEnum, String message) {
        super(businessStatusEnum.getCode(), message);
    }

    public ValidationException(IBusinessStatusEnum businessStatusEnum, Throwable cause) {
        super(businessStatusEnum.getCode(), businessStatusEnum.getDesc(), cause);
    }

    public ValidationException(IBusinessStatusEnum businessStatusEnum, String message, Throwable cause) {
        super(businessStatusEnum.getCode(), message, cause);
    }
}
