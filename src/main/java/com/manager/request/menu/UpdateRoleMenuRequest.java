package com.manager.request.menu;

/**
 * Created by shencx on 2017/5/3.
 */
public class UpdateRoleMenuRequest {

    /** 菜单主键ID */
    private Integer menuInfoId;

    /** 角色值 */
    private Integer roleType;

    public Integer getMenuInfoId() {
        return menuInfoId;
    }

    public void setMenuInfoId(Integer menuInfoId) {
        this.menuInfoId = menuInfoId;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }
}
