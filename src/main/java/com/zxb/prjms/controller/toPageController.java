package com.zxb.prjms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ҳ����ת������
 * @author Think
 *
 */
@Controller
public class toPageController {
	
	/**
	 * ��ת��MyBugListҳ�棬��ҳ
	 */
	@RequestMapping("/My/MyBugList")	
	public String toMyBugList(){
		return "MyBugList";
	}
	/**
	 * ��ת��MyTaskListҳ�棬��ҳ
	 */
	@RequestMapping("/My/MyTaskList")	
	public String toMyTaskList(){
		return "MyTaskList";
	}
	/**
	 * ��ת��MyModuleListҳ��
	 */
	@RequestMapping("/My/MyModList")	
	public String toMyModList(){
		return "MyModuleList";
	}
	/**
	 * ��ת��MyListҳ�棬��ҳ
	 */
	@RequestMapping("/My/MyList")	
	public String toMyList(){
		return "MyList";
	}
	/**
	 * ��ת��TaskListҳ�棬��������
	 */
	@RequestMapping("/Prj/EventList")	
	public String toEventList(){
		return "EventList";
	}
	/**
	 * ��ת��TaskListҳ�棬��������
	 */
	@RequestMapping("/Prj/TaskList")	
	public String toTaskList(){
		return "TaskList";
	}
	/**
	 * ��ת��ModuleListҳ�棬ģ������
	 */
	@RequestMapping("/Prj/ModList")	
	public String toModList(){
		return "ModuleList";
	}
	/**
	 * ��ת��ProjectListҳ�棬��Ŀ����
	 */
	@RequestMapping("/Prj/PrjList")	
	public String toPrjList(){
		return "ProjectList";
	}
	/**
	 * ��ת��BugListҳ�棬���в��Թ���
	 * @return
	 */
	@RequestMapping("/Test/BugList")	
	public String toBugList(){
		return "BugList";
	}
	/**
	 * ��ת��EmployeeListҳ�棬����Ա������
	 * @return
	 */
	@RequestMapping("/Org/EmpList")	
	public String toEmpList(){
		return "EmployeeList";
	}
	/**
	 * ��ת��DepartmentListҳ�棬����Ա������
	 * @return
	 */
	@RequestMapping("/Org/DeptList")	
	public String toDeptList(){
		return "DepartmentList";
	}
}
