package com.manager.enums;

import com.manager.service.IEnum;

/**
 * Created by shencx on 2017/4/10.
 */
public enum PlatformTypeEnum implements IEnum<Integer> {

    WINDOWS(0, "windows"),
    IOS(1, "iOS"),
    ANDROID(2, "Android"),
    MOBILE(3,   "移动端")
    ;
    PlatformTypeEnum(int value, String title) {
        this.value = value;
        this.title = title;
    }

    private int value;
    private String title;

    @Override
    public Integer getValue() {
        return value;
    }

    public String getTitle() {
        return title;
    }

    public static PlatformTypeEnum create(Integer value) {
        return EnumUtils.getEnum(values(), value);
    }
}
