package com.zxb.prjms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zxb.prjms.bean.EmployeeExample.Criteria;
import com.zxb.prjms.bean.Employee;
import com.zxb.prjms.bean.EmployeeExample;
import com.zxb.prjms.dao.EmployeeMapper;

@Service
public class EmployeeService {
	@Autowired
	EmployeeMapper employeeMapper;
	
	/**
	 * 查询所有员工
	 * @return
	 */
	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		return employeeMapper.selectByExampleWithDepartment(null);
	}
	/**
	 * 员工保存
	 * @param employee
	 */
	public void saveEmp(Employee employee) {
		// TODO Auto-generated method stub
		employeeMapper.insertSelective(employee);
	}
	/**
	 * 按照员工ID查询员工
	 * @param id
	 * @return
	 */
	public Employee getEmp(String accountid) {
		// TODO Auto-generated method stub
		Employee employee = employeeMapper.selectByPrimaryKey(accountid);
		return employee;
	}
	/**
	 * 员工更新
	 * @param employee
	 */
	public void updateEmp(Employee employee) {
		// TODO Auto-generated method stub
		employeeMapper.updateByPrimaryKeySelective(employee);
	}
	/**
	 * 员工删除
	 * @param id
	 */
	public void deleteEmp(String accountid) {
		// TODO Auto-generated method stub
		employeeMapper.deleteByPrimaryKey(accountid);
	}
	/**
	 * 登陆
	 * @param accountid
	 * @param password
	 * @return
	 */
	public boolean checkEmp(String accountid,String password) {
		// TODO Auto-generated method stub
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andAccountidEqualTo(accountid);
		if(password!=null)criteria.andPasswordEqualTo(password);
		long count = employeeMapper.countByExample(example);
		return count==0;
	}
	/**
	 * 根据部门id查询
	 * @param employeeExample
	 * @return
	 */
	public List<Employee> getEmpWherePrj(EmployeeExample employeeExample) {
		// TODO Auto-generated method stub
		return employeeMapper.selectByExample(employeeExample);
	}

	
}
