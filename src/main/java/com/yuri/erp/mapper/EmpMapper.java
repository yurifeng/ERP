package com.yuri.erp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yuri.erp.entity.Employee;

/**   
*    
* Project Name：erp   
* Class Name：EmpMapper   
* Description：Emp的接口
* @author：yuriFeng  
* @date：2018年3月18日 下午4:29:13   
* Contact：yuri_feng@outlook.com 
*      
*/
public interface EmpMapper {

	
	/**
	 * 持久层中通过id查询Employee
	 * @param id
	 * @return Employee
	 */
	Employee selectEmpById(@Param("id") Long id);

	/**
	 * 持久层中查询所有Emp
	 * @return List<Employee>
	 */
	List<Employee> selectAllEmps();

	
	/**
	 * 持久层中按分页查询Employee
	 * @param pageNow 当前的页码
	 * @param pageSize 一共的页数
	 * @return List<Employee>
	 */
	List<Employee> selectEmpsByPage(@Param("pageNow") int pageNow, @Param("pageSize") int pageSize);

	/**
	 * 持久层中删除Employee
	 * @param employee
	 */
	void deleteEmp(Employee employee);

	
	/**
	 * 持久层中添加Employee
	 * @param employee
	 */
	void insertEmp(Employee employee);

	/**
	 * 持久层中更新Employee
	 * @param employee
	 */
	void updateEmp(Employee employee);
	
	
	/**
	 * 持久层中计算Employee的个数
	 * @return java.lang.Integer
	 */
	int selectEmpCount();
}
