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
 * 处理员工的CURD
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
	 * 员工删除
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
			event.setEventtype("删除员工");
			event.setEventcontent(employee.getAccountid()+" name:"+employee.getEname());
			eventController.saveEvent(request, response, session, event);
			return Msg.success();
		}
		else{
			return Msg.fail().add("error", "您没有权限！");
		}
	}
	
	/**
	 * 如果直接发送ajax=put形式的请求
	 * employee对象封装不上
	 * 原因：
	 * tomcat问题：将请求体中的数据，封装一个map
	 * 				reques.getParameter("")就会从这个map中取值
	 * 				SpringMVC封装POJO对象的时候，会把POJO中每个属性的值，reques.getParameter("")
	 * ajax发送PUT请求引发的血案，PUT请求，请求体中的数据，reques.getParameter("")拿不到
	 * tomcat看到是PUT就不会封装请求体中为map，只有POST形式的请求才封装请求为map
	 * 要能支持直接发送PUT之类的请求，还需封装请求体中的数据
	 * 配置上HttpPutFormContentFilter，作用是将请求体中的数据解析包装成一个map
	 * request被重新包装，reques.getParameter()被重写，就会从自己封装的map中取数据
	 * 员工更新
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
			event.setEventtype("更新员工");
			event.setEventcontent(employee.getAccountid()+" name:"+ employee.getEname());
			eventController.saveEvent(request, response, session, event);
			return Msg.success();
		}
		else{
			return Msg.fail().add("error", "您没有权限！");
		}
	}
	
	/**
	 * 根据id查询员工
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
			return Msg.fail().add("error", "您没有权限！");
		}
	}
	/**
	 * 根据部门查询员工
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
	 * 员工保存
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
				event.setEventtype("新增员工");
				event.setEventcontent(employee.getAccountid()+" name:"+employee.getEname());
				eventController.saveEvent(request, response, session, event);
				return Msg.success();
			}else{
				/*Map<String,Object> map = new HashMap<>();
				map.put(employee.getAccountid(), "账号ID已存在！");*/
				return Msg.fail().add("error", "账号ID已存在！");
			}
		}else{
			return Msg.fail().add("error", "您没有权限！");
		}
	}
	/**
	 * 查询员工数据（分页查询）
	 * 导入Json包
	 * @return
	 */
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(name="pn",defaultValue="1")Integer pn){
				//引入PageHelper分页插件
				//在查询之前只需要调用PageHelper.startPage,传入页码和每一页大小
				PageHelper.startPage(pn,5);
				//starPage后面紧跟的查询就是分页查询
				List<Employee> emps = employeeService.getAll();
				//使用pageInfo包装查询以后的结果，只需将PageInfo交给页面
				//封装了详细的分页信息，包括查询出来的数据,传入连续显示的页数
				PageInfo page = new PageInfo(emps,5);
				return Msg.success().add("pageInfo",page);
	}
	/**
	 * 登录
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
			//用户不存在
			return Msg.fail();
		}else{
			//用户存在,存入session,跳转
			employee.setLastlogin(new Date());
			employeeService.updateEmp(employee);
			Employee emp = employeeService.getEmp(employee.getAccountid());
			request.getSession().setAttribute("LOGIN_EMP", emp);
			session.setAttribute(emp.getAccountid(), emp);//连接websocket
			System.out.println("连接websocket---------------");
            request.getSession().setMaxInactiveInterval(30*60);//session超时30min
			return Msg.success();
			
		}
		
	}
	/**
	 * 退出登录
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
