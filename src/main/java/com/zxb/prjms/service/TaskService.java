package com.zxb.prjms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxb.prjms.bean.Task;
import com.zxb.prjms.bean.TaskExample;
import com.zxb.prjms.dao.TaskMapper;
@Service
public class TaskService {
	@Autowired
	TaskMapper taskMapper;
	/**
	 * ��ѯ��������
	 * @param taskExample
	 * @return
	 */
	public List<Task> getAll(TaskExample taskExample) {
		// TODO Auto-generated method stub
		return taskMapper.selectByExampleWithPrj(taskExample);
	}
	/**
	 * ��������
	 * @param task
	 */
	public void saveTask(Task task) {
		// TODO Auto-generated method stub
		taskMapper.insertSelective(task);
	}
	/**
	 * ����id��ѯ����
	 * @param id
	 * @return
	 */
	public Task getTask(Integer id) {
		// TODO Auto-generated method stub
		return taskMapper.selectByPrimaryKeyWithPrj(id);
	}
	/**
	 * ��������
	 * @param task
	 */
	public void updateTask(Task task) {
		// TODO Auto-generated method stub
		taskMapper.updateByPrimaryKeySelective(task);
	}
	/**
	 * ɾ������
	 * @param id
	 */
	public void deleteTask(Integer id) {
		// TODO Auto-generated method stub
		taskMapper.deleteByPrimaryKey(id);
	}
	
	
	
}
