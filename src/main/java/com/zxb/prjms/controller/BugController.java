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
 * 处理BUG的CURD
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
	 * 删除
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
			event.setEventtype("删除BUG");
			event.setEventcontent("#"+bug.getId()+":"+bug.getBughead());
			eventController.saveEvent(request, response, session, event);
			return Msg.success();
		}else{
			return Msg.fail().add("error", "您没有该权限！");
		}
	}
	
	/**
	 * 更新BUG
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/bug/{id}",method=RequestMethod.PUT)
	@ResponseBody
	public Msg updateBug(HttpServletRequest request, HttpServletResponse response,HttpSession session,Bug bug){
		Bug b = bugService.getBug(bug.getId());
		Employee e = (Employee)session.getAttribute("LOGIN_EMP");
		if(e.getAccountid().equals( b.getProject().getCreatorid())){
		
			if(bug.getStatus()!=null && bug.getStatus().equals(new String("已完成")))bug.setFinishtime(new Date());
			bugService.updateBug(bug);
			Event event = new Event();
			event.setEventtype("更新BUG状态");
			event.setEventcontent("#"+b.getId()+"<"+b.getBughead()+">"+bug.getStatus());
			eventController.saveEvent(request, response, session, event);
			return Msg.success();
		}else{
			return Msg.fail().add("error", "您没有该权限！");
		}
	}
	/**
	 * 更新BUG(员工)
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
			if(bug.getStatus()!=null && bug.getStatus().equals(new String("待审核"))){
				Event event = new Event();
				event.setEventtype("提交待审BUG");
				event.setEventcontent("#"+b.getId()+"<"+b.getBughead()+">");
				eventController.saveEvent(request, response, session, event);
			}
			return Msg.success();
		}else{
			return Msg.fail().add("error", "您没有该权限！");
		}
	}
	/**
	 * 指派BUG
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
			event.setEventtype("指派BUG");
			event.setEventcontent("#"+b.getId()+"<"+b.getBughead()+">给"+bug.getAssignid());
			eventController.saveEvent(request, response, session, event);
			return Msg.success();
		}else{
			return Msg.fail().add("error", "您没有该权限！");
		}
	}
	/**
	 * 根据id查询BUG
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
	 * BUG保存
	 * @return
	 */
	@RequestMapping(value="/bug",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveBug(HttpServletRequest request, HttpServletResponse response,HttpSession session,Bug bug){
		Employee e = (Employee)session.getAttribute("LOGIN_EMP");
		if(e.getPower() == 5){
		
			if(bug.getStatus()==null)bug.setStatus("未完成");
			if(bug.getCreatorid()==null){
				Employee emp = (Employee)session.getAttribute("LOGIN_EMP");
				bug.setCreatorid(emp.getAccountid());
			}
			if(bug.getCreatetime()==null)bug.setCreatetime(new Date());;
			bugService.saveBug(bug);
			Event event = new Event();
			event.setEventtype("发布BUG");
			event.setEventcontent(" <"+bug.getBughead()+">");
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
	@RequestMapping("/bugs")
	@ResponseBody
	public Msg getBugsWithJson(@RequestParam(name="pn",defaultValue="1")Integer pn,String status){
		BugExample bugExample = new BugExample();
		Criteria criteria = bugExample.createCriteria();
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
		List<Bug> bugs = bugService.getAll(bugExample);
		//使用pageInfo包装查询以后的结果，只需将PageInfo交给页面
		//封装了详细的分页信息，包括查询出来的数据,传入连续显示的页数
		PageInfo page = new PageInfo(bugs,5);
		return Msg.success().add("pageInfo",page);
	}
	/**
	 * 查询我的所有项目数据（分页查询）
	 * 导入Json包
	 * @return
	 */
	@RequestMapping("/myBugs")
	@ResponseBody
	public Msg getMyBugsWithJson(@RequestParam(name="pn",defaultValue="1")Integer pn,String status,HttpSession session){
		BugExample bugExample = new BugExample();
		Employee emp = (Employee)session.getAttribute("LOGIN_EMP");
		if(status!=null){
			if(status.equals("未完成")){
				bugExample.or().andStatusEqualTo("未完成").andAssignidEqualTo(emp.getAccountid());
				//bugExample.or().andStatusEqualTo("未完成").andCreatoridEqualTo(emp.getAccountid());
			}else if(status.equals("待审核")){
				bugExample.or().andStatusEqualTo("待审核").andAssignidEqualTo(emp.getAccountid());
				//bugExample.or().andStatusEqualTo("已完成").andCreatoridEqualTo(emp.getAccountid());
			}else if(status.equals("审核通过")){
				bugExample.or().andStatusEqualTo("审核通过").andAssignidEqualTo(emp.getAccountid());
				//bugExample.or().andStatusEqualTo("已完成").andCreatoridEqualTo(emp.getAccountid());
			}
		}else{
			bugExample.or().andAssignidEqualTo(emp.getAccountid());
			//bugExample.or().andCreatoridEqualTo(emp.getAccountid());
		}
		bugExample.or().andStatusEqualTo("审核通过");
		//引入PageHelper分页插件
		//在查询之前只需要调用PageHelper.startPage,传入页码和每一页大小
		PageHelper.startPage(pn,5);
		//starPage后面紧跟的查询就是分页查询
		List<Bug> bugs = bugService.getAll(bugExample);
		//使用pageInfo包装查询以后的结果，只需将PageInfo交给页面
		//封装了详细的分页信息，包括查询出来的数据,传入连续显示的页数
		PageInfo page = new PageInfo(bugs,5);
		return Msg.success().add("pageInfo",page);
	}
}
