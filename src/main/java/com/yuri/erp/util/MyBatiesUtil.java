package com.yuri.erp.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**   
*    
* Project Name：erp   
* Class Name：MyBatiesUtil   
* Description：Mybatis的工具类
* @author：yuriFeng  
* @date：2018年3月18日 下午4:44:31   
* Contact：yuri_feng@outlook.com 
*      
*/
public class MyBatiesUtil {

	private static SqlSessionFactory sqlSessionFactory = null;

	static {
		try (InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml")) {
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static SqlSession sqlSession() {
		return sqlSessionFactory == null ? null : sqlSessionFactory.openSession();
	}

	public static void close(SqlSession sqlSession) {
		if (sqlSession != null) {
			sqlSession.close();
		}
	}
}
