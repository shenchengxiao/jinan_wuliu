package com.manager.pojo;

import java.io.Serializable;

/**
 * [STRATO MyBatis Generator]
 * Table: user_binding
@mbggenerated do_not_delete_during_merge 2017-04-10 09:45:58
 */
public class UserBinding implements Serializable {
    /**
     * Column: user_binding.id
    @mbggenerated 2017-04-10 09:45:58
     */
    private Integer id;

    /**
     * Column: user_binding.user_id
    @mbggenerated 2017-04-10 09:45:58
     */
    private Integer userId;

    /**
     * Column: user_binding.user_name
    @mbggenerated 2017-04-10 09:45:58
     */
    private String userName;

    /**
     * Column: user_binding.hardpan_num
    @mbggenerated 2017-04-10 09:45:58
     */
    private String hardpanNum;

    /**
     * Column: user_binding.network_card
    @mbggenerated 2017-04-10 09:45:58
     */
    private String networkCard;

    /**
     * Column: user_binding.temporary_card
    @mbggenerated 2017-04-10 09:45:58
     */
    private String temporaryCard;

    /**
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