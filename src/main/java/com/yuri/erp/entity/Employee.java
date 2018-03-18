package com.yuri.erp.entity;

import java.util.Date;

import lombok.Data;

/**   
*    
* Project Name：erp   
* Class Name：Employee   
* Description：Employee实体类
* @author：yuriFeng  
* @date：2018年3月18日 下午4:21:11   
* Contact：yuri_feng@outlook.com 
*      
*/
@Data
public class Employee {
	
	private Long id;
	private String headImage;
	private String name;
	private Date hiredate;
	private Gender gender;
	private Double salary;
	
}
