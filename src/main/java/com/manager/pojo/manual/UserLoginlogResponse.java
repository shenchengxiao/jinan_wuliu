package com.manager.pojo.manual;

import java.io.Serializable;
import java.util.Date;

import com.manager.enums.PlatformTypeEnum;

public class UserLoginlogResponse implements Serializable{
    private Integer loginLogId;

    private Integer userId;

    private String username;

    private String ipAddress;

    private String port;

    private Date loginTime;

    private Integer statues;

    private Integer serverId;

    private Integer visitNum;

    private Double longitude;

    private Double latitude;

    private String locateAddress;
    private PlatformTypeEnum platformType;

    private String platformItem;
    private String ip;
    


	public PlatformTypeEnum getPlatformType() {
		return platformType;
	}

	public void setPlatformType(PlatformTypeEnum platformType) {
		this.platformType = platformType;
	}

	public String getPlatformItem() {
		return platformItem;
	}

	public void setPlatformItem(String platformItem) {
		this.platformItem = platformItem;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getLoginLogId() {
        return loginLogId;
    }

    public void setLoginLogId(Integer loginLogId) {
        this.loginLogId = loginLogId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress == null ? null : ipAddress.trim();
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port == null ? null : port.trim();
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Integer getStatues() {
        return statues;
    }

    public void setStatues(Integer statues) {
        this.statues = statues;
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }

    public Integer getVisitNum() {
        return visitNum;
    }

    public void setVisitNum(Integer visitNum) {
        this.visitNum = visitNum;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getLocateAddress() {
        return locateAddress;
    }

    public void setLocateAddress(String locateAddress) {
        this.locateAddress = locateAddress == null ? null : locateAddress.trim();
    }

	@Override
	public String toString() {
		return "UserLoginlogResponse [loginLogId=" + loginLogId + ", userId=" + userId + ", username=" + username
				+ ", ipAddress=" + ipAddress + ", port=" + port + ", loginTime=" + loginTime + ", statues=" + statues
				+ ", serverId=" + serverId + ", visitNum=" + visitNum + ", longitude=" + longitude + ", latitude="
				+ latitude + ", locateAddress=" + locateAddress + ", platformType=" + platformType + ", platformItem="
				+ platformItem + ", ip=" + ip + "]";
	}

    
	
    
}