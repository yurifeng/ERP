package com.yuri.erp.mapper;

import org.apache.ibatis.annotations.Param;

import com.yuri.erp.entity.User;

/**   
*    
* Project Name：erp   
* Class Name：UserMapper   
* Description：User的接口
* @author：yuriFeng  
* @date：2018年3月18日 下午4:33:45   
* Contact：yuri_feng@outlook.com 
*      
*/
public interface UserMapper {

	/**
	 * 持久层中根据username和password查询User
	 * @param username
	 * @param password
	 * @return User
	 */
	User selectUser(@Param("username") String username, @Param("password") String password);

	
	/**
	 * 持久层中添加User
	 * @param user
	 */
	void insertUser(User user);
}
