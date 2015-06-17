package io.shuqi.graduation.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import io.shuqi.graduation.dao.NewsCommentDao;
import io.shuqi.graduation.dao.UserDao;
import io.shuqi.graduation.domain.NewsComments;
import io.shuqi.graduation.domain.User;
import io.shuqi.graduation.domain.bo.Pager;
import io.shuqi.graduation.enumtype.UserTypeEnum;
import io.shuqi.graduation.service.UserService;
import io.shuqi.graduation.utils.DateUtil;
import io.shuqi.graduation.utils.EncryptUtil;
import io.shuqi.graduation.utils.RequestUtil;
import io.shuqi.graduation.utils.StringUtil;

@Service("userService")
@Transactional
public class UserServiceImpl extends UserService {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private NewsCommentDao newsCommentDao;
	
	@Override
	public ModelAndView register(ModelAndView mv,HttpServletRequest request) {
		String loginName = RequestUtil.getString(request, "loginname");
		String email = RequestUtil.getString(request, "email");
		String password = RequestUtil.getString(request, "password");
		String userType = RequestUtil.getString(request, "userType");
		
		Map<String, String> error = new HashMap<String, String>();
		if(!StringUtil.isEmailString(email)){
			error.put("eEmail", "你的邮件地址不对");
		}else{
			if(userDao.getUserByEmail(email)!=null){
				error.put("eEmail", "注册的邮箱已存在");
			}
		}
		if(StringUtil.isNull(loginName)){
			error.put("eLoginName", "登录名不能为空");
		}else{
			if(userDao.getUserByLoginName(loginName)!=null){
				error.put("eLoginName", "登录名已存在");
			}else if(!loginName.matches("^\\w+$")){
				error.put("eLoginName", "用户名只能为数字与英文的组合");
			}else if(loginName.length()>15){
				error.put("eLoginName", "用户名不能太长");
			}
		}
		if(StringUtil.isNull(password)){
			error.put("ePassword", "密码不能为空");
		}
		if(StringUtil.isNull(userType)){
			error.put("euserType", "请选择要注册的用户类型");
		}
		
		if(error.size()>0){
			mv.addAllObjects(error);
			mv.addObject("loginname", loginName);
			mv.addObject("email", email);
			mv.addObject("password", password);
			mv.setViewName("/templet/register");
		}else{
			//如果没有选择，则默认为学生
			if(StringUtil.isNull(userType)) 
				userType ="student";
			password = EncryptUtil.ecodingPasswd(password, "md5");
			User user = new User(password,loginName,email,true,userType,new Date(),0L);
			user.setLastLoginTime(new Date());
			userDao.save(user);
			HttpSession session = request.getSession();
			session.setAttribute("currentUser", userDao.getUserByLoginName(loginName));
			String displayName = StringUtil.isNull(user.getNickName())?user.getLoginName():user.getNickName();
			session.setAttribute("displayName", displayName);
			
			if(user.getUserType().equals(UserTypeEnum.STUDENT.getName())){
				mv.setViewName("redirect:/index");
			}else{
				mv.setViewName("redirect:/admin");
			}	
		}
		return mv;
	}


	@Override
	public ModelAndView Login(ModelAndView mv, HttpServletRequest request) {
		String loginName = RequestUtil.getString(request, "loginname");
		String password = RequestUtil.getString(request, "password");
		
		Map<String, String> error = new HashMap<String, String>();
		if(StringUtil.isNull(loginName)){
			error.put("eLoginName", "登录名不能为空");
		}
		if(StringUtil.isNull(password)){
			error.put("ePassword", "密码不能为空");
		}
		if(error.size()>0){
			mv.addAllObjects(error);
			mv.addObject("loginname", loginName);
			mv.addObject("password", password);
			mv.setViewName("/templet/login");
		}else{
			User user = userDao.getUserByLoginName(loginName);
			if(user!=null&&user.isAccess()&&user.getPassword().equals(EncryptUtil.ecodingPasswd(password, "md5"))){
				HttpSession session= request.getSession();
				session.setAttribute("currentUser", user);
				String displayName = StringUtil.isNull(user.getNickName())?user.getLoginName():user.getNickName();
				session.setAttribute("displayName", displayName);
				//更新上次登录时间
				userDao.updateLoginTime(user.getId());
				if(UserTypeEnum.ADMIN.getName().equals(user.getUserType())||UserTypeEnum.TEACHER.getName().equals(user.getUserType())){
					mv.setViewName("redirect:/admin");
				}else{
					mv.setViewName("redirect:/index");
				}
			}else{
				if(!user.isAccess()){
					mv.addObject("loginname", loginName);
					error.put("eLoginName", "你已经被限制登录");
					error.put("ePassword", "你已经被限制登录");
					mv.addAllObjects(error);
					mv.setViewName("/templet/login");
				}else{
					mv.addObject("loginname", loginName);
					error.put("eLoginName", "用户名或者密码错误");
					error.put("ePassword", "用户名或者密码错误");
					mv.addAllObjects(error);
					mv.setViewName("/templet/login");
				}
			}
		}
		return mv;
	}


