package com.manager.utils;


import com.manager.service.IEnum;

/**
 * Created by yuchimin on 16/8/4.
 */
public enum UserRoleEnum implements IEnum<Integer> {
    /**
     * 商家用户管理员角色.
     */
    SPAdmin(1, "SP商家管理员"),
    PartnerAdmin(2, "商家合伙人管理员"),
//  MerchantAdmin(4, "普通商家管理员"),

    /**
     * 口粮平台用户角色
     */
    SuperAdmin(16, "超级管理员"),
    ChannelAdmin(32, "通道管理员"),
    Reviewer(64, "审核人员"),
    Editor(128, "运营编辑"),
    CustomerServer(256, "客服人员"),
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
