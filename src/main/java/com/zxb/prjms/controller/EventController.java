package com.zxb.prjms.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;

import com.zxb.prjms.bean.Department;
import com.zxb.prjms.bean.Employee;
import com.zxb.prjms.bean.Event;
import com.zxb.prjms.bean.EventExample;
import com.zxb.prjms.bean.Msg;
import com.zxb.prjms.service.EventService;
import com.zxb.prjms.socket.SocketHandler;
import com.zxb.prjms.utils.JsonDateValueProcessor;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
/**
 * 动态信息CRUD
 * @author Think
 *
 */
@Controller
public class EventController {

	@Autowired
	SocketHandler  socketHandler;
	@Autowired
	EventService eventService;
	
	/**
	 * 插入动态信息
	 * @return
	 */
	@RequestMapping(value="/event",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveEvent(HttpServletRequest request, HttpServletResponse response,HttpSession session,Event event){
		if(event.getEmployeeid()==null){
			Employee emp = (Employee)session.getAttribute("LOGIN_EMP");
			event.setEmployeeid(emp.getAccountid());
		}
		if(event.getOperatetime()==null)event.setOperatetime(new Date());
		eventService.saveEvent(event);
		Map<String,Object> map=new HashMap<>();
		map.put("MSG", event);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
		socketHandler.sendMessageToUsers(new TextMessage(JSONObject.fromObject(map,jsonConfig).toString()));
		return Msg.success();
	}
	/**
	 * 查询动态信息
	 * @return
	 */
	@RequestMapping(value="/newEvent",method=RequestMethod.GET)
	@ResponseBody
	public Msg getEvent(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 24);
		Date date = calendar.getTime();
		EventExample eventExample = new EventExample();
		eventExample.createCriteria().andOperatetimeGreaterThan(date);
		List<Event> list = eventService.getEvent(eventExample);
		return Msg.success().add("newEvent", list);
	}
	/**
	 * 根据日期查询动态信息
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value="/dayEvent",method=RequestMethod.GET)
	@ResponseBody
	public Msg getDayEvent(String date) throws ParseException{
		date = date+" 00:00:00";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = sdf.parse(date);
		Calendar c = Calendar.getInstance();  
        c.setTime(d);  
        c.add(Calendar.DAY_OF_MONTH, 1); 
        Date D = c.getTime();
		EventExample eventExample = new EventExample();
		eventExample.createCriteria().andOperatetimeBetween(d, D);
		List<Event> list = eventService.getEvent(eventExample);
		return Msg.success().add("dayEvent", list);
	}
	/**
	 * 查询所有动态信息
	 * @return
	 */
	@RequestMapping(value="/allEvent",method=RequestMethod.GET)
	@ResponseBody
	public Msg getAllEvent(){
		List<Event> list = eventService.getEvent(null);
		return Msg.success().add("allEvent", list);
	}
}
