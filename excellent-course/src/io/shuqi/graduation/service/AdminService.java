package io.shuqi.graduation.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

public abstract class AdminService {
	/**
	 * 网站后台管理的默认页面
	 * @author shuqi
	 * @date   2015年4月28日
	 * @version since 1.0
	 * @param mv
	 * @param request
	 * @return
	 */
	public abstract ModelAndView adminIndex(ModelAndView mv,HttpServletRequest request);
	/**
	 * 网站基本信息
	 * @author shuqi
	 * @date   2015年4月28日
	 * @version since 1.0
	 * @param mv
	 * @param request
	 * @return
	 */
	public abstract ModelAndView webBaseInfo(ModelAndView mv, HttpServletRequest request);
	/**
	 * 教师注册管理
	 * @author shuqi
	 * @date   2015年4月28日
	 * @version since 1.0
	 * @param mv
	 * @param request
	 * @return
	 */
	public abstract ModelAndView teacherRegisterManger(ModelAndView mv, HttpServletRequest request);
	/**
	 * 教师注册确认
	 * @author shuqi
	 * @date   2015年4月28日
	 * @version since 1.0
	 * @param mv
	 * @param request
	 * @return
	 */
	public abstract Map<String,Object> confirmTeacher(HttpServletRequest request,Long id);
	/**
	 * 拒绝教师注册
	 * @author shuqi
	 * @date   2015年4月28日
	 * @version since 1.0
	 * @param mv
	 * @param request
	 * @return
	 */
	public abstract Map<String,Object> refuseTeacher(HttpServletRequest request,Long id);
	/**
	 * 学生注册管理
	 * @author shuqi
	 * @date   2015年4月28日
	 * @version since 1.0
	 * @param mv
	 * @param request
	 * @return
	 */
	public abstract ModelAndView studentRegisterManger(ModelAndView mv, HttpServletRequest request);
}
