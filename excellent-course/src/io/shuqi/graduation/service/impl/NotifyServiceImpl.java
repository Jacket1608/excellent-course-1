package io.shuqi.graduation.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import io.shuqi.graduation.dao.NotifyDao;
import io.shuqi.graduation.domain.Notification;
import io.shuqi.graduation.domain.User;
import io.shuqi.graduation.domain.bo.Pager;
import io.shuqi.graduation.enumtype.UserTypeEnum;
import io.shuqi.graduation.exception.DaoException;
import io.shuqi.graduation.service.NotifyService;
import io.shuqi.graduation.utils.DateUtil;
import io.shuqi.graduation.utils.RequestUtil;
import io.shuqi.graduation.utils.StringUtil;

@Service
@Transactional
public class NotifyServiceImpl extends NotifyService {
	@Autowired
	private NotifyDao notifyDao;

	private Logger log = Logger.getLogger(getClass());

	@Override
	public ModelAndView notifyManager(HttpServletRequest request, Long crrentPage, int pageSize) {
		ModelAndView mv = new ModelAndView("/templet/InfoManager");
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		Pager<Notification> pager = new Pager<>(crrentPage, pageSize);
		//判断当前用户是admin还是teacher，admin查询不限制，teacher只能查看自己的
		if (currentUser != null && (UserTypeEnum.ADMIN.getName().equals(currentUser.getUserType()) || UserTypeEnum.TEACHER.getName().equals(currentUser.getUserType()))) {
			pager = notifyDao.getNotifyPager(pager, currentUser);
		} else {
			//不知道的用户则不让看
			mv.setViewName("/templet/personalInfo");
			return mv;
		}
		mv.addObject("pager", pager);
		mv.addObject("souresType", "notification");
		return mv;
	}

	@Override
	public Map<String, Object> publishNotification(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		// 0、验证用户的权限(只有管理员和教师能发公告)
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		if (!(currentUser != null && (UserTypeEnum.ADMIN.getName().equals(currentUser.getUserType()) || UserTypeEnum.TEACHER.getName().equals(currentUser.getUserType()) ) )) {
			result.put("success", false);
			result.put("msg", "你没有操作权限");
			return result;
		}
		boolean success = true;
		StringBuilder msg = new StringBuilder();
		//1、得到前台传递的参数
		String title = RequestUtil.getString(request, "title");
		String createTime = RequestUtil.getString(request, "createTime");
		String content = RequestUtil.getString(request, "content");
		String key = RequestUtil.getString(request, "key");
		String images = RequestUtil.getString(request, "images");
		// 2、存在一个漫长的验证过程
		if (StringUtil.isNull(title)) {
			success = false;
			msg.append("公告标题不能为空<br/>");
		}
		if (!StringUtil.isNull(createTime) && !StringUtil.isDateString(createTime)) {
			success = false;
			msg.append("发布时间格式错误<br/>");
		}
		if (StringUtil.isNull(content)) {
			success = false;
			msg.append("发布内容不能为空<br/>");
		}
		// 3、如果验证通过就可以进行下一步存储了
		if (success) {
			Notification notification = new Notification();
			notification.setTitle(title);
			notification.setCreateUser(currentUser);
			notification.setNtfImage(images);
			
			//日期不重要，转换不成功，就当前时间
			try {
				notification.setCreateTime(new Timestamp(DateUtil.parseStringToDate(createTime, "yyyy-MM-dd").getTime()));
			} catch (ParseException e) {
				notification.setCreateTime(new Timestamp(System.currentTimeMillis()));
			}
			notification.setModifyTime(new Timestamp(System.currentTimeMillis()));
			notification.setNitificationKey(key);
			notification.setContent(content);
			try {
				notifyDao.save(notification);
				//保存成功  ok
				msg.append("发布成功<br/>");
			} catch (Exception e) {
				success = false;
				msg.append("发布成功<br/>");
				log.error(e.toString());
				e.printStackTrace();
			}

		}

		// 4、封装结果返回
		result.put("success", success);
		result.put("msg", msg);
		return result;
	}

