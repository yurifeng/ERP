package com.yuri.erp.entity;

/**   
*    
* Project Name：erp   
* Class Name：Gender   
* Description：Gender枚举类
* @author：yuriFeng  
* @date：2018年3月18日 下午4:21:32   
* Contact：yuri_feng@outlook.com 
*      
*/
public enum Gender {

	/**
	 * 男
	 */
	MALE("男"),
	
	/**
	* 女
	*/
	FEMALE("女");

	private String str;

	private Gender(String str) {
		this.str = str;
	}

	@Override
	public String toString() {
		return str;
	}
}
