package com.zxb.prjms.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxb.prjms.bean.Msg;
import com.zxb.prjms.service.EmployeeService;
import com.zxb.prjms.bean.Employee;
import com.zxb.prjms.bean.EmployeeExample;
import com.zxb.prjms.bean.Event;
/**
 * ����Ա����CURD
 * @author root
 *
 */
@Controller
public class EmployeeController {
	
	
	@Autowired
	EmployeeService employeeService;
	@Autowired
	EventController eventController;
	
	/**
	 * Ա��ɾ��
	 * @param accountid
	 * @return
	 */
	@RequestMapping(value="/emp/{accountid}",method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteEmpById(@PathVariable("accountid")String accountid,HttpServletRequest request, HttpServletResponse response,HttpSession session){
		Employee e = (Employee)session.getAttribute("LOGIN_EMP");
		if(e.getPower()<=2){
			Employee employee = employeeService.getEmp(accountid);
			employeeService.deleteEmp(accountid);
			Event event = new Event();
			event.setEventtype("ɾ��Ա��");
			event.setEventcontent(employee.getAccountid()+" name:"+employee.getEname());
			eventController.saveEvent(request, response, session, event);
			return Msg.success();
		}
		else{
			return Msg.fail().add("error", "��û��Ȩ�ޣ�");
		}
	}
	
	/**
	 * ���ֱ�ӷ���ajax=put��ʽ������
	 * employee�����װ����
	 * ԭ��
	 * tomcat���⣺���������е����ݣ���װһ��map
	 * 				reques.getParameter("")�ͻ�����map��ȡֵ
	 * 				SpringMVC��װPOJO�����ʱ�򣬻��POJO��ÿ�����Ե�ֵ��reques.getParameter("")
	 * ajax����PUT����������Ѫ����PUT�����������е����ݣ�reques.getParameter("")�ò���
	 * tomcat������PUT�Ͳ����װ��������Ϊmap��ֻ��POST��ʽ������ŷ�װ����Ϊmap
	 * Ҫ��֧��ֱ�ӷ���PUT֮������󣬻����װ�������е�����
	 * ������HttpPutFormContentFilter�������ǽ��������е����ݽ�����װ��һ��map
	 * request�����°�װ��reques.getParameter()����д���ͻ���Լ���װ��map��ȡ����
	 * Ա������
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/emp/{accountid}",method=RequestMethod.PUT)
	@ResponseBody
	public Msg updateEmp(HttpServletRequest request, HttpServletResponse response,HttpSession session,Employee employee){
		Employee e = (Employee)session.getAttribute("LOGIN_EMP");
		if(e.getPower()<=2){
			employeeService.updateEmp(employee);
			Event event = new Event();
			event.setEventtype("����Ա��");
			event.setEventcontent(employee.getAccountid()+" name:"+ employee.getEname());
			eventController.saveEvent(request, response, session, event);
			return Msg.success();
		}
		else{
			return Msg.fail().add("error", "��û��Ȩ�ޣ�");
		}
	}
	
	/**
	 * ����id��ѯԱ��
	 * @param accountid
	 * @return
	 */
	@RequestMapping(value="/emp/{accountid}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("accountid")String accountid,HttpSession session){
		Employee e = (Employee)session.getAttribute("LOGIN_EMP");
		if(e.getPower()<=2){
			Employee employee = employeeService.getEmp(accountid);
			return Msg.success().add("emp", employee);
		}
		else{
			return Msg.fail().add("error", "��û��Ȩ�ޣ�");
		}
	}
	/**
	 * ���ݲ��Ų�ѯԱ��
	 * @param accountid
	 * @return
	 */
	@RequestMapping(value="/empWherePrj/{departmentid}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getEmpWherePrj(@PathVariable("departmentid")Integer departmentid){
		EmployeeExample employeeExample = new EmployeeExample();
		employeeExample.createCriteria().andDepartmentidEqualTo(departmentid);
		List<Employee> emps = employeeService.getEmpWherePrj(employeeExample);
		return Msg.success().add("emps", emps);
	}
	/**
	 * Ա������
	 * @return
	 */
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(HttpServletRequest request, HttpServletResponse response,HttpSession session,Employee employee){
		Employee e = (Employee)session.getAttribute("LOGIN_EMP");
		if(e.getPower()<=2){
			boolean b = employeeService.checkEmp(employee.getAccountid(),null);
			if(b){
				if(employee.getPassword()==null)employee.setPassword("123");
				employeeService.saveEmp(employee);
				Event event = new Event();
				event.setEventtype("����Ա��");
				event.setEventcontent(employee.getAccountid()+" name:"+employee.getEname());
				eventController.saveEvent(request, response, session, event);
				return Msg.success();
			}else{
				/*Map<String,Object> map = new HashMap<>();
				map.put(employee.getAccountid(), "�˺�ID�Ѵ��ڣ�");*/
				return Msg.fail().add("error", "�˺�ID�Ѵ��ڣ�");
			}
		}else{
			return Msg.fail().add("error", "��û��Ȩ�ޣ�");
		}
	}
	/**
	 * ��ѯԱ�����ݣ���ҳ��ѯ��
	 * ����Json��
	 * @return
	 */
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(name="pn",defaultValue="1")Integer pn){
				//����PageHelper��ҳ���
				//�ڲ�ѯ֮ǰֻ��Ҫ����PageHelper.startPage,����ҳ���ÿһҳ��С
				PageHelper.startPage(pn,5);
				//starPage��������Ĳ�ѯ���Ƿ�ҳ��ѯ
				List<Employee> emps = employeeService.getAll();
				//ʹ��pageInfo��װ��ѯ�Ժ�Ľ����ֻ�轫PageInfo����ҳ��
				//��װ����ϸ�ķ�ҳ��Ϣ��������ѯ����������,����������ʾ��ҳ��
				PageInfo page = new PageInfo(emps,5);
				return Msg.success().add("pageInfo",page);
	}
	/**
	 * ��¼
	 * @param request
	 * @param response
	 * @param session
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Msg empLogin(HttpServletRequest request, HttpServletResponse response,HttpSession session,Employee employee){
		boolean b = employeeService.checkEmp(employee.getAccountid(),employee.getPassword());
		if(b){
			//�û�������
			return Msg.fail();
		}else{
			//�û�����,����session,��ת
			employee.setLastlogin(new Date());
			employeeService.updateEmp(employee);
			Employee emp = employeeService.getEmp(employee.getAccountid());
			request.getSession().setAttribute("LOGIN_EMP", emp);
			session.setAttribute(emp.getAccountid(), emp);//����websocket
			System.out.println("����websocket---------------");
            request.getSession().setMaxInactiveInterval(30*60);//session��ʱ30min
			return Msg.success();
			
		}
		
	}
	/**
	 * �˳���¼
	 * @throws Exception 
	 */
	@RequestMapping("/outLogin")	
	public void outLogin(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession(false);
		if(session.getAttribute("LOGIN_EMP")==null){
			response.sendRedirect(request.getContextPath());
		}
		session.removeAttribute("LOGIN_EMP");
		response.sendRedirect(request.getContextPath());
	}
	

}
