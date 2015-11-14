package io.shuqi.graduation.service;


import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;







import org.springframework.web.servlet.ModelAndView;

public abstract class UserService {
	/**
	 * 注册用户，时保持
	 * @author shuqi
	 * @date   2015年4月27日
	 * @version since 1.0
	 * @param mv
	 * @param request
	 * @return
	 */
	public abstract ModelAndView register(ModelAndView mv,HttpServletRequest request);
	/**
	 * 登录
	 * @author shuqi
	 * @date   2015年4月27日
	 * @version since 1.0
	 * @param mv
	 * @param request
	 * @return
	 */
	public abstract ModelAndView Login(ModelAndView mv, HttpServletRequest request);
	/**
	 * 重置用户密码
	 * @author shuqi
	 * @date   2015年4月29日
	 * @version since 1.0
	 * @param request
	 * @param teacherId
	 * @return
	 */
	public abstract Map<String, Object> resetPassword(HttpServletRequest request, Long userID);
	/**
	 * 启用用户账号
	 * @author shuqi
	 * @date   2015年4月28日
	 * @version since 1.0
	 * @param mv
	 * @param request
	 * @return
	 */
	public abstract Map<String,Object> restartUser(HttpServletRequest request,Long id);
	/**
	 * 停用用户账号
	 * @author shuqi
	 * @date   2015年4月28日
	 * @version since 1.0
	 * @param mv
	 * @param request
	 * @return
	 */
	public abstract Map<String,Object> stopUser(HttpServletRequest request,Long id);
	/**
	 * 显示个人信息
	 * @author shuqi
	 * @date   2015年4月29日
	 * @version since 1.0
	 * @param mv
	 * @param request
	 * @return
	 */
	public abstract ModelAndView personInfo(ModelAndView mv, HttpServletRequest request);
	/**
	 * 修改个人信息
	 * @author shuqi
	 * @date   2015年4月30日
	 * @version since 1.0
	 * @param mv
	 * @param request
	 * @return
	 * @throws ParseException 
	 */
	public abstract Map<String,Object> savePersonInfo(HttpServletRequest request) throws ParseException;
	/**
	 * 修改密码
	 * @author shuqi
	 * @date   2015年4月30日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	public abstract Map<String, Object> modifyPassword(HttpServletRequest request);
	/**
	 * 我的新闻评论
	 * @author shuqi
	 * @date   2015年4月30日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	public abstract ModelAndView myNewsComments(HttpServletRequest request,Long crrentIndex,int pageSize);
	/**
	 * 新建用户，判断是否有权限
	 * @author shuqi
	 * @date   2015年5月1日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	public abstract ModelAndView newUserIndex(HttpServletRequest request);
	/**
	 * 用户注册动作
	 * @author shuqi
	 * @date   2015年5月1日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	public abstract Map<String,Object> newUserAction(HttpServletRequest request);
	/**
	 * 得到用户信息
	 * @author shuqi
	 * @date   2015年5月12日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	public abstract Map<String, Object> getPersonInfo(HttpServletRequest request);
}
