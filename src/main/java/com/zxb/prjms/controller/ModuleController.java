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
 * 处理模块的CURD
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
	 * 模块删除
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
			event.setEventtype("删除模块");
			event.setEventcontent("#"+module.getId()+"<"+module.getModulename()+">");
			eventController.saveEvent(request, response, session, event);
			return Msg.success();
		}
		else{
			return Msg.fail().add("error", "您权限不足！");
		}
	}
	
	/**
	 * 更新
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/mod/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Msg updateMod(HttpServletRequest request, HttpServletResponse response,HttpSession session,Module module){
		Module mod = moduleService.getMod(module.getId());
		Employee e = (Employee)session.getAttribute("LOGIN_EMP");
		if(e.getAccountid().equals( mod.getProject().getCreatorid())){
			if(module.getStatus()!=null && module.getStatus().equals(new String("已完成"))){
				//if(e.getAccountid().equals( mod.getAssignid()))return Msg.fail().add("error", "您的权限不足！");
				module.setFinishtime(new Date());
			}
			moduleService.updateMod(module);;
			Event event = new Event();
			event.setEventtype("更新模块状态");
			event.setEventcontent("#"+mod.getId()+"<"+mod.getModulename()+">:"+module.getStatus());
			eventController.saveEvent(request, response, session, event);
			return Msg.success();
		}
		else{
			return Msg.fail().add("error", "您权限不足！");
		}
	}
	/**
	 * 更新（员工）
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
			if(module.getStatus()!=null && module.getStatus().equals(new String("待审核"))){
				Event event = new Event();
				event.setEventtype("提交待审模块");
				event.setEventcontent("#"+mod.getId()+"<"+mod.getModulename()+">");
				eventController.saveEvent(request, response, session, event);
			}
			return Msg.success();
		}
		else{
			return Msg.fail().add("error", "您权限不足！");
		}
	}
	/**
	 * 指派
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
			event.setEventtype("指派模块");
			event.setEventcontent("#"+mod.getId()+"<"+mod.getModulename()+">给"+module.getAssignid());
			eventController.saveEvent(request, response, session, event);
			return Msg.success();
		}
		else{
			return Msg.fail().add("error", "您权限不足！");
		}
	}
	
	/**
	 * 根据id查询模块
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
	 * 模块保存
	 * @return
	 */
	@RequestMapping(value="/mod",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveMod(HttpServletRequest request, HttpServletResponse response,HttpSession session,Module module){
		Employee e = (Employee)session.getAttribute("LOGIN_EMP");
		if(e.getPower() == 1){
			if(module.getStatus()==null)module.setStatus("未完成");
			if(module.getCreatorid()==null){
				module.setCreatorid(e.getAccountid());
			}
			if(module.getCreatetime()==null)module.setCreatetime(new Date());;
			moduleService.saveMod(module);
			Event event = new Event();
			event.setEventtype("新增模块");
			event.setEventcontent(" <"+module.getModulename()+">");
			eventController.saveEvent(request, response, session, event);
			return Msg.success();
		}
		else{
			return Msg.fail().add("error", "您权限不足！");
		}
	}
	/**
	 * 根据parentid查询模块
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
	 * 查询所有项目数据（分页查询）
	 * 导入Json包
	 * @return
	 */
	@RequestMapping("/mods")
	@ResponseBody
	public Msg getModsWithJson(@RequestParam(name="pn",defaultValue="1")Integer pn,String status){
				ModuleExample moduleExample = new ModuleExample();
				Criteria criteria = moduleExample.createCriteria();
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
				List<Module> mods = moduleService.getAll(moduleExample);
				//使用pageInfo包装查询以后的结果，只需将PageInfo交给页面
				//封装了详细的分页信息，包括查询出来的数据,传入连续显示的页数
				PageInfo page = new PageInfo(mods,5);
				return Msg.success().add("pageInfo",page);
	}
	/**
	 * 查询我的所有项目数据（分页查询）
	 * 导入Json包
	 * @return
	 */
	@RequestMapping("/myMods")
	@ResponseBody
	public Msg getMyModsWithJson(@RequestParam(name="pn",defaultValue="1")Integer pn,String status,HttpSession session){
				ModuleExample moduleExample = new ModuleExample();
				Employee emp = (Employee)session.getAttribute("LOGIN_EMP");
				if(status!=null){
					if(status.equals("未完成")){
						moduleExample.or().andStatusEqualTo("未完成").andAssignidEqualTo(emp.getAccountid());
						//moduleExample.or().andStatusEqualTo("未完成").andCreatoridEqualTo(emp.getAccountid());
					}else if(status.equals("待审核")){
						moduleExample.or().andStatusEqualTo("待审核").andAssignidEqualTo(emp.getAccountid());
						//moduleExample.or().andStatusEqualTo("已完成").andCreatoridEqualTo(emp.getAccountid());
					}else if(status.equals("审核通过")){
						moduleExample.or().andStatusEqualTo("审核通过").andAssignidEqualTo(emp.getAccountid());
						//moduleExample.or().andStatusEqualTo("已完成").andCreatoridEqualTo(emp.getAccountid());
					}
				}else{
					moduleExample.or().andAssignidEqualTo(emp.getAccountid());
					//moduleExample.or().andCreatoridEqualTo(emp.getAccountid());
				}
				moduleExample.or().andStatusEqualTo("审核通过");
				//引入PageHelper分页插件
				//在查询之前只需要调用PageHelper.startPage,传入页码和每一页大小
				PageHelper.startPage(pn,5);
				//starPage后面紧跟的查询就是分页查询
				List<Module> mods = moduleService.getAll(moduleExample);
				//使用pageInfo包装查询以后的结果，只需将PageInfo交给页面
				//封装了详细的分页信息，包括查询出来的数据,传入连续显示的页数
				PageInfo page = new PageInfo(mods,5);
				return Msg.success().add("pageInfo",page);
	}
	
}
