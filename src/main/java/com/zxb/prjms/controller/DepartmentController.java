package com.zxb.prjms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zxb.prjms.bean.Department;
import com.zxb.prjms.bean.Employee;
import com.zxb.prjms.bean.Event;
import com.zxb.prjms.bean.Msg;
import com.zxb.prjms.service.DepartmentService;

/**
 * 处理和部门有关的请求
 * @author Think
 *
 */
@Controller
public class DepartmentController {
	
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	EventController eventController;
	
	/**
	 * 部门删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/dept/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteEmpById(@PathVariable("id")Integer id,HttpServletRequest request, HttpServletResponse response,HttpSession session){
		Employee e = (Employee)session.getAttribute("LOGIN_EMP");
		if(e.getPower()<=1){
			Department department = departmentService.getDept(id);
			departmentService.deleteDept(id);
			Event event = new Event();
			event.setEventtype("删除部门");
			event.setEventcontent("#"+department.getId()+":"+department.getDepartmentname());
			eventController.saveEvent(request, response, session, event);
			return Msg.success();
		}else{
			return Msg.fail().add("error", "您权限不足！");
		}
	}
	/**
	 * 更新部门
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/dept/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Msg updateDept(HttpServletRequest request, HttpServletResponse response,HttpSession session,Department department){
		Employee e = (Employee)session.getAttribute("LOGIN_EMP");
		if(e.getPower()<=1){	
			departmentService.updateDept(department);
			Department d = departmentService.getDept(department.getId());
			Event event = new Event();
			event.setEventtype("更新部门");
			event.setEventcontent("#"+d.getId()+":"+d.getDepartmentname());
			eventController.saveEvent(request, response, session, event);
			return Msg.success();
		}else{
			return Msg.fail().add("error", "您权限不足！");
		}
	}
	/**
	 * 根据id查询部门
	 * @return
	 */
	@RequestMapping(value="/dept/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getDept(@PathVariable("id")Integer id){
		
		Department department = departmentService.getDept(id);
		return Msg.success().add("dept", department);
	}
	/**
	 * 新建部门
	 * @return
	 */
	@RequestMapping(value="/dept",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveDept(HttpServletRequest request, HttpServletResponse response,HttpSession session,Department department){
		Employee e = (Employee)session.getAttribute("LOGIN_EMP");
		if(e.getPower()<=1){
			departmentService.saveDept(department);
			Event event = new Event();
			event.setEventtype("新建部门");
			event.setEventcontent("#"+department.getId()+":"+department.getDepartmentname());
			eventController.saveEvent(request, response, session, event);
			return Msg.success();
		}else{
			return Msg.fail().add("error", "您权限不足！");
		}
	}
	/**
	 * 返回所有部门信息
	 */
	@RequestMapping("/depts")
	@ResponseBody
	public Msg getDepts(){
		List<Department> list = departmentService.getDepts();
		return Msg.success().add("depts", list);
	}
}
