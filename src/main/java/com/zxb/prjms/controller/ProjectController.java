package com.zxb.prjms.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zxb.prjms.bean.Employee;
import com.zxb.prjms.bean.Event;
import com.zxb.prjms.bean.Module;
import com.zxb.prjms.bean.ModuleExample;
import com.zxb.prjms.bean.Msg;
import com.zxb.prjms.bean.Project;
import com.zxb.prjms.bean.ProjectExample;
import com.zxb.prjms.bean.ProjectExample.Criteria;
import com.zxb.prjms.service.ProjectService;
/**
 * 处理项目的CURD
 * @author root
 *
 */
@Controller
public class ProjectController {
	
	
	@Autowired
	ProjectService projectService;
	@Autowired
	EventController eventController;
	/**
	 * 项目删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/prj/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deletePrjById(@PathVariable("id")Integer id,HttpServletRequest request, HttpServletResponse response,HttpSession session){
		Project project = projectService.getPrj(id);
		Employee e = (Employee)session.getAttribute("LOGIN_EMP");
		if(e.getAccountid().equals(project.getCreatorid())){
			Event event = new Event();
			event.setEventtype("删除项目");
			event.setEventcontent("#"+project.getId()+":"+project.getProjectname());
			projectService.deletePrj(id);
			eventController.saveEvent(request, response, session, event);
			return Msg.success();
		}else{
			return Msg.fail().add("error", "您的权限不足！");
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
	 * 项目更新
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/prj/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Msg updatePrj(HttpServletRequest request, HttpServletResponse response,HttpSession session,Project project){
		Project prj = projectService.getPrj(project.getId());
		Employee e = (Employee)session.getAttribute("LOGIN_EMP");
		if(e.getAccountid().equals(prj.getCreatorid())){
			if(project.getStatus()!=null && project.getStatus().equals(new String("已完成"))){
				project.setFinishtime(new Date());
			}
			projectService.updatePrj(project);
			Event event = new Event();
			event.setEventtype("更新项目状态");
			event.setEventcontent("#"+prj.getId()+"<"+prj.getProjectname()+">:"+project.getStatus());
			eventController.saveEvent(request, response, session, event);
			return Msg.success();
		}else{
			return Msg.fail().add("error", "您的权限不足！");
		}
	}
	/**
	 * 项目更新（员工）
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/myprj/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Msg updateMyPrj(HttpServletRequest request, HttpServletResponse response,HttpSession session,Project project){
		Project prj = projectService.getPrj(project.getId());
		Employee e = (Employee)session.getAttribute("LOGIN_EMP");
		if(e.getAccountid().equals(prj.getHeaderid())){
			projectService.updatePrj(project);
			if(project.getStatus()!=null && project.getStatus().equals(new String("待审核"))){
				Event event = new Event();
				event.setEventtype("提交待审项目");
				event.setEventcontent("#"+prj.getId()+"<"+prj.getProjectname()+">");
				eventController.saveEvent(request, response, session, event);
			}
			
			return Msg.success();
		}else{
			return Msg.fail().add("error", "您的权限不足！");
		}
	}
	/**
	 * 指派
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/AssignPrj/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Msg assignPrj(HttpServletRequest request, HttpServletResponse response,HttpSession session,Project project){
		Project prj = projectService.getPrj(project.getId());
		Employee e = (Employee)session.getAttribute("LOGIN_EMP");
		if(e.getAccountid().equals( prj.getCreatorid())){
			projectService.updatePrj(project);
			Event event = new Event();
			event.setEventtype("指派项目");
			event.setEventcontent("#"+prj.getId()+"<"+prj.getProjectname()+">给"+project.getHeaderid());
			eventController.saveEvent(request, response, session, event);
			return Msg.success();
		}
		else{
			return Msg.fail().add("error", "您权限不足！");
		}
	}
	
	/**
	 * 根据id查询项目
	 * @param accountid
	 * @return
	 */
	@RequestMapping(value="/prj/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getPrj(@PathVariable("id")Integer id){
		
		Project project = projectService.getPrj(id);
		return Msg.success().add("prj", project);
	}
	/**
	 * 保存
	 * @return
	 */
	@RequestMapping(value="/prj",method=RequestMethod.POST)
	@ResponseBody
	public Msg savePrj(HttpServletRequest request, HttpServletResponse response,HttpSession session,Project project){
		Employee e = (Employee)session.getAttribute("LOGIN_EMP");
		if(e.getPower() == 1){
			if(project.getStatus()==null)project.setStatus("未完成");
			if(project.getCreatorid()==null){
				Employee emp = (Employee)session.getAttribute("LOGIN_EMP");
				project.setCreatorid(emp.getAccountid());
			}
			if(project.getCreatetime()==null)project.setCreatetime(new Date());;
				projectService.savePrj(project);
				Event event = new Event();
				event.setEventtype("新增项目");
				event.setEventcontent(" <"+project.getProjectname()+">");
				eventController.saveEvent(request, response, session, event);
				return Msg.success();
		}else{
			return Msg.fail().add("error", "您的权限不足！");
		}
	}
	/**
	 * 查询所有项目数据（分页查询）
	 * 导入Json包
	 * @return
	 */
	@RequestMapping("/prjs")
	@ResponseBody
	public Msg getPrjsWithJson(@RequestParam(name="pn",defaultValue="1")Integer pn,String status){
				ProjectExample projectExample = new ProjectExample();
				Criteria criteria = projectExample.createCriteria();
				//System.out.println(status);
				if(status!=null){
					if(status.equals("未完成")){
						criteria.andStatusEqualTo("未完成");
					}else if(status.equals("已完成")){
						criteria.andStatusEqualTo("已完成");
					}else if(status.equals("待审核")){
						criteria.andStatusEqualTo("待审核");
					}else if(status.equals("审核通过")){
						criteria.andStatusEqualTo("审核通过");
					}
				}
				//引入PageHelper分页插件
				//在查询之前只需要调用PageHelper.startPage,传入页码和每一页大小
				PageHelper.startPage(pn,5);
				//starPage后面紧跟的查询就是分页查询
				List<Project> prjs = projectService.getAll(projectExample);
				//使用pageInfo包装查询以后的结果，只需将PageInfo交给页面
				//封装了详细的分页信息，包括查询出来的数据,传入连续显示的页数				
				PageInfo page = new PageInfo(prjs,5);
				return Msg.success().add("pageInfo",page);
	}
	/**
	 * 查询我的项目数据（分页查询）
	 * 导入Json包
	 * @return
	 */
	@RequestMapping("/myPrjs")
	@ResponseBody
	public Msg getMyPrjsWithJson(@RequestParam(name="pn",defaultValue="1")Integer pn,String status,HttpServletRequest request, HttpServletResponse response,HttpSession session){
		ProjectExample projectExample = new ProjectExample();
		Employee emp = (Employee)session.getAttribute("LOGIN_EMP");
		if(status!=null){
			if(status.equals("未完成")){
				projectExample.or().andStatusEqualTo("未完成").andHeaderidEqualTo(emp.getAccountid());
				//projectExample.or().andStatusEqualTo("未完成").andCreatoridEqualTo(emp.getAccountid());
			}else if(status.equals("待审核")){
				projectExample.or().andStatusEqualTo("待审核").andHeaderidEqualTo(emp.getAccountid());
				//projectExample.or().andStatusEqualTo("待审核").andCreatoridEqualTo(emp.getAccountid());
			}else if(status.equals("审核通过")){
				projectExample.or().andStatusEqualTo("审核通过").andHeaderidEqualTo(emp.getAccountid());
				//projectExample.or().andStatusEqualTo("审核通过").andCreatoridEqualTo(emp.getAccountid());
			}
		}else{
			projectExample.or().andHeaderidEqualTo(emp.getAccountid());
			//projectExample.or().andCreatoridEqualTo(emp.getAccountid());
		}
		projectExample.or().andStatusEqualTo("审核通过");
		//引入PageHelper分页插件
		//在查询之前只需要调用PageHelper.startPage,传入页码和每一页大小
		PageHelper.startPage(pn,5);
		//starPage后面紧跟的查询就是分页查询
		List<Project> prjs = projectService.getAll(projectExample);
		//使用pageInfo包装查询以后的结果，只需将PageInfo交给页面
		//封装了详细的分页信息，包括查询出来的数据,传入连续显示的页数				
		PageInfo page = new PageInfo(prjs,5);
		return Msg.success().add("pageInfo",page);
	}
}
