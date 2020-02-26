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
	 * 查询所有任务
	 * @param taskExample
	 * @return
	 */
	public List<Task> getAll(TaskExample taskExample) {
		// TODO Auto-generated method stub
		return taskMapper.selectByExampleWithPrj(taskExample);
	}
	/**
	 * 保存任务
	 * @param task
	 */
	public void saveTask(Task task) {
		// TODO Auto-generated method stub
		taskMapper.insertSelective(task);
	}
	/**
	 * 根据id查询任务
	 * @param id
	 * @return
	 */
	public Task getTask(Integer id) {
		// TODO Auto-generated method stub
		return taskMapper.selectByPrimaryKeyWithPrj(id);
	}
	/**
	 * 更新任务
	 * @param task
	 */
	public void updateTask(Task task) {
		// TODO Auto-generated method stub
		taskMapper.updateByPrimaryKeySelective(task);
	}
	/**
	 * 删除任务
	 * @param id
	 */
	public void deleteTask(Integer id) {
		// TODO Auto-generated method stub
		taskMapper.deleteByPrimaryKey(id);
	}
	
	
	
}
