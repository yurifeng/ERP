package com.yuri.erp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.yuri.erp.entity.Employee;
import com.yuri.erp.entity.Gender;
import com.yuri.erp.service.EmpService;
import com.yuri.erp.util.BeanFactory;
import com.yuri.erp.util.DateUtil;
import com.yuri.erp.util.PageBean;

/**   
*    
* Project Name：erp   
* Class Name：EmpController   
* Description：Emp控制器
* @author：yuriFeng  
* @date：2018年3月18日 下午4:15:41   
* Contact：yuri_feng@outlook.com 
*      
*/
@MultipartConfig
@WebServlet(name = "EmpController", urlPatterns = "/permission/emp/*")
public class EmpController extends HttpServlet {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -6839543948866973046L;

	/**
	 * 通过BeanFactory拿到empService对象(避免在控制层写入new操作)
	 */
	EmpService empSerive = (EmpService) BeanFactory.getBean("empService");

	/**
	 * 重写HttpServlet中的doGet()方法
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();

		String pathInfo = request.getPathInfo();

		//定义下个跳转页面
		String nextPage = null;
		if ("/list".equals(pathInfo)) {
			//List<Employee> emps = empSerive.findAllEmps();
			PageBean<Employee> page = empSerive.findEmpsByPage(1, 3);
			//把查询出的员工集合添加到共享数据范围给下个页面提取
			//request.setAttribute("emps", emps);
			request.setAttribute("page", page);
			//设置跳转
			nextPage = "/WEB-INF/pages/emp/list.jsp";
		} else if ("/getData".equals(pathInfo)) {
			Integer pageNow = Integer.parseInt(request.getParameter("pageNow"));
			Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
			PageBean<Employee> page = empSerive.findEmpsByPage(pageNow, pageSize);
			request.setAttribute("page", page);
			nextPage = "/WEB-INF/pages/emp/data.jsp";
		} else if ("/upload".equals(pathInfo)) {
			//执行文件上传操作
			//String savePath = request.getServletContext().getRealPath("/assets/img");
			String fileName = uploadHeadImage(request);

			//更新数据库记录
			String id = request.getParameter("id");
			Employee employee = empSerive.findEmpById(Long.parseLong(id));
			employee.setHeadImage(fileName);
			empSerive.editEmp(employee);

			//设置跳转
			nextPage = "/permission/emp/list";

		} else if ("/edit".equals(pathInfo)) {
			//判断是否要上传头像
			Part part = request.getPart("file");
			String header = part.getHeader("content-disposition");
			String fileName = getFileName(header);
			if ("".equals(fileName)) {
				fileName = request.getParameter("head");
			}
			//执行更新操作
			Employee employee = new Employee();
			employee.setId(Long.parseLong(request.getParameter("id")));
			employee.setHeadImage(fileName);
			employee.setName(request.getParameter("name"));
			employee.setHiredate(DateUtil.parse(request.getParameter("hiredate"), "yyyy-MM-dd"));
			employee.setGender(Enum.valueOf(Gender.class, request.getParameter("gender")));
			employee.setSalary(Double.parseDouble(request.getParameter("salary")));
			empSerive.editEmp(employee);
			nextPage = "/permission/emp/list";
		} else if ("/download".equals(pathInfo)) {
			response.setContentType("application/download");
			String filename = "D:\\image\\" + request.getParameter("path");
			File f = new File(filename);
			if (f.exists()) {
				FileInputStream fis = new FileInputStream(f);
				filename = new String(f.getName().getBytes("UTF-8"), "ISO-8859-1");
				byte[] b = new byte[fis.available()];
				fis.read(b);
				response.setHeader("Content-Disposition", "attachment; filename=" + filename + "");
				ServletOutputStream sos = response.getOutputStream();
				sos.write(b);
				sos.flush();
				sos.close();
				fis.close();
			}
		} else if ("/delete".equals(pathInfo)) {
			String id = request.getParameter("id");
			Employee employee = empSerive.findEmpById(Long.parseLong(id));
			try {
				empSerive.removeEmp(employee);
				out.print("1");
			} catch (Exception e) {
				out.print("0");
			}
		}
		if (nextPage != null) {
			request.getRequestDispatcher(nextPage).forward(request, response);
		}
	}

	/**
	 * 重写HttpServlet中的doPost()方法
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * 根据请求头解析出文件名
	 * 请求头的格式：火狐和google浏览器下：form-data; name="file"; filename="上传的文件名"
	 *           IE浏览器下：form-data; name="file"; filename="上传的文件名"
	 * @param header 请求头
	 * @return java.lang.String 文件名
	 */
	private String getFileName(String header) {
		/**
		 * String[] tempArr1 = header.split(";");代码执行完之后，在不同的浏览器下，tempArr1数组里面的内容稍有区别
		 * 火狐或者google浏览器下：tempArr1={form-data,name="file",filename="snmp4j--api.zip"}
		 * IE浏览器下：tempArr1={form-data,name="file",filename="E:\snmp4j--api.zip"}
		 */
		String[] tempArr1 = header.split(";");
		/**
		 *火狐或者google浏览器下：tempArr2={filename,"snmp4j--api.zip"}
		 *IE浏览器下：tempArr2={filename,"E:\snmp4j--api.zip"}
		 */
		String[] tempArr2 = tempArr1[2].split("=");
		//获取文件名，兼容各种浏览器的写法
		String fileName = tempArr2[1].substring(tempArr2[1].lastIndexOf("\\") + 1).replaceAll("\"", "");
		return fileName;
	}

	
	/**
	 * 头像上传的方法
	 * @param request
	 * @return java.lang.String
	 * @throws IOException
	 * @throws ServletException
	 */
	private String uploadHeadImage(HttpServletRequest request) throws IOException, ServletException {
		String savePath = "D:/image";
		Part part = request.getPart("file");
		String header = part.getHeader("content-disposition");
		String fileName = getFileName(header);
		part.write(savePath + File.separator + fileName);
		return fileName;
	}
}
