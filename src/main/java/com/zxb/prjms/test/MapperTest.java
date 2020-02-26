package com.zxb.prjms.test;

import java.util.Date;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zxb.prjms.bean.Department;
import com.zxb.prjms.dao.DepartmentMapper;
import com.zxb.prjms.dao.EmployeeMapper;

/**
 * 测试dao层的工作
 * @author zxb
 *推荐Spring的项目就可以使用Spring单元测试，可以自动注入我们 需要的组件
 *1、导入SpringTest模块
 *2、@ContextConfiguration指定Spring配置文件的位置
 *3、直接autowired要使用的组件即可
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest {
	
	@Autowired
	DepartmentMapper departmentMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	SqlSession sqlSession;
	/**
	 * 测试DepartmentMapper
	 */
	@Test
	public void testCRUD(){
//		//1、创建SpringIOC容器
//		ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
//		//2、从容器中获取Mapper
//		DepartmentMapper bean = ioc.getBean(DepartmentMapper.class);
		System.out.println(departmentMapper);
		//1、插入几个部门
		//departmentMapper.insertSelective(new Department(null,"设计部",null));
		//departmentMapper.insertSelective(new Department(null,"测试部",null));
		//2、生成员工数据，测试员工插入
		//employeeMapper.insertSelective(new Employee("kfb123", "123", "Jerry", "Java开发", 2, new Date()));
		//3、批量插入多个员工，使用可以执行批量操作的sqlsession
		/*for(){
			employeeMapper.insertSelective(new Employee("kfb123", "123", "Jerry", "Java开发", 2, new Date()));
			
		}*/
		/*EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		for(int i = 0; i<500; i++){
			String uid = UUID.randomUUID().toString().substring(0,5) + i;
			mapper.insertSelective(new Employee("csb"+i,"123",uid,"测试",3,new Date()));
		}
		System.out.println("批量完成");*/
	}
	
	
}
