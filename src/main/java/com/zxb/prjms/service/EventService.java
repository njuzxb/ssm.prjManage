package com.zxb.prjms.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxb.prjms.bean.Event;
import com.zxb.prjms.bean.EventExample;
import com.zxb.prjms.dao.EventMapper;

@Service
public class EventService {
	
	@Autowired
	EventMapper eventMapper;
	
	/**
	 * 插入动态信息
	 * @param event
	 */
	public void saveEvent(Event event) {
		// TODO Auto-generated method stub
		eventMapper.insertSelective(event);
	}

	public List<Event> getEvent(EventExample eventExample) {
		// TODO Auto-generated method stub
		return eventMapper.selectByExample(eventExample);
	}

}
