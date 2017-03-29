package com.manager.pojo;

import com.manager.request.BaseEntity;

import java.util.Date;

public class UserInfo extends BaseEntity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.id
     *
     * @mbggenerated Tue Mar 28 18:23:32 CST 2017
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.user_name
     *
     * @mbggenerated Tue Mar 28 18:23:32 CST 2017
     */
    private String userName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.passwd
     *
     * @mbggenerated Tue Mar 28 18:23:32 CST 2017
     */
    private String passwd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.phone_num
     *
     * @mbggenerated Tue Mar 28 18:23:32 CST 2017
     */
    private String phoneNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.role
     *
     * @mbggenerated Tue Mar 28 18:23:32 CST 2017
     */
    private Integer role;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.be_used
     *
     * @mbggenerated Tue Mar 28 18:23:32 CST 2017
     */
    private Integer beUsed;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_info.create_time
     *
     * @mbggenerated Tue Mar 28 18:23:32 CST 2017
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.id
     *
     * @return the value of user_info.id
     *
     * @mbggenerated Tue Mar 28 18:23:32 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.id
     *
     * @param id the value for user_info.id
     *
     * @mbggenerated Tue Mar 28 18:23:32 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.user_name
     *
     * @return the value of user_info.user_name
     *
     * @mbggenerated Tue Mar 28 18:23:32 CST 2017
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.user_name
     *
     * @param userName the value for user_info.user_name
     *
     * @mbggenerated Tue Mar 28 18:23:32 CST 2017
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.passwd
     *
     * @return the value of user_info.passwd
     *
     * @mbggenerated Tue Mar 28 18:23:32 CST 2017
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.passwd
     *
     * @param passwd the value for user_info.passwd
     *
     * @mbggenerated Tue Mar 28 18:23:32 CST 2017
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.phone_num
     *
     * @return the value of user_info.phone_num
     *
     * @mbggenerated Tue Mar 28 18:23:32 CST 2017
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.phone_num
     *
     * @param phoneNum the value for user_info.phone_num
     *
     * @mbggenerated Tue Mar 28 18:23:32 CST 2017
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.role
     *
     * @return the value of user_info.role
     *
     * @mbggenerated Tue Mar 28 18:23:32 CST 2017
     */
    public Integer getRole() {
        return role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.role
     *
     * @param role the value for user_info.role
     *
     * @mbggenerated Tue Mar 28 18:23:32 CST 2017
     */
    public void setRole(Integer role) {
        this.role = role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.be_used
     *
     * @return the value of user_info.be_used
     *
     * @mbggenerated Tue Mar 28 18:23:32 CST 2017
     */
    public Integer getBeUsed() {
        return beUsed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.be_used
     *
     * @param beUsed the value for user_info.be_used
     *
     * @mbggenerated Tue Mar 28 18:23:32 CST 2017
     */
    public void setBeUsed(Integer beUsed) {
        this.beUsed = beUsed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_info.create_time
     *
     * @return the value of user_info.create_time
     *
     * @mbggenerated Tue Mar 28 18:23:32 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_info.create_time
     *
     * @param createTime the value for user_info.create_time
     *
     * @mbggenerated Tue Mar 28 18:23:32 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
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
        UserInfo other = (UserInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getPasswd() == null ? other.getPasswd() == null : this.getPasswd().equals(other.getPasswd()))
            && (this.getPhoneNum() == null ? other.getPhoneNum() == null : this.getPhoneNum().equals(other.getPhoneNum()))
            && (this.getRole() == null ? other.getRole() == null : this.getRole().equals(other.getRole()))
            && (this.getBeUsed() == null ? other.getBeUsed() == null : this.getBeUsed().equals(other.getBeUsed()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_info
     *
     * @mbggenerated Tue Mar 28 18:23:32 CST 2017
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getPasswd() == null) ? 0 : getPasswd().hashCode());
        result = prime * result + ((getPhoneNum() == null) ? 0 : getPhoneNum().hashCode());
        result = prime * result + ((getRole() == null) ? 0 : getRole().hashCode());
        result = prime * result + ((getBeUsed() == null) ? 0 : getBeUsed().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }
}