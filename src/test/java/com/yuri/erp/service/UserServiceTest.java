package com.yuri.erp.service;

import org.junit.Test;

import com.yuri.erp.entity.User;
import com.yuri.erp.service.UserService;
import com.yuri.erp.util.BeanFactory;

/**   
*    
* Project Name：erp   
* Class Name：UserServiceTest   
* Description：User业务层的测试类
* @author：yuriFeng  
* @date：2018年3月18日 下午4:49:01   
* Contact：yuri_feng@outlook.com 
*      
*/
public class UserServiceTest {

	UserService userService = (UserService) BeanFactory.getBean("userService");

	@Test
	public void testRegisterUser() {
		User user = new User();
		user.setUsername("java");
		user.setPassword("java");
		userService.registerUser(user);
	}

	@Test
	public void testLoign() {
		User u = userService.loign("java", "java");
		System.out.println(u);
	}

}
