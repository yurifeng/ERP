package com.yuri.erp.service;

import java.util.List;

import com.yuri.erp.entity.Employee;
import com.yuri.erp.util.PageBean;

/**   
*    
* Project Name：erp   
* Class Name：EmpService   
* Description：Emp的业务层
* @author：yuriFeng  
* @date：2018年3月18日 下午4:37:33   
* Contact：yuri_feng@outlook.com 
*      
*/
public interface EmpService {

	/**
	 * 业务层通过id查询Employee
	 * @param id
	 * @return Employee
	 */
	Employee findEmpById(Long id);

	/**
	 * 业务层查询所有Employee
	 * @return List<Employee>
	 */
	List<Employee> findAllEmps();

	/**
	 * 业务层删除Employee
	 * @param employee
	 */
	void removeEmp(Employee employee);

	/**
	 * 业务层添加Employee
	 * @param employee
	 */
	void addEmp(Employee employee);

	/**
	 * 业务层修改Employee
	 * @param employee
	 */
	void editEmp(Employee employee);

	/**
	 * 业务层通过分页查询Employee
	 * @param pageNow 当前页码
	 * @param pageSize 总页数
	 * @return PageBean<Employee>
	 */
	PageBean<Employee> findEmpsByPage(int pageNow, int pageSize);
}
