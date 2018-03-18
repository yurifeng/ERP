package com.yuri.erp.service.impl;

import org.apache.ibatis.session.SqlSession;

import com.yuri.erp.entity.User;
import com.yuri.erp.mapper.UserMapper;
import com.yuri.erp.service.UserService;
import com.yuri.erp.util.MyBatiesUtil;

/**   
*    
* Project Name：erp   
* Class Name：UserServiceImpl   
* Description：UserServiceImpl实现类
* @author：yuriFeng  
* @date：2018年3月18日 下午4:43:15   
* Contact：yuri_feng@outlook.com 
*      
*/
public class UserServiceImpl implements UserService{
	
	SqlSession sqlSession = MyBatiesUtil.sqlSession();
	UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

	@Override
	public void registerUser(User user) {
		userMapper.insertUser(user);
	}

	@Override
	public User loign(String username, String password) {
		return userMapper.selectUser(username, password);
	}

	@Override
	public void logout() {
		
	}
}
