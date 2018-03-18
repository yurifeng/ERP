package com.yuri.erp.mapper;

import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.yuri.erp.mapper.EmpMapper;
import com.yuri.erp.util.MyBatiesUtil;

/**   
*    
* Project Name：erp   
* Class Name：EmpMapperTest   
* Description：Emp持久层的测试类
* @author：yuriFeng  
* @date：2018年3月18日 下午4:47:06   
* Contact：yuri_feng@outlook.com 
*      
*/
public class EmpMapperTest {

	SqlSession sqlSession = MyBatiesUtil.sqlSession();
	EmpMapper empMapper = null;

	@Before
	public void setUp() {
		empMapper = sqlSession.getMapper(EmpMapper.class);
	}

	@After
	public void tearDown() {
		MyBatiesUtil.close(sqlSession);
	}

	@Test
	public void testSelectAllEmps() {
		empMapper.selectAllEmps().forEach(System.out::println);
	}

	@Test
	public void testSelectEmpById() {
		System.out.println(empMapper.selectEmpById(1L));
	}

	@Test
	public void testSelectEmpsByPage() {
		empMapper.selectEmpsByPage(2, 3).forEach(System.out::println);
	}
	
	@Test
	public void testSelectEmpCount() {
		System.out.println(empMapper.selectEmpCount());
	}
}
