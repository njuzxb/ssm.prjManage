package com.zxb.prjms.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BugExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BugExample() {
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

        public Criteria andProjectidIsNull() {
            addCriterion("projectid is null");
            return (Criteria) this;
        }

        public Criteria andProjectidIsNotNull() {
            addCriterion("projectid is not null");
            return (Criteria) this;
        }

        public Criteria andProjectidEqualTo(Integer value) {
            addCriterion("projectid =", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidNotEqualTo(Integer value) {
            addCriterion("projectid <>", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidGreaterThan(Integer value) {
            addCriterion("projectid >", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidGreaterThanOrEqualTo(Integer value) {
            addCriterion("projectid >=", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidLessThan(Integer value) {
            addCriterion("projectid <", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidLessThanOrEqualTo(Integer value) {
            addCriterion("projectid <=", value, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidIn(List<Integer> values) {
            addCriterion("projectid in", values, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidNotIn(List<Integer> values) {
            addCriterion("projectid not in", values, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidBetween(Integer value1, Integer value2) {
            addCriterion("projectid between", value1, value2, "projectid");
            return (Criteria) this;
        }

        public Criteria andProjectidNotBetween(Integer value1, Integer value2) {
            addCriterion("projectid not between", value1, value2, "projectid");
            return (Criteria) this;
        }

        public Criteria andModuleidIsNull() {
            addCriterion("moduleid is null");
            return (Criteria) this;
        }

        public Criteria andModuleidIsNotNull() {
            addCriterion("moduleid is not null");
            return (Criteria) this;
        }

        public Criteria andModuleidEqualTo(Integer value) {
            addCriterion("moduleid =", value, "moduleid");
            return (Criteria) this;
        }

        public Criteria andModuleidNotEqualTo(Integer value) {
            addCriterion("moduleid <>", value, "moduleid");
            return (Criteria) this;
        }

        public Criteria andModuleidGreaterThan(Integer value) {
            addCriterion("moduleid >", value, "moduleid");
            return (Criteria) this;
        }

        public Criteria andModuleidGreaterThanOrEqualTo(Integer value) {
            addCriterion("moduleid >=", value, "moduleid");
            return (Criteria) this;
        }

        public Criteria andModuleidLessThan(Integer value) {
            addCriterion("moduleid <", value, "moduleid");
            return (Criteria) this;
        }

        public Criteria andModuleidLessThanOrEqualTo(Integer value) {
            addCriterion("moduleid <=", value, "moduleid");
            return (Criteria) this;
        }

        public Criteria andModuleidIn(List<Integer> values) {
            addCriterion("moduleid in", values, "moduleid");
            return (Criteria) this;
        }

        public Criteria andModuleidNotIn(List<Integer> values) {
            addCriterion("moduleid not in", values, "moduleid");
            return (Criteria) this;
        }

        public Criteria andModuleidBetween(Integer value1, Integer value2) {
            addCriterion("moduleid between", value1, value2, "moduleid");
            return (Criteria) this;
        }

        public Criteria andModuleidNotBetween(Integer value1, Integer value2) {
            addCriterion("moduleid not between", value1, value2, "moduleid");
            return (Criteria) this;
        }

        public Criteria andBugtypeIsNull() {
            addCriterion("bugtype is null");
            return (Criteria) this;
        }

        public Criteria andBugtypeIsNotNull() {
            addCriterion("bugtype is not null");
            return (Criteria) this;
        }

        public Criteria andBugtypeEqualTo(String value) {
            addCriterion("bugtype =", value, "bugtype");
            return (Criteria) this;
        }

        public Criteria andBugtypeNotEqualTo(String value) {
            addCriterion("bugtype <>", value, "bugtype");
            return (Criteria) this;
        }

        public Criteria andBugtypeGreaterThan(String value) {
            addCriterion("bugtype >", value, "bugtype");
            return (Criteria) this;
        }

        public Criteria andBugtypeGreaterThanOrEqualTo(String value) {
            addCriterion("bugtype >=", value, "bugtype");
            return (Criteria) this;
        }

        public Criteria andBugtypeLessThan(String value) {
            addCriterion("bugtype <", value, "bugtype");
            return (Criteria) this;
        }

        public Criteria andBugtypeLessThanOrEqualTo(String value) {
            addCriterion("bugtype <=", value, "bugtype");
            return (Criteria) this;
        }

        public Criteria andBugtypeLike(String value) {
            addCriterion("bugtype like", value, "bugtype");
            return (Criteria) this;
        }

        public Criteria andBugtypeNotLike(String value) {
            addCriterion("bugtype not like", value, "bugtype");
            return (Criteria) this;
        }

        public Criteria andBugtypeIn(List<String> values) {
            addCriterion("bugtype in", values, "bugtype");
            return (Criteria) this;
        }

        public Criteria andBugtypeNotIn(List<String> values) {
            addCriterion("bugtype not in", values, "bugtype");
            return (Criteria) this;
        }

        public Criteria andBugtypeBetween(String value1, String value2) {
            addCriterion("bugtype between", value1, value2, "bugtype");
            return (Criteria) this;
        }

        public Criteria andBugtypeNotBetween(String value1, String value2) {
            addCriterion("bugtype not between", value1, value2, "bugtype");
            return (Criteria) this;
        }

        public Criteria andBugheadIsNull() {
            addCriterion("bughead is null");
            return (Criteria) this;
        }

        public Criteria andBugheadIsNotNull() {
            addCriterion("bughead is not null");
            return (Criteria) this;
        }

        public Criteria andBugheadEqualTo(String value) {
            addCriterion("bughead =", value, "bughead");
            return (Criteria) this;
        }

        public Criteria andBugheadNotEqualTo(String value) {
            addCriterion("bughead <>", value, "bughead");
            return (Criteria) this;
        }

        public Criteria andBugheadGreaterThan(String value) {
            addCriterion("bughead >", value, "bughead");
            return (Criteria) this;
        }

        public Criteria andBugheadGreaterThanOrEqualTo(String value) {
            addCriterion("bughead >=", value, "bughead");
            return (Criteria) this;
        }

        public Criteria andBugheadLessThan(String value) {
            addCriterion("bughead <", value, "bughead");
            return (Criteria) this;
        }

        public Criteria andBugheadLessThanOrEqualTo(String value) {
            addCriterion("bughead <=", value, "bughead");
            return (Criteria) this;
        }

        public Criteria andBugheadLike(String value) {
            addCriterion("bughead like", value, "bughead");
            return (Criteria) this;
        }

        public Criteria andBugheadNotLike(String value) {
            addCriterion("bughead not like", value, "bughead");
            return (Criteria) this;
        }

        public Criteria andBugheadIn(List<String> values) {
            addCriterion("bughead in", values, "bughead");
            return (Criteria) this;
        }

        public Criteria andBugheadNotIn(List<String> values) {
            addCriterion("bughead not in", values, "bughead");
            return (Criteria) this;
        }

        public Criteria andBugheadBetween(String value1, String value2) {
            addCriterion("bughead between", value1, value2, "bughead");
            return (Criteria) this;
        }

        public Criteria andBugheadNotBetween(String value1, String value2) {
            addCriterion("bughead not between", value1, value2, "bughead");
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

        public Criteria andAssignidIsNull() {
            addCriterion("assignid is null");
            return (Criteria) this;
        }

        public Criteria andAssignidIsNotNull() {
            addCriterion("assignid is not null");
            return (Criteria) this;
        }

        public Criteria andAssignidEqualTo(String value) {
            addCriterion("assignid =", value, "assignid");
            return (Criteria) this;
        }

        public Criteria andAssignidNotEqualTo(String value) {
            addCriterion("assignid <>", value, "assignid");
            return (Criteria) this;
        }

        public Criteria andAssignidGreaterThan(String value) {
            addCriterion("assignid >", value, "assignid");
            return (Criteria) this;
        }

        public Criteria andAssignidGreaterThanOrEqualTo(String value) {
            addCriterion("assignid >=", value, "assignid");
            return (Criteria) this;
        }

        public Criteria andAssignidLessThan(String value) {
            addCriterion("assignid <", value, "assignid");
            return (Criteria) this;
        }

        public Criteria andAssignidLessThanOrEqualTo(String value) {
            addCriterion("assignid <=", value, "assignid");
            return (Criteria) this;
        }

        public Criteria andAssignidLike(String value) {
            addCriterion("assignid like", value, "assignid");
            return (Criteria) this;
        }

        public Criteria andAssignidNotLike(String value) {
            addCriterion("assignid not like", value, "assignid");
            return (Criteria) this;
        }

        public Criteria andAssignidIn(List<String> values) {
            addCriterion("assignid in", values, "assignid");
            return (Criteria) this;
        }

        public Criteria andAssignidNotIn(List<String> values) {
            addCriterion("assignid not in", values, "assignid");
            return (Criteria) this;
        }

        public Criteria andAssignidBetween(String value1, String value2) {
            addCriterion("assignid between", value1, value2, "assignid");
            return (Criteria) this;
        }

        public Criteria andAssignidNotBetween(String value1, String value2) {
            addCriterion("assignid not between", value1, value2, "assignid");
            return (Criteria) this;
        }

        public Criteria andPriorityIsNull() {
            addCriterion("priority is null");
            return (Criteria) this;
        }

        public Criteria andPriorityIsNotNull() {
            addCriterion("priority is not null");
            return (Criteria) this;
        }

        public Criteria andPriorityEqualTo(Integer value) {
            addCriterion("priority =", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotEqualTo(Integer value) {
            addCriterion("priority <>", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThan(Integer value) {
            addCriterion("priority >", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityGreaterThanOrEqualTo(Integer value) {
            addCriterion("priority >=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThan(Integer value) {
            addCriterion("priority <", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityLessThanOrEqualTo(Integer value) {
            addCriterion("priority <=", value, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityIn(List<Integer> values) {
            addCriterion("priority in", values, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotIn(List<Integer> values) {
            addCriterion("priority not in", values, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityBetween(Integer value1, Integer value2) {
            addCriterion("priority between", value1, value2, "priority");
            return (Criteria) this;
        }

        public Criteria andPriorityNotBetween(Integer value1, Integer value2) {
            addCriterion("priority not between", value1, value2, "priority");
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