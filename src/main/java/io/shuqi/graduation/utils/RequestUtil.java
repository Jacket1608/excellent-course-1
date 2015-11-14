package io.shuqi.graduation.utils;

import javax.servlet.http.HttpServletRequest;
/**
 * request的请求参数获取工具类
 * RequestUtil.java
 * @author shuqi
 * @date   2015-4-24
 * @version since 1.0
 */
public class RequestUtil {
	/**
	 * 得到一个String类型的request参数
	 * @author shuqi
	 * @date   2015-4-24
	 * @version since 1.0
	 * @param request 请求对象
	 * @param paraName 请求的参数名
	 * @return
	 */
	public static String getString(HttpServletRequest request,String paraName){
		String result =request.getParameter(paraName);
		if(!(result!=null&&!"".equals(result))){
			result ="";
		}
		return result;
	}
	/**
	 * 得到一个Long类型的request参数
	 * @author shuqi
	 * @date   2015-4-24
	 * @version since 1.0
	 * @param request 请求对象
	 * @param paraName 请求的参数名
	 * @return
	 */
	public static Long getLong(HttpServletRequest request,String paraName){
		return Long.valueOf(getString(request, paraName));
	}
}
