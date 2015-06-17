package io.shuqi.graduation.utils;
/**
 * 字符串的工具类
 * StringUtil.java
 * @author shuqi
 * @date   2015-4-24
 * @version since 1.0
 */
public class StringUtil {

	public static boolean isEmailString(String email){
		if(!isNull(email)){
			return email.matches("^\\w+@\\w+(\\.\\w{2,3}){1,2}$");
		}else{
			return false;
		}
	}
	public static boolean isNumberString(String email){
		if(!isNull(email)){
			return email.matches("^\\d+$");
		}else{
			return false;
		}
	}
	public static boolean isNull(String string){
		if(string!=null&&!"".equals(string.trim())){
			return false;
		}else{
			return true;
		}
			
	}
	public static boolean isWEbNull(String string){
		if(string!=null&&!"".equals(string.trim())&&!"undefined".equals(string.trim())&&!"null".equals(string.trim())){
			return true;
		}else{
			return false;
		}
		
	}
	public static boolean isDateString(String date) {
		if(!isNull(date)){
			return date.matches("^[1-9]\\d{3}\\-\\d{1,2}\\-\\d{1,2}$");
		}else{
			return false;
		}
	}
}
