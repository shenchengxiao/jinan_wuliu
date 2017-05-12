package com.manager.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeletehistoryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeletehistoryExample() {
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

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andSumcountIsNull() {
            addCriterion("sumcount is null");
            return (Criteria) this;
        }

        public Criteria andSumcountIsNotNull() {
            addCriterion("sumcount is not null");
            return (Criteria) this;
        }

        public Criteria andSumcountEqualTo(Integer value) {
            addCriterion("sumcount =", value, "sumcount");
            return (Criteria) this;
        }

        public Criteria andSumcountNotEqualTo(Integer value) {
            addCriterion("sumcount <>", value, "sumcount");
            return (Criteria) this;
        }

        public Criteria andSumcountGreaterThan(Integer value) {
            addCriterion("sumcount >", value, "sumcount");
            return (Criteria) this;
        }

        public Criteria andSumcountGreaterThanOrEqualTo(Integer value) {
            addCriterion("sumcount >=", value, "sumcount");
            return (Criteria) this;
        }

        public Criteria andSumcountLessThan(Integer value) {
            addCriterion("sumcount <", value, "sumcount");
            return (Criteria) this;
        }

        public Criteria andSumcountLessThanOrEqualTo(Integer value) {
            addCriterion("sumcount <=", value, "sumcount");
            return (Criteria) this;
        }

        public Criteria andSumcountIn(List<Integer> values) {
            addCriterion("sumcount in", values, "sumcount");
            return (Criteria) this;
        }

        public Criteria andSumcountNotIn(List<Integer> values) {
            addCriterion("sumcount not in", values, "sumcount");
            return (Criteria) this;
        }

        public Criteria andSumcountBetween(Integer value1, Integer value2) {
            addCriterion("sumcount between", value1, value2, "sumcount");
            return (Criteria) this;
        }

        public Criteria andSumcountNotBetween(Integer value1, Integer value2) {
            addCriterion("sumcount not between", value1, value2, "sumcount");
            return (Criteria) this;
        }

        public Criteria andResultsIsNull() {
            addCriterion("results is null");
            return (Criteria) this;
        }

        public Criteria andResultsIsNotNull() {
            addCriterion("results is not null");
            return (Criteria) this;
        }

        public Criteria andResultsEqualTo(String value) {
            addCriterion("results =", value, "results");
            return (Criteria) this;
        }

        public Criteria andResultsNotEqualTo(String value) {
            addCriterion("results <>", value, "results");
            return (Criteria) this;
        }

        public Criteria andResultsGreaterThan(String value) {
            addCriterion("results >", value, "results");
            return (Criteria) this;
        }

        public Criteria andResultsGreaterThanOrEqualTo(String value) {
            addCriterion("results >=", value, "results");
            return (Criteria) this;
        }

        public Criteria andResultsLessThan(String value) {
            addCriterion("results <", value, "results");
            return (Criteria) this;
        }

        public Criteria andResultsLessThanOrEqualTo(String value) {
            addCriterion("results <=", value, "results");
            return (Criteria) this;
        }

        public Criteria andResultsLike(String value) {
            addCriterion("results like", value, "results");
            return (Criteria) this;
        }

        public Criteria andResultsNotLike(String value) {
            addCriterion("results not like", value, "results");
            return (Criteria) this;
        }

        public Criteria andResultsIn(List<String> values) {
            addCriterion("results in", values, "results");
            return (Criteria) this;
        }

        public Criteria andResultsNotIn(List<String> values) {
            addCriterion("results not in", values, "results");
            return (Criteria) this;
        }

        public Criteria andResultsBetween(String value1, String value2) {
            addCriterion("results between", value1, value2, "results");
            return (Criteria) this;
        }

        public Criteria andResultsNotBetween(String value1, String value2) {
            addCriterion("results not between", value1, value2, "results");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

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