	@Override
	public Map<String, Object> resetPassword(HttpServletRequest request, Long userId) {
		//权限判断，如果是当前用户或者是admin  这有权限
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		Map<String,Object> result  = new HashMap<>();
		if(currentUser!=null&&(currentUser.getId()==userId||UserTypeEnum.ADMIN.getName().equals((currentUser.getUserType())))){
			//更新密码
			User tempUser = userDao.getById(userId);
			tempUser.setPassword(EncryptUtil.ecodingPasswd("123456", "md5"));
			userDao.update(tempUser);
			//返回结果
			result.put("success", true);
			result.put("msg", "重置密码成功");
		}else{
			result.put("success", false);
			result.put("msg", "重置密码失败");
		}
		return result;
	}

	@Override
	public Map<String,Object> restartUser(HttpServletRequest request,Long userId) {
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		Map<String,Object> result  = new HashMap<>();
		result = HasRight(currentUser, result,UserTypeEnum.ADMIN);
		if(!(boolean)result.get("success")){
			return result;
		}
		//有权限再操作
		userDao.changeUserStates(true,true,userId);
		result.put("success", true);
		result.put("msg", "操作成功");
			
		return result;
	}
	@Override
	public Map<String,Object> stopUser(HttpServletRequest request,Long userId) {
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		Map<String,Object> result  = new HashMap<>();
		result = HasRight(currentUser, result,UserTypeEnum.ADMIN);
		if(!(boolean)result.get("success")){
			return result;
		}
		//有权限再操作
		userDao.changeUserStates(false,false,userId);
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
	public ModelAndView personInfo(ModelAndView mv, HttpServletRequest request) {
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		if(currentUser!=null&&currentUser.isAccess()){
			mv.setViewName("/templet/personalInfo");
			mv.addObject("user",userDao.getById(currentUser.getId()));
		}else{
			mv.setViewName("/templet/hasNoRight");
		}
		return mv;
	}


	@Override
	public Map<String, Object> savePersonInfo(HttpServletRequest request) throws ParseException {
		Map<String, Object> result = new HashMap<>();
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		if(currentUser!=null&&currentUser.isAccess()){
			result = setUser(request,currentUser);
			if((boolean) result.get("success")){
				
				result.put("success", true);
				result.put("msg", "个人信息修改成功");
			}
		}else{
			result.put("success", false);
			result.put("msg", "你没有修改权限");
		}
		return result;
	}


	private Map<String, Object> setUser(HttpServletRequest request,User currentUser ) throws ParseException {
		Map<String, Object> result = new HashMap<>();
		Map<String, Object> errors = new HashMap<>();
		User daoUser = userDao.getById(currentUser.getId());
		String email= RequestUtil.getString(request, "email");
		if(!StringUtil.isEmailString(email)){
			errors.put("email", "邮箱地址格式不对");
		}
		String qq= RequestUtil.getString(request, "userqq");
		if(!StringUtil.isNull(qq)&&!StringUtil.isNumberString(qq)){
			errors.put("userqq", "qq格式不对");
		}
		String phoneNumber= RequestUtil.getString(request, "phoneNumber");
		if(!StringUtil.isNull(phoneNumber)&&!StringUtil.isNumberString(phoneNumber)){
			errors.put("phoneNumber", "电话格式不对");
		}
		String birthday= RequestUtil.getString(request, "birthday");
		if(!StringUtil.isDateString(birthday)){
			errors.put("birthday", "生日格式不对");
		}
		
		
		if(errors.size()==0){
			daoUser.setEmail(email);
			daoUser.setUserqq(qq);
			daoUser.setPhoneNumber(phoneNumber);
			daoUser.setBirthday(DateUtil.parseStringToDate(birthday, "yyyy-MM-dd"));
			
			daoUser.setRealName(RequestUtil.getString(request, "realName"));
			daoUser.setNickName(RequestUtil.getString(request, "nickName"));
			daoUser.setDescription(RequestUtil.getString(request, "description"));
			daoUser.setLocation(RequestUtil.getString(request, "location"));
			daoUser.setBirthLocation(RequestUtil.getString(request, "birthLocation"));
			daoUser.setGender(RequestUtil.getString(request, "gender"));
			daoUser.setPersonalPage(RequestUtil.getString(request, "personalPage"));
			daoUser.setPersonalTitle(RequestUtil.getString(request, "personalTitle"));
			daoUser.setPersonalFavorite(RequestUtil.getString(request, "personalFavorite"));
			//执行更新
			userDao.update(daoUser);
			//更新session
			request.getSession().setAttribute("currentUser", daoUser);
			String displayName = StringUtil.isNull(daoUser.getNickName())?daoUser.getLoginName():daoUser.getNickName();
			request.getSession().setAttribute("displayName", displayName);
			result.put("success", true);
		}else{
			result.put("success", false);
			StringBuilder errorString = new StringBuilder();
			for (Entry<String, Object>  key : errors.entrySet()) {
				 errorString.append((String)key.getValue()); 
				 errorString.append("<br/>"); 
			}
			result.put("msg", errorString.toString());
		}
		
		return result;
	}


	@Override
	public Map<String, Object> modifyPassword(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<>();
		String oldPassword = RequestUtil.getString(request, "password");
		String newPassword = RequestUtil.getString(request, "newpasseord");
		String reNewPassword = RequestUtil.getString(request, "renewpassword");
		//判断密码是否为空
		if(StringUtil.isNull(newPassword)||StringUtil.isNull(reNewPassword)){
			result.put("success", false);
			result.put("msg", "新密码不能为空");
			return result;
		}
		if(!newPassword.equals(reNewPassword)){
			result.put("success", false);
			result.put("msg", "两次输入的密码不同");
			return result;
		}	
			
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		User daoUser = userDao.getById(currentUser.getId());
		//判断原密码是否正确
		if(daoUser.getPassword().equals(EncryptUtil.ecodingPasswd(oldPassword, "md5"))){
			daoUser.setPassword(EncryptUtil.ecodingPasswd(newPassword, "md5"));
			userDao.update(daoUser);
			result.put("success", true);
			result.put("msg", "修改成功");
		}else{
			result.put("success", false);
			result.put("msg", "原密码不正确");
		}
		return result;
	}


	@Override
	public ModelAndView myNewsComments(HttpServletRequest request,Long crrentIndex,int pageSize) {
		ModelAndView mv = new ModelAndView("/templet/myNewsComments");
		Pager<NewsComments> pager = new Pager<>(crrentIndex,pageSize);
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		if(currentUser==null){
			mv.setViewName("redirct:/login");
			return mv;
		}
		pager = newsCommentDao.getMyNewsComments(pager,currentUser);
		mv.addObject("pager",pager);
		mv.addObject("souresType","myNewsComments");
		return mv;
	}


	@Override
	public ModelAndView newUserIndex(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		Map<String,Object> result  = new HashMap<>();
		result = HasRight(currentUser, result,UserTypeEnum.ADMIN);
		if(!(boolean)result.get("success")){
			mv.setViewName((String) result.get("viewName"));
			return mv;
		}else{
			mv.setViewName("/user/newUser");
			return mv;
		}
	}


	@Override
	public Map<String,Object> newUserAction(HttpServletRequest request) {
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		Map<String,Object> result  = new HashMap<>();
		result = HasRight(currentUser, result,UserTypeEnum.ADMIN);
		if(!(boolean)result.get("success")){
			return result;
		}else{
			Map<String, Object> errors = new HashMap<>();
			String loginName = RequestUtil.getString(request, "loginName");
			String email = RequestUtil.getString(request, "email");
			String gender = RequestUtil.getString(request, "gender");
			String password = RequestUtil.getString(request, "password");
			String userType = RequestUtil.getString(request, "userType");
			if(!StringUtil.isEmailString(email)){
				errors.put("email", "邮箱地址格式不对");
			}else{
				if(userDao.getUserByEmail(email)!=null){
					errors.put("eEmail", "注册的邮箱已存在");
				}
			}
			if(StringUtil.isNull(loginName)){
				errors.put("loginName", "登录名不能为空");
			}else{
				if(userDao.getUserByLoginName(loginName)!=null){
					errors.put("loginName", "用户名已存在");
				}
			}
			if(StringUtil.isNull(password)){
				errors.put("password", "密码不能为空");
			}
			if(StringUtil.isNull(userType)){
				errors.put("userType", "用户类型不能为空");
			}
			if(errors.size()>0){
				result.put("success", false);
				StringBuilder errorString = new StringBuilder();
				for (Entry<String, Object>  key : errors.entrySet()) {
					 errorString.append((String)key.getValue()); 
					 errorString.append("<br/>"); 
				}
				result.put("msg", errorString.toString());
				return result;
			}else{
				User newUser = new User();
				newUser.setRealName(RequestUtil.getString(request, "realName"));
				newUser.setLoginName(loginName);
				newUser.setEmail(email);
				newUser.setGender(gender);
				newUser.setPassword(EncryptUtil.ecodingPasswd(password, "md5"));
				newUser.setUserType(userType);
				newUser.setAccess(true);
				newUser.setConfirm(true);
				userDao.save(newUser);
				result.put("success", true);
				result.put("msg", "注册用户成功");
				return result;
				
			}
		}
	}


	@Override
	public Map<String, Object> getPersonInfo(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<>();
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		Long id = RequestUtil.getLong(request, "id");
		User qUser = userDao.getById(id);
		if(currentUser!=null){
			result.put("user", qUser);
			result.put("success", true);
			result.put("msg", "个人信息");
		}else{
			result.put("success", false);
			result.put("msg", "请登录");
		}
		return result;
	}
	
}
