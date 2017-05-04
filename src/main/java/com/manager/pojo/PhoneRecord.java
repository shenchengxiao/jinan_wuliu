package com.manager.pojo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * [STRATO MyBatis Generator]
 * Table: phone_record
@mbggenerated do_not_delete_during_merge 2017-05-02 14:57:01
 */
public class PhoneRecord implements Serializable {
    /**
     * Column: phone_record.r_id
    @mbggenerated 2017-05-02 14:57:01
     */
    private Integer rId;

    /**
     *   �ͷ����
     * Column: phone_record.service_num
    @mbggenerated 2017-05-02 14:57:01
     */
    private String serviceNum;

    /**
     *   ����绰
     * Column: phone_record.in_phone
    @mbggenerated 2017-05-02 14:57:01
     */
    private String inPhone;

    /**
     *   �ͻ����
     * Column: phone_record.cust_id
    @mbggenerated 2017-05-02 14:57:01
     */
    private String custId;

    /**
     *   ����ʱ��
     * Column: phone_record.in_time
    @mbggenerated 2017-05-02 14:57:01
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date inTime;

    /**
     *   �Ҷ�ʱ��
     * Column: phone_record.out_time
    @mbggenerated 2017-05-02 14:57:01
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date outTime;

    /**
     *   ����ʱ��
     * Column: phone_record.create_time
    @mbggenerated 2017-05-02 14:57:01
     */
    private Date createTime;

    /**
     *   �޸�ʱ��
     * Column: phone_record.update_time
    @mbggenerated 2017-05-02 14:57:01
     */
    private Date updateTime;

    /**
     *   �Ƿ������1���ǣ�0����
     * Column: phone_record.is_answer
    @mbggenerated 2017-05-02 14:57:01
     */
    private Integer isAnswer;

    /**
     *   �Ƿ�Ҷϣ�1���ǣ�0����
     * Column: phone_record.is_hang
    @mbggenerated 2017-05-02 14:57:01
     */
    private Integer isHang;
    
    /**时长*/
    private long duration; 
    
    /** 按条件查询时长总计 */
    private long sumduration;
    
    /** 按条件查询话务量*/
    private long count;
    
    /** 时长总计 */
    private long totaltime;
    
    /** 总话务量*/
    private long total;

    /**
     * Table: phone_record
    @mbggenerated 2017-05-02 14:57:01
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

    public Integer getIsAnswer() {
        return isAnswer;
    }

    public void setIsAnswer(Integer isAnswer) {
        this.isAnswer = isAnswer;
    }

    public Integer getIsHang() {
        return isHang;
    }

    public void setIsHang(Integer isHang) {
        this.isHang = isHang;
    }

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public long getSumduration() {
		return sumduration;
	}

	public void setSumduration(long sumduration) {
		this.sumduration = sumduration;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public long getTotaltime() {
		return totaltime;
	}

	public void setTotaltime(long totaltime) {
		this.totaltime = totaltime;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
    
	
    
}