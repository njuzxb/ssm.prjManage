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
import com.zxb.prjms.bean.ModuleExample.Criteria;
import com.zxb.prjms.bean.Msg;
import com.zxb.prjms.bean.Project;
import com.zxb.prjms.service.ModuleService;
/**
 * ����ģ���CURD
 * @author root
 *
 */
@Controller
public class ModuleController {
	
	
	@Autowired
	ModuleService moduleService;
	@Autowired
	EventController eventController;
	
	/**
	 * ģ��ɾ��
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/mod/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deletePrjById(@PathVariable("id")Integer id,HttpServletRequest request, HttpServletResponse response,HttpSession session){
		Module module = moduleService.getMod(id);
		Employee e = (Employee)session.getAttribute("LOGIN_EMP");
		if(e.getAccountid().equals(module.getCreatorid())){
			
			moduleService.deleteMod(id);
			Event event = new Event();
			event.setEventtype("ɾ��ģ��");
			event.setEventcontent("#"+module.getId()+"<"+module.getModulename()+">");
			eventController.saveEvent(request, response, session, event);
			return Msg.success();
		}
		else{
			return Msg.fail().add("error", "��Ȩ�޲��㣡");
		}
	}
	
	/**
	 * ����
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/mod/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Msg updateMod(HttpServletRequest request, HttpServletResponse response,HttpSession session,Module module){
		Module mod = moduleService.getMod(module.getId());
		Employee e = (Employee)session.getAttribute("LOGIN_EMP");
		if(e.getAccountid().equals( mod.getProject().getCreatorid())){
			if(module.getStatus()!=null && module.getStatus().equals(new String("�����"))){
				//if(e.getAccountid().equals( mod.getAssignid()))return Msg.fail().add("error", "����Ȩ�޲��㣡");
				module.setFinishtime(new Date());
			}
			moduleService.updateMod(module);;
			Event event = new Event();
			event.setEventtype("����ģ��״̬");
			event.setEventcontent("#"+mod.getId()+"<"+mod.getModulename()+">:"+module.getStatus());
			eventController.saveEvent(request, response, session, event);
			return Msg.success();
		}
		else{
			return Msg.fail().add("error", "��Ȩ�޲��㣡");
		}
	}
	/**
	 * ���£�Ա����
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/mymod/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Msg updateMyMod(HttpServletRequest request, HttpServletResponse response,HttpSession session,Module module){
		Module mod = moduleService.getMod(module.getId());
		Employee e = (Employee)session.getAttribute("LOGIN_EMP");
		if(e.getAccountid().equals(mod.getAssignid())){
			moduleService.updateMod(module);
			if(module.getStatus()!=null && module.getStatus().equals(new String("�����"))){
				Event event = new Event();
				event.setEventtype("�ύ����ģ��");
				event.setEventcontent("#"+mod.getId()+"<"+mod.getModulename()+">");
				eventController.saveEvent(request, response, session, event);
			}
			return Msg.success();
		}
		else{
			return Msg.fail().add("error", "��Ȩ�޲��㣡");
		}
	}
	/**
	 * ָ��
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/AssignMod/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Msg assignMod(HttpServletRequest request, HttpServletResponse response,HttpSession session,Module module){
		Module mod = moduleService.getMod(module.getId());
		Employee e = (Employee)session.getAttribute("LOGIN_EMP");
		if(e.getAccountid().equals( mod.getProject().getCreatorid())){
			moduleService.updateMod(module);
			Event event = new Event();
			event.setEventtype("ָ��ģ��");
			event.setEventcontent("#"+mod.getId()+"<"+mod.getModulename()+">��"+module.getAssignid());
			eventController.saveEvent(request, response, session, event);
			return Msg.success();
		}
		else{
			return Msg.fail().add("error", "��Ȩ�޲��㣡");
		}
	}
	
	/**
	 * ����id��ѯģ��
	 * @param accountid
	 * @return
	 */
	@RequestMapping(value="/mod/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getPrj(@PathVariable("id")Integer id,HttpSession session){
		
			Module module = moduleService.getMod(id);
			return Msg.success().add("mod", module);
		
	}
	/**
	 * ģ�鱣��
	 * @return
	 */
	@RequestMapping(value="/mod",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveMod(HttpServletRequest request, HttpServletResponse response,HttpSession session,Module module){
		Employee e = (Employee)session.getAttribute("LOGIN_EMP");
		if(e.getPower() == 1){
			if(module.getStatus()==null)module.setStatus("δ���");
			if(module.getCreatorid()==null){
				module.setCreatorid(e.getAccountid());
			}
			if(module.getCreatetime()==null)module.setCreatetime(new Date());;
			moduleService.saveMod(module);
			Event event = new Event();
			event.setEventtype("����ģ��");
			event.setEventcontent(" <"+module.getModulename()+">");
			eventController.saveEvent(request, response, session, event);
			return Msg.success();
		}
		else{
			return Msg.fail().add("error", "��Ȩ�޲��㣡");
		}
	}
	/**
	 * ����parentid��ѯģ��
	 */
	@RequestMapping(value="/pmod/{projectid}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getPmod(@PathVariable("projectid")Integer projectid){
		ModuleExample moduleExample = new ModuleExample();
		moduleExample.createCriteria().andProjectidEqualTo(projectid);
		List<Module> mods = moduleService.getAll(moduleExample);
		return Msg.success().add("mods", mods);
	}
	/**
	 * ��ѯ������Ŀ���ݣ���ҳ��ѯ��
	 * ����Json��
	 * @return
	 */
	@RequestMapping("/mods")
	@ResponseBody
	public Msg getModsWithJson(@RequestParam(name="pn",defaultValue="1")Integer pn,String status){
				ModuleExample moduleExample = new ModuleExample();
				Criteria criteria = moduleExample.createCriteria();
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
				List<Module> mods = moduleService.getAll(moduleExample);
				//ʹ��pageInfo��װ��ѯ�Ժ�Ľ����ֻ�轫PageInfo����ҳ��
				//��װ����ϸ�ķ�ҳ��Ϣ��������ѯ����������,����������ʾ��ҳ��
				PageInfo page = new PageInfo(mods,5);
				return Msg.success().add("pageInfo",page);
	}
	/**
	 * ��ѯ�ҵ�������Ŀ���ݣ���ҳ��ѯ��
	 * ����Json��
	 * @return
	 */
	@RequestMapping("/myMods")
	@ResponseBody
	public Msg getMyModsWithJson(@RequestParam(name="pn",defaultValue="1")Integer pn,String status,HttpSession session){
				ModuleExample moduleExample = new ModuleExample();
				Employee emp = (Employee)session.getAttribute("LOGIN_EMP");
				if(status!=null){
					if(status.equals("δ���")){
						moduleExample.or().andStatusEqualTo("δ���").andAssignidEqualTo(emp.getAccountid());
						//moduleExample.or().andStatusEqualTo("δ���").andCreatoridEqualTo(emp.getAccountid());
					}else if(status.equals("�����")){
						moduleExample.or().andStatusEqualTo("�����").andAssignidEqualTo(emp.getAccountid());
						//moduleExample.or().andStatusEqualTo("�����").andCreatoridEqualTo(emp.getAccountid());
					}else if(status.equals("���ͨ��")){
						moduleExample.or().andStatusEqualTo("���ͨ��").andAssignidEqualTo(emp.getAccountid());
						//moduleExample.or().andStatusEqualTo("�����").andCreatoridEqualTo(emp.getAccountid());
					}
				}else{
					moduleExample.or().andAssignidEqualTo(emp.getAccountid());
					//moduleExample.or().andCreatoridEqualTo(emp.getAccountid());
				}
				moduleExample.or().andStatusEqualTo("���ͨ��");
				//����PageHelper��ҳ���
				//�ڲ�ѯ֮ǰֻ��Ҫ����PageHelper.startPage,����ҳ���ÿһҳ��С
				PageHelper.startPage(pn,5);
				//starPage��������Ĳ�ѯ���Ƿ�ҳ��ѯ
				List<Module> mods = moduleService.getAll(moduleExample);
				//ʹ��pageInfo��װ��ѯ�Ժ�Ľ����ֻ�轫PageInfo����ҳ��
				//��װ����ϸ�ķ�ҳ��Ϣ��������ѯ����������,����������ʾ��ҳ��
				PageInfo page = new PageInfo(mods,5);
				return Msg.success().add("pageInfo",page);
	}
	
}
