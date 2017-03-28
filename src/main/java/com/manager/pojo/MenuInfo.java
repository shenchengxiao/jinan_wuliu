package com.manager.pojo;

import com.manager.request.BaseEntity;

public class MenuInfo extends BaseEntity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu_info.id
     *
     * @mbggenerated Mon Mar 27 17:43:50 CST 2017
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu_info.menu_name
     *
     * @mbggenerated Mon Mar 27 17:43:50 CST 2017
     */
    private String menuName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu_info.menu_url
     *
     * @mbggenerated Mon Mar 27 17:43:50 CST 2017
     */
    private String menuUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu_info.parent_id
     *
     * @mbggenerated Mon Mar 27 17:43:50 CST 2017
     */
    private Integer parentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column menu_info.be_used
     *
     * @mbggenerated Mon Mar 27 17:43:50 CST 2017
     */
    private Integer beUsed;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu_info.id
     *
     * @return the value of menu_info.id
     *
     * @mbggenerated Mon Mar 27 17:43:50 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu_info.id
     *
     * @param id the value for menu_info.id
     *
     * @mbggenerated Mon Mar 27 17:43:50 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu_info.menu_name
     *
     * @return the value of menu_info.menu_name
     *
     * @mbggenerated Mon Mar 27 17:43:50 CST 2017
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu_info.menu_name
     *
     * @param menuName the value for menu_info.menu_name
     *
     * @mbggenerated Mon Mar 27 17:43:50 CST 2017
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu_info.menu_url
     *
     * @return the value of menu_info.menu_url
     *
     * @mbggenerated Mon Mar 27 17:43:50 CST 2017
     */
    public String getMenuUrl() {
        return menuUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu_info.menu_url
     *
     * @param menuUrl the value for menu_info.menu_url
     *
     * @mbggenerated Mon Mar 27 17:43:50 CST 2017
     */
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu_info.parent_id
     *
     * @return the value of menu_info.parent_id
     *
     * @mbggenerated Mon Mar 27 17:43:50 CST 2017
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu_info.parent_id
     *
     * @param parentId the value for menu_info.parent_id
     *
     * @mbggenerated Mon Mar 27 17:43:50 CST 2017
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column menu_info.be_used
     *
     * @return the value of menu_info.be_used
     *
     * @mbggenerated Mon Mar 27 17:43:50 CST 2017
     */
    public Integer getBeUsed() {
        return beUsed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column menu_info.be_used
     *
     * @param beUsed the value for menu_info.be_used
     *
     * @mbggenerated Mon Mar 27 17:43:50 CST 2017
     */
    public void setBeUsed(Integer beUsed) {
        this.beUsed = beUsed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_info
     *
     * @mbggenerated Mon Mar 27 17:43:50 CST 2017
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
        MenuInfo other = (MenuInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMenuName() == null ? other.getMenuName() == null : this.getMenuName().equals(other.getMenuName()))
            && (this.getMenuUrl() == null ? other.getMenuUrl() == null : this.getMenuUrl().equals(other.getMenuUrl()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getBeUsed() == null ? other.getBeUsed() == null : this.getBeUsed().equals(other.getBeUsed()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table menu_info
     *
     * @mbggenerated Mon Mar 27 17:43:50 CST 2017
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMenuName() == null) ? 0 : getMenuName().hashCode());
        result = prime * result + ((getMenuUrl() == null) ? 0 : getMenuUrl().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getBeUsed() == null) ? 0 : getBeUsed().hashCode());
        return result;
    }
}