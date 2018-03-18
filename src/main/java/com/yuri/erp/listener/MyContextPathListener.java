package com.yuri.erp.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


/**   
*    
* Project Name：erp   
* Class Name：MyContextPathListener   
* Description：监听器,作用是仅当应用启动时,把当前应用路径添加到servlet上下文范围中
* @author：yuriFeng  
* @date：2018年3月18日 下午4:28:22   
* Contact：yuri_feng@outlook.com 
*      
*/
@WebListener
public class MyContextPathListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		String base = sce.getServletContext().getContextPath();
		sce.getServletContext().setAttribute("base", base);
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		sce.getServletContext().removeAttribute("base");
	}
}
