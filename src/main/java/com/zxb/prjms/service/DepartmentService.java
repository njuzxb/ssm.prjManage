package com.zxb.prjms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxb.prjms.bean.Department;
import com.zxb.prjms.dao.DepartmentMapper;
@Service
public class DepartmentService {
	@Autowired
	private DepartmentMapper departmentMapper;
	/**
	 * ������в���
	 * @return
	 */
	public List<Department> getDepts() {
		// TODO Auto-generated method stub
		List<Department> list = departmentMapper.selectByExample(null);
		return list;
	}
	/**
	 * �����²���
	 * @param department
	 */
	public void saveDept(Department department) {
		// TODO Auto-generated method stub
		departmentMapper.insertSelective(department);
	}
	/**
	 * ����id��ѯ����
	 * @param id
	 * @return
	 */
	public Department getDept(Integer id) {
		// TODO Auto-generated method stub
		Department department = departmentMapper.selectByPrimaryKey(id);
		return department;
	}
	/**
	 * ���²���
	 * @param department
	 */
	public void updateDept(Department department) {
		// TODO Auto-generated method stub
		departmentMapper.updateByPrimaryKeySelective(department);
	}
	public void deleteDept(Integer id) {
		// TODO Auto-generated method stub
		departmentMapper.deleteByPrimaryKey(id);
	}

}
