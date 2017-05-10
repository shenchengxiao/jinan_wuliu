package com.manager.request.message;

import com.manager.request.BaseQuery;

public class UserMessageRequest extends BaseQuery{
	
	private String userIds;
	private String content;
	private Integer mType;
	
	public Integer getmType() {
		return mType;
	}
	public void setmType(Integer mType) {
		this.mType = mType;
	}
	public String getUserIds() {
		return userIds;
	}
	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
