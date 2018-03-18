package com.yuri.erp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**   
*    
* Project Name：erp   
* Class Name：DateUtil   
* Description：Date日期工具类
* @author：yuriFeng  
* @date：2018年3月18日 下午4:44:11   
* Contact：yuri_feng@outlook.com 
*      
*/
public class DateUtil {
	/**
	 * 将字符串类型转换成Date类型
	 */
	public static Date parse(String date, String pattern) {
		Date d = null;
		try {
			d = new SimpleDateFormat(pattern).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	/**
	 * 将日期按照模板转换为字符串类型
	 */
	public static String formatDate(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 根据输入年月日来获得时间
	 */
	public static Date creatDate(int year, int month, int date) {
		Calendar c = Calendar.getInstance();
		c.set(year, month - 1, date);
		return c.getTime();
	}
}
