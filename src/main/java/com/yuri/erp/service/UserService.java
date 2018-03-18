package com.yuri.erp.service;

import com.yuri.erp.entity.User;

/**   
*    
* Project Name：erp   
* Class Name：UserService   
* Description：User的业务层
* @author：yuriFeng  
* @date：2018年3月18日 下午4:41:30   
* Contact：yuri_feng@outlook.com 
*      
*/
public interface UserService {

	
	/**
	 * 业务层注册用户
	 * @param user
	 */
	void registerUser(User user);

	
	/**
	 * 业务层用户登录
	 * @param username 用户名
	 * @param password 密码
	 * @return User
	 */
	User loign(String username, String password);

	
	/**
	 * 业务层注销用户登录
	 */
	void logout();
}
