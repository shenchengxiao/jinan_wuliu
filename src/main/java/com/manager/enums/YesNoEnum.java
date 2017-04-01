package com.manager.enums;

import com.manager.service.IEnum;

/**
 * Created by shencx on 2017/3/31.
 */
public enum YesNoEnum implements IEnum<Integer> {

    NO(0, "否"),
    YES(1, "是")
    ;
    YesNoEnum(int value, String title) {
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

    public static YesNoEnum create(Integer value) {
        return EnumUtils.getEnum(values(), value);
    }
}
