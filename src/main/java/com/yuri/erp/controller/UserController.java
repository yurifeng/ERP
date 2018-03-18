package com.yuri.erp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yuri.erp.entity.User;
import com.yuri.erp.service.UserService;
import com.yuri.erp.util.BeanFactory;


/**   
*    
* Project Name：erp   
* Class Name：UserController   
* Description：User的控制器
* @author：yuriFeng  
* @date：2018年3月18日 下午4:20:32   
* Contact：yuri_feng@outlook.com 
*      
*/
@WebServlet(name = "UserController", urlPatterns = "/permission/user/*")
public class UserController extends HttpServlet {

	private static final long serialVersionUID = -6839543948866973046L;

	UserService userService = (UserService) BeanFactory.getBean("userService");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String pathInfo = request.getPathInfo();

		//定义状态变量
		boolean isRedirect = false;

		//定义下个跳转页面
		String nextPage = "";
		if ("/preLogin".equals(pathInfo)) {
			//获取名为user的cookie值(用户名:密码)
			String userInfo = getCookieValue(request.getCookies(), "user");
			if (userInfo != null) {
				//切割用户信息,得到用户名密码的数组
				String[] infoArr = userInfo.split(":");
				//分别把用户名和密码设置到请求范围
				request.setAttribute("username", infoArr[0]);
				request.setAttribute("password", infoArr[1]);
			}
			nextPage = "/WEB-INF/pages/user/login.jsp";

		} else if ("/login".equals(pathInfo)) {
			//从请求中获取表单提交的参数
			String username = request.getParameter("username");
			//注意:避免mybatis参数为null时的报错,设置当参数为null时使用""代替(也可以设置mybatis全局配置null参数)
			//username = (username == null) ? "" : username;
			String password = request.getParameter("password");
			//password = (password == null) ? "" : password;
			String nologin = request.getParameter("nologin");//七天免登陆标识

			//说明执行登陆操作
			User user = userService.loign(username, password);

			//关键操作,把登陆成功的用户信息放入到Session中
			session.setAttribute("user", user);

			//判断
			if (user != null) {
				//判断是否要免登陆
				Cookie c = null;
				if ("0".equals(nologin)) {
					c = new Cookie("user", user.toString());
					c.setMaxAge(7 * 24 * 60 * 60);
				} else {
					//说明不需要免登陆,则覆盖原有cookie
					c = new Cookie("user", null);
					c.setMaxAge(0);//0表示删除cookie
				}
				//设置cookie全局访问
				c.setPath("/");
				//把cookie添加到响应中
				response.addCookie(c);

				//说明登陆成功,则跳转到下一个页面
				nextPage = "/permission/emp/list";
			} else {
				//登陆失败,则停留在登陆页面并且作提示
				nextPage = "/WEB-INF/pages/user/login.jsp?info=1";
			}
		} else if ("/logout".equals(pathInfo)) {
			//说明要退出操作
			//1.销毁session
			session.invalidate();
			//2.页面重定向到登陆页面
			nextPage = request.getContextPath() + "/permission/user/preLogin";
			//修改状态变量
			isRedirect = true;
		}
		if (isRedirect) {
			response.sendRedirect(nextPage);
			return;
		}
		request.getRequestDispatcher(nextPage).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * 从cookie数组中获取指定名称的cookie
	 */
	private String getCookieValue(Cookie[] cookies, String cookieName) {
		if (cookies == null) {
			return null;
		}
		String value = null;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(cookieName)) {
				value = cookie.getValue();
			}
		}
		return value;
	}
}
