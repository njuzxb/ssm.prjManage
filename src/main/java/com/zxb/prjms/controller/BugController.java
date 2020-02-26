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
import com.zxb.prjms.bean.Bug;
import com.zxb.prjms.bean.BugExample;
import com.zxb.prjms.bean.Employee;
import com.zxb.prjms.bean.Event;
import com.zxb.prjms.bean.Module;
import com.zxb.prjms.bean.ModuleExample;
import com.zxb.prjms.bean.Msg;
import com.zxb.prjms.bean.BugExample.Criteria;
import com.zxb.prjms.service.BugService;
import com.zxb.prjms.service.ModuleService;
/**
 * ����BUG��CURD
 * @author root
 *
 */
@Controller
public class BugController {
	
	
	@Autowired
	BugService bugService;
	@Autowired
	EventController eventController;
	
	/**
	 * ɾ��
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/bug/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteBugById(@PathVariable("id")Integer id,HttpServletRequest request, HttpServletResponse response,HttpSession session){
		Bug bug = bugService.getBug(id);
		Employee e = (Employee)session.getAttribute("LOGIN_EMP");
		if(e.getAccountid().equals(bug.getCreatorid())){
		
			bugService.deleteBug(id);
			Event event = new Event();
			event.setEventtype("ɾ��BUG");
			event.setEventcontent("#"+bug.getId()+":"+bug.getBughead());
			eventController.saveEvent(request, response, session, event);
			return Msg.success();
		}else{
			return Msg.fail().add("error", "��û�и�Ȩ�ޣ�");
		}
	}
	
	/**
	 * ����BUG
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/bug/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Msg updateBug(HttpServletRequest request, HttpServletResponse response,HttpSession session,Bug bug){
		Bug b = bugService.getBug(bug.getId());
		Employee e = (Employee)session.getAttribute("LOGIN_EMP");
		if(e.getAccountid().equals( b.getProject().getCreatorid())){
		
			if(bug.getStatus()!=null && bug.getStatus().equals(new String("�����")))bug.setFinishtime(new Date());
			bugService.updateBug(bug);
			Event event = new Event();
			event.setEventtype("����BUG״̬");
			event.setEventcontent("#"+b.getId()+"<"+b.getBughead()+">"+bug.getStatus());
			eventController.saveEvent(request, response, session, event);
			return Msg.success();
		}else{
			return Msg.fail().add("error", "��û�и�Ȩ�ޣ�");
		}
	}
	/**
	 * ����BUG(Ա��)
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/mybug/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Msg updateMyBug(HttpServletRequest request, HttpServletResponse response,HttpSession session,Bug bug){
		Bug b = bugService.getBug(bug.getId());
		Employee e = (Employee)session.getAttribute("LOGIN_EMP");
		if(e.getAccountid().equals(b.getAssignid())){
			bugService.updateBug(bug);
			if(bug.getStatus()!=null && bug.getStatus().equals(new String("�����"))){
				Event event = new Event();
				event.setEventtype("�ύ����BUG");
				event.setEventcontent("#"+b.getId()+"<"+b.getBughead()+">");
				eventController.saveEvent(request, response, session, event);
			}
			return Msg.success();
		}else{
			return Msg.fail().add("error", "��û�и�Ȩ�ޣ�");
		}
	}
	/**
	 * ָ��BUG
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/assignbug/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Msg updateAssignBug(HttpServletRequest request, HttpServletResponse response,HttpSession session,Bug bug){
		Bug b = bugService.getBug(bug.getId());
		Employee e = (Employee)session.getAttribute("LOGIN_EMP");
		if(e.getAccountid().equals( b.getProject().getCreatorid())){
			bugService.updateBug(bug);
			Event event = new Event();
			event.setEventtype("ָ��BUG");
			event.setEventcontent("#"+b.getId()+"<"+b.getBughead()+">��"+bug.getAssignid());
			eventController.saveEvent(request, response, session, event);
			return Msg.success();
		}else{
			return Msg.fail().add("error", "��û�и�Ȩ�ޣ�");
		}
	}
	/**
	 * ����id��ѯBUG
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/bug/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getBug(@PathVariable("id")Integer id){
		
		Bug bug = bugService.getBug(id);
		return Msg.success().add("bug", bug);
	}
	/**
	 * BUG����
	 * @return
	 */
	@RequestMapping(value="/bug",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveBug(HttpServletRequest request, HttpServletResponse response,HttpSession session,Bug bug){
		Employee e = (Employee)session.getAttribute("LOGIN_EMP");
		if(e.getPower() == 5){
		
			if(bug.getStatus()==null)bug.setStatus("δ���");
			if(bug.getCreatorid()==null){
				Employee emp = (Employee)session.getAttribute("LOGIN_EMP");
				bug.setCreatorid(emp.getAccountid());
			}
			if(bug.getCreatetime()==null)bug.setCreatetime(new Date());;
			bugService.saveBug(bug);
			Event event = new Event();
			event.setEventtype("����BUG");
			event.setEventcontent(" <"+bug.getBughead()+">");
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
	@RequestMapping("/bugs")
	@ResponseBody
	public Msg getBugsWithJson(@RequestParam(name="pn",defaultValue="1")Integer pn,String status){
		BugExample bugExample = new BugExample();
		Criteria criteria = bugExample.createCriteria();
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
		List<Bug> bugs = bugService.getAll(bugExample);
		//ʹ��pageInfo��װ��ѯ�Ժ�Ľ����ֻ�轫PageInfo����ҳ��
		//��װ����ϸ�ķ�ҳ��Ϣ��������ѯ����������,����������ʾ��ҳ��
		PageInfo page = new PageInfo(bugs,5);
		return Msg.success().add("pageInfo",page);
	}
	/**
	 * ��ѯ�ҵ�������Ŀ���ݣ���ҳ��ѯ��
	 * ����Json��
	 * @return
	 */
	@RequestMapping("/myBugs")
	@ResponseBody
	public Msg getMyBugsWithJson(@RequestParam(name="pn",defaultValue="1")Integer pn,String status,HttpSession session){
		BugExample bugExample = new BugExample();
		Employee emp = (Employee)session.getAttribute("LOGIN_EMP");
		if(status!=null){
			if(status.equals("δ���")){
				bugExample.or().andStatusEqualTo("δ���").andAssignidEqualTo(emp.getAccountid());
				//bugExample.or().andStatusEqualTo("δ���").andCreatoridEqualTo(emp.getAccountid());
			}else if(status.equals("�����")){
				bugExample.or().andStatusEqualTo("�����").andAssignidEqualTo(emp.getAccountid());
				//bugExample.or().andStatusEqualTo("�����").andCreatoridEqualTo(emp.getAccountid());
			}else if(status.equals("���ͨ��")){
				bugExample.or().andStatusEqualTo("���ͨ��").andAssignidEqualTo(emp.getAccountid());
				//bugExample.or().andStatusEqualTo("�����").andCreatoridEqualTo(emp.getAccountid());
			}
		}else{
			bugExample.or().andAssignidEqualTo(emp.getAccountid());
			//bugExample.or().andCreatoridEqualTo(emp.getAccountid());
		}
		bugExample.or().andStatusEqualTo("���ͨ��");
		//����PageHelper��ҳ���
		//�ڲ�ѯ֮ǰֻ��Ҫ����PageHelper.startPage,����ҳ���ÿһҳ��С
		PageHelper.startPage(pn,5);
		//starPage��������Ĳ�ѯ���Ƿ�ҳ��ѯ
		List<Bug> bugs = bugService.getAll(bugExample);
		//ʹ��pageInfo��װ��ѯ�Ժ�Ľ����ֻ�轫PageInfo����ҳ��
		//��װ����ϸ�ķ�ҳ��Ϣ��������ѯ����������,����������ʾ��ҳ��
		PageInfo page = new PageInfo(bugs,5);
		return Msg.success().add("pageInfo",page);
	}
}
