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
	 * 查出所有部门
	 * @return
	 */
	public List<Department> getDepts() {
		// TODO Auto-generated method stub
		List<Department> list = departmentMapper.selectByExample(null);
		return list;
	}
	/**
	 * 插入新部门
	 * @param department
	 */
	public void saveDept(Department department) {
		// TODO Auto-generated method stub
		departmentMapper.insertSelective(department);
	}
	/**
	 * 根据id查询部门
	 * @param id
	 * @return
	 */
	public Department getDept(Integer id) {
		// TODO Auto-generated method stub
		Department department = departmentMapper.selectByPrimaryKey(id);
		return department;
	}
	/**
	 * 更新部门
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
