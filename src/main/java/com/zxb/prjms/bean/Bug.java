package com.zxb.prjms.bean;

import java.util.Date;

public class Bug {
    private Integer id;

    private Integer projectid;

    private Integer moduleid;

    private String bugtype;

    private String bughead;

    private String creatorid;

    private String assignid;

    private Integer priority;

    private Date createtime;

    private Date finishtime;

    private String status;

    private String bugcontent;
    //联合查询项目
    public Project project;
    //联合查询父模块
    public Module module;
    

    public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public Integer getModuleid() {
        return moduleid;
    }

    public void setModuleid(Integer moduleid) {
        this.moduleid = moduleid;
    }

    public String getBugtype() {
        return bugtype;
    }

    public void setBugtype(String bugtype) {
        this.bugtype = bugtype == null ? null : bugtype.trim();
    }

    public String getBughead() {
        return bughead;
    }

    public void setBughead(String bughead) {
        this.bughead = bughead == null ? null : bughead.trim();
    }

    public String getCreatorid() {
        return creatorid;
    }

    public void setCreatorid(String creatorid) {
        this.creatorid = creatorid == null ? null : creatorid.trim();
    }

    public String getAssignid() {
        return assignid;
    }

    public void setAssignid(String assignid) {
        this.assignid = assignid == null ? null : assignid.trim();
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getFinishtime() {
        return finishtime;
    }

    public void setFinishtime(Date finishtime) {
        this.finishtime = finishtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getBugcontent() {
        return bugcontent;
    }

    public void setBugcontent(String bugcontent) {
        this.bugcontent = bugcontent == null ? null : bugcontent.trim();
    }
}