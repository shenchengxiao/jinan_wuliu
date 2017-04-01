package com.manager.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BlackWordExample {
    /**
     * Table: black_word
    @mbggenerated 2017-03-31 14:46:06
     */
    protected String orderByClause;

    /**
     * Table: black_word
    @mbggenerated 2017-03-31 14:46:06
     */
    protected boolean distinct;

    /**
     * Table: black_word
    @mbggenerated 2017-03-31 14:46:06
     */
    protected List<Criteria> oredCriteria;

    public BlackWordExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * [STRATO MyBatis Generator]
     * Table: black_word
    @mbggenerated 2017-03-31 14:46:06
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

        public Criteria andBWIdIsNull() {
            addCriterion("b_w_id is null");
            return (Criteria) this;
        }

        public Criteria andBWIdIsNotNull() {
            addCriterion("b_w_id is not null");
            return (Criteria) this;
        }

        public Criteria andBWIdEqualTo(Integer value) {
            addCriterion("b_w_id =", value, "bWId");
            return (Criteria) this;
        }

        public Criteria andBWIdNotEqualTo(Integer value) {
            addCriterion("b_w_id <>", value, "bWId");
            return (Criteria) this;
        }

        public Criteria andBWIdGreaterThan(Integer value) {
            addCriterion("b_w_id >", value, "bWId");
            return (Criteria) this;
        }

        public Criteria andBWIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("b_w_id >=", value, "bWId");
            return (Criteria) this;
        }

        public Criteria andBWIdLessThan(Integer value) {
            addCriterion("b_w_id <", value, "bWId");
            return (Criteria) this;
        }

        public Criteria andBWIdLessThanOrEqualTo(Integer value) {
            addCriterion("b_w_id <=", value, "bWId");
            return (Criteria) this;
        }

        public Criteria andBWIdIn(List<Integer> values) {
            addCriterion("b_w_id in", values, "bWId");
            return (Criteria) this;
        }

        public Criteria andBWIdNotIn(List<Integer> values) {
            addCriterion("b_w_id not in", values, "bWId");
            return (Criteria) this;
        }

        public Criteria andBWIdBetween(Integer value1, Integer value2) {
            addCriterion("b_w_id between", value1, value2, "bWId");
            return (Criteria) this;
        }

        public Criteria andBWIdNotBetween(Integer value1, Integer value2) {
            addCriterion("b_w_id not between", value1, value2, "bWId");
            return (Criteria) this;
        }

        public Criteria andBlackWordIsNull() {
            addCriterion("black_word is null");
            return (Criteria) this;
        }

        public Criteria andBlackWordIsNotNull() {
            addCriterion("black_word is not null");
            return (Criteria) this;
        }

        public Criteria andBlackWordEqualTo(String value) {
            addCriterion("black_word =", value, "blackWord");
            return (Criteria) this;
        }

        public Criteria andBlackWordNotEqualTo(String value) {
            addCriterion("black_word <>", value, "blackWord");
            return (Criteria) this;
        }

        public Criteria andBlackWordGreaterThan(String value) {
            addCriterion("black_word >", value, "blackWord");
            return (Criteria) this;
        }

        public Criteria andBlackWordGreaterThanOrEqualTo(String value) {
            addCriterion("black_word >=", value, "blackWord");
            return (Criteria) this;
        }

        public Criteria andBlackWordLessThan(String value) {
            addCriterion("black_word <", value, "blackWord");
            return (Criteria) this;
        }

        public Criteria andBlackWordLessThanOrEqualTo(String value) {
            addCriterion("black_word <=", value, "blackWord");
            return (Criteria) this;
        }

        public Criteria andBlackWordLike(String value) {
            addCriterion("black_word like", value, "blackWord");
            return (Criteria) this;
        }

        public Criteria andBlackWordNotLike(String value) {
            addCriterion("black_word not like", value, "blackWord");
            return (Criteria) this;
        }

        public Criteria andBlackWordIn(List<String> values) {
            addCriterion("black_word in", values, "blackWord");
            return (Criteria) this;
        }

        public Criteria andBlackWordNotIn(List<String> values) {
            addCriterion("black_word not in", values, "blackWord");
            return (Criteria) this;
        }

        public Criteria andBlackWordBetween(String value1, String value2) {
            addCriterion("black_word between", value1, value2, "blackWord");
            return (Criteria) this;
        }

        public Criteria andBlackWordNotBetween(String value1, String value2) {
            addCriterion("black_word not between", value1, value2, "blackWord");
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

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andEnabledIsNull() {
            addCriterion("enabled is null");
            return (Criteria) this;
        }

        public Criteria andEnabledIsNotNull() {
            addCriterion("enabled is not null");
            return (Criteria) this;
        }

        public Criteria andEnabledEqualTo(Integer value) {
            addCriterion("enabled =", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotEqualTo(Integer value) {
            addCriterion("enabled <>", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledGreaterThan(Integer value) {
            addCriterion("enabled >", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledGreaterThanOrEqualTo(Integer value) {
            addCriterion("enabled >=", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledLessThan(Integer value) {
            addCriterion("enabled <", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledLessThanOrEqualTo(Integer value) {
            addCriterion("enabled <=", value, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledIn(List<Integer> values) {
            addCriterion("enabled in", values, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotIn(List<Integer> values) {
            addCriterion("enabled not in", values, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledBetween(Integer value1, Integer value2) {
            addCriterion("enabled between", value1, value2, "enabled");
            return (Criteria) this;
        }

        public Criteria andEnabledNotBetween(Integer value1, Integer value2) {
            addCriterion("enabled not between", value1, value2, "enabled");
            return (Criteria) this;
        }
    }

    /**
     * This class corresponds to the database table black_word
    @mbggenerated do_not_delete_during_merge 2017-03-31 14:46:06
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * [STRATO MyBatis Generator]
     * Table: black_word
    @mbggenerated 2017-03-31 14:46:06
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