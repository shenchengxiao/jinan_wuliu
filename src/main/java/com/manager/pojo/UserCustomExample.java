package com.manager.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserCustomExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserCustomExample() {
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

        public Criteria andSendProvinceIsNull() {
            addCriterion("send_province is null");
            return (Criteria) this;
        }

        public Criteria andSendProvinceIsNotNull() {
            addCriterion("send_province is not null");
            return (Criteria) this;
        }

        public Criteria andSendProvinceEqualTo(String value) {
            addCriterion("send_province =", value, "sendProvince");
            return (Criteria) this;
        }

        public Criteria andSendProvinceNotEqualTo(String value) {
            addCriterion("send_province <>", value, "sendProvince");
            return (Criteria) this;
        }

        public Criteria andSendProvinceGreaterThan(String value) {
            addCriterion("send_province >", value, "sendProvince");
            return (Criteria) this;
        }

        public Criteria andSendProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("send_province >=", value, "sendProvince");
            return (Criteria) this;
        }

        public Criteria andSendProvinceLessThan(String value) {
            addCriterion("send_province <", value, "sendProvince");
            return (Criteria) this;
        }

        public Criteria andSendProvinceLessThanOrEqualTo(String value) {
            addCriterion("send_province <=", value, "sendProvince");
            return (Criteria) this;
        }

        public Criteria andSendProvinceLike(String value) {
            addCriterion("send_province like", value, "sendProvince");
            return (Criteria) this;
        }

        public Criteria andSendProvinceNotLike(String value) {
            addCriterion("send_province not like", value, "sendProvince");
            return (Criteria) this;
        }

        public Criteria andSendProvinceIn(List<String> values) {
            addCriterion("send_province in", values, "sendProvince");
            return (Criteria) this;
        }

        public Criteria andSendProvinceNotIn(List<String> values) {
            addCriterion("send_province not in", values, "sendProvince");
            return (Criteria) this;
        }

        public Criteria andSendProvinceBetween(String value1, String value2) {
            addCriterion("send_province between", value1, value2, "sendProvince");
            return (Criteria) this;
        }

        public Criteria andSendProvinceNotBetween(String value1, String value2) {
            addCriterion("send_province not between", value1, value2, "sendProvince");
            return (Criteria) this;
        }

        public Criteria andSendCityIsNull() {
            addCriterion("send_city is null");
            return (Criteria) this;
        }

        public Criteria andSendCityIsNotNull() {
            addCriterion("send_city is not null");
            return (Criteria) this;
        }

        public Criteria andSendCityEqualTo(String value) {
            addCriterion("send_city =", value, "sendCity");
            return (Criteria) this;
        }

        public Criteria andSendCityNotEqualTo(String value) {
            addCriterion("send_city <>", value, "sendCity");
            return (Criteria) this;
        }

        public Criteria andSendCityGreaterThan(String value) {
            addCriterion("send_city >", value, "sendCity");
            return (Criteria) this;
        }

        public Criteria andSendCityGreaterThanOrEqualTo(String value) {
            addCriterion("send_city >=", value, "sendCity");
            return (Criteria) this;
        }

        public Criteria andSendCityLessThan(String value) {
            addCriterion("send_city <", value, "sendCity");
            return (Criteria) this;
        }

        public Criteria andSendCityLessThanOrEqualTo(String value) {
            addCriterion("send_city <=", value, "sendCity");
            return (Criteria) this;
        }

        public Criteria andSendCityLike(String value) {
            addCriterion("send_city like", value, "sendCity");
            return (Criteria) this;
        }

        public Criteria andSendCityNotLike(String value) {
            addCriterion("send_city not like", value, "sendCity");
            return (Criteria) this;
        }

        public Criteria andSendCityIn(List<String> values) {
            addCriterion("send_city in", values, "sendCity");
            return (Criteria) this;
        }

        public Criteria andSendCityNotIn(List<String> values) {
            addCriterion("send_city not in", values, "sendCity");
            return (Criteria) this;
        }

        public Criteria andSendCityBetween(String value1, String value2) {
            addCriterion("send_city between", value1, value2, "sendCity");
            return (Criteria) this;
        }

        public Criteria andSendCityNotBetween(String value1, String value2) {
            addCriterion("send_city not between", value1, value2, "sendCity");
            return (Criteria) this;
        }

        public Criteria andReceiveProvinceIsNull() {
            addCriterion("receive_province is null");
            return (Criteria) this;
        }

        public Criteria andReceiveProvinceIsNotNull() {
            addCriterion("receive_province is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveProvinceEqualTo(String value) {
            addCriterion("receive_province =", value, "receiveProvince");
            return (Criteria) this;
        }

        public Criteria andReceiveProvinceNotEqualTo(String value) {
            addCriterion("receive_province <>", value, "receiveProvince");
            return (Criteria) this;
        }

        public Criteria andReceiveProvinceGreaterThan(String value) {
            addCriterion("receive_province >", value, "receiveProvince");
            return (Criteria) this;
        }

        public Criteria andReceiveProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("receive_province >=", value, "receiveProvince");
            return (Criteria) this;
        }

        public Criteria andReceiveProvinceLessThan(String value) {
            addCriterion("receive_province <", value, "receiveProvince");
            return (Criteria) this;
        }

        public Criteria andReceiveProvinceLessThanOrEqualTo(String value) {
            addCriterion("receive_province <=", value, "receiveProvince");
            return (Criteria) this;
        }

        public Criteria andReceiveProvinceLike(String value) {
            addCriterion("receive_province like", value, "receiveProvince");
            return (Criteria) this;
        }

        public Criteria andReceiveProvinceNotLike(String value) {
            addCriterion("receive_province not like", value, "receiveProvince");
            return (Criteria) this;
        }

        public Criteria andReceiveProvinceIn(List<String> values) {
            addCriterion("receive_province in", values, "receiveProvince");
            return (Criteria) this;
        }

        public Criteria andReceiveProvinceNotIn(List<String> values) {
            addCriterion("receive_province not in", values, "receiveProvince");
            return (Criteria) this;
        }

        public Criteria andReceiveProvinceBetween(String value1, String value2) {
            addCriterion("receive_province between", value1, value2, "receiveProvince");
            return (Criteria) this;
        }

        public Criteria andReceiveProvinceNotBetween(String value1, String value2) {
            addCriterion("receive_province not between", value1, value2, "receiveProvince");
            return (Criteria) this;
        }

        public Criteria andReceiveCityIsNull() {
            addCriterion("receive_city is null");
            return (Criteria) this;
        }

        public Criteria andReceiveCityIsNotNull() {
            addCriterion("receive_city is not null");
            return (Criteria) this;
        }

        public Criteria andReceiveCityEqualTo(String value) {
            addCriterion("receive_city =", value, "receiveCity");
            return (Criteria) this;
        }

        public Criteria andReceiveCityNotEqualTo(String value) {
            addCriterion("receive_city <>", value, "receiveCity");
            return (Criteria) this;
        }

        public Criteria andReceiveCityGreaterThan(String value) {
            addCriterion("receive_city >", value, "receiveCity");
            return (Criteria) this;
        }

        public Criteria andReceiveCityGreaterThanOrEqualTo(String value) {
            addCriterion("receive_city >=", value, "receiveCity");
            return (Criteria) this;
        }

        public Criteria andReceiveCityLessThan(String value) {
            addCriterion("receive_city <", value, "receiveCity");
            return (Criteria) this;
        }

        public Criteria andReceiveCityLessThanOrEqualTo(String value) {
            addCriterion("receive_city <=", value, "receiveCity");
            return (Criteria) this;
        }

        public Criteria andReceiveCityLike(String value) {
            addCriterion("receive_city like", value, "receiveCity");
            return (Criteria) this;
        }

        public Criteria andReceiveCityNotLike(String value) {
            addCriterion("receive_city not like", value, "receiveCity");
            return (Criteria) this;
        }

        public Criteria andReceiveCityIn(List<String> values) {
            addCriterion("receive_city in", values, "receiveCity");
            return (Criteria) this;
        }

        public Criteria andReceiveCityNotIn(List<String> values) {
            addCriterion("receive_city not in", values, "receiveCity");
            return (Criteria) this;
        }

        public Criteria andReceiveCityBetween(String value1, String value2) {
            addCriterion("receive_city between", value1, value2, "receiveCity");
            return (Criteria) this;
        }

        public Criteria andReceiveCityNotBetween(String value1, String value2) {
            addCriterion("receive_city not between", value1, value2, "receiveCity");
            return (Criteria) this;
        }

        public Criteria andIsSendIsNull() {
            addCriterion("is_send is null");
            return (Criteria) this;
        }

        public Criteria andIsSendIsNotNull() {
            addCriterion("is_send is not null");
            return (Criteria) this;
        }

        public Criteria andIsSendEqualTo(Byte value) {
            addCriterion("is_send =", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendNotEqualTo(Byte value) {
            addCriterion("is_send <>", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendGreaterThan(Byte value) {
            addCriterion("is_send >", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_send >=", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendLessThan(Byte value) {
            addCriterion("is_send <", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendLessThanOrEqualTo(Byte value) {
            addCriterion("is_send <=", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendIn(List<Byte> values) {
            addCriterion("is_send in", values, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendNotIn(List<Byte> values) {
            addCriterion("is_send not in", values, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendBetween(Byte value1, Byte value2) {
            addCriterion("is_send between", value1, value2, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendNotBetween(Byte value1, Byte value2) {
            addCriterion("is_send not between", value1, value2, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsReceiveIsNull() {
            addCriterion("is_receive is null");
            return (Criteria) this;
        }

        public Criteria andIsReceiveIsNotNull() {
            addCriterion("is_receive is not null");
            return (Criteria) this;
        }

        public Criteria andIsReceiveEqualTo(Byte value) {
            addCriterion("is_receive =", value, "isReceive");
            return (Criteria) this;
        }

        public Criteria andIsReceiveNotEqualTo(Byte value) {
            addCriterion("is_receive <>", value, "isReceive");
            return (Criteria) this;
        }

        public Criteria andIsReceiveGreaterThan(Byte value) {
            addCriterion("is_receive >", value, "isReceive");
            return (Criteria) this;
        }

        public Criteria andIsReceiveGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_receive >=", value, "isReceive");
            return (Criteria) this;
        }

        public Criteria andIsReceiveLessThan(Byte value) {
            addCriterion("is_receive <", value, "isReceive");
            return (Criteria) this;
        }

        public Criteria andIsReceiveLessThanOrEqualTo(Byte value) {
            addCriterion("is_receive <=", value, "isReceive");
            return (Criteria) this;
        }

        public Criteria andIsReceiveIn(List<Byte> values) {
            addCriterion("is_receive in", values, "isReceive");
            return (Criteria) this;
        }

        public Criteria andIsReceiveNotIn(List<Byte> values) {
            addCriterion("is_receive not in", values, "isReceive");
            return (Criteria) this;
        }

        public Criteria andIsReceiveBetween(Byte value1, Byte value2) {
            addCriterion("is_receive between", value1, value2, "isReceive");
            return (Criteria) this;
        }

        public Criteria andIsReceiveNotBetween(Byte value1, Byte value2) {
            addCriterion("is_receive not between", value1, value2, "isReceive");
            return (Criteria) this;
        }

        public Criteria andIsReceiveSelfIsNull() {
            addCriterion("is_receive_self is null");
            return (Criteria) this;
        }

        public Criteria andIsReceiveSelfIsNotNull() {
            addCriterion("is_receive_self is not null");
            return (Criteria) this;
        }

        public Criteria andIsReceiveSelfEqualTo(Byte value) {
            addCriterion("is_receive_self =", value, "isReceiveSelf");
            return (Criteria) this;
        }

        public Criteria andIsReceiveSelfNotEqualTo(Byte value) {
            addCriterion("is_receive_self <>", value, "isReceiveSelf");
            return (Criteria) this;
        }

        public Criteria andIsReceiveSelfGreaterThan(Byte value) {
            addCriterion("is_receive_self >", value, "isReceiveSelf");
            return (Criteria) this;
        }

        public Criteria andIsReceiveSelfGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_receive_self >=", value, "isReceiveSelf");
            return (Criteria) this;
        }

        public Criteria andIsReceiveSelfLessThan(Byte value) {
            addCriterion("is_receive_self <", value, "isReceiveSelf");
            return (Criteria) this;
        }

        public Criteria andIsReceiveSelfLessThanOrEqualTo(Byte value) {
            addCriterion("is_receive_self <=", value, "isReceiveSelf");
            return (Criteria) this;
        }

        public Criteria andIsReceiveSelfIn(List<Byte> values) {
            addCriterion("is_receive_self in", values, "isReceiveSelf");
            return (Criteria) this;
        }

        public Criteria andIsReceiveSelfNotIn(List<Byte> values) {
            addCriterion("is_receive_self not in", values, "isReceiveSelf");
            return (Criteria) this;
        }

        public Criteria andIsReceiveSelfBetween(Byte value1, Byte value2) {
            addCriterion("is_receive_self between", value1, value2, "isReceiveSelf");
            return (Criteria) this;
        }

        public Criteria andIsReceiveSelfNotBetween(Byte value1, Byte value2) {
            addCriterion("is_receive_self not between", value1, value2, "isReceiveSelf");
            return (Criteria) this;
        }

        public Criteria andIsManagerIsNull() {
            addCriterion("is_manager is null");
            return (Criteria) this;
        }

        public Criteria andIsManagerIsNotNull() {
            addCriterion("is_manager is not null");
            return (Criteria) this;
        }

        public Criteria andIsManagerEqualTo(Byte value) {
            addCriterion("is_manager =", value, "isManager");
            return (Criteria) this;
        }

        public Criteria andIsManagerNotEqualTo(Byte value) {
            addCriterion("is_manager <>", value, "isManager");
            return (Criteria) this;
        }

        public Criteria andIsManagerGreaterThan(Byte value) {
            addCriterion("is_manager >", value, "isManager");
            return (Criteria) this;
        }

        public Criteria andIsManagerGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_manager >=", value, "isManager");
            return (Criteria) this;
        }

        public Criteria andIsManagerLessThan(Byte value) {
            addCriterion("is_manager <", value, "isManager");
            return (Criteria) this;
        }

        public Criteria andIsManagerLessThanOrEqualTo(Byte value) {
            addCriterion("is_manager <=", value, "isManager");
            return (Criteria) this;
        }

        public Criteria andIsManagerIn(List<Byte> values) {
            addCriterion("is_manager in", values, "isManager");
            return (Criteria) this;
        }

        public Criteria andIsManagerNotIn(List<Byte> values) {
            addCriterion("is_manager not in", values, "isManager");
            return (Criteria) this;
        }

        public Criteria andIsManagerBetween(Byte value1, Byte value2) {
            addCriterion("is_manager between", value1, value2, "isManager");
            return (Criteria) this;
        }

        public Criteria andIsManagerNotBetween(Byte value1, Byte value2) {
            addCriterion("is_manager not between", value1, value2, "isManager");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeIsNull() {
            addCriterion("platform_type is null");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeIsNotNull() {
            addCriterion("platform_type is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeEqualTo(Byte value) {
            addCriterion("platform_type =", value, "platformType");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeNotEqualTo(Byte value) {
            addCriterion("platform_type <>", value, "platformType");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeGreaterThan(Byte value) {
            addCriterion("platform_type >", value, "platformType");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("platform_type >=", value, "platformType");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeLessThan(Byte value) {
            addCriterion("platform_type <", value, "platformType");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeLessThanOrEqualTo(Byte value) {
            addCriterion("platform_type <=", value, "platformType");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeIn(List<Byte> values) {
            addCriterion("platform_type in", values, "platformType");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeNotIn(List<Byte> values) {
            addCriterion("platform_type not in", values, "platformType");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeBetween(Byte value1, Byte value2) {
            addCriterion("platform_type between", value1, value2, "platformType");
            return (Criteria) this;
        }

        public Criteria andPlatformTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("platform_type not between", value1, value2, "platformType");
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

        public Criteria andIsPhoneLimitIsNull() {
            addCriterion("is_phone_limit is null");
            return (Criteria) this;
        }

        public Criteria andIsPhoneLimitIsNotNull() {
            addCriterion("is_phone_limit is not null");
            return (Criteria) this;
        }

        public Criteria andIsPhoneLimitEqualTo(Byte value) {
            addCriterion("is_phone_limit =", value, "isPhoneLimit");
            return (Criteria) this;
        }

        public Criteria andIsPhoneLimitNotEqualTo(Byte value) {
            addCriterion("is_phone_limit <>", value, "isPhoneLimit");
            return (Criteria) this;
        }

        public Criteria andIsPhoneLimitGreaterThan(Byte value) {
            addCriterion("is_phone_limit >", value, "isPhoneLimit");
            return (Criteria) this;
        }

        public Criteria andIsPhoneLimitGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_phone_limit >=", value, "isPhoneLimit");
            return (Criteria) this;
        }

        public Criteria andIsPhoneLimitLessThan(Byte value) {
            addCriterion("is_phone_limit <", value, "isPhoneLimit");
            return (Criteria) this;
        }

        public Criteria andIsPhoneLimitLessThanOrEqualTo(Byte value) {
            addCriterion("is_phone_limit <=", value, "isPhoneLimit");
            return (Criteria) this;
        }

        public Criteria andIsPhoneLimitIn(List<Byte> values) {
            addCriterion("is_phone_limit in", values, "isPhoneLimit");
            return (Criteria) this;
        }

        public Criteria andIsPhoneLimitNotIn(List<Byte> values) {
            addCriterion("is_phone_limit not in", values, "isPhoneLimit");
            return (Criteria) this;
        }

        public Criteria andIsPhoneLimitBetween(Byte value1, Byte value2) {
            addCriterion("is_phone_limit between", value1, value2, "isPhoneLimit");
            return (Criteria) this;
        }

        public Criteria andIsPhoneLimitNotBetween(Byte value1, Byte value2) {
            addCriterion("is_phone_limit not between", value1, value2, "isPhoneLimit");
            return (Criteria) this;
        }

        public Criteria andIsReceiveCarIsNull() {
            addCriterion("is_receive_car is null");
            return (Criteria) this;
        }

        public Criteria andIsReceiveCarIsNotNull() {
            addCriterion("is_receive_car is not null");
            return (Criteria) this;
        }

        public Criteria andIsReceiveCarEqualTo(Byte value) {
            addCriterion("is_receive_car =", value, "isReceiveCar");
            return (Criteria) this;
        }

        public Criteria andIsReceiveCarNotEqualTo(Byte value) {
            addCriterion("is_receive_car <>", value, "isReceiveCar");
            return (Criteria) this;
        }

        public Criteria andIsReceiveCarGreaterThan(Byte value) {
            addCriterion("is_receive_car >", value, "isReceiveCar");
            return (Criteria) this;
        }

        public Criteria andIsReceiveCarGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_receive_car >=", value, "isReceiveCar");
            return (Criteria) this;
        }

        public Criteria andIsReceiveCarLessThan(Byte value) {
            addCriterion("is_receive_car <", value, "isReceiveCar");
            return (Criteria) this;
        }

        public Criteria andIsReceiveCarLessThanOrEqualTo(Byte value) {
            addCriterion("is_receive_car <=", value, "isReceiveCar");
            return (Criteria) this;
        }

        public Criteria andIsReceiveCarIn(List<Byte> values) {
            addCriterion("is_receive_car in", values, "isReceiveCar");
            return (Criteria) this;
        }

        public Criteria andIsReceiveCarNotIn(List<Byte> values) {
            addCriterion("is_receive_car not in", values, "isReceiveCar");
            return (Criteria) this;
        }

        public Criteria andIsReceiveCarBetween(Byte value1, Byte value2) {
            addCriterion("is_receive_car between", value1, value2, "isReceiveCar");
            return (Criteria) this;
        }

        public Criteria andIsReceiveCarNotBetween(Byte value1, Byte value2) {
            addCriterion("is_receive_car not between", value1, value2, "isReceiveCar");
            return (Criteria) this;
        }

        public Criteria andIsReceiveGoodsIsNull() {
            addCriterion("is_receive_goods is null");
            return (Criteria) this;
        }

        public Criteria andIsReceiveGoodsIsNotNull() {
            addCriterion("is_receive_goods is not null");
            return (Criteria) this;
        }

        public Criteria andIsReceiveGoodsEqualTo(Byte value) {
            addCriterion("is_receive_goods =", value, "isReceiveGoods");
            return (Criteria) this;
        }

        public Criteria andIsReceiveGoodsNotEqualTo(Byte value) {
            addCriterion("is_receive_goods <>", value, "isReceiveGoods");
            return (Criteria) this;
        }

        public Criteria andIsReceiveGoodsGreaterThan(Byte value) {
            addCriterion("is_receive_goods >", value, "isReceiveGoods");
            return (Criteria) this;
        }

        public Criteria andIsReceiveGoodsGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_receive_goods >=", value, "isReceiveGoods");
            return (Criteria) this;
        }

        public Criteria andIsReceiveGoodsLessThan(Byte value) {
            addCriterion("is_receive_goods <", value, "isReceiveGoods");
            return (Criteria) this;
        }

        public Criteria andIsReceiveGoodsLessThanOrEqualTo(Byte value) {
            addCriterion("is_receive_goods <=", value, "isReceiveGoods");
            return (Criteria) this;
        }

        public Criteria andIsReceiveGoodsIn(List<Byte> values) {
            addCriterion("is_receive_goods in", values, "isReceiveGoods");
            return (Criteria) this;
        }

        public Criteria andIsReceiveGoodsNotIn(List<Byte> values) {
            addCriterion("is_receive_goods not in", values, "isReceiveGoods");
            return (Criteria) this;
        }

        public Criteria andIsReceiveGoodsBetween(Byte value1, Byte value2) {
            addCriterion("is_receive_goods between", value1, value2, "isReceiveGoods");
            return (Criteria) this;
        }

        public Criteria andIsReceiveGoodsNotBetween(Byte value1, Byte value2) {
            addCriterion("is_receive_goods not between", value1, value2, "isReceiveGoods");
            return (Criteria) this;
        }

        public Criteria andIsSendCarIsNull() {
            addCriterion("is_send_car is null");
            return (Criteria) this;
        }

        public Criteria andIsSendCarIsNotNull() {
            addCriterion("is_send_car is not null");
            return (Criteria) this;
        }

        public Criteria andIsSendCarEqualTo(Byte value) {
            addCriterion("is_send_car =", value, "isSendCar");
            return (Criteria) this;
        }

        public Criteria andIsSendCarNotEqualTo(Byte value) {
            addCriterion("is_send_car <>", value, "isSendCar");
            return (Criteria) this;
        }

        public Criteria andIsSendCarGreaterThan(Byte value) {
            addCriterion("is_send_car >", value, "isSendCar");
            return (Criteria) this;
        }

        public Criteria andIsSendCarGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_send_car >=", value, "isSendCar");
            return (Criteria) this;
        }

        public Criteria andIsSendCarLessThan(Byte value) {
            addCriterion("is_send_car <", value, "isSendCar");
            return (Criteria) this;
        }

        public Criteria andIsSendCarLessThanOrEqualTo(Byte value) {
            addCriterion("is_send_car <=", value, "isSendCar");
            return (Criteria) this;
        }

        public Criteria andIsSendCarIn(List<Byte> values) {
            addCriterion("is_send_car in", values, "isSendCar");
            return (Criteria) this;
        }

        public Criteria andIsSendCarNotIn(List<Byte> values) {
            addCriterion("is_send_car not in", values, "isSendCar");
            return (Criteria) this;
        }

        public Criteria andIsSendCarBetween(Byte value1, Byte value2) {
            addCriterion("is_send_car between", value1, value2, "isSendCar");
            return (Criteria) this;
        }

        public Criteria andIsSendCarNotBetween(Byte value1, Byte value2) {
            addCriterion("is_send_car not between", value1, value2, "isSendCar");
            return (Criteria) this;
        }

        public Criteria andIsSendGoodsIsNull() {
            addCriterion("is_send_goods is null");
            return (Criteria) this;
        }

        public Criteria andIsSendGoodsIsNotNull() {
            addCriterion("is_send_goods is not null");
            return (Criteria) this;
        }

        public Criteria andIsSendGoodsEqualTo(Byte value) {
            addCriterion("is_send_goods =", value, "isSendGoods");
            return (Criteria) this;
        }

        public Criteria andIsSendGoodsNotEqualTo(Byte value) {
            addCriterion("is_send_goods <>", value, "isSendGoods");
            return (Criteria) this;
        }

        public Criteria andIsSendGoodsGreaterThan(Byte value) {
            addCriterion("is_send_goods >", value, "isSendGoods");
            return (Criteria) this;
        }

        public Criteria andIsSendGoodsGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_send_goods >=", value, "isSendGoods");
            return (Criteria) this;
        }

        public Criteria andIsSendGoodsLessThan(Byte value) {
            addCriterion("is_send_goods <", value, "isSendGoods");
            return (Criteria) this;
        }

        public Criteria andIsSendGoodsLessThanOrEqualTo(Byte value) {
            addCriterion("is_send_goods <=", value, "isSendGoods");
            return (Criteria) this;
        }

        public Criteria andIsSendGoodsIn(List<Byte> values) {
            addCriterion("is_send_goods in", values, "isSendGoods");
            return (Criteria) this;
        }

        public Criteria andIsSendGoodsNotIn(List<Byte> values) {
            addCriterion("is_send_goods not in", values, "isSendGoods");
            return (Criteria) this;
        }

        public Criteria andIsSendGoodsBetween(Byte value1, Byte value2) {
            addCriterion("is_send_goods between", value1, value2, "isSendGoods");
            return (Criteria) this;
        }

        public Criteria andIsSendGoodsNotBetween(Byte value1, Byte value2) {
            addCriterion("is_send_goods not between", value1, value2, "isSendGoods");
            return (Criteria) this;
        }

        public Criteria andIsLookPhoneIsNull() {
            addCriterion("is_look_phone is null");
            return (Criteria) this;
        }

        public Criteria andIsLookPhoneIsNotNull() {
            addCriterion("is_look_phone is not null");
            return (Criteria) this;
        }

        public Criteria andIsLookPhoneEqualTo(Byte value) {
            addCriterion("is_look_phone =", value, "isLookPhone");
            return (Criteria) this;
        }

        public Criteria andIsLookPhoneNotEqualTo(Byte value) {
            addCriterion("is_look_phone <>", value, "isLookPhone");
            return (Criteria) this;
        }

        public Criteria andIsLookPhoneGreaterThan(Byte value) {
            addCriterion("is_look_phone >", value, "isLookPhone");
            return (Criteria) this;
        }

        public Criteria andIsLookPhoneGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_look_phone >=", value, "isLookPhone");
            return (Criteria) this;
        }

        public Criteria andIsLookPhoneLessThan(Byte value) {
            addCriterion("is_look_phone <", value, "isLookPhone");
            return (Criteria) this;
        }

        public Criteria andIsLookPhoneLessThanOrEqualTo(Byte value) {
            addCriterion("is_look_phone <=", value, "isLookPhone");
            return (Criteria) this;
        }

        public Criteria andIsLookPhoneIn(List<Byte> values) {
            addCriterion("is_look_phone in", values, "isLookPhone");
            return (Criteria) this;
        }

        public Criteria andIsLookPhoneNotIn(List<Byte> values) {
            addCriterion("is_look_phone not in", values, "isLookPhone");
            return (Criteria) this;
        }

        public Criteria andIsLookPhoneBetween(Byte value1, Byte value2) {
            addCriterion("is_look_phone between", value1, value2, "isLookPhone");
            return (Criteria) this;
        }

        public Criteria andIsLookPhoneNotBetween(Byte value1, Byte value2) {
            addCriterion("is_look_phone not between", value1, value2, "isLookPhone");
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