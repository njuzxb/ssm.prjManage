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
 * ����Ͳ����йص�����
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
	 * ����ɾ��
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
			event.setEventtype("ɾ������");
			event.setEventcontent("#"+department.getId()+":"+department.getDepartmentname());
			eventController.saveEvent(request, response, session, event);
			return Msg.success();
		}else{
			return Msg.fail().add("error", "��Ȩ�޲��㣡");
		}
	}
	/**
	 * ���²���
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
			event.setEventtype("���²���");
			event.setEventcontent("#"+d.getId()+":"+d.getDepartmentname());
			eventController.saveEvent(request, response, session, event);
			return Msg.success();
		}else{
			return Msg.fail().add("error", "��Ȩ�޲��㣡");
		}
	}
	/**
	 * ����id��ѯ����
	 * @return
	 */
	@RequestMapping(value="/dept/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getDept(@PathVariable("id")Integer id){
		
		Department department = departmentService.getDept(id);
		return Msg.success().add("dept", department);
	}
	/**
	 * �½�����
	 * @return
	 */
	@RequestMapping(value="/dept",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveDept(HttpServletRequest request, HttpServletResponse response,HttpSession session,Department department){
		Employee e = (Employee)session.getAttribute("LOGIN_EMP");
		if(e.getPower()<=1){
			departmentService.saveDept(department);
			Event event = new Event();
			event.setEventtype("�½�����");
			event.setEventcontent("#"+department.getId()+":"+department.getDepartmentname());
			eventController.saveEvent(request, response, session, event);
			return Msg.success();
		}else{
			return Msg.fail().add("error", "��Ȩ�޲��㣡");
		}
	}
	/**
	 * �������в�����Ϣ
	 */
	@RequestMapping("/depts")
	@ResponseBody
	public Msg getDepts(){
		List<Department> list = departmentService.getDepts();
		return Msg.success().add("depts", list);
	}
}
