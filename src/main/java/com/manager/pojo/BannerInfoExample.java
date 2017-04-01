package com.manager.pojo;

import com.manager.enums.YesNoEnum;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BannerInfoExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table banner_info
     *
     * @mbggenerated Sat Apr 01 12:32:06 CST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table banner_info
     *
     * @mbggenerated Sat Apr 01 12:32:06 CST 2017
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table banner_info
     *
     * @mbggenerated Sat Apr 01 12:32:06 CST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_info
     *
     * @mbggenerated Sat Apr 01 12:32:06 CST 2017
     */
    public BannerInfoExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_info
     *
     * @mbggenerated Sat Apr 01 12:32:06 CST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_info
     *
     * @mbggenerated Sat Apr 01 12:32:06 CST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_info
     *
     * @mbggenerated Sat Apr 01 12:32:06 CST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_info
     *
     * @mbggenerated Sat Apr 01 12:32:06 CST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_info
     *
     * @mbggenerated Sat Apr 01 12:32:06 CST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_info
     *
     * @mbggenerated Sat Apr 01 12:32:06 CST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_info
     *
     * @mbggenerated Sat Apr 01 12:32:06 CST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_info
     *
     * @mbggenerated Sat Apr 01 12:32:06 CST 2017
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_info
     *
     * @mbggenerated Sat Apr 01 12:32:06 CST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table banner_info
     *
     * @mbggenerated Sat Apr 01 12:32:06 CST 2017
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table banner_info
     *
     * @mbggenerated Sat Apr 01 12:32:06 CST 2017
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andBannerNameIsNull() {
            addCriterion("banner_name is null");
            return (Criteria) this;
        }

        public Criteria andBannerNameIsNotNull() {
            addCriterion("banner_name is not null");
            return (Criteria) this;
        }

        public Criteria andBannerNameEqualTo(String value) {
            addCriterion("banner_name =", value, "bannerName");
            return (Criteria) this;
        }

        public Criteria andBannerNameNotEqualTo(String value) {
            addCriterion("banner_name <>", value, "bannerName");
            return (Criteria) this;
        }

        public Criteria andBannerNameGreaterThan(String value) {
            addCriterion("banner_name >", value, "bannerName");
            return (Criteria) this;
        }

        public Criteria andBannerNameGreaterThanOrEqualTo(String value) {
            addCriterion("banner_name >=", value, "bannerName");
            return (Criteria) this;
        }

        public Criteria andBannerNameLessThan(String value) {
            addCriterion("banner_name <", value, "bannerName");
            return (Criteria) this;
        }

        public Criteria andBannerNameLessThanOrEqualTo(String value) {
            addCriterion("banner_name <=", value, "bannerName");
            return (Criteria) this;
        }

        public Criteria andBannerNameLike(String value) {
            addCriterion("banner_name like", value, "bannerName");
            return (Criteria) this;
        }

        public Criteria andBannerNameNotLike(String value) {
            addCriterion("banner_name not like", value, "bannerName");
            return (Criteria) this;
        }

        public Criteria andBannerNameIn(List<String> values) {
            addCriterion("banner_name in", values, "bannerName");
            return (Criteria) this;
        }

        public Criteria andBannerNameNotIn(List<String> values) {
            addCriterion("banner_name not in", values, "bannerName");
            return (Criteria) this;
        }

        public Criteria andBannerNameBetween(String value1, String value2) {
            addCriterion("banner_name between", value1, value2, "bannerName");
            return (Criteria) this;
        }

        public Criteria andBannerNameNotBetween(String value1, String value2) {
            addCriterion("banner_name not between", value1, value2, "bannerName");
            return (Criteria) this;
        }

        public Criteria andImageUrlIsNull() {
            addCriterion("image_url is null");
            return (Criteria) this;
        }

        public Criteria andImageUrlIsNotNull() {
            addCriterion("image_url is not null");
            return (Criteria) this;
        }

        public Criteria andImageUrlEqualTo(String value) {
            addCriterion("image_url =", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotEqualTo(String value) {
            addCriterion("image_url <>", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlGreaterThan(String value) {
            addCriterion("image_url >", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlGreaterThanOrEqualTo(String value) {
            addCriterion("image_url >=", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLessThan(String value) {
            addCriterion("image_url <", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLessThanOrEqualTo(String value) {
            addCriterion("image_url <=", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLike(String value) {
            addCriterion("image_url like", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotLike(String value) {
            addCriterion("image_url not like", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlIn(List<String> values) {
            addCriterion("image_url in", values, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotIn(List<String> values) {
            addCriterion("image_url not in", values, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlBetween(String value1, String value2) {
            addCriterion("image_url between", value1, value2, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotBetween(String value1, String value2) {
            addCriterion("image_url not between", value1, value2, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andBeUsedIsNull() {
            addCriterion("be_used is null");
            return (Criteria) this;
        }

        public Criteria andBeUsedIsNotNull() {
            addCriterion("be_used is not null");
            return (Criteria) this;
        }

        public Criteria andBeUsedEqualTo(YesNoEnum value) {
            addCriterion("be_used =", value, "beUsed");
            return (Criteria) this;
        }

        public Criteria andBeUsedNotEqualTo(YesNoEnum value) {
            addCriterion("be_used <>", value, "beUsed");
            return (Criteria) this;
        }

        public Criteria andBeUsedGreaterThan(YesNoEnum value) {
            addCriterion("be_used >", value, "beUsed");
            return (Criteria) this;
        }

        public Criteria andBeUsedGreaterThanOrEqualTo(YesNoEnum value) {
            addCriterion("be_used >=", value, "beUsed");
            return (Criteria) this;
        }

        public Criteria andBeUsedLessThan(YesNoEnum value) {
            addCriterion("be_used <", value, "beUsed");
            return (Criteria) this;
        }

        public Criteria andBeUsedLessThanOrEqualTo(YesNoEnum value) {
            addCriterion("be_used <=", value, "beUsed");
            return (Criteria) this;
        }

        public Criteria andBeUsedIn(List<YesNoEnum> values) {
            addCriterion("be_used in", values, "beUsed");
            return (Criteria) this;
        }

        public Criteria andBeUsedNotIn(List<YesNoEnum> values) {
            addCriterion("be_used not in", values, "beUsed");
            return (Criteria) this;
        }

        public Criteria andBeUsedBetween(YesNoEnum value1, YesNoEnum value2) {
            addCriterion("be_used between", value1, value2, "beUsed");
            return (Criteria) this;
        }

        public Criteria andBeUsedNotBetween(YesNoEnum value1, YesNoEnum value2) {
            addCriterion("be_used not between", value1, value2, "beUsed");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table banner_info
     *
     * @mbggenerated do_not_delete_during_merge Sat Apr 01 12:32:06 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table banner_info
     *
     * @mbggenerated Sat Apr 01 12:32:06 CST 2017
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}