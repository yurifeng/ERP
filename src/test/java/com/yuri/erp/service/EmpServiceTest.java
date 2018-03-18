package com.yuri.erp.service;

import org.junit.Test;

import com.yuri.erp.entity.Employee;
import com.yuri.erp.service.EmpService;
import com.yuri.erp.util.BeanFactory;
import com.yuri.erp.util.PageBean;

/**   
*    
* Project Name：erp   
* Class Name：EmpServiceTest   
* Description：Emp业务层的测试类
* @author：yuriFeng  
* @date：2018年3月18日 下午4:47:43   
* Contact：yuri_feng@outlook.com 
*      
*/
public class EmpServiceTest {

	EmpService empService = (EmpService) BeanFactory.getBean("empService");

	@Test
	public void testFindEmpsByPage() {
		PageBean<Employee> page = empService.findEmpsByPage(4, 3);

		page.getPageDatas().forEach(System.out::println);
		System.out.println(page.getPageSize());
		System.out.println(page.getPageNow());
		System.out.println(page.getTotalRecords());
		System.out.println(page.getTotalPages());
		System.out.println(page.getIsHavePrePage());
		System.out.println(page.getIsHaveNextPage());
	}
}
