package com.manager.service
        ;

/**
 * 业务枚举,用来描述业务处理状态.
 */
public interface IBusinessStatusEnum {

    /**
     * 业务处理状态码
     */
    int getCode();

    /**
     * 业务处理状态内容
     * @return
     */
    String getDesc();

}
