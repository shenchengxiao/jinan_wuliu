package com.manager.pojo;

import java.io.Serializable;

/**
 * [STRATO MyBatis Generator]
 * Table: user_binding
@mbggenerated do_not_delete_during_merge 2017-04-10 09:45:58
 */
public class UserBinding implements Serializable {
    /**
     *   用户绑定设备信息
     * Column: user_binding.id
    @mbggenerated 2017-04-10 09:45:58
     */
    private Integer id;

    /**
     *   用户id
     * Column: user_binding.user_id
    @mbggenerated 2017-04-10 09:45:58
     */
    private Integer userId;

    /**
     *   账户(用户名称)
     * Column: user_binding.user_name
    @mbggenerated 2017-04-10 09:45:58
     */
    private String userName;

    /**
     *   硬盘号
     * Column: user_binding.hardpan_num
    @mbggenerated 2017-04-10 09:45:58
     */
    private String hardpanNum;

    /**
     *   网卡号
     * Column: user_binding.network_card
    @mbggenerated 2017-04-10 09:45:58
     */
    private String networkCard;

    /**
     *   临时硬盘网卡号
     * Column: user_binding.temporary_card
    @mbggenerated 2017-04-10 09:45:58
     */
    private String temporaryCard;

    /**
     *   是否绑定电脑
     * Column: user_binding.is_binding
    @mbggenerated 2017-04-10 09:45:58
     */
    private Byte isBinding;

    /**
     * Table: user_binding
    @mbggenerated 2017-04-10 09:45:58
     */
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
        this.userName = userName == null ? null : userName.trim();
    }

    public String getHardpanNum() {
        return hardpanNum;
    }

    public void setHardpanNum(String hardpanNum) {
        this.hardpanNum = hardpanNum == null ? null : hardpanNum.trim();
    }

    public String getNetworkCard() {
        return networkCard;
    }

    public void setNetworkCard(String networkCard) {
        this.networkCard = networkCard == null ? null : networkCard.trim();
    }

    public String getTemporaryCard() {
        return temporaryCard;
    }

    public void setTemporaryCard(String temporaryCard) {
        this.temporaryCard = temporaryCard == null ? null : temporaryCard.trim();
    }

    public Byte getIsBinding() {
        return isBinding;
    }

    public void setIsBinding(Byte isBinding) {
        this.isBinding = isBinding;
    }
}