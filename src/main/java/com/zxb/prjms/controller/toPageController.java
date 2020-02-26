package com.zxb.prjms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转控制器
 * @author Think
 *
 */
@Controller
public class toPageController {
	
	/**
	 * 跳转到MyBugList页面，首页
	 */
	@RequestMapping("/My/MyBugList")	
	public String toMyBugList(){
		return "MyBugList";
	}
	/**
	 * 跳转到MyTaskList页面，首页
	 */
	@RequestMapping("/My/MyTaskList")	
	public String toMyTaskList(){
		return "MyTaskList";
	}
	/**
	 * 跳转到MyModuleList页面
	 */
	@RequestMapping("/My/MyModList")	
	public String toMyModList(){
		return "MyModuleList";
	}
	/**
	 * 跳转到MyList页面，首页
	 */
	@RequestMapping("/My/MyList")	
	public String toMyList(){
		return "MyList";
	}
	/**
	 * 跳转到TaskList页面，任务总览
	 */
	@RequestMapping("/Prj/EventList")	
	public String toEventList(){
		return "EventList";
	}
	/**
	 * 跳转到TaskList页面，任务总览
	 */
	@RequestMapping("/Prj/TaskList")	
	public String toTaskList(){
		return "TaskList";
	}
	/**
	 * 跳转到ModuleList页面，模块总览
	 */
	@RequestMapping("/Prj/ModList")	
	public String toModList(){
		return "ModuleList";
	}
	/**
	 * 跳转到ProjectList页面，项目总览
	 */
	@RequestMapping("/Prj/PrjList")	
	public String toPrjList(){
		return "ProjectList";
	}
	/**
	 * 跳转到BugList页面，进行测试管理
	 * @return
	 */
	@RequestMapping("/Test/BugList")	
	public String toBugList(){
		return "BugList";
	}
	/**
	 * 跳转到EmployeeList页面，进行员工管理
	 * @return
	 */
	@RequestMapping("/Org/EmpList")	
	public String toEmpList(){
		return "EmployeeList";
	}
	/**
	 * 跳转到DepartmentList页面，进行员工管理
	 * @return
	 */
	@RequestMapping("/Org/DeptList")	
	public String toDeptList(){
		return "DepartmentList";
	}
}
