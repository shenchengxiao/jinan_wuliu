package com.manager.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * [STRATO MyBatis Generator]
 * Table: phone_record
@mbggenerated do_not_delete_during_merge 2017-04-28 17:23:47
 */
public class PhoneRecord implements Serializable {
    /**
     * Column: phone_record.r_id
    @mbggenerated 2017-04-28 17:23:47
     */
    private Integer rId;

    /**
     *   客服编号
     * Column: phone_record.service_num
    @mbggenerated 2017-04-28 17:23:47
     */
    private String serviceNum;

    /**
     *   打入电话
     * Column: phone_record.in_phone
    @mbggenerated 2017-04-28 17:23:47
     */
    private String inPhone;

    /**
     *   客户编号
     * Column: phone_record.cust_id
    @mbggenerated 2017-04-28 17:23:47
     */
    private String custId;

    /**
     *   打入时间
     * Column: phone_record.in_time
    @mbggenerated 2017-04-28 17:23:47
     */
    private Date inTime;

    /**
     *   挂断时间
     * Column: phone_record.out_time
    @mbggenerated 2017-04-28 17:23:47
     */
    private Date outTime;

    /**
     *   创建时间
     * Column: phone_record.create_time
    @mbggenerated 2017-04-28 17:23:47
     */
    private Date createTime;

    /**
     *   修改时间
     * Column: phone_record.update_time
    @mbggenerated 2017-04-28 17:23:47
     */
    private Date updateTime;

    /**
     * Table: phone_record
    @mbggenerated 2017-04-28 17:23:47
     */
    private static final long serialVersionUID = 1L;

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public String getServiceNum() {
        return serviceNum;
    }

    public void setServiceNum(String serviceNum) {
        this.serviceNum = serviceNum == null ? null : serviceNum.trim();
    }

    public String getInPhone() {
        return inPhone;
    }

    public void setInPhone(String inPhone) {
        this.inPhone = inPhone == null ? null : inPhone.trim();
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId == null ? null : custId.trim();
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}