	@Override
	public Map<String, Object> deleteNotifyByID(Long id, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		// 0、验证用户的权限(只有管理员发布新闻)
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		if(!(currentUser!=null)){
			result.put("success", false);
			result.put("msg", "你没有操作权限");
			return result;
		}
		// 开始删除吧
		if(UserTypeEnum.ADMIN.getName().equals(currentUser.getUserType())){
			try {
				notifyDao.deleteById(id);
				result.put("success", true);
				result.put("msg", "操作成功");
			} catch (DaoException e) {
				e.printStackTrace();
				result.put("success", false);
				result.put("msg", "你没有操作权限");
			}
		}else if(UserTypeEnum.TEACHER.getName().equals(currentUser.getUserType())){
			Notification notification = notifyDao.getById(id);
			if(notification.getCreateUser().getId()==currentUser.getId()){
				try {
					notifyDao.deleteById(id);
					result.put("success", true);
					result.put("msg", "操作成功");
				} catch (DaoException e) {
					e.printStackTrace();
					result.put("success", false);
					result.put("msg", "你没有操作权限");
				}
			}else{
				result.put("success", false);
				result.put("msg", "你没有操作权限");
			}
		}
		return result;
	}

	@Override
	public ModelAndView editIndex(Long id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		if(currentUser!=null&&UserTypeEnum.ADMIN.getName().equals(currentUser.getUserType())){
			mv.addObject("notification",notifyDao.getById(id));
			mv.setViewName("/notification/editNotification");
		}else if(currentUser!=null&&UserTypeEnum.TEACHER.getName().equals(currentUser.getUserType())){
			Notification notification = notifyDao.getById(id);
			if(notification.getCreateUser().getId() == currentUser.getId()){
				mv.addObject("notification",notification);
				mv.setViewName("/notification/editNotification");
			}else{
				mv.setViewName("/templet/hasNoRight");
			}
		}else{
			mv.setViewName("/templet/hasNoRight");
		}
		return mv;
	}

	@Override
	public Map<String, Object> editAction(Long id, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		// 0、验证用户的权限(只有管理员和教师能发公告)
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		if (!(currentUser != null && (UserTypeEnum.ADMIN.getName().equals(currentUser.getUserType()) || UserTypeEnum.TEACHER.getName().equals(currentUser.getUserType()) ) )) {
			result.put("success", false);
			result.put("msg", "你没有操作权限");
			return result;
		}
		Notification notification = notifyDao.getById(id);
		if (notification==null) {
			result.put("success", false);
			result.put("msg", "你编辑的公告不存在");
			return result;
		}
		boolean success = true;
		StringBuilder msg = new StringBuilder();
		//1、得到前台传递的参数
		String title = RequestUtil.getString(request, "title");
		String content = RequestUtil.getString(request, "content");
		String key = RequestUtil.getString(request, "key");
		String images = RequestUtil.getString(request, "images");
		// 2、存在一个漫长的验证过程
		if (StringUtil.isNull(title)) {
			success = false;
			msg.append("公告标题不能为空<br/>");
		}
		if (StringUtil.isNull(content)) {
			success = false;
			msg.append("发布内容不能为空<br/>");
		}
		// 3、如果验证通过就可以进行下一步存储了
		if (success) {
			notification.setTitle(title);
			//如果管理员更新其他人的公告不更改用户
			if(!(UserTypeEnum.ADMIN.getName().equals(currentUser.getUserType())&&notification.getCreateUser().getId()!=currentUser.getId())){
				notification.setCreateUser(currentUser);
			}
			notification.setNtfImage(images);
			
			//日期不重要，转换不成功，就当前时间
			notification.setModifyTime(new Timestamp(System.currentTimeMillis()));
			notification.setNitificationKey(key);
			notification.setContent(content);
			try {
				notifyDao.update(notification);
				//保存成功  ok
				msg.append("修改成功<br/>");
			} catch (Exception e) {
				success = false;
				msg.append("修改不成功<br/>");
				log.error(e.toString());
				e.printStackTrace();
			}

		}

		// 4、封装结果返回
		result.put("success", success);
		result.put("msg", msg);
		return result;
	}

	@Override
	public List<Notification> getNewNotification(int i) {
		return notifyDao.getNewNotification(i);
	}

	@Override
	public Pager<Notification> getPagerByPager(Pager<Notification> pager) {
		return notifyDao.getNotifyPager(pager);
	}

}
