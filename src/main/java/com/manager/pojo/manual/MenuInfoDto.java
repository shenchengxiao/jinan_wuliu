package com.manager.pojo.manual;

import java.io.Serializable;

/**
 * Created by shencx on 2017/3/27.
 */
public class MenuInfoDto implements Serializable{

    /**
     * 主键 ID
     */
    private Integer id;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单 URL
     */
    private String menuUrl;

    /**
     * 父节点ID（菜单根节点）
     */
    private Integer parentId;

    /**
     * 角色类型
     */
    private Integer roleType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MenuInfoDto{");
        sb.append("id=").append(id);
        sb.append(", menuName='").append(menuName).append('\'');
        sb.append(", menuUrl='").append(menuUrl).append('\'');
        sb.append(", parentId=").append(parentId);
        sb.append(", roleType=").append(roleType);
        sb.append('}');
        return sb.toString();
    }
}
