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
 * ����dao��Ĺ���
 * @author zxb
 *�Ƽ�Spring����Ŀ�Ϳ���ʹ��Spring��Ԫ���ԣ������Զ�ע������ ��Ҫ�����
 *1������SpringTestģ��
 *2��@ContextConfigurationָ��Spring�����ļ���λ��
 *3��ֱ��autowiredҪʹ�õ��������
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
	 * ����DepartmentMapper
	 */
	@Test
	public void testCRUD(){
//		//1������SpringIOC����
//		ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
//		//2���������л�ȡMapper
//		DepartmentMapper bean = ioc.getBean(DepartmentMapper.class);
		System.out.println(departmentMapper);
		//1�����뼸������
		//departmentMapper.insertSelective(new Department(null,"��Ʋ�",null));
		//departmentMapper.insertSelective(new Department(null,"���Բ�",null));
		//2������Ա�����ݣ�����Ա������
		//employeeMapper.insertSelective(new Employee("kfb123", "123", "Jerry", "Java����", 2, new Date()));
		//3������������Ա����ʹ�ÿ���ִ������������sqlsession
		/*for(){
			employeeMapper.insertSelective(new Employee("kfb123", "123", "Jerry", "Java����", 2, new Date()));
			
		}*/
		/*EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		for(int i = 0; i<500; i++){
			String uid = UUID.randomUUID().toString().substring(0,5) + i;
			mapper.insertSelective(new Employee("csb"+i,"123",uid,"����",3,new Date()));
		}
		System.out.println("�������");*/
	}
	
	
}
