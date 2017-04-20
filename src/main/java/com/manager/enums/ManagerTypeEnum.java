package com.manager.enums;

import com.manager.service.IEnum;

/**
 * Created by shencx on 2017/4/20.
 */
public enum ManagerTypeEnum implements IEnum<Integer>{


    ADMINISTRATOR(1, "系统管理员"),
    WORKER(2,        "工作人员"),
    OWNERS(3,        "车主"),
    SHIPPER(4,       "货主"),
    UNVERIFIED(5,    "未认证用户")
    ;
    ManagerTypeEnum(int value, String title) {
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

    public static ManagerTypeEnum create(Integer value) {
        return EnumUtils.getEnum(values(), value);
    }
}
