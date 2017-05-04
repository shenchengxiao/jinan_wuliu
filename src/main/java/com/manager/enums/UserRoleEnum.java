package com.manager.enums;


import com.manager.service.IEnum;

/**
 * Created by shencx on 17/3/29.
 */
public enum UserRoleEnum implements IEnum<Integer> {

    /**
     * 用户角色
     */
    SuperAdmin(16, "超级管理员"),
    CustomerServer(32, "客服人员"),
    Worker(64,  "工作人员")
    ;
    UserRoleEnum(int value, String title) {
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

    public static UserRoleEnum create(Integer value) {
        return EnumUtils.getEnum(values(), value);
    }
}
