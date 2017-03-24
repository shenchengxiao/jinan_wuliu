package com.manager.exception;


import com.manager.service.ISystemStatusEnum;

/**
 * 数据库异常定义
 *
 */
public class DatabaseException extends YCSystemException {

    public DatabaseException(String message) {
        super("", message);
    }

    public DatabaseException(String code, String message) {
        super(code, message);
    }

    public DatabaseException(String code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public DatabaseException(ISystemStatusEnum systemStatusEnum) {
        super(systemStatusEnum.getCode(), systemStatusEnum.getDesc());
    }

    public DatabaseException(ISystemStatusEnum systemStatusEnum, String message) {
        super(systemStatusEnum.getCode(), message);
    }

    public DatabaseException(ISystemStatusEnum systemStatusEnum, Throwable cause) {
        super(systemStatusEnum.getCode(), systemStatusEnum.getDesc(), cause);
    }

    public DatabaseException(ISystemStatusEnum systemStatusEnum, String message, Throwable cause) {
        super(systemStatusEnum.getCode(), message, cause);
    }

}
