package com.yuri.erp.util;

import java.io.InputStream;
import java.util.Properties;

/**   
*    
* Project Name：erp   
* Class Name：BeanFactory   
* Description：Bean工厂,用来负责创建具体的Bean实例
* @author：yuriFeng  
* @date：2018年3月18日 下午4:43:38   
* Contact：yuri_feng@outlook.com 
*      
*/
public class BeanFactory {

	public static final String BEAN_PROPERTIES = "bean.properties";
	static Properties prop;

	static {
		try {
			prop = new Properties();
			InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(BEAN_PROPERTIES);
			prop.load(in);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 传入key,根据配置文件key-value对,得到value,再根据value得到Class实例,最后通过Class实例创建对象
	 * 例如:
	 * studentDao->com.tz.jdbc.dao.impl.StudentDaoImpl->StudentDaoImpl的Class实例->反射获取StudentDaoImpl的对象
	 */
	public static Object getBean(String beanNameKey) {
		Object obj = null;
		try {
			Class<?> c = Class.forName(prop.getProperty(beanNameKey));
			//反射创建对象
			obj = c.newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
