package com.manager.response;

import com.manager.pojo.User;

public class UserInfoResponse extends User{

    /**
     *   �û�id
     * Column: user_binding.user_id
    @mbggenerated 2017-04-10 09:45:58
     */
    private Integer userId;

    /**
     *   �˻�(�û�����)
     * Column: user_binding.user_name
    @mbggenerated 2017-04-10 09:45:58
     */
    private String userName;

    /**
     *   Ӳ�̺�
     * Column: user_binding.hardpan_num
    @mbggenerated 2017-04-10 09:45:58
     */
    private String hardpanNum;

    /**
     *   ������
     * Column: user_binding.network_card
    @mbggenerated 2017-04-10 09:45:58
     */
    private String networkCard;

    /**
     *   ��ʱӲ��������
     * Column: user_binding.temporary_card
    @mbggenerated 2017-04-10 09:45:58
     */
    private String temporaryCard;

    /**
     *   �Ƿ�󶨵���
     * Column: user_binding.is_binding
    @mbggenerated 2017-04-10 09:45:58
     */
    private Byte isBinding;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getHardpanNum() {
		return hardpanNum;
	}

	public void setHardpanNum(String hardpanNum) {
		this.hardpanNum = hardpanNum;
	}

	public String getNetworkCard() {
		return networkCard;
	}

	public void setNetworkCard(String networkCard) {
		this.networkCard = networkCard;
	}

	public String getTemporaryCard() {
		return temporaryCard;
	}

	public void setTemporaryCard(String temporaryCard) {
		this.temporaryCard = temporaryCard;
	}

	public Byte getIsBinding() {
		return isBinding;
	}

	public void setIsBinding(Byte isBinding) {
		this.isBinding = isBinding;
	}
    
    
}
