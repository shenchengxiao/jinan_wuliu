package com.manager.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by shencx on 2017/3/30.
 */
public class BlackWordResponse implements Serializable{

	private Integer bWId;
	private String blackWord;
	private Date createTime;
	private Integer enabled;
	public Integer getbWId() {
		return bWId;
	}
	public void setbWId(Integer bWId) {
		this.bWId = bWId;
	}
	public String getBlackWord() {
		return blackWord;
	}
	public void setBlackWord(String blackWord) {
		this.blackWord = blackWord;
	}
	public Integer getEnabled() {
		return enabled;
	}
	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "BlackWordResponse [bWId=" + bWId + ", blackWord=" + blackWord + ", createTime=" + createTime
				+ ", enabled=" + enabled + "]";
	}
	
	
	
	
}
