package com.yuri.erp.entity;

import lombok.Data;

/**   
*    
* Project Name：erp   
* Class Name：User   
* Description：User实体类
* @author：yuriFeng  
* @date：2018年3月18日 下午4:23:24   
* Contact：yuri_feng@outlook.com 
*      
*/
@Data
public class User {

	private Long id;
	private String username;
	private String password;

	@Override
	public String toString() {
		return username + ":" + password;
	}
}
