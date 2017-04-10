package com.manager.response;

import java.io.Serializable;
import java.util.Date;

import com.manager.utils.DateTimeUtil;

/**
 * @author Sid
 *
 */
public class ItemResponse implements Serializable{
	
	private Long itemId;

    private Integer userId;

    private String userPhones;

    private Integer typeId;
    

	private Date createTime;
	
	private Byte status;

    private String content;
    
    private String userNum;

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserPhones() {
		return userPhones;
	}

	public void setUserPhones(String userPhones) {
		this.userPhones = userPhones;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	@Override
	public String toString() {
		return "ItemResponse [itemId=" + itemId + ", userId=" + userId + ", userPhones=" + userPhones + ", typeId="
				+ typeId + ", createTime=" + createTime + ", status=" + status + ", content=" + content + ", userNum="
				+ userNum + "]";
	}
    
    
}
