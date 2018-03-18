package com.yuri.erp.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.yuri.erp.entity.Employee;
import com.yuri.erp.mapper.EmpMapper;
import com.yuri.erp.service.EmpService;
import com.yuri.erp.util.MyBatiesUtil;
import com.yuri.erp.util.PageBean;

/**   
*    
* Project Name：erp   
* Class Name：EmpServiceImpl   
* Description：EmpServiceImpl实现类
* @author：yuriFeng  
* @date：2018年3月18日 下午4:42:44   
* Contact：yuri_feng@outlook.com 
*      
*/
public class EmpServiceImpl implements EmpService {

	SqlSession sqlSession = MyBatiesUtil.sqlSession();
	EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);

	@Override
	public Employee findEmpById(Long id) {
		return empMapper.selectEmpById(id);
	}

	@Override
	public List<Employee> findAllEmps() {
		return empMapper.selectAllEmps();
	}

	@Override
	public void removeEmp(Employee employee) {
		empMapper.deleteEmp(employee);
		sqlSession.commit();
	}

	@Override
	public void addEmp(Employee employee) {
		empMapper.insertEmp(employee);
		sqlSession.commit();
	}

	@Override
	public void editEmp(Employee employee) {
		empMapper.updateEmp(employee);
		sqlSession.commit();
	}

	@Override
	public PageBean<Employee> findEmpsByPage(int pageNow, int pageSize) {
		PageBean<Employee> pageBean = new PageBean<>();
		List<Employee> emps = empMapper.selectEmpsByPage(pageNow, pageSize);
		pageBean.setPageDatas(emps);
		pageBean.setPageNow(pageNow);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalRecords(empMapper.selectEmpCount());
		return pageBean;
	}
}
