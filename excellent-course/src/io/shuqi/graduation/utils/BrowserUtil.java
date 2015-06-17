package io.shuqi.graduation.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import io.shuqi.graduation.enumtype.BrowserEnum;

/**
 * 浏览器工具
 * @author shuqi
 * @date   2015年4月27日
 * @version since 1.0
 */
public class BrowserUtil {
	
	/**
	 * 得到浏览器版本的枚举
	 * @author shuqi
	 * @date   2015年4月27日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	public static BrowserEnum  getBrowserEnum(String userAgent){
		if(!StringUtil.isNull(userAgent)){
			if(userAgent.indexOf("MSIE")>0){
				return BrowserEnum.IE;
			}else if(userAgent.indexOf("Chrome")>0){
				return BrowserEnum.CHROME;
				
			}else if(userAgent.indexOf("Firefox")>0){
				return BrowserEnum.FIREFOX;
			}else{
				return BrowserEnum.UNKNOW;
			}
		}
		return BrowserEnum.UNKNOW;
	}
	/**
	 * 得到浏览器版本的枚举
	 * @author shuqi
	 * @date   2015年4月27日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	public static BrowserEnum  getBrowserEnum(HttpServletRequest request){
		return getBrowserEnum(request.getHeader("User-Agent"));
	}
	/**
	 * 得到IE的版本
	 * @author shuqi
	 * @date   2015年4月27日
	 * @version since 1.0
	 * @param request  HttpServletRequest对象
	 * @return 如果不是IE则返回-1
	 */
	public static float  getIEVersion(HttpServletRequest request){
		return getIEVersion(request.getHeader("User-Agent"));
	}
	/**
	 * 得到IE的版本
	 * @author shuqi
	 * @date   2015年4月27日
	 * @version since 1.0
	 * @param userAgent 请求的User-Agent
	 * @return 如果不是IE则返回-1
	 */
	public static float  getIEVersion(String userAgent){
		if (getBrowserEnum(userAgent).isIE()) {
			Pattern pattern  = Pattern.compile("MSIE( )+(\\d+\\.\\d+)+");
			Matcher matcher =  pattern.matcher(userAgent);
			if(matcher.find()){
				return Float.valueOf(matcher.group(2));
			}else{
				return -1;
			}
		}
		return -1;
	}
}
