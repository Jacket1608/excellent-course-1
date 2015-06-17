package io.shuqi.graduation.controller;


import io.shuqi.graduation.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
/**
 * 登录Controller
 * LoginController.java
 * @author shuqi
 * @date   2015-4-24
 * @version since 1.0
 */
@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	/**
	 * 进入登录页面
	 * @author shuqi
	 * @date   2015-4-24
	 * @version since 1.0
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public ModelAndView loginindex(){
		ModelAndView mv = new ModelAndView("/templet/login");
		return mv;
	}
	/**
	 * 进入登出页面
	 * @author shuqi
	 * @date   2015-4-24
	 * @version since 1.0
	 * @return
	 */
	@RequestMapping(value="/loginout",method=RequestMethod.GET)
	public ModelAndView loginoutAction(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.removeAttribute("currentUser");
		ModelAndView mv = new ModelAndView("redirect:/login");
		return mv;
	}
	/**
	 * 用户登录操作
	 * @author shuqi
	 * @date   2015-4-24
	 * @version since 1.0
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ModelAndView loginAction(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		mv = userService.Login(mv,request);
		return mv;
	}
	/**
	 * 用户登录操作
	 * @author shuqi
	 * @date   2015-4-24
	 * @version since 1.0
	 * @return
	 */
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public ModelAndView registerIndex(){
		ModelAndView mv = new ModelAndView("/templet/register");
		return mv;
	}
	/**
	 * 用户登录操作
	 * @author shuqi
	 * @date   2015-4-24
	 * @version since 1.0
	 * @return
	 */
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public ModelAndView registerAction(HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		return userService.register(mv,request);
	}
}
