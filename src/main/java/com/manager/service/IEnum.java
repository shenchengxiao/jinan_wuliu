package com.manager.service;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.manager.utils.EnumResponseConverter;


/**
 * 实现描述：枚举类接口
 */
@JsonSerialize(converter = EnumResponseConverter.class)
public interface IEnum<T> {

    T getValue();

    String getTitle();

}
