package com.manager.pojo;

import com.manager.request.BaseEntity;
import java.util.Date;

public class Query extends BaseEntity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column query.id
     *
     * @mbggenerated Wed Apr 19 11:11:58 CST 2017
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column query.content
     *
     * @mbggenerated Wed Apr 19 11:11:58 CST 2017
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column query.contenttype
     *
     * @mbggenerated Wed Apr 19 11:11:58 CST 2017
     */
    private Integer contenttype;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column query.type
     *
     * @mbggenerated Wed Apr 19 11:11:58 CST 2017
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column query.createtime
     *
     * @mbggenerated Wed Apr 19 11:11:58 CST 2017
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column query.userid
     *
     * @mbggenerated Wed Apr 19 11:11:58 CST 2017
     */
    private Integer userid;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column query.id
     *
     * @return the value of query.id
     *
     * @mbggenerated Wed Apr 19 11:11:58 CST 2017
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column query.id
     *
     * @param id the value for query.id
     *
     * @mbggenerated Wed Apr 19 11:11:58 CST 2017
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column query.content
     *
     * @return the value of query.content
     *
     * @mbggenerated Wed Apr 19 11:11:58 CST 2017
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column query.content
     *
     * @param content the value for query.content
     *
     * @mbggenerated Wed Apr 19 11:11:58 CST 2017
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column query.contenttype
     *
     * @return the value of query.contenttype
     *
     * @mbggenerated Wed Apr 19 11:11:58 CST 2017
     */
    public Integer getContenttype() {
        return contenttype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column query.contenttype
     *
     * @param contenttype the value for query.contenttype
     *
     * @mbggenerated Wed Apr 19 11:11:58 CST 2017
     */
    public void setContenttype(Integer contenttype) {
        this.contenttype = contenttype;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column query.type
     *
     * @return the value of query.type
     *
     * @mbggenerated Wed Apr 19 11:11:58 CST 2017
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column query.type
     *
     * @param type the value for query.type
     *
     * @mbggenerated Wed Apr 19 11:11:58 CST 2017
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column query.createtime
     *
     * @return the value of query.createtime
     *
     * @mbggenerated Wed Apr 19 11:11:58 CST 2017
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column query.createtime
     *
     * @param createtime the value for query.createtime
     *
     * @mbggenerated Wed Apr 19 11:11:58 CST 2017
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column query.userid
     *
     * @return the value of query.userid
     *
     * @mbggenerated Wed Apr 19 11:11:58 CST 2017
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column query.userid
     *
     * @param userid the value for query.userid
     *
     * @mbggenerated Wed Apr 19 11:11:58 CST 2017
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table query
     *
     * @mbggenerated Wed Apr 19 11:11:58 CST 2017
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Query other = (Query) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getContenttype() == null ? other.getContenttype() == null : this.getContenttype().equals(other.getContenttype()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table query
     *
     * @mbggenerated Wed Apr 19 11:11:58 CST 2017
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getContenttype() == null) ? 0 : getContenttype().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        return result;
    }
}