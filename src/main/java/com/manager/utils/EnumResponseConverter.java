package com.manager.utils;


import com.fasterxml.jackson.databind.util.StdConverter;
import com.manager.service.IEnum;

/**
 *
 * Json解析转换器
 * Json响应枚举类型为明文
 */
public class EnumResponseConverter extends StdConverter<IEnum, String> {

    @Override
    public String convert(IEnum iEnum) {
        return null == iEnum ? "" : iEnum.getTitle();
    }
}
