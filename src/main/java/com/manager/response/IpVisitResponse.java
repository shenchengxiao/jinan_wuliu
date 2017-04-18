package com.manager.response;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * [STRATO MyBatis Generator]
 * Table: ip_visit
@mbggenerated do_not_delete_during_merge 2017-04-14 16:21:16
 */
public class IpVisitResponse implements Serializable {
    /**
     * Column: ip_visit.id
    @mbggenerated 2017-04-14 16:21:16
     */
    private Integer id;

    /**
     *   ������ip
     * Column: ip_visit.ip
    @mbggenerated 2017-04-14 16:21:16
     */
    private String ip;

    /**
     *   �˿ں�
     * Column: ip_visit.port
    @mbggenerated 2017-04-14 16:21:16
     */
    private String port;

    /**
     *   ����
     * Column: ip_visit.domain
    @mbggenerated 2017-04-14 16:21:16
     */
    private String domain;

    /**
     *   ����ʱ��
     * Column: ip_visit.create_time
    @mbggenerated 2017-04-14 16:21:16
     */
    private Date createTime;
    
    private String functionDesc;

    private Integer status;
    

    public String getFunctionDesc() {
		return functionDesc;
	}

	public void setFunctionDesc(String functionDesc) {
		this.functionDesc = functionDesc;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
     * Table: ip_visit
    @mbggenerated 2017-04-14 16:21:16
     */
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port == null ? null : port.trim();
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain == null ? null : domain.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

	@Override
	public String toString() {
		return "IpVisitResponse [id=" + id + ", ip=" + ip + ", port=" + port + ", domain=" + domain + ", createTime="
				+ createTime + ", functionDesc=" + functionDesc + ", status=" + status + "]";
	}

	
    
    
}