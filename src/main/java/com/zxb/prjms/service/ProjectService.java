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
	 * ��ѯ������Ŀ
	 * @return
	 */
	public List<Project> getAll(ProjectExample projectExample) {
		// TODO Auto-generated method stub
		return projectMapper.selectByExample(projectExample);
	}
	/**
	 * ������Ŀ
	 * @param project
	 */
	public void savePrj(Project project) {
		// TODO Auto-generated method stub
		projectMapper.insertSelective(project);
	}
	/**
	 * ����id��ѯ��Ŀ��Ϣ
	 * @param id
	 * @return
	 */
	public Project getPrj(Integer id) {
		// TODO Auto-generated method stub
		return projectMapper.selectByPrimaryKey(id);
	}
	/**
	 * ����id������Ŀ��Ϣ
	 * @param project
	 */
	public void updatePrj(Project project) {
		// TODO Auto-generated method stub
		projectMapper.updateByPrimaryKeySelective(project);
	}
	/**
	 * ����ɾ����Ŀ
	 * @param id
	 */
	public void deletePrj(Integer id) {
		// TODO Auto-generated method stub
		projectMapper.deleteByPrimaryKey(id);
	}

}
