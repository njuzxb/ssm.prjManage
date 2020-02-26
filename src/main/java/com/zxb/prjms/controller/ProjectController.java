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
 * ������Ŀ��CURD
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
	 * ��Ŀɾ��
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
			event.setEventtype("ɾ����Ŀ");
			event.setEventcontent("#"+project.getId()+":"+project.getProjectname());
			projectService.deletePrj(id);
			eventController.saveEvent(request, response, session, event);
			return Msg.success();
		}else{
			return Msg.fail().add("error", "����Ȩ�޲��㣡");
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
	 * ��Ŀ����
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/prj/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Msg updatePrj(HttpServletRequest request, HttpServletResponse response,HttpSession session,Project project){
		Project prj = projectService.getPrj(project.getId());
		Employee e = (Employee)session.getAttribute("LOGIN_EMP");
		if(e.getAccountid().equals(prj.getCreatorid())){
			if(project.getStatus()!=null && project.getStatus().equals(new String("�����"))){
				project.setFinishtime(new Date());
			}
			projectService.updatePrj(project);
			Event event = new Event();
			event.setEventtype("������Ŀ״̬");
			event.setEventcontent("#"+prj.getId()+"<"+prj.getProjectname()+">:"+project.getStatus());
			eventController.saveEvent(request, response, session, event);
			return Msg.success();
		}else{
			return Msg.fail().add("error", "����Ȩ�޲��㣡");
		}
	}
	/**
	 * ��Ŀ���£�Ա����
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
			if(project.getStatus()!=null && project.getStatus().equals(new String("�����"))){
				Event event = new Event();
				event.setEventtype("�ύ������Ŀ");
				event.setEventcontent("#"+prj.getId()+"<"+prj.getProjectname()+">");
				eventController.saveEvent(request, response, session, event);
			}
			
			return Msg.success();
		}else{
			return Msg.fail().add("error", "����Ȩ�޲��㣡");
		}
	}
	/**
	 * ָ��
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
			event.setEventtype("ָ����Ŀ");
			event.setEventcontent("#"+prj.getId()+"<"+prj.getProjectname()+">��"+project.getHeaderid());
			eventController.saveEvent(request, response, session, event);
			return Msg.success();
		}
		else{
			return Msg.fail().add("error", "��Ȩ�޲��㣡");
		}
	}
	
	/**
	 * ����id��ѯ��Ŀ
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
	 * ����
	 * @return
	 */
	@RequestMapping(value="/prj",method=RequestMethod.POST)
	@ResponseBody
	public Msg savePrj(HttpServletRequest request, HttpServletResponse response,HttpSession session,Project project){
		Employee e = (Employee)session.getAttribute("LOGIN_EMP");
		if(e.getPower() == 1){
			if(project.getStatus()==null)project.setStatus("δ���");
			if(project.getCreatorid()==null){
				Employee emp = (Employee)session.getAttribute("LOGIN_EMP");
				project.setCreatorid(emp.getAccountid());
			}
			if(project.getCreatetime()==null)project.setCreatetime(new Date());;
				projectService.savePrj(project);
				Event event = new Event();
				event.setEventtype("������Ŀ");
				event.setEventcontent(" <"+project.getProjectname()+">");
				eventController.saveEvent(request, response, session, event);
				return Msg.success();
		}else{
			return Msg.fail().add("error", "����Ȩ�޲��㣡");
		}
	}
	/**
	 * ��ѯ������Ŀ���ݣ���ҳ��ѯ��
	 * ����Json��
	 * @return
	 */
	@RequestMapping("/prjs")
	@ResponseBody
	public Msg getPrjsWithJson(@RequestParam(name="pn",defaultValue="1")Integer pn,String status){
				ProjectExample projectExample = new ProjectExample();
				Criteria criteria = projectExample.createCriteria();
				//System.out.println(status);
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
				List<Project> prjs = projectService.getAll(projectExample);
				//ʹ��pageInfo��װ��ѯ�Ժ�Ľ����ֻ�轫PageInfo����ҳ��
				//��װ����ϸ�ķ�ҳ��Ϣ��������ѯ����������,����������ʾ��ҳ��				
				PageInfo page = new PageInfo(prjs,5);
				return Msg.success().add("pageInfo",page);
	}
	/**
	 * ��ѯ�ҵ���Ŀ���ݣ���ҳ��ѯ��
	 * ����Json��
	 * @return
	 */
	@RequestMapping("/myPrjs")
	@ResponseBody
	public Msg getMyPrjsWithJson(@RequestParam(name="pn",defaultValue="1")Integer pn,String status,HttpServletRequest request, HttpServletResponse response,HttpSession session){
		ProjectExample projectExample = new ProjectExample();
		Employee emp = (Employee)session.getAttribute("LOGIN_EMP");
		if(status!=null){
			if(status.equals("δ���")){
				projectExample.or().andStatusEqualTo("δ���").andHeaderidEqualTo(emp.getAccountid());
				//projectExample.or().andStatusEqualTo("δ���").andCreatoridEqualTo(emp.getAccountid());
			}else if(status.equals("�����")){
				projectExample.or().andStatusEqualTo("�����").andHeaderidEqualTo(emp.getAccountid());
				//projectExample.or().andStatusEqualTo("�����").andCreatoridEqualTo(emp.getAccountid());
			}else if(status.equals("���ͨ��")){
				projectExample.or().andStatusEqualTo("���ͨ��").andHeaderidEqualTo(emp.getAccountid());
				//projectExample.or().andStatusEqualTo("���ͨ��").andCreatoridEqualTo(emp.getAccountid());
			}
		}else{
			projectExample.or().andHeaderidEqualTo(emp.getAccountid());
			//projectExample.or().andCreatoridEqualTo(emp.getAccountid());
		}
		projectExample.or().andStatusEqualTo("���ͨ��");
		//����PageHelper��ҳ���
		//�ڲ�ѯ֮ǰֻ��Ҫ����PageHelper.startPage,����ҳ���ÿһҳ��С
		PageHelper.startPage(pn,5);
		//starPage��������Ĳ�ѯ���Ƿ�ҳ��ѯ
		List<Project> prjs = projectService.getAll(projectExample);
		//ʹ��pageInfo��װ��ѯ�Ժ�Ľ����ֻ�轫PageInfo����ҳ��
		//��װ����ϸ�ķ�ҳ��Ϣ��������ѯ����������,����������ʾ��ҳ��				
		PageInfo page = new PageInfo(prjs,5);
		return Msg.success().add("pageInfo",page);
	}
}
