package com.manager.pojo;

import java.util.ArrayList;
import java.util.List;

public class UserBindingExample {
    /**
     * Table: user_binding
    @mbggenerated 2017-04-10 09:45:58
     */
    protected String orderByClause;

    /**
     * Table: user_binding
    @mbggenerated 2017-04-10 09:45:58
     */
    protected boolean distinct;

    /**
     * Table: user_binding
    @mbggenerated 2017-04-10 09:45:58
     */
    protected List<Criteria> oredCriteria;

    public UserBindingExample() {
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
     * Table: user_binding
    @mbggenerated 2017-04-10 09:45:58
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andHardpanNumIsNull() {
            addCriterion("hardpan_num is null");
            return (Criteria) this;
        }

        public Criteria andHardpanNumIsNotNull() {
            addCriterion("hardpan_num is not null");
            return (Criteria) this;
        }

        public Criteria andHardpanNumEqualTo(String value) {
            addCriterion("hardpan_num =", value, "hardpanNum");
            return (Criteria) this;
        }

        public Criteria andHardpanNumNotEqualTo(String value) {
            addCriterion("hardpan_num <>", value, "hardpanNum");
            return (Criteria) this;
        }

        public Criteria andHardpanNumGreaterThan(String value) {
            addCriterion("hardpan_num >", value, "hardpanNum");
            return (Criteria) this;
        }

        public Criteria andHardpanNumGreaterThanOrEqualTo(String value) {
            addCriterion("hardpan_num >=", value, "hardpanNum");
            return (Criteria) this;
        }

        public Criteria andHardpanNumLessThan(String value) {
            addCriterion("hardpan_num <", value, "hardpanNum");
            return (Criteria) this;
        }

        public Criteria andHardpanNumLessThanOrEqualTo(String value) {
            addCriterion("hardpan_num <=", value, "hardpanNum");
            return (Criteria) this;
        }

        public Criteria andHardpanNumLike(String value) {
            addCriterion("hardpan_num like", value, "hardpanNum");
            return (Criteria) this;
        }

        public Criteria andHardpanNumNotLike(String value) {
            addCriterion("hardpan_num not like", value, "hardpanNum");
            return (Criteria) this;
        }

        public Criteria andHardpanNumIn(List<String> values) {
            addCriterion("hardpan_num in", values, "hardpanNum");
            return (Criteria) this;
        }

        public Criteria andHardpanNumNotIn(List<String> values) {
            addCriterion("hardpan_num not in", values, "hardpanNum");
            return (Criteria) this;
        }

        public Criteria andHardpanNumBetween(String value1, String value2) {
            addCriterion("hardpan_num between", value1, value2, "hardpanNum");
            return (Criteria) this;
        }

        public Criteria andHardpanNumNotBetween(String value1, String value2) {
            addCriterion("hardpan_num not between", value1, value2, "hardpanNum");
            return (Criteria) this;
        }

        public Criteria andNetworkCardIsNull() {
            addCriterion("network_card is null");
            return (Criteria) this;
        }

        public Criteria andNetworkCardIsNotNull() {
            addCriterion("network_card is not null");
            return (Criteria) this;
        }

        public Criteria andNetworkCardEqualTo(String value) {
            addCriterion("network_card =", value, "networkCard");
            return (Criteria) this;
        }

        public Criteria andNetworkCardNotEqualTo(String value) {
            addCriterion("network_card <>", value, "networkCard");
            return (Criteria) this;
        }

        public Criteria andNetworkCardGreaterThan(String value) {
            addCriterion("network_card >", value, "networkCard");
            return (Criteria) this;
        }

        public Criteria andNetworkCardGreaterThanOrEqualTo(String value) {
            addCriterion("network_card >=", value, "networkCard");
            return (Criteria) this;
        }

        public Criteria andNetworkCardLessThan(String value) {
            addCriterion("network_card <", value, "networkCard");
            return (Criteria) this;
        }

        public Criteria andNetworkCardLessThanOrEqualTo(String value) {
            addCriterion("network_card <=", value, "networkCard");
            return (Criteria) this;
        }

        public Criteria andNetworkCardLike(String value) {
            addCriterion("network_card like", value, "networkCard");
            return (Criteria) this;
        }

        public Criteria andNetworkCardNotLike(String value) {
            addCriterion("network_card not like", value, "networkCard");
            return (Criteria) this;
        }

        public Criteria andNetworkCardIn(List<String> values) {
            addCriterion("network_card in", values, "networkCard");
            return (Criteria) this;
        }

        public Criteria andNetworkCardNotIn(List<String> values) {
            addCriterion("network_card not in", values, "networkCard");
            return (Criteria) this;
        }

        public Criteria andNetworkCardBetween(String value1, String value2) {
            addCriterion("network_card between", value1, value2, "networkCard");
            return (Criteria) this;
        }

        public Criteria andNetworkCardNotBetween(String value1, String value2) {
            addCriterion("network_card not between", value1, value2, "networkCard");
            return (Criteria) this;
        }

        public Criteria andTemporaryCardIsNull() {
            addCriterion("temporary_card is null");
            return (Criteria) this;
        }

        public Criteria andTemporaryCardIsNotNull() {
            addCriterion("temporary_card is not null");
            return (Criteria) this;
        }

        public Criteria andTemporaryCardEqualTo(String value) {
            addCriterion("temporary_card =", value, "temporaryCard");
            return (Criteria) this;
        }

        public Criteria andTemporaryCardNotEqualTo(String value) {
            addCriterion("temporary_card <>", value, "temporaryCard");
            return (Criteria) this;
        }

        public Criteria andTemporaryCardGreaterThan(String value) {
            addCriterion("temporary_card >", value, "temporaryCard");
            return (Criteria) this;
        }

        public Criteria andTemporaryCardGreaterThanOrEqualTo(String value) {
            addCriterion("temporary_card >=", value, "temporaryCard");
            return (Criteria) this;
        }

        public Criteria andTemporaryCardLessThan(String value) {
            addCriterion("temporary_card <", value, "temporaryCard");
            return (Criteria) this;
        }

        public Criteria andTemporaryCardLessThanOrEqualTo(String value) {
            addCriterion("temporary_card <=", value, "temporaryCard");
            return (Criteria) this;
        }

        public Criteria andTemporaryCardLike(String value) {
            addCriterion("temporary_card like", value, "temporaryCard");
            return (Criteria) this;
        }

        public Criteria andTemporaryCardNotLike(String value) {
            addCriterion("temporary_card not like", value, "temporaryCard");
            return (Criteria) this;
        }

        public Criteria andTemporaryCardIn(List<String> values) {
            addCriterion("temporary_card in", values, "temporaryCard");
            return (Criteria) this;
        }

        public Criteria andTemporaryCardNotIn(List<String> values) {
            addCriterion("temporary_card not in", values, "temporaryCard");
            return (Criteria) this;
        }

        public Criteria andTemporaryCardBetween(String value1, String value2) {
            addCriterion("temporary_card between", value1, value2, "temporaryCard");
            return (Criteria) this;
        }

        public Criteria andTemporaryCardNotBetween(String value1, String value2) {
            addCriterion("temporary_card not between", value1, value2, "temporaryCard");
            return (Criteria) this;
        }

        public Criteria andIsBindingIsNull() {
            addCriterion("is_binding is null");
            return (Criteria) this;
        }

        public Criteria andIsBindingIsNotNull() {
            addCriterion("is_binding is not null");
            return (Criteria) this;
        }

        public Criteria andIsBindingEqualTo(Byte value) {
            addCriterion("is_binding =", value, "isBinding");
            return (Criteria) this;
        }

        public Criteria andIsBindingNotEqualTo(Byte value) {
            addCriterion("is_binding <>", value, "isBinding");
            return (Criteria) this;
        }

        public Criteria andIsBindingGreaterThan(Byte value) {
            addCriterion("is_binding >", value, "isBinding");
            return (Criteria) this;
        }

        public Criteria andIsBindingGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_binding >=", value, "isBinding");
            return (Criteria) this;
        }

        public Criteria andIsBindingLessThan(Byte value) {
            addCriterion("is_binding <", value, "isBinding");
            return (Criteria) this;
        }

        public Criteria andIsBindingLessThanOrEqualTo(Byte value) {
            addCriterion("is_binding <=", value, "isBinding");
            return (Criteria) this;
        }

        public Criteria andIsBindingIn(List<Byte> values) {
            addCriterion("is_binding in", values, "isBinding");
            return (Criteria) this;
        }

        public Criteria andIsBindingNotIn(List<Byte> values) {
            addCriterion("is_binding not in", values, "isBinding");
            return (Criteria) this;
        }

        public Criteria andIsBindingBetween(Byte value1, Byte value2) {
            addCriterion("is_binding between", value1, value2, "isBinding");
            return (Criteria) this;
        }

        public Criteria andIsBindingNotBetween(Byte value1, Byte value2) {
            addCriterion("is_binding not between", value1, value2, "isBinding");
            return (Criteria) this;
        }
    }

    /**
     * This class corresponds to the database table user_binding
    @mbggenerated do_not_delete_during_merge 2017-04-10 09:45:58
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * [STRATO MyBatis Generator]
     * Table: user_binding
    @mbggenerated 2017-04-10 09:45:58
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