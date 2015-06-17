package io.shuqi.graduation.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import io.shuqi.graduation.service.AdminService;
import io.shuqi.graduation.service.NewsService;
import io.shuqi.graduation.service.NotifyService;
import io.shuqi.graduation.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller("adminController")
public class AdminController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private UserService userService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private NotifyService notifyService;
	/**
	 * 网站后台管理的默认页面
	 * @author shuqi
	 * @date   2015年4月28日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/admin"},method=RequestMethod.GET)
	public ModelAndView adminIndex(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		return adminService.adminIndex(mv, request);
	}
	/**
	 * 网站后台管理的管理员的网站基本信息
	 * @author shuqi
	 * @date   2015年4月28日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/admin/webBaseInfo"},method=RequestMethod.GET)
	public ModelAndView webBaseInfo(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		return adminService.webBaseInfo(mv, request);
	}
	/**
	 * 网站后台管理的管理员的教师注册管理
	 * @author shuqi
	 * @date   2015年4月28日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/admin/teacherRegisterManger"},method=RequestMethod.GET)
	public ModelAndView teacherRegisterManger(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		return adminService.teacherRegisterManger(mv, request);
	}
	/**
	 * 网站后台管理的管理员的教师注册管理
	 * @author shuqi
	 * @date   2015年4月28日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/admin/studentRegisterManger"},method=RequestMethod.GET)
	public ModelAndView studentRegisterManger(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		return adminService.studentRegisterManger(mv, request);
	}
	/**
	 * 网站后台管理的管理员的教师注册确认
	 * @author shuqi
	 * @date   2015年4月28日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/admin/confirmTeacher/{teacherId}"},method=RequestMethod.POST)
	@ResponseBody public Map<String,Object> confirmTeacher(HttpServletRequest request,@PathVariable Long teacherId){
		return adminService.confirmTeacher(request,teacherId);
	}
	/**
	 * 网站后台管理的管理员的教师注册拒绝
	 * @author shuqi
	 * @date   2015年4月28日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/admin/refuseTeacher/{teacherId}"},method=RequestMethod.POST)
	@ResponseBody public Map<String,Object> refuseTeacher(HttpServletRequest request,@PathVariable Long teacherId){
		return adminService.refuseTeacher(request,teacherId);
	}
	/**
	 * 网站后台管理的管理员的用户注册停用
	 * @author shuqi
	 * @date   2015年4月28日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/admin/stopUser/{userID}"},method=RequestMethod.POST)
	@ResponseBody public Map<String,Object> stopTeacher(HttpServletRequest request,@PathVariable Long userID){
		return userService.stopUser(request,userID);
	}
	/**
	 * 网站后台管理的管理员的用户注册停用
	 * @author shuqi
	 * @date   2015年4月28日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/admin/restartUser/{userID}"},method=RequestMethod.POST)
	@ResponseBody public Map<String,Object> restartTeacher(HttpServletRequest request,@PathVariable Long userID){
		return userService.restartUser(request,userID);
	}
	/**
	 * 网站后台管理的管理员的教师注册停用
	 * @author shuqi
	 * @date   2015年4月28日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/admin/resetPassword/{teacherId}"},method=RequestMethod.POST)
	@ResponseBody public Map<String,Object> resetPassword(HttpServletRequest request,@PathVariable Long teacherId){
		return userService.resetPassword(request,teacherId);
	}
	/**
	 * 公告管理
	 * @author shuqi
	 * @date   2015年4月30日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/admin/notifyManager/{crrentPage}/{pageSize}"},method=RequestMethod.GET)
	public ModelAndView notifyManager(HttpServletRequest request,@PathVariable Long crrentPage,@PathVariable int pageSize){
		return notifyService.notifyManager(request,crrentPage,pageSize);
	}
	/**
	 * 新闻管理
	 * @author shuqi
	 * @date   2015年4月30日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/admin/newsManager/{crrentPage}/{pageSize}"},method=RequestMethod.GET)
	public ModelAndView newsManager(HttpServletRequest request,@PathVariable Long crrentPage,@PathVariable int pageSize){
		return newsService.newsManager(request,crrentPage,pageSize);
	}
}
