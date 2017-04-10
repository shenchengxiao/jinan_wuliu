package com.manager.pojo;

import com.manager.enums.YesNoEnum;
import com.manager.request.BaseEntity;
import java.util.Date;

public class Advert extends BaseEntity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column advert.id
     *
     * @mbggenerated Sat Apr 01 09:33:56 CST 2017
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column advert.start_time
     *
     * @mbggenerated Sat Apr 01 09:33:56 CST 2017
     */
    private Date startTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column advert.stop_time
     *
     * @mbggenerated Sat Apr 01 09:33:56 CST 2017
     */
    private Date stopTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column advert.price
     *
     * @mbggenerated Sat Apr 01 09:33:56 CST 2017
     */
    private Float price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column advert.linkman
     *
     * @mbggenerated Sat Apr 01 09:33:56 CST 2017
     */
    private String linkman;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column advert.linkphone
     *
     * @mbggenerated Sat Apr 01 09:33:56 CST 2017
     */
    private String linkphone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column advert.status
     *
     * @mbggenerated Sat Apr 01 09:33:56 CST 2017
     */
    private YesNoEnum status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column advert.content
     *
     * @mbggenerated Sat Apr 01 09:33:56 CST 2017
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column advert.link_url
     *
     * @mbggenerated Sat Apr 01 09:33:56 CST 2017
     */
    private String linkUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column advert.create_time
     *
     * @mbggenerated Sat Apr 01 09:33:56 CST 2017
     */
    private Date createTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column advert.id
     *
     * @return the value of advert.id
     *
     * @mbggenerated Sat Apr 01 09:33:56 CST 2017
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column advert.id
     *
     * @param id the value for advert.id
     *
     * @mbggenerated Sat Apr 01 09:33:56 CST 2017
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column advert.start_time
     *
     * @return the value of advert.start_time
     *
     * @mbggenerated Sat Apr 01 09:33:56 CST 2017
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column advert.start_time
     *
     * @param startTime the value for advert.start_time
     *
     * @mbggenerated Sat Apr 01 09:33:56 CST 2017
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column advert.stop_time
     *
     * @return the value of advert.stop_time
     *
     * @mbggenerated Sat Apr 01 09:33:56 CST 2017
     */
    public Date getStopTime() {
        return stopTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column advert.stop_time
     *
     * @param stopTime the value for advert.stop_time
     *
     * @mbggenerated Sat Apr 01 09:33:56 CST 2017
     */
    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column advert.price
     *
     * @return the value of advert.price
     *
     * @mbggenerated Sat Apr 01 09:33:56 CST 2017
     */
    public Float getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column advert.price
     *
     * @param price the value for advert.price
     *
     * @mbggenerated Sat Apr 01 09:33:56 CST 2017
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column advert.linkman
     *
     * @return the value of advert.linkman
     *
     * @mbggenerated Sat Apr 01 09:33:56 CST 2017
     */
    public String getLinkman() {
        return linkman;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column advert.linkman
     *
     * @param linkman the value for advert.linkman
     *
     * @mbggenerated Sat Apr 01 09:33:56 CST 2017
     */
    public void setLinkman(String linkman) {
        this.linkman = linkman == null ? null : linkman.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column advert.linkphone
     *
     * @return the value of advert.linkphone
     *
     * @mbggenerated Sat Apr 01 09:33:56 CST 2017
     */
    public String getLinkphone() {
        return linkphone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column advert.linkphone
     *
     * @param linkphone the value for advert.linkphone
     *
     * @mbggenerated Sat Apr 01 09:33:56 CST 2017
     */
    public void setLinkphone(String linkphone) {
        this.linkphone = linkphone == null ? null : linkphone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column advert.status
     *
     * @return the value of advert.status
     *
     * @mbggenerated Sat Apr 01 09:33:56 CST 2017
     */
    public YesNoEnum getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column advert.status
     *
     * @param status the value for advert.status
     *
     * @mbggenerated Sat Apr 01 09:33:56 CST 2017
     */
    public void setStatus(YesNoEnum status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column advert.content
     *
     * @return the value of advert.content
     *
     * @mbggenerated Sat Apr 01 09:33:56 CST 2017
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column advert.content
     *
     * @param content the value for advert.content
     *
     * @mbggenerated Sat Apr 01 09:33:56 CST 2017
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column advert.link_url
     *
     * @return the value of advert.link_url
     *
     * @mbggenerated Sat Apr 01 09:33:56 CST 2017
     */
    public String getLinkUrl() {
        return linkUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column advert.link_url
     *
     * @param linkUrl the value for advert.link_url
     *
     * @mbggenerated Sat Apr 01 09:33:56 CST 2017
     */
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl == null ? null : linkUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column advert.create_time
     *
     * @return the value of advert.create_time
     *
     * @mbggenerated Sat Apr 01 09:33:56 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column advert.create_time
     *
     * @param createTime the value for advert.create_time
     *
     * @mbggenerated Sat Apr 01 09:33:56 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table advert
     *
     * @mbggenerated Sat Apr 01 09:33:56 CST 2017
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
        Advert other = (Advert) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
            && (this.getStopTime() == null ? other.getStopTime() == null : this.getStopTime().equals(other.getStopTime()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getLinkman() == null ? other.getLinkman() == null : this.getLinkman().equals(other.getLinkman()))
            && (this.getLinkphone() == null ? other.getLinkphone() == null : this.getLinkphone().equals(other.getLinkphone()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getLinkUrl() == null ? other.getLinkUrl() == null : this.getLinkUrl().equals(other.getLinkUrl()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table advert
     *
     * @mbggenerated Sat Apr 01 09:33:56 CST 2017
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
        result = prime * result + ((getStopTime() == null) ? 0 : getStopTime().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getLinkman() == null) ? 0 : getLinkman().hashCode());
        result = prime * result + ((getLinkphone() == null) ? 0 : getLinkphone().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getLinkUrl() == null) ? 0 : getLinkUrl().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }
}