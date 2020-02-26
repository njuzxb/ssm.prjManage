package com.zxb.prjms.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProjectExample() {
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

        public Criteria andProjectnameIsNull() {
            addCriterion("projectname is null");
            return (Criteria) this;
        }

        public Criteria andProjectnameIsNotNull() {
            addCriterion("projectname is not null");
            return (Criteria) this;
        }

        public Criteria andProjectnameEqualTo(String value) {
            addCriterion("projectname =", value, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameNotEqualTo(String value) {
            addCriterion("projectname <>", value, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameGreaterThan(String value) {
            addCriterion("projectname >", value, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameGreaterThanOrEqualTo(String value) {
            addCriterion("projectname >=", value, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameLessThan(String value) {
            addCriterion("projectname <", value, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameLessThanOrEqualTo(String value) {
            addCriterion("projectname <=", value, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameLike(String value) {
            addCriterion("projectname like", value, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameNotLike(String value) {
            addCriterion("projectname not like", value, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameIn(List<String> values) {
            addCriterion("projectname in", values, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameNotIn(List<String> values) {
            addCriterion("projectname not in", values, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameBetween(String value1, String value2) {
            addCriterion("projectname between", value1, value2, "projectname");
            return (Criteria) this;
        }

        public Criteria andProjectnameNotBetween(String value1, String value2) {
            addCriterion("projectname not between", value1, value2, "projectname");
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

        public Criteria andFinishtimeIsNull() {
            addCriterion("finishtime is null");
            return (Criteria) this;
        }

        public Criteria andFinishtimeIsNotNull() {
            addCriterion("finishtime is not null");
            return (Criteria) this;
        }

        public Criteria andFinishtimeEqualTo(Date value) {
            addCriterion("finishtime =", value, "finishtime");
            return (Criteria) this;
        }

        public Criteria andFinishtimeNotEqualTo(Date value) {
            addCriterion("finishtime <>", value, "finishtime");
            return (Criteria) this;
        }

        public Criteria andFinishtimeGreaterThan(Date value) {
            addCriterion("finishtime >", value, "finishtime");
            return (Criteria) this;
        }

        public Criteria andFinishtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("finishtime >=", value, "finishtime");
            return (Criteria) this;
        }

        public Criteria andFinishtimeLessThan(Date value) {
            addCriterion("finishtime <", value, "finishtime");
            return (Criteria) this;
        }

        public Criteria andFinishtimeLessThanOrEqualTo(Date value) {
            addCriterion("finishtime <=", value, "finishtime");
            return (Criteria) this;
        }

        public Criteria andFinishtimeIn(List<Date> values) {
            addCriterion("finishtime in", values, "finishtime");
            return (Criteria) this;
        }

        public Criteria andFinishtimeNotIn(List<Date> values) {
            addCriterion("finishtime not in", values, "finishtime");
            return (Criteria) this;
        }

        public Criteria andFinishtimeBetween(Date value1, Date value2) {
            addCriterion("finishtime between", value1, value2, "finishtime");
            return (Criteria) this;
        }

        public Criteria andFinishtimeNotBetween(Date value1, Date value2) {
            addCriterion("finishtime not between", value1, value2, "finishtime");
            return (Criteria) this;
        }

        public Criteria andProcessIsNull() {
            addCriterion("process is null");
            return (Criteria) this;
        }

        public Criteria andProcessIsNotNull() {
            addCriterion("process is not null");
            return (Criteria) this;
        }

        public Criteria andProcessEqualTo(Integer value) {
            addCriterion("process =", value, "process");
            return (Criteria) this;
        }

        public Criteria andProcessNotEqualTo(Integer value) {
            addCriterion("process <>", value, "process");
            return (Criteria) this;
        }

        public Criteria andProcessGreaterThan(Integer value) {
            addCriterion("process >", value, "process");
            return (Criteria) this;
        }

        public Criteria andProcessGreaterThanOrEqualTo(Integer value) {
            addCriterion("process >=", value, "process");
            return (Criteria) this;
        }

        public Criteria andProcessLessThan(Integer value) {
            addCriterion("process <", value, "process");
            return (Criteria) this;
        }

        public Criteria andProcessLessThanOrEqualTo(Integer value) {
            addCriterion("process <=", value, "process");
            return (Criteria) this;
        }

        public Criteria andProcessIn(List<Integer> values) {
            addCriterion("process in", values, "process");
            return (Criteria) this;
        }

        public Criteria andProcessNotIn(List<Integer> values) {
            addCriterion("process not in", values, "process");
            return (Criteria) this;
        }

        public Criteria andProcessBetween(Integer value1, Integer value2) {
            addCriterion("process between", value1, value2, "process");
            return (Criteria) this;
        }

        public Criteria andProcessNotBetween(Integer value1, Integer value2) {
            addCriterion("process not between", value1, value2, "process");
            return (Criteria) this;
        }

        public Criteria andHeaderidIsNull() {
            addCriterion("headerid is null");
            return (Criteria) this;
        }

        public Criteria andHeaderidIsNotNull() {
            addCriterion("headerid is not null");
            return (Criteria) this;
        }

        public Criteria andHeaderidEqualTo(String value) {
            addCriterion("headerid =", value, "headerid");
            return (Criteria) this;
        }

        public Criteria andHeaderidNotEqualTo(String value) {
            addCriterion("headerid <>", value, "headerid");
            return (Criteria) this;
        }

        public Criteria andHeaderidGreaterThan(String value) {
            addCriterion("headerid >", value, "headerid");
            return (Criteria) this;
        }

        public Criteria andHeaderidGreaterThanOrEqualTo(String value) {
            addCriterion("headerid >=", value, "headerid");
            return (Criteria) this;
        }

        public Criteria andHeaderidLessThan(String value) {
            addCriterion("headerid <", value, "headerid");
            return (Criteria) this;
        }

        public Criteria andHeaderidLessThanOrEqualTo(String value) {
            addCriterion("headerid <=", value, "headerid");
            return (Criteria) this;
        }

        public Criteria andHeaderidLike(String value) {
            addCriterion("headerid like", value, "headerid");
            return (Criteria) this;
        }

        public Criteria andHeaderidNotLike(String value) {
            addCriterion("headerid not like", value, "headerid");
            return (Criteria) this;
        }

        public Criteria andHeaderidIn(List<String> values) {
            addCriterion("headerid in", values, "headerid");
            return (Criteria) this;
        }

        public Criteria andHeaderidNotIn(List<String> values) {
            addCriterion("headerid not in", values, "headerid");
            return (Criteria) this;
        }

        public Criteria andHeaderidBetween(String value1, String value2) {
            addCriterion("headerid between", value1, value2, "headerid");
            return (Criteria) this;
        }

        public Criteria andHeaderidNotBetween(String value1, String value2) {
            addCriterion("headerid not between", value1, value2, "headerid");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreatoridIsNull() {
            addCriterion("creatorid is null");
            return (Criteria) this;
        }

        public Criteria andCreatoridIsNotNull() {
            addCriterion("creatorid is not null");
            return (Criteria) this;
        }

        public Criteria andCreatoridEqualTo(String value) {
            addCriterion("creatorid =", value, "creatorid");
            return (Criteria) this;
        }

        public Criteria andCreatoridNotEqualTo(String value) {
            addCriterion("creatorid <>", value, "creatorid");
            return (Criteria) this;
        }

        public Criteria andCreatoridGreaterThan(String value) {
            addCriterion("creatorid >", value, "creatorid");
            return (Criteria) this;
        }

        public Criteria andCreatoridGreaterThanOrEqualTo(String value) {
            addCriterion("creatorid >=", value, "creatorid");
            return (Criteria) this;
        }

        public Criteria andCreatoridLessThan(String value) {
            addCriterion("creatorid <", value, "creatorid");
            return (Criteria) this;
        }

        public Criteria andCreatoridLessThanOrEqualTo(String value) {
            addCriterion("creatorid <=", value, "creatorid");
            return (Criteria) this;
        }

        public Criteria andCreatoridLike(String value) {
            addCriterion("creatorid like", value, "creatorid");
            return (Criteria) this;
        }

        public Criteria andCreatoridNotLike(String value) {
            addCriterion("creatorid not like", value, "creatorid");
            return (Criteria) this;
        }

        public Criteria andCreatoridIn(List<String> values) {
            addCriterion("creatorid in", values, "creatorid");
            return (Criteria) this;
        }

        public Criteria andCreatoridNotIn(List<String> values) {
            addCriterion("creatorid not in", values, "creatorid");
            return (Criteria) this;
        }

        public Criteria andCreatoridBetween(String value1, String value2) {
            addCriterion("creatorid between", value1, value2, "creatorid");
            return (Criteria) this;
        }

        public Criteria andCreatoridNotBetween(String value1, String value2) {
            addCriterion("creatorid not between", value1, value2, "creatorid");
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