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
import com.zxb.prjms.bean.Msg;
import com.zxb.prjms.bean.Task;
import com.zxb.prjms.bean.TaskExample;
import com.zxb.prjms.bean.TaskExample.Criteria;
import com.zxb.prjms.service.TaskService;
/**
 * 处理模块的CURD
 * @author root
 *
 */
@Controller
public class TaskController {
	
	
	@Autowired
	TaskService taskService;
	@Autowired
	EventController eventController;
	
	/**
	 * 任务删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/task/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteTaskById(@PathVariable("id")Integer id,HttpServletRequest request, HttpServletResponse response,HttpSession session){
		Task task = taskService.getTask(id);
		Employee e = (Employee)session.getAttribute("LOGIN_EMP");
		if(e.getAccountid().equals(task.getCreatorid())){
			
			taskService.deleteTask(id);
			Event event = new Event();
			event.setEventtype("删除任务");
			event.setEventcontent("#"+task.getId()+":"+task.getTaskname());
			eventController.saveEvent(request, response, session, event);
			return Msg.success();
		}else{
			return Msg.fail().add("error", "您没有该权限！");
		}
	}
	
	/**
	 * 管理员更新
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/task/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Msg updateTask(HttpServletRequest request, HttpServletResponse response,HttpSession session,Task task){
		Task t = taskService.getTask(task.getId());
		Employee e = (Employee)session.getAttribute("LOGIN_EMP");
		if(e.getAccountid().equals( t.getProject().getCreatorid())){
		
			if(task.getStatus()!=null && task.getStatus().equals(new String("已完成")))task.setFinishtime(new Date());
			taskService.updateTask(task);
			Event event = new Event();
			event.setEventtype("更新任务状态");
			event.setEventcontent("#"+t.getId()+"<"+t.getTaskname()+">:"+task.getStatus());
			eventController.saveEvent(request, response, session, event);
			return Msg.success();
		}else{
			return Msg.fail().add("error", "您没有该权限！");
		}
	}
	/**
	 * 更新(员工)
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/mytask/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Msg updateMyTask(HttpServletRequest request, HttpServletResponse response,HttpSession session,Task task){
		Task t = taskService.getTask(task.getId());
		Employee e = (Employee)session.getAttribute("LOGIN_EMP");
		if(e.getAccountid().equals(t.getAssignid())){
			taskService.updateTask(task);
			if(task.getStatus()!=null && task.getStatus().equals(new String("待审核"))){
				Event event = new Event();
				event.setEventtype("提交待审任务");
				event.setEventcontent("#"+t.getId()+"<"+t.getTaskname()+">");
				eventController.saveEvent(request, response, session, event);
			}
			return Msg.success();
		}else{
			return Msg.fail().add("error", "您没有该权限！");
		}
	}
	/**
	 * 指派
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/assigntask/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Msg updateAssignTask(HttpServletRequest request, HttpServletResponse response,HttpSession session,Task task){
		Task t = taskService.getTask(task.getId());
		Employee e = (Employee)session.getAttribute("LOGIN_EMP");
		if(e.getAccountid().equals( t.getProject().getCreatorid())){
			taskService.updateTask(task);
			Event event = new Event();
			event.setEventtype("指派任务");
			event.setEventcontent("#"+t.getId()+"<"+t.getTaskname()+">给"+task.getAssignid());
			eventController.saveEvent(request, response, session, event);
			return Msg.success();
		}else{
			return Msg.fail().add("error", "您没有该权限！");
		}
	}
	/**
	 * 根据id查询模块
	 * @param accountid
	 * @return
	 */
	@RequestMapping(value="/task/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getTask(@PathVariable("id")Integer id){
		
		Task task = taskService.getTask(id);
		return Msg.success().add("task", task);
	}
	/**
	 * 任务保存
	 * @return
	 */
	@RequestMapping(value="/task",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveTask(HttpServletRequest request, HttpServletResponse response,HttpSession session,Task task){
		Employee e = (Employee)session.getAttribute("LOGIN_EMP");
		if(e.getPower() == 1){
		
			if(task.getStatus()==null)task.setStatus("未完成");
			if(task.getCreatorid()==null){
				Employee emp = (Employee)session.getAttribute("LOGIN_EMP");
				task.setCreatorid(emp.getAccountid());
			}
			if(task.getCreatetime()==null)task.setCreatetime(new Date());;
			taskService.saveTask(task);
			Event event = new Event();
			event.setEventtype("新增任务");
			event.setEventcontent(" <"+task.getTaskname()+">");
			eventController.saveEvent(request, response, session, event);
				return Msg.success();
		}else{
			return Msg.fail().add("error", "您没有该权限！");
		}
	}
	/**
	 * 查询所有项目数据（分页查询）
	 * 导入Json包
	 * @return
	 */
	@RequestMapping("/tasks")
	@ResponseBody
	public Msg getTasksWithJson(@RequestParam(name="pn",defaultValue="1")Integer pn,String status){
				TaskExample taskExample = new TaskExample();
				Criteria criteria = taskExample.createCriteria();
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
				List<Task> tasks = taskService.getAll(taskExample);
				//使用pageInfo包装查询以后的结果，只需将PageInfo交给页面
				//封装了详细的分页信息，包括查询出来的数据,传入连续显示的页数
				PageInfo page = new PageInfo(tasks,5);
				return Msg.success().add("pageInfo",page);
	}
	/**
	 * 查询我的所有项目数据（分页查询）
	 * 导入Json包
	 * @return
	 */
	@RequestMapping("/myTasks")
	@ResponseBody
	public Msg getMyTasksWithJson(@RequestParam(name="pn",defaultValue="1")Integer pn,String status,HttpSession session){
				TaskExample taskExample = new TaskExample();
				Employee emp = (Employee)session.getAttribute("LOGIN_EMP");
				
				if(status!=null){
					if(status.equals("未完成")){
						taskExample.or().andStatusEqualTo("未完成").andAssignidEqualTo(emp.getAccountid());
						//taskExample.or().andStatusEqualTo("未完成").andCreatoridEqualTo(emp.getAccountid());
					}else if(status.equals("待审核")){
						taskExample.or().andStatusEqualTo("待审核").andAssignidEqualTo(emp.getAccountid());
						//taskExample.or().andStatusEqualTo("已完成").andCreatoridEqualTo(emp.getAccountid());
					}else if(status.equals("审核通过")){
						taskExample.or().andStatusEqualTo("审核通过").andAssignidEqualTo(emp.getAccountid());
						//taskExample.or().andStatusEqualTo("已完成").andCreatoridEqualTo(emp.getAccountid());
					}
				}else{
					taskExample.or().andAssignidEqualTo(emp.getAccountid());
					//taskExample.or().andCreatoridEqualTo(emp.getAccountid());
				}
				taskExample.or().andStatusEqualTo("审核通过");
				//引入PageHelper分页插件
				//在查询之前只需要调用PageHelper.startPage,传入页码和每一页大小
				PageHelper.startPage(pn,5);
				//starPage后面紧跟的查询就是分页查询
				List<Task> tasks = taskService.getAll(taskExample);
				//使用pageInfo包装查询以后的结果，只需将PageInfo交给页面
				//封装了详细的分页信息，包括查询出来的数据,传入连续显示的页数
				PageInfo page = new PageInfo(tasks,5);
				return Msg.success().add("pageInfo",page);
	}
}
