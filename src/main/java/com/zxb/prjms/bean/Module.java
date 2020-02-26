package com.zxb.prjms.bean;

import java.util.Date;

import com.zxb.prjms.bean.Module;
import com.zxb.prjms.bean.Project;

public class Module {
    private Integer id;

    private Integer projectid;

    private String modulename;

    private Integer parentid;

    private Date createtime;

    private Date finishtime;

    private String status;

    private String creatorid;

    private String assignid;

    private String content;
    //联合查询项目
    public Project project;
    //联合查询父模块
    public Module parentModule;
    

    public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Module getParentModule() {
		return parentModule;
	}

	public void setParentModule(Module parentModule) {
		this.parentModule = parentModule;
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

    public String getModulename() {
        return modulename;
    }

    public void setModulename(String modulename) {
        this.modulename = modulename == null ? null : modulename.trim();
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}