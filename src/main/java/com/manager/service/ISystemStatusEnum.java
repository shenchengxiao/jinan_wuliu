package com.manager.service;

/**
 * 业务系统状态定义.
 *
 */
public interface ISystemStatusEnum {

    /**
     * 业务系统处理状态码
     */
    String getCode();

    /**
     * 业务系统处理状态内容
     * @return
     */
    String getDesc();

}
