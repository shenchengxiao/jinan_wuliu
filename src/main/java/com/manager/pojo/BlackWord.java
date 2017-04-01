package com.manager.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * [STRATO MyBatis Generator]
 * Table: black_word
@mbggenerated do_not_delete_during_merge 2017-03-31 14:46:06
 */
public class BlackWord implements Serializable {
    /**
     * Column: black_word.b_w_id
    @mbggenerated 2017-03-31 14:46:06
     */
    private Integer bWId;

    /**
     *   黑词
     * Column: black_word.black_word
    @mbggenerated 2017-03-31 14:46:06
     */
    private String blackWord;

    /**
     *   创建时间
     * Column: black_word.create_time
    @mbggenerated 2017-03-31 14:46:06
     */
    private Date createTime;

    /**
     *   修改时间
     * Column: black_word.update_time
    @mbggenerated 2017-03-31 14:46:06
     */
    private Date updateTime;

    /**
     *   是否有效（0：无效，1：有效）
     * Column: black_word.enabled
    @mbggenerated 2017-03-31 14:46:06
     */
    private Integer enabled;

    /**
     * Table: black_word
    @mbggenerated 2017-03-31 14:46:06
     */
    private static final long serialVersionUID = 1L;

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
        this.blackWord = blackWord == null ? null : blackWord.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }
}