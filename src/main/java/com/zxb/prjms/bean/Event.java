package com.zxb.prjms.bean;

import java.util.Date;

public class Event {
    private Integer id;

    private Date operatetime;

    private String employeeid;

    private String eventtype;

    private String eventcontent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getOperatetime() {
        return operatetime;
    }

    public void setOperatetime(Date operatetime) {
        this.operatetime = operatetime;
    }

    public String getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(String employeeid) {
        this.employeeid = employeeid == null ? null : employeeid.trim();
    }

    public String getEventtype() {
        return eventtype;
    }

    public void setEventtype(String eventtype) {
        this.eventtype = eventtype == null ? null : eventtype.trim();
    }

    public String getEventcontent() {
        return eventcontent;
    }

    public void setEventcontent(String eventcontent) {
        this.eventcontent = eventcontent == null ? null : eventcontent.trim();
    }
}