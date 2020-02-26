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
	 * ��ѯ����Ա��
	 * @return
	 */
	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		return employeeMapper.selectByExampleWithDepartment(null);
	}
	/**
	 * Ա������
	 * @param employee
	 */
	public void saveEmp(Employee employee) {
		// TODO Auto-generated method stub
		employeeMapper.insertSelective(employee);
	}
	/**
	 * ����Ա��ID��ѯԱ��
	 * @param id
	 * @return
	 */
	public Employee getEmp(String accountid) {
		// TODO Auto-generated method stub
		Employee employee = employeeMapper.selectByPrimaryKey(accountid);
		return employee;
	}
	/**
	 * Ա������
	 * @param employee
	 */
	public void updateEmp(Employee employee) {
		// TODO Auto-generated method stub
		employeeMapper.updateByPrimaryKeySelective(employee);
	}
	/**
	 * Ա��ɾ��
	 * @param id
	 */
	public void deleteEmp(String accountid) {
		// TODO Auto-generated method stub
		employeeMapper.deleteByPrimaryKey(accountid);
	}
	/**
	 * ��½
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
	 * ���ݲ���id��ѯ
	 * @param employeeExample
	 * @return
	 */
	public List<Employee> getEmpWherePrj(EmployeeExample employeeExample) {
		// TODO Auto-generated method stub
		return employeeMapper.selectByExample(employeeExample);
	}

	
}
