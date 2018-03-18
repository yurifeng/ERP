package com.yuri.erp.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.yuri.erp.entity.User;
import com.yuri.erp.mapper.UserMapper;
import com.yuri.erp.util.MyBatiesUtil;


/**   
*    
* Project Name：erp   
* Class Name：UserMapperTest   
* Description：User持久层的测试类
* @author：yuriFeng  
* @date：2018年3月18日 下午4:47:25   
* Contact：yuri_feng@outlook.com 
*      
*/
public class UserMapperTest {

	SqlSession sqlSession = MyBatiesUtil.sqlSession();
	UserMapper userMapper = null;

	@Before
	public void setUp() {
		userMapper = sqlSession.getMapper(UserMapper.class);
	}

	@After
	public void tearDown() {
		MyBatiesUtil.close(sqlSession);
	}

	@Test
	public void testSelectUser() {
		User user = userMapper.selectUser("admin", "123456");
		System.out.println(user);
	}

	@Test
	public void testInsertUser() {
		User user = new User();
		user.setUsername("admin");
		user.setPassword("123456");
		userMapper.insertUser(user);
		sqlSession.commit();
	}
}
