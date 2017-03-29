package com.manager.pojo;

import com.manager.request.BaseEntity;

public class RoleMenuInfo extends BaseEntity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_menu_info.id
     *
     * @mbggenerated Tue Mar 28 18:23:32 CST 2017
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_menu_info.menu_info_id
     *
     * @mbggenerated Tue Mar 28 18:23:32 CST 2017
     */
    private Integer menuInfoId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role_menu_info.role_type
     *
     * @mbggenerated Tue Mar 28 18:23:32 CST 2017
     */
    private Integer roleType;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_menu_info.id
     *
     * @return the value of role_menu_info.id
     *
     * @mbggenerated Tue Mar 28 18:23:32 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_menu_info.id
     *
     * @param id the value for role_menu_info.id
     *
     * @mbggenerated Tue Mar 28 18:23:32 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_menu_info.menu_info_id
     *
     * @return the value of role_menu_info.menu_info_id
     *
     * @mbggenerated Tue Mar 28 18:23:32 CST 2017
     */
    public Integer getMenuInfoId() {
        return menuInfoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_menu_info.menu_info_id
     *
     * @param menuInfoId the value for role_menu_info.menu_info_id
     *
     * @mbggenerated Tue Mar 28 18:23:32 CST 2017
     */
    public void setMenuInfoId(Integer menuInfoId) {
        this.menuInfoId = menuInfoId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role_menu_info.role_type
     *
     * @return the value of role_menu_info.role_type
     *
     * @mbggenerated Tue Mar 28 18:23:32 CST 2017
     */
    public Integer getRoleType() {
        return roleType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role_menu_info.role_type
     *
     * @param roleType the value for role_menu_info.role_type
     *
     * @mbggenerated Tue Mar 28 18:23:32 CST 2017
     */
    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menu_info
     *
     * @mbggenerated Tue Mar 28 18:23:32 CST 2017
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        RoleMenuInfo other = (RoleMenuInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMenuInfoId() == null ? other.getMenuInfoId() == null : this.getMenuInfoId().equals(other.getMenuInfoId()))
            && (this.getRoleType() == null ? other.getRoleType() == null : this.getRoleType().equals(other.getRoleType()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role_menu_info
     *
     * @mbggenerated Tue Mar 28 18:23:32 CST 2017
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMenuInfoId() == null) ? 0 : getMenuInfoId().hashCode());
        result = prime * result + ((getRoleType() == null) ? 0 : getRoleType().hashCode());
        return result;
    }
}