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
 * ����ģ���CURD
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
	 * ����ɾ��
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
			event.setEventtype("ɾ������");
			event.setEventcontent("#"+task.getId()+":"+task.getTaskname());
			eventController.saveEvent(request, response, session, event);
			return Msg.success();
		}else{
			return Msg.fail().add("error", "��û�и�Ȩ�ޣ�");
		}
	}
	
	/**
	 * ����Ա����
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/task/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Msg updateTask(HttpServletRequest request, HttpServletResponse response,HttpSession session,Task task){
		Task t = taskService.getTask(task.getId());
		Employee e = (Employee)session.getAttribute("LOGIN_EMP");
		if(e.getAccountid().equals( t.getProject().getCreatorid())){
		
			if(task.getStatus()!=null && task.getStatus().equals(new String("�����")))task.setFinishtime(new Date());
			taskService.updateTask(task);
			Event event = new Event();
			event.setEventtype("��������״̬");
			event.setEventcontent("#"+t.getId()+"<"+t.getTaskname()+">:"+task.getStatus());
			eventController.saveEvent(request, response, session, event);
			return Msg.success();
		}else{
			return Msg.fail().add("error", "��û�и�Ȩ�ޣ�");
		}
	}
	/**
	 * ����(Ա��)
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
			if(task.getStatus()!=null && task.getStatus().equals(new String("�����"))){
				Event event = new Event();
				event.setEventtype("�ύ��������");
				event.setEventcontent("#"+t.getId()+"<"+t.getTaskname()+">");
				eventController.saveEvent(request, response, session, event);
			}
			return Msg.success();
		}else{
			return Msg.fail().add("error", "��û�и�Ȩ�ޣ�");
		}
	}
	/**
	 * ָ��
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
			event.setEventtype("ָ������");
			event.setEventcontent("#"+t.getId()+"<"+t.getTaskname()+">��"+task.getAssignid());
			eventController.saveEvent(request, response, session, event);
			return Msg.success();
		}else{
			return Msg.fail().add("error", "��û�и�Ȩ�ޣ�");
		}
	}
	/**
	 * ����id��ѯģ��
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
	 * ���񱣴�
	 * @return
	 */
	@RequestMapping(value="/task",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveTask(HttpServletRequest request, HttpServletResponse response,HttpSession session,Task task){
		Employee e = (Employee)session.getAttribute("LOGIN_EMP");
		if(e.getPower() == 1){
		
			if(task.getStatus()==null)task.setStatus("δ���");
			if(task.getCreatorid()==null){
				Employee emp = (Employee)session.getAttribute("LOGIN_EMP");
				task.setCreatorid(emp.getAccountid());
			}
			if(task.getCreatetime()==null)task.setCreatetime(new Date());;
			taskService.saveTask(task);
			Event event = new Event();
			event.setEventtype("��������");
			event.setEventcontent(" <"+task.getTaskname()+">");
			eventController.saveEvent(request, response, session, event);
				return Msg.success();
		}else{
			return Msg.fail().add("error", "��û�и�Ȩ�ޣ�");
		}
	}
	/**
	 * ��ѯ������Ŀ���ݣ���ҳ��ѯ��
	 * ����Json��
	 * @return
	 */
	@RequestMapping("/tasks")
	@ResponseBody
	public Msg getTasksWithJson(@RequestParam(name="pn",defaultValue="1")Integer pn,String status){
				TaskExample taskExample = new TaskExample();
				Criteria criteria = taskExample.createCriteria();
				if(status!=null){
					if(status.equals("δ���")){
						criteria.andStatusEqualTo("δ���");
					}else if(status.equals("�����")){
						criteria.andStatusEqualTo("�����");
					}else if(status.equals("�����")){
						criteria.andStatusEqualTo("�����");
					}else if(status.equals("���ͨ��")){
						criteria.andStatusEqualTo("���ͨ��");
					}
				}
				//����PageHelper��ҳ���
				//�ڲ�ѯ֮ǰֻ��Ҫ����PageHelper.startPage,����ҳ���ÿһҳ��С
				PageHelper.startPage(pn,5);
				//starPage��������Ĳ�ѯ���Ƿ�ҳ��ѯ
				List<Task> tasks = taskService.getAll(taskExample);
				//ʹ��pageInfo��װ��ѯ�Ժ�Ľ����ֻ�轫PageInfo����ҳ��
				//��װ����ϸ�ķ�ҳ��Ϣ��������ѯ����������,����������ʾ��ҳ��
				PageInfo page = new PageInfo(tasks,5);
				return Msg.success().add("pageInfo",page);
	}
	/**
	 * ��ѯ�ҵ�������Ŀ���ݣ���ҳ��ѯ��
	 * ����Json��
	 * @return
	 */
	@RequestMapping("/myTasks")
	@ResponseBody
	public Msg getMyTasksWithJson(@RequestParam(name="pn",defaultValue="1")Integer pn,String status,HttpSession session){
				TaskExample taskExample = new TaskExample();
				Employee emp = (Employee)session.getAttribute("LOGIN_EMP");
				
				if(status!=null){
					if(status.equals("δ���")){
						taskExample.or().andStatusEqualTo("δ���").andAssignidEqualTo(emp.getAccountid());
						//taskExample.or().andStatusEqualTo("δ���").andCreatoridEqualTo(emp.getAccountid());
					}else if(status.equals("�����")){
						taskExample.or().andStatusEqualTo("�����").andAssignidEqualTo(emp.getAccountid());
						//taskExample.or().andStatusEqualTo("�����").andCreatoridEqualTo(emp.getAccountid());
					}else if(status.equals("���ͨ��")){
						taskExample.or().andStatusEqualTo("���ͨ��").andAssignidEqualTo(emp.getAccountid());
						//taskExample.or().andStatusEqualTo("�����").andCreatoridEqualTo(emp.getAccountid());
					}
				}else{
					taskExample.or().andAssignidEqualTo(emp.getAccountid());
					//taskExample.or().andCreatoridEqualTo(emp.getAccountid());
				}
				taskExample.or().andStatusEqualTo("���ͨ��");
				//����PageHelper��ҳ���
				//�ڲ�ѯ֮ǰֻ��Ҫ����PageHelper.startPage,����ҳ���ÿһҳ��С
				PageHelper.startPage(pn,5);
				//starPage��������Ĳ�ѯ���Ƿ�ҳ��ѯ
				List<Task> tasks = taskService.getAll(taskExample);
				//ʹ��pageInfo��װ��ѯ�Ժ�Ľ����ֻ�轫PageInfo����ҳ��
				//��װ����ϸ�ķ�ҳ��Ϣ��������ѯ����������,����������ʾ��ҳ��
				PageInfo page = new PageInfo(tasks,5);
				return Msg.success().add("pageInfo",page);
	}
}
