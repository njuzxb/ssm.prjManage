package com.zxb.prjms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxb.prjms.bean.Project;
import com.zxb.prjms.bean.ProjectExample;
import com.zxb.prjms.dao.ProjectMapper;
@Service
public class ProjectService {
	@Autowired
	ProjectMapper projectMapper;
	
	/**
	 * 查询所有项目
	 * @return
	 */
	public List<Project> getAll(ProjectExample projectExample) {
		// TODO Auto-generated method stub
		return projectMapper.selectByExample(projectExample);
	}
	/**
	 * 保存项目
	 * @param project
	 */
	public void savePrj(Project project) {
		// TODO Auto-generated method stub
		projectMapper.insertSelective(project);
	}
	/**
	 * 根据id查询项目信息
	 * @param id
	 * @return
	 */
	public Project getPrj(Integer id) {
		// TODO Auto-generated method stub
		return projectMapper.selectByPrimaryKey(id);
	}
	/**
	 * 根据id更新项目信息
	 * @param project
	 */
	public void updatePrj(Project project) {
		// TODO Auto-generated method stub
		projectMapper.updateByPrimaryKeySelective(project);
	}
	/**
	 * 单个删除项目
	 * @param id
	 */
	public void deletePrj(Integer id) {
		// TODO Auto-generated method stub
		projectMapper.deleteByPrimaryKey(id);
	}

}
