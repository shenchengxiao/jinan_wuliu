package com.manager.request.custom;


import com.manager.enums.PlatformTypeEnum;
import com.manager.enums.YesNoEnum;
import com.manager.request.BaseQuery;

public class UserCustomRequest extends BaseQuery{
	
	private Integer id;
	private String username;
	private Integer isManager;
	private String platformType;
	private Integer isBinding;
	private Integer isPhoneLimit;
	private Integer isReceiveCar;
	private Integer isReceiveGoods;
	private Integer isSendCar;
	private Integer isSendGoods;
	private Integer isLookPhone;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIsManager() {
		return isManager;
	}

	public void setIsManager(Integer isManager) {
		this.isManager = isManager;
	}

	
	

	public String getPlatformType() {
		return platformType;
	}

	public void setPlatformType(String platformType) {
		this.platformType = platformType;
	}

	public Integer getIsBinding() {
		return isBinding;
	}

	public void setIsBinding(Integer isBinding) {
		this.isBinding = isBinding;
	}

	public Integer getIsPhoneLimit() {
		return isPhoneLimit;
	}

	public void setIsPhoneLimit(Integer isPhoneLimit) {
		this.isPhoneLimit = isPhoneLimit;
	}

	public Integer getIsReceiveCar() {
		return isReceiveCar;
	}

	public void setIsReceiveCar(Integer isReceiveCar) {
		this.isReceiveCar = isReceiveCar;
	}

	public Integer getIsReceiveGoods() {
		return isReceiveGoods;
	}

	public void setIsReceiveGoods(Integer isReceiveGoods) {
		this.isReceiveGoods = isReceiveGoods;
	}

	public Integer getIsSendCar() {
		return isSendCar;
	}

	public void setIsSendCar(Integer isSendCar) {
		this.isSendCar = isSendCar;
	}

	public Integer getIsSendGoods() {
		return isSendGoods;
	}

	public void setIsSendGoods(Integer isSendGoods) {
		this.isSendGoods = isSendGoods;
	}

	public Integer getIsLookPhone() {
		return isLookPhone;
	}

	public void setIsLookPhone(Integer isLookPhone) {
		this.isLookPhone = isLookPhone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
	

}
