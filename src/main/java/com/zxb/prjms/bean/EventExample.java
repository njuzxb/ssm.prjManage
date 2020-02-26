package com.zxb.prjms.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EventExample() {
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

        public Criteria andOperatetimeIsNull() {
            addCriterion("operatetime is null");
            return (Criteria) this;
        }

        public Criteria andOperatetimeIsNotNull() {
            addCriterion("operatetime is not null");
            return (Criteria) this;
        }

        public Criteria andOperatetimeEqualTo(Date value) {
            addCriterion("operatetime =", value, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeNotEqualTo(Date value) {
            addCriterion("operatetime <>", value, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeGreaterThan(Date value) {
            addCriterion("operatetime >", value, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("operatetime >=", value, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeLessThan(Date value) {
            addCriterion("operatetime <", value, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeLessThanOrEqualTo(Date value) {
            addCriterion("operatetime <=", value, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeIn(List<Date> values) {
            addCriterion("operatetime in", values, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeNotIn(List<Date> values) {
            addCriterion("operatetime not in", values, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeBetween(Date value1, Date value2) {
            addCriterion("operatetime between", value1, value2, "operatetime");
            return (Criteria) this;
        }

        public Criteria andOperatetimeNotBetween(Date value1, Date value2) {
            addCriterion("operatetime not between", value1, value2, "operatetime");
            return (Criteria) this;
        }

        public Criteria andEmployeeidIsNull() {
            addCriterion("employeeid is null");
            return (Criteria) this;
        }

        public Criteria andEmployeeidIsNotNull() {
            addCriterion("employeeid is not null");
            return (Criteria) this;
        }

        public Criteria andEmployeeidEqualTo(String value) {
            addCriterion("employeeid =", value, "employeeid");
            return (Criteria) this;
        }

        public Criteria andEmployeeidNotEqualTo(String value) {
            addCriterion("employeeid <>", value, "employeeid");
            return (Criteria) this;
        }

        public Criteria andEmployeeidGreaterThan(String value) {
            addCriterion("employeeid >", value, "employeeid");
            return (Criteria) this;
        }

        public Criteria andEmployeeidGreaterThanOrEqualTo(String value) {
            addCriterion("employeeid >=", value, "employeeid");
            return (Criteria) this;
        }

        public Criteria andEmployeeidLessThan(String value) {
            addCriterion("employeeid <", value, "employeeid");
            return (Criteria) this;
        }

        public Criteria andEmployeeidLessThanOrEqualTo(String value) {
            addCriterion("employeeid <=", value, "employeeid");
            return (Criteria) this;
        }

        public Criteria andEmployeeidLike(String value) {
            addCriterion("employeeid like", value, "employeeid");
            return (Criteria) this;
        }

        public Criteria andEmployeeidNotLike(String value) {
            addCriterion("employeeid not like", value, "employeeid");
            return (Criteria) this;
        }

        public Criteria andEmployeeidIn(List<String> values) {
            addCriterion("employeeid in", values, "employeeid");
            return (Criteria) this;
        }

        public Criteria andEmployeeidNotIn(List<String> values) {
            addCriterion("employeeid not in", values, "employeeid");
            return (Criteria) this;
        }

        public Criteria andEmployeeidBetween(String value1, String value2) {
            addCriterion("employeeid between", value1, value2, "employeeid");
            return (Criteria) this;
        }

        public Criteria andEmployeeidNotBetween(String value1, String value2) {
            addCriterion("employeeid not between", value1, value2, "employeeid");
            return (Criteria) this;
        }

        public Criteria andEventtypeIsNull() {
            addCriterion("eventtype is null");
            return (Criteria) this;
        }

        public Criteria andEventtypeIsNotNull() {
            addCriterion("eventtype is not null");
            return (Criteria) this;
        }

        public Criteria andEventtypeEqualTo(String value) {
            addCriterion("eventtype =", value, "eventtype");
            return (Criteria) this;
        }

        public Criteria andEventtypeNotEqualTo(String value) {
            addCriterion("eventtype <>", value, "eventtype");
            return (Criteria) this;
        }

        public Criteria andEventtypeGreaterThan(String value) {
            addCriterion("eventtype >", value, "eventtype");
            return (Criteria) this;
        }

        public Criteria andEventtypeGreaterThanOrEqualTo(String value) {
            addCriterion("eventtype >=", value, "eventtype");
            return (Criteria) this;
        }

        public Criteria andEventtypeLessThan(String value) {
            addCriterion("eventtype <", value, "eventtype");
            return (Criteria) this;
        }

        public Criteria andEventtypeLessThanOrEqualTo(String value) {
            addCriterion("eventtype <=", value, "eventtype");
            return (Criteria) this;
        }

        public Criteria andEventtypeLike(String value) {
            addCriterion("eventtype like", value, "eventtype");
            return (Criteria) this;
        }

        public Criteria andEventtypeNotLike(String value) {
            addCriterion("eventtype not like", value, "eventtype");
            return (Criteria) this;
        }

        public Criteria andEventtypeIn(List<String> values) {
            addCriterion("eventtype in", values, "eventtype");
            return (Criteria) this;
        }

        public Criteria andEventtypeNotIn(List<String> values) {
            addCriterion("eventtype not in", values, "eventtype");
            return (Criteria) this;
        }

        public Criteria andEventtypeBetween(String value1, String value2) {
            addCriterion("eventtype between", value1, value2, "eventtype");
            return (Criteria) this;
        }

        public Criteria andEventtypeNotBetween(String value1, String value2) {
            addCriterion("eventtype not between", value1, value2, "eventtype");
            return (Criteria) this;
        }

        public Criteria andEventcontentIsNull() {
            addCriterion("eventcontent is null");
            return (Criteria) this;
        }

        public Criteria andEventcontentIsNotNull() {
            addCriterion("eventcontent is not null");
            return (Criteria) this;
        }

        public Criteria andEventcontentEqualTo(String value) {
            addCriterion("eventcontent =", value, "eventcontent");
            return (Criteria) this;
        }

        public Criteria andEventcontentNotEqualTo(String value) {
            addCriterion("eventcontent <>", value, "eventcontent");
            return (Criteria) this;
        }

        public Criteria andEventcontentGreaterThan(String value) {
            addCriterion("eventcontent >", value, "eventcontent");
            return (Criteria) this;
        }

        public Criteria andEventcontentGreaterThanOrEqualTo(String value) {
            addCriterion("eventcontent >=", value, "eventcontent");
            return (Criteria) this;
        }

        public Criteria andEventcontentLessThan(String value) {
            addCriterion("eventcontent <", value, "eventcontent");
            return (Criteria) this;
        }

        public Criteria andEventcontentLessThanOrEqualTo(String value) {
            addCriterion("eventcontent <=", value, "eventcontent");
            return (Criteria) this;
        }

        public Criteria andEventcontentLike(String value) {
            addCriterion("eventcontent like", value, "eventcontent");
            return (Criteria) this;
        }

        public Criteria andEventcontentNotLike(String value) {
            addCriterion("eventcontent not like", value, "eventcontent");
            return (Criteria) this;
        }

        public Criteria andEventcontentIn(List<String> values) {
            addCriterion("eventcontent in", values, "eventcontent");
            return (Criteria) this;
        }

        public Criteria andEventcontentNotIn(List<String> values) {
            addCriterion("eventcontent not in", values, "eventcontent");
            return (Criteria) this;
        }

        public Criteria andEventcontentBetween(String value1, String value2) {
            addCriterion("eventcontent between", value1, value2, "eventcontent");
            return (Criteria) this;
        }

        public Criteria andEventcontentNotBetween(String value1, String value2) {
            addCriterion("eventcontent not between", value1, value2, "eventcontent");
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