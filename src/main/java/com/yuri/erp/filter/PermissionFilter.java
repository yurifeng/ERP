package com.yuri.erp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yuri.erp.entity.User;

/**   
*    
* Project Name：erp   
* Class Name：PermissionFilter   
* Description：权限控制拦截器
* @author：yuriFeng  
* @date：2018年3月18日 下午4:25:31   
* Contact：yuri_feng@outlook.com 
*      
*/
@WebFilter(filterName = "PermissionFilter", urlPatterns = "/permission/*")
public class PermissionFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		//把request和response转换成http版本
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null && !"/preLogin".equals(request.getPathInfo()) && !"/login".equals(request.getPathInfo())) {
			response.sendRedirect(request.getContextPath() + "/permission/user/preLogin?info=3");
		} else {
			//通过
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
	}
}
