package com.yuri.erp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**   
*    
* Project Name：erp   
* Class Name：CharacterEncodingFilter   
* Description：字符编码过滤器
* @author：yuriFeng  
* @date：2018年3月18日 下午4:23:42   
* Contact：yuri_feng@outlook.com 
*      
*/
@WebFilter(filterName = "CharacterEncodingFilter", urlPatterns = "/*", initParams = {
		@WebInitParam(name = "encoding", value = "utf-8") })
public class CharacterEncodingFilter implements Filter {

	private String defaultEncoding = "UTF-8";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		//获取拦截器初始化参数
		String encoding = filterConfig.getInitParameter("encoding");

		if (encoding != null && !"".equals(encoding)) {
			defaultEncoding = encoding;
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		request.setCharacterEncoding(defaultEncoding);
		response.setContentType("text/html;charset=" + defaultEncoding + "");
		//通过
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		//不打算重写此方法(该方法可以为空)
		//但是重写的方法框架必须要,只是为了符合规范
	}
}
