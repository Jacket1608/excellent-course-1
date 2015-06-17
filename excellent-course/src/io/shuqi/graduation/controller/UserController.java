package io.shuqi.graduation.controller;

import java.text.ParseException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import io.shuqi.graduation.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	/**
	 * 编辑用户信息
	 * @author shuqi
	 * @date   2015年4月30日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/user/personalInfo",method=RequestMethod.GET) 
	public ModelAndView personInfoIndex(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		return userService.personInfo(mv,request);
	}
	/**
	 * 编辑用户信息
	 * @author shuqi
	 * @date   2015年4月30日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/user/getPersonalInfo",method=RequestMethod.GET) 
	@ResponseBody public Map<String,Object> getPersonInfoJson(HttpServletRequest request){
		return userService.getPersonInfo(request);
	}
	/**
	 * 编辑用户信息action
	 * @author shuqi
	 * @date   2015年4月30日
	 * @version since 1.0
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="/user/personalInfo",method=RequestMethod.POST) 
	@ResponseBody public Map<String,Object> personInfoAction(HttpServletRequest request) throws ParseException{
		return userService.savePersonInfo(request);
	}
	/**
	 * 修改密码主页
	 * @author shuqi
	 * @date   2015年4月30日
	 * @version since 1.0
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="/user/modifyPassword",method=RequestMethod.GET) 
	public ModelAndView modifyPasswordIndex(HttpServletRequest request) throws ParseException{
		return new ModelAndView("/templet/modifyPassword") ;
	}
	/**
	 * 修改密码Action
	 * @author shuqi
	 * @date   2015年4月30日
	 * @version since 1.0
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="/user/modifyPassword",method=RequestMethod.POST) 
	@ResponseBody public Map<String,Object> modifyPasswordAction(HttpServletRequest request) throws ParseException{
		return userService.modifyPassword(request);
	}
	/**
	 * 我的新闻回复查看
	 * @author shuqi
	 * @date   2015年4月30日
	 * @version since 1.0
	 * @param request
	 * @param currentPage
	 * @param pageSize
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="/user/myNewsComments/{currentPage}/{pageSize}",method=RequestMethod.GET) 
	public ModelAndView myNewsComments(HttpServletRequest request,@PathVariable(value="currentPage") Long currentPage,@PathVariable(value="pageSize") int pageSize) throws ParseException{
		return userService.myNewsComments(request,currentPage,pageSize);
	}
	/**
	 * 管理员添加用户
	 * @author shuqi
	 * @date   2015年4月30日
	 * @version since 1.0
	 * @param request
	 * @param currentPage
	 * @param pageSize
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="/user/newUser",method=RequestMethod.GET) 
	public ModelAndView newUserIndex(HttpServletRequest request){
		return userService.newUserIndex(request);
	}
	/**
	 * 管理员添加用户
	 * @author shuqi
	 * @date   2015年4月30日
	 * @version since 1.0
	 * @param request
	 * @param currentPage
	 * @param pageSize
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="/user/newUser",method=RequestMethod.POST) 
	@ResponseBody public Map<String,Object> newUserAction(HttpServletRequest request){
		return userService.newUserAction(request);
	}
}
