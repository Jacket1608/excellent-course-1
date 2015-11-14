package io.shuqi.graduation.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public abstract class InfoService {
	
	/**
	 * 信息中心主页
	 * @author shuqi
	 * @date   2015年5月13日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	public abstract ModelAndView getIndex(HttpServletRequest request);

	/**
	 * 信息中心--ajax请求
	 * @author shuqi
	 * @date   2015年5月13日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	public abstract ModelAndView infoAjax(HttpServletRequest request,HttpServletResponse response);

	/**
	 * 信息中心--查看请求
	 * @author shuqi
	 * @date   2015年5月13日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	public abstract ModelAndView infoViewJsp(Long id, String sourseType, HttpServletRequest request);

}
