package com.manager.request.blackword;

import com.manager.request.BaseQuery;

import java.math.BigDecimal;

/**
 * Created by shencx on 2017/3/30.
 */
public class BlackWordRequest extends BaseQuery{

	private Integer bWId;
	private String blackWord;
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
	
	
}
