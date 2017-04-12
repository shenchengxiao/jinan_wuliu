package com.manager.request.message;

import com.manager.request.BaseQuery;

public class UserMessageRequest extends BaseQuery{
	
	private String userIds;
	private String content;
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
