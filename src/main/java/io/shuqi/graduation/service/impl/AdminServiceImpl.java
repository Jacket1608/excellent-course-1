package io.shuqi.graduation.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import io.shuqi.graduation.dao.UserDao;
import io.shuqi.graduation.domain.User;
import io.shuqi.graduation.domain.bo.SiteInfoCount;
import io.shuqi.graduation.enumtype.UserTypeEnum;
import io.shuqi.graduation.service.AdminService;
import io.shuqi.graduation.service.SiteInfoCountQuery;
import io.shuqi.graduation.utils.DateUtil;

@Service("adminService")
@Transactional
public class AdminServiceImpl extends AdminService {

	@Autowired
	private SiteInfoCountQuery siteInfoCountQuery;
	@Autowired
	private UserDao userDao;
	@Override
	public ModelAndView adminIndex(ModelAndView mv,HttpServletRequest request) {
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		//如果没登陆就去登录吧
		if(currentUser==null){
			mv.setViewName("redirct:/login");
			return mv;
		}
		//如果别名为空，则后端显示登录名
		if(currentUser.getNickName()==null){
			mv.addObject("currentUserName", currentUser.getLoginName());
			mv.addObject("lastLogintime", DateUtil.formatDate(currentUser.getLastLoginTime()));
		}else{
			mv.addObject("currentUserName", currentUser.getNickName());
			mv.addObject("lastLogintime", DateUtil.formatDate(currentUser.getLastLoginTime()));
		}
		switch (UserTypeEnum.getUserTypeEnum(currentUser.getUserType())) {
			case ADMIN:
				//为admin准备数据
				mv = getAdminIndexData(mv,request);
				mv.setViewName("/admin/admin");
				break;
			case TEACHER:
				//为teacher准备数据
				mv = getTeacherIndexData(mv,request);
				mv.setViewName("/admin/teacher");
				break;
			case STUDENT:
				//为student准备数据
				mv = getStudentIndexData(mv,request);
				mv.setViewName("/admin/student");
				break;
			default:
				mv.setViewName("redirct:/login");
				break;
		}
		return mv;
	}
	/**
	 * 得到学生的数据
	 * @author shuqi
	 * @date   2015年4月28日
	 * @version since 1.0
	 * @param mv
	 * @param request
	 * @return
	 */
	private ModelAndView getStudentIndexData(ModelAndView mv, HttpServletRequest request) {
		mv.setViewName("/admin/student");
		return mv;
	}
	/**
	 * 得到教师的数据
	 * @author shuqi
	 * @date   2015年4月28日
	 * @version since 1.0
	 * @param mv
	 * @param request
	 * @return
	 */
	private ModelAndView getTeacherIndexData(ModelAndView mv, HttpServletRequest request) {
		mv.setViewName("/admin/teacher");
		return mv;
	}
	/**
	 * 得到管理员的数据
	 * @author shuqi
	 * @date   2015年4月28日
	 * @version since 1.0
	 * @param mv
	 * @param request
	 * @return
	 */
	private ModelAndView getAdminIndexData(ModelAndView mv,HttpServletRequest request) {
		List<SiteInfoCount>  siteInfoCountList = new ArrayList<SiteInfoCount>();
		siteInfoCountList.add(siteInfoCountQuery.studentCount());
		siteInfoCountList.add(siteInfoCountQuery.teacherCount());
		siteInfoCountList.add(siteInfoCountQuery.newsCount());
		siteInfoCountList.add(siteInfoCountQuery.newsCommentsCount());
		siteInfoCountList.add(siteInfoCountQuery.notificationCount());
		mv.addObject("siteInfoCountList",siteInfoCountList);
		return mv;
	}
	@Override
	public ModelAndView webBaseInfo(ModelAndView mv, HttpServletRequest request) {
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		//如果没登陆就去登录吧
		Map<String,Object> result  = new HashMap<>();
		result = HasRight(currentUser, result,UserTypeEnum.ADMIN);
		if(!(boolean)result.get("success")){
			mv.setViewName((String)result.get("viewName"));
			return mv;
		}
		
		//如果别名为空，则后端显示登录名
		if(currentUser.getNickName()==null){
			mv.addObject("currentUserName", currentUser.getLoginName());
			mv.addObject("lastLogintime", DateUtil.formatDate(currentUser.getLastLoginTime()));
		}else{
			mv.addObject("currentUserName", currentUser.getNickName());
			mv.addObject("lastLogintime", DateUtil.formatDate(currentUser.getLastLoginTime()));
		}
		mv = getAdminIndexData(mv,request);
		mv.setViewName("/admin/admin/webBaseInfo");
		return 	mv;
	}
	@Override
	public ModelAndView teacherRegisterManger(ModelAndView mv, HttpServletRequest request) {
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		//如果没登陆就去登录吧
		Map<String,Object> result  = new HashMap<>();
		result = HasRight(currentUser, result,UserTypeEnum.ADMIN);
		if(!(boolean)result.get("success")){
			mv.setViewName((String)result.get("viewName"));
			return mv;
		}
		
		mv.setViewName("/admin/admin/teacherRegisterManger");
		List<User> confirmTeacherItems =  userDao.getListByStatusAndType(true, false, "teacher");
		List<User> confirmedTeacherItems =  userDao.getListByStatusAndType(true, true, "teacher");
		List<User> noAccessTeacherItems =  userDao.getListByStatusAndType(false, false, "teacher");
		mv.addObject("confirmTeacherItems",confirmTeacherItems);
		mv.addObject("confirmedTeacherItems",confirmedTeacherItems);
		mv.addObject("noAccessTeacherItems",noAccessTeacherItems);
 		return mv;
	}
	@Override
	public Map<String,Object> confirmTeacher(HttpServletRequest request,Long teacherId) {
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		Map<String,Object> result  = new HashMap<>();
		result = HasRight(currentUser, result,UserTypeEnum.ADMIN);
		if(!(boolean)result.get("success")){
			return result;
		}
		//有权限再操作
		userDao.changeUserStates(true,true,"teacher",teacherId);
		result.put("success", true);
		result.put("msg", "操作成功");
		return result;
	}
	@Override
	public Map<String,Object> refuseTeacher(HttpServletRequest request,Long teacherId) {
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		Map<String,Object> result  = new HashMap<>();
		result = HasRight(currentUser, result,UserTypeEnum.ADMIN);
		if(!(boolean)result.get("success")){
			return result;
		}
		//有权限再操作
		userDao.changeUserStates(false,false,"teacher",teacherId);
		result.put("success", true);
		result.put("msg", "操作成功");
			
		return result;
	}
	/**
	 * 是否具有权限
	 * @author shuqi
	 * @date   2015年4月29日
	 * @version since 1.0
	 * @param currentUser 当前用户
	 * @param result 返回结果信息
	 * @param usertype 某种用户类型的权限
	 * @return
	 */
	private Map<String, Object> HasRight(User currentUser, Map<String, Object> result,UserTypeEnum usertype) {
		//如果没登陆就去登录吧
		if(currentUser==null){
			result.put("success", false);
			result.put("msg", "请重新登录");
			result.put("viewName", "redirct:/login");
		}else if(!usertype.getName().equals(currentUser.getUserType())){
			result.put("success", false);
			result.put("viewName", "/templet/hasNoRight");
		}else{
			result.put("success", true);
		}
		return result;
	}
	@Override
	public ModelAndView studentRegisterManger(ModelAndView mv, HttpServletRequest request) {
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		//如果没登陆就去登录吧
		Map<String,Object> result  = new HashMap<>();
		result = HasRight(currentUser, result,UserTypeEnum.ADMIN);
		if(!(boolean)result.get("success")){
			mv.setViewName((String)result.get("viewName"));
			return mv;
		}
		
		mv.setViewName("/admin/admin/studentRegisterManger");
		//目前学生的confirm字段是没有意义的
		List<User> noAccessItems =  userDao.getListByStatusAndType(false, false, "student");
		noAccessItems.addAll(userDao.getListByStatusAndType(false, true, "student"));
		List<User> accessItems =  userDao.getListByStatusAndType(true, false, "student");
		accessItems.addAll(userDao.getListByStatusAndType(true, true, "student"));
		
		mv.addObject("accessItems",accessItems);
		mv.addObject("noAccessItems",noAccessItems);
		return mv;
	}
}
