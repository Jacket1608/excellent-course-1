package io.shuqi.graduation.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;



import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;







import org.springframework.web.util.HtmlUtils;

import io.shuqi.graduation.dao.AttachDao;
import io.shuqi.graduation.dao.ClassDetailCommentsDao;
import io.shuqi.graduation.dao.ClassDetailDao;
import io.shuqi.graduation.dao.CourseClassDao;
import io.shuqi.graduation.dao.CourseDao;
import io.shuqi.graduation.dao.CourseLevelDao;
import io.shuqi.graduation.domain.Attach;
import io.shuqi.graduation.domain.BbsContent;
import io.shuqi.graduation.domain.ClassDetail;
import io.shuqi.graduation.domain.ClassDetailComments;
import io.shuqi.graduation.domain.Course;
import io.shuqi.graduation.domain.CourseClass;
import io.shuqi.graduation.domain.User;
import io.shuqi.graduation.domain.bo.Pager;
import io.shuqi.graduation.enumtype.UserTypeEnum;
import io.shuqi.graduation.service.AttachService;
import io.shuqi.graduation.service.BbsService;
import io.shuqi.graduation.service.CourseService;
import io.shuqi.graduation.utils.DateUtil;
import io.shuqi.graduation.utils.RequestUtil;
import io.shuqi.graduation.utils.StringUtil;

@Service("courseService")
@Transactional
public class CourseServiceImpl extends CourseService {

	@Autowired
	private CourseLevelDao courseLevelDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private CourseClassDao courseClassDao;
	@Autowired
	private AttachDao attachDao;
	@Autowired
	private ClassDetailDao classDetailDao;
	@Autowired
	private AttachService attachService;
	@Autowired
	private BbsService bbsService;
	@Autowired
	private ClassDetailCommentsDao  classDetailCommentsDao;

	private Logger log = Logger.getLogger(getClass());

	@Override
	public ModelAndView addCourseIndex(HttpServletRequest request) {
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		ModelAndView mv = new ModelAndView();
		if (currentUser != null && UserTypeEnum.ADMIN.getName().equals(currentUser.getUserType())) {
			mv.addObject("courseLevels", courseLevelDao.getAll());
			mv.setViewName("/course/addCourse");
			return mv;
		} else {
			mv.setViewName("/templet/hasNoRight");
			return mv;
		}
	}

	@Override
	public Map<String, Object> addCourseAction(HttpServletRequest request) {
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		Map<String, Object> result = new HashMap<String, Object>();
		if (currentUser != null && UserTypeEnum.ADMIN.getName().equals(currentUser.getUserType())) {
			Map<String, Object> errors = new HashMap<String, Object>();
			String name = RequestUtil.getString(request, "name");
			Long courseLevel = RequestUtil.getLong(request, "courseLevel");
			String description = RequestUtil.getString(request, "description");
			String teacherTeam = RequestUtil.getString(request, "teacherTeam");
			String teachContent = RequestUtil.getString(request, "teachContent");
			String teachMethod = RequestUtil.getString(request, "teachMethod");
			String teachEnvironment = RequestUtil.getString(request, "teachEnvironment");
			String teachOutline = RequestUtil.getString(request, "teachOutline");
			if (StringUtil.isNull(name)) {
				errors.put("name", "科目名不能为空");
			} else {
				Course temp = courseDao.getCourseByName(name);
				if (temp != null && temp.getCourseLevel().getId() == courseLevel) {
					errors.put("name", "科目已经存在");
				}
			}
			if (!(courseLevel != -1 && courseLevelDao.getById(courseLevel) != null)) {
				errors.put("courseLevel", "科目归类不能为空");
			}
			if (StringUtil.isNull(description)) {
				errors.put("description", "科目名介绍不能为空");
			}
			if (StringUtil.isNull(teacherTeam)) {
				errors.put("teacherTeam", "教师团队不能为空");
			}
			if (StringUtil.isNull(teachMethod)) {
				errors.put("teachMethod", "教学方式不能为空");
			}
			if (StringUtil.isNull(teachContent)) {
				errors.put("teachContent", "教学内容不能为空");
			}
			if (StringUtil.isNull(teachEnvironment)) {
				errors.put("teachEnvironment", "教学环境为空");
			}
			if (StringUtil.isNull(teachOutline)) {
				errors.put("teachOutline", "教学大纲不能为空");
			}
			if (errors.size() <= 0) {
				try {
					Course course = new Course(name, courseLevelDao.getById(courseLevel), description, teacherTeam, teachContent, teachMethod, teachEnvironment, teachOutline);
					courseDao.save(course);
					result.put("success", true);
					result.put("msg", "添加成功");
				} catch (Exception e) {
					log.error("保存科目失败", e);
					result.put("success", false);
					result.put("msg", "添加失败");
				}
			} else {
				StringBuilder msg = new StringBuilder();
				for (Map.Entry<String, Object> item : errors.entrySet()) {
					msg.append(item.getValue()).append("<br/>");
				}
				result.put("success", false);
				result.put("msg", msg.toString());
			}
		} else {
			result.put("success", false);
			result.put("msg", "你没有查看权限额");
		}
		return result;
	}

	@Override
	public ModelAndView courseManager(HttpServletRequest request, Pager<Course> pager) {
		ModelAndView mv = new ModelAndView();
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		if (currentUser != null && UserTypeEnum.ADMIN.getName().equals(currentUser.getUserType())) {
			mv.addObject("pager", courseDao.getPager(pager));
			mv.addObject("souresType", "course");
			mv.setViewName("/course/courseManager");
		} else {
			mv.setViewName("/templet/hasNoRight");
		}
		return mv;
	}

	@Override
	public ModelAndView editCourseIndex(HttpServletRequest request, Long id) {
		ModelAndView mv = new ModelAndView();
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		if (currentUser != null && UserTypeEnum.ADMIN.getName().equals(currentUser.getUserType())) {
			mv.addObject("courseLevels", courseLevelDao.getAll());
			mv.addObject("course", courseDao.getById(id));
			mv.setViewName("/course/editCourse");
		} else {
			mv.setViewName("/templet/hasNoRight");
		}
		return mv;
	}

	@Override
	public Map<String, Object> editCourseAction(HttpServletRequest request, Long id) {
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		Map<String, Object> result = new HashMap<String, Object>();
		//权限判断
		if (currentUser != null && UserTypeEnum.ADMIN.getName().equals(currentUser.getUserType())) {
			Map<String, Object> errors = new HashMap<String, Object>();
			String name = RequestUtil.getString(request, "name");
			Long courseLevel = RequestUtil.getLong(request, "courseLevel");
			String description = RequestUtil.getString(request, "description");
			String teacherTeam = RequestUtil.getString(request, "teacherTeam");
			String teachContent = RequestUtil.getString(request, "teachContent");
			String teachMethod = RequestUtil.getString(request, "teachMethod");
			String teachEnvironment = RequestUtil.getString(request, "teachEnvironment");
			String teachOutline = RequestUtil.getString(request, "teachOutline");

			Course oldcourse = courseDao.getById(id);
			if (oldcourse == null) {
				errors.put("id", "编辑科目不存在");
			}
			if (StringUtil.isNull(name)) {
				errors.put("name", "科目名不能为空");
			} else {
				Course temp = courseDao.getCourseByName(name);
				if (temp != null && temp.getCourseLevel().getId() == courseLevel) {
					if (oldcourse != null && !oldcourse.getName().equals(name))
						errors.put("name", "科目已经存在");
				}
			}
			if (!(courseLevel != -1 && courseLevelDao.getById(courseLevel) != null)) {
				errors.put("courseLevel", "科目归类必需选择");
			}
			if (StringUtil.isNull(description)) {
				errors.put("description", "科目名介绍不能为空");
			}
			if (StringUtil.isNull(teacherTeam)) {
				errors.put("teacherTeam", "教师团队不能为空");
			}
			if (StringUtil.isNull(teachMethod)) {
				errors.put("teachMethod", "教学方式不能为空");
			}
			if (StringUtil.isNull(teachContent)) {
				errors.put("teachContent", "教学内容不能为空");
			}
			if (StringUtil.isNull(teachEnvironment)) {
				errors.put("teachEnvironment", "教学环境为空");
			}
			if (StringUtil.isNull(teachOutline)) {
				errors.put("teachOutline", "教学大纲不能为空");
			}

			if (errors.size() <= 0) {
				try {
					oldcourse.setName(name);
					oldcourse.setCourseLevel(courseLevelDao.getById(courseLevel));
					oldcourse.setDescription(description);
					oldcourse.setTeachContent(teachContent);
					oldcourse.setTeachEnvironment(teachEnvironment);
					oldcourse.setTeacherTeam(teacherTeam);
					oldcourse.setTeachMethod(teachMethod);
					oldcourse.setTeachOutline(teachOutline);
					courseDao.update(oldcourse);
					result.put("success", true);
					result.put("msg", "编辑成功");
				} catch (Exception e) {
					log.error("保存科目失败", e);
					result.put("success", false);
					result.put("msg", "添加失败");
				}
			} else {
				StringBuilder msg = new StringBuilder();
				for (Map.Entry<String, Object> item : errors.entrySet()) {
					msg.append(item.getValue()).append("<br/>");
				}
				result.put("success", false);
				result.put("msg", msg.toString());
			}
		} else {
			result.put("success", false);
			result.put("msg", "你没有查看权限额");
		}
		return result;
	}

	@Override
	public ModelAndView courseClassManager(HttpServletRequest request, Pager<CourseClass> pager) {
		ModelAndView mv = new ModelAndView();
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		if (currentUser != null && UserTypeEnum.TEACHER.getName().equals(currentUser.getUserType())) {
			mv.addObject("pager", courseClassDao.getPagerByUser(currentUser, pager));
			mv.addObject("souresType", "courseClass");
			mv.setViewName("/course/courseClassesManager");
		} else {
			mv.setViewName("/templet/hasNoRight");
		}
		return mv;
	}

	@Override
	public Map<String, Object> addCourseClassAction(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<>();
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		if (currentUser != null && UserTypeEnum.TEACHER.getName().equals(currentUser.getUserType())) {
			Long courseId = RequestUtil.getLong(request, "course");
			String courseClassImage = RequestUtil.getString(request, "courseClassImage");
			String description = RequestUtil.getString(request, "description");
			String title = RequestUtil.getString(request, "title");
			Map<String, Object> errors = new HashMap<>();

			if (StringUtil.isNull(title)) {
				errors.put("title", "课程标题不能为空");
			} else {
				if (courseClassDao.getByName(title) != null) {
					errors.put("title", "课程标题已存在");
				}
			}
			if (StringUtil.isNull(courseClassImage)) {
				errors.put("courseImage", "课程展示图片不能为空");
			}
			if (StringUtil.isNull(description)) {
				errors.put("description", "课程介绍不能为空");
			}
			Course course = courseDao.getById(courseId);
			if (course == null) {
				errors.put("course", "课程所属科目不能为空");
			}
			if (errors.size() <= 0) {
				try {
					CourseClass courseClass = new CourseClass(title, courseClassImage, description, new Date(), course, currentUser);
					courseClassDao.save(courseClass);
					result.put("success", true);
					result.put("msg", "保存成功");
				} catch (Exception e) {
					log.error("保存课程失败", e);
					result.put("success", false);
					result.put("msg", "保存失败");
				}
			} else {
				StringBuilder errorstr = new StringBuilder();
				for (Map.Entry<String, Object> item : errors.entrySet()) {
					errorstr.append(item.getValue()).append("<br/>");
				}
				result.put("success", false);
				result.put("msg", errorstr.toString());
			}

		} else {
			result.put("success", false);
			result.put("msg", "你没有权限");
		}
		return result;
	}

	@Override
	public ModelAndView addCourseClassIndex(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		if (currentUser != null && UserTypeEnum.TEACHER.getName().equals(currentUser.getUserType())) {
			mv.addObject("courses", courseDao.findAll());
			mv.setViewName("/course/addCourseClass");
		} else {
			mv.setViewName("/templet/hasNoRight");
		}
		return mv;
	}

	@Override
	public ModelAndView editCourseClassIndex(HttpServletRequest request, Long id) {
		ModelAndView mv = new ModelAndView();
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		if (currentUser != null && UserTypeEnum.TEACHER.getName().equals(currentUser.getUserType())) {
			CourseClass courseClass = courseClassDao.getById(id);
			if (courseClass != null && courseClass.getCreateUser().getId() == currentUser.getId()) {
				mv.addObject("courseClass", courseClass);
				mv.addObject("courses", courseDao.findAll());
				mv.setViewName("/course/editCourseClass");
			} else {
				mv.setViewName("/templet/hasNoRight");
			}
		} else {
			mv.setViewName("/templet/hasNoRight");
		}
		return mv;
	}

	@Override
	public Map<String, Object> editCourseClassAction(HttpServletRequest request, Long id) {
		Map<String, Object> result = new HashMap<>();
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		if (currentUser != null && UserTypeEnum.TEACHER.getName().equals(currentUser.getUserType())) {
			Long courseId = RequestUtil.getLong(request, "course");
			String courseClassImage = RequestUtil.getString(request, "courseClassImage");
			String description = RequestUtil.getString(request, "description");
			String title = RequestUtil.getString(request, "title");
			Map<String, Object> errors = new HashMap<>();
			CourseClass old = courseClassDao.getById(id);
			if (old == null) {
				errors.put("id", "编辑的课程不存在");
			}
			if (StringUtil.isNull(title)) {
				errors.put("title", "课程标题不能为空");
			} else {
				CourseClass temp = courseClassDao.getByName(title);
				if (temp != null) {
					if (old != null && old.getId() != temp.getId()) {
						errors.put("title", "课程标题已存在");
					}
				}
			}
			if (StringUtil.isNull(courseClassImage)) {
				errors.put("courseImage", "课程展示图片不能为空");
			}
			if (StringUtil.isNull(description)) {
				errors.put("description", "课程介绍不能为空");
			}
			Course course = courseDao.getById(courseId);
			if (course == null) {
				errors.put("course", "课程所属科目不能为空");
			}
			if (errors.size() <= 0) {
				try {
					old.setCourse(course);
					old.setDescription(description);
					old.setTitle(title);
					old.setCourseClassImage(courseClassImage);
					courseClassDao.update(old);
					result.put("success", true);
					result.put("msg", "编辑成功");
				} catch (Exception e) {
					log.error("保存课程失败", e);
					result.put("success", false);
					result.put("msg", "编辑失败");
				}
			} else {
				StringBuilder errorstr = new StringBuilder();
				for (Map.Entry<String, Object> item : errors.entrySet()) {
					errorstr.append(item.getValue()).append("<br/>");
				}
				result.put("success", false);
				result.put("msg", errorstr.toString());
			}
		} else {
			result.put("success", false);
			result.put("msg", "你没有权限");
		}
		return result;
	}

	@Override
	public ModelAndView addCourseClassDetailIndex(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		if (currentUser != null && UserTypeEnum.TEACHER.getName().equals(currentUser.getUserType())) {
			mv.addObject("courseClasses", courseClassDao.findAllByUser(currentUser));
			mv.setViewName("/course/addClassDetail");
		} else {
			mv.setViewName("/templet/hasNoRight");
		}
		return mv;
	}

	@Override
	public Map<String, Object> addCourseClassDetailAction(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<>();
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		if (currentUser != null && UserTypeEnum.TEACHER.getName().equals(currentUser.getUserType())) {
			String title = RequestUtil.getString(request, "title");
			Long courseClassId = RequestUtil.getLong(request, "courseClass");
			String content = RequestUtil.getString(request, "content");
			String[] attachIds = request.getParameterValues("attachIds");
			//同样是验证
			Map<String, Object> erors = new HashMap<String, Object>();
			if (StringUtil.isNull(title)) {
				erors.put("title", "标题不能为空");
			}
			if (StringUtil.isNull(content)) {
				erors.put("content", "课程不能为空");
			}
			CourseClass courseClass = courseClassDao.getById(courseClassId);
			if (courseClass == null) {
				erors.put("courseClass", "请选择一个课程");
			}
			StringBuilder attachIdsStr = new StringBuilder();
			//判断是否包含附件
			List<Attach> attachs = new ArrayList<>(0);
			if (attachIds != null && attachIds.length > 0) {
				for (String idstr : attachIds) {
					Attach attach = attachDao.getById(Long.valueOf(idstr));
					if (attach != null) {
						attachIdsStr.append(idstr).append("|");
						attachs.add(attach);
					} else {
						erors.put("courseClass", "上传的附件不存在，请重新上传附件");
					}
				}
			}
			if (erors.size() == 0) {
				try {
					ClassDetail classDetail = new ClassDetail(title, content, attachIdsStr.toString(), new Date(), currentUser, courseClass, courseClass.getCourse());
					classDetailDao.save(classDetail);
					for (Attach attach : attachs) {
						attach.setFileType("classDetail");
						attach.setFromName(classDetail.getTitle());
						attach.setFromId(courseClass.getId());
						attach.setFromSubId(classDetail.getId());
						attachDao.update(attach);
					}
					result.put("success", true);
					result.put("msg", "保存成功");
				} catch (Exception e) {
					log.error("保存课程内容失败", e);
					result.put("success", false);
					result.put("msg", "保存失败");
				}
			} else {
				StringBuilder errorsString = new StringBuilder();
				for (Map.Entry<String, Object> item : erors.entrySet()) {
					errorsString.append(item.getValue()).append("<br/>");
				}
				result.put("success", false);
				result.put("msg", errorsString.toString());
			}
		} else {
			result.put("success", false);
			result.put("msg", "你没有权限");
		}
		return result;
	}

	@Override
	public ModelAndView classDetailManager(HttpServletRequest request, Pager<ClassDetail> pager) {
		ModelAndView mv = new ModelAndView();
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		if (currentUser != null && UserTypeEnum.TEACHER.getName().equals(currentUser.getUserType())) {
			mv.addObject("pager", classDetailDao.getPagerByUser(pager, currentUser));
			mv.addObject("souresType", "classDetail");
			mv.setViewName("/course/classDetailManager");
		} else {
			mv.setViewName("/templet/hasNoRight");
		}
		return mv;
	}

	@Override
	public ModelAndView editClasssDetailIndex(HttpServletRequest request, Long id) {
		ModelAndView mv = new ModelAndView();
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		ClassDetail classDetail = classDetailDao.getById(id);
		if (currentUser != null && classDetail != null && UserTypeEnum.TEACHER.getName().equals(currentUser.getUserType()) && classDetail.getCreateUser().getId() == currentUser.getId()) {
			mv.addObject("courseClasses", courseClassDao.findAllByUser(currentUser));
			mv.addObject("classDetail", classDetail);
			List<Attach> attachs = new ArrayList<>(0);
			if (!StringUtil.isNull(classDetail.getAttachIds())) {
				String[] attachIds = classDetail.getAttachIds().trim().split("\\|");
				for (String idStr : attachIds) {
					Attach attach = attachDao.getById(Long.valueOf(idStr));
					attachs.add(attach);
				}
			}
			mv.addObject("attachs", attachs);
			mv.setViewName("/course/editClassDetail");
		} else {
			mv.setViewName("/templet/hasNoRight");
		}
		return mv;
	}

	@Override
	public Map<String, Object> editClasssDetailAction(HttpServletRequest request, Long id) {
		Map<String, Object> result = new HashMap<>();
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		ClassDetail classDetail = classDetailDao.getById(id);
		if (currentUser != null && classDetail != null && UserTypeEnum.TEACHER.getName().equals(currentUser.getUserType()) && classDetail.getCreateUser().getId() == currentUser.getId()) {
			String title = RequestUtil.getString(request, "title");
			Long courseClassId = RequestUtil.getLong(request, "courseClass");
			String content = RequestUtil.getString(request, "content");
			String[] attachIds = request.getParameterValues("attachIds");
			//同样是验证
			Map<String, Object> erors = new HashMap<String, Object>();
			if (StringUtil.isNull(title)) {
				erors.put("title", "标题不能为空");
			}
			if (StringUtil.isNull(content)) {
				erors.put("title", "课程不能为空");
			}
			CourseClass courseClass = courseClassDao.getById(courseClassId);
			if (courseClass == null) {
				erors.put("courseClass", "请选择一个课程");
			}
			StringBuilder attachIdsStr = new StringBuilder();
			//判断是否包含附件
			List<Attach> attachs = new ArrayList<>(0);
			if (attachIds != null && attachIds.length > 0) {
				for (String idstr : attachIds) {
					Attach attach = attachDao.getById(Long.valueOf(idstr));
					if (attach != null) {
						attachIdsStr.append(idstr).append("|");
						attachs.add(attach);
					} else {
						erors.put("courseClass", "上传的附件不存在，请重新上传附件");
					}
				}
			}
			if (erors.size() == 0) {
				try {
					classDetail.setTitle(title);
					classDetail.setContent(content);
					classDetail.setCourse(courseClass.getCourse());
					classDetail.setCourseClass(courseClass);
					classDetail.setAttachIds(attachIdsStr.toString());
					classDetailDao.update(classDetail);
					attachDao.updateToUnknowById(courseClass.getId(), classDetail.getId(),"classDetail");
					for (Attach attach : attachs) {
						attach.setFileType("classDetail");
						;
						attach.setFromName(title);
						attach.setFromId(courseClass.getId());
						attach.setFromSubId(classDetail.getId());
						attachDao.update(attach);
					}
					result.put("success", true);
					result.put("msg", "保存成功");
				} catch (Exception e) {
					log.error("保存课程内容失败", e);
					result.put("success", false);
					result.put("msg", "保存失败");
				}
			} else {
				result.put("success", false);
				result.put("msg", "保存失败");
			}
		} else {
			result.put("success", false);
			result.put("msg", "你没有权限");
		}
		return result;
	}

	@Override
	public ModelAndView courseIndex(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/home/course");
		//热门资料
		List<Attach> attachs = attachService.getNewAttachs(7);
		mv.addObject("attachs", attachs);
		//最新讨论
		List<BbsContent> bbsContents = bbsService.getTopNewBbs(10);
		mv.addObject("bbsContents", bbsContents);
		//得到科目的分类Pager
		Pager<Course> pager = courseDao.getPager(new Pager<Course>(1L, 5));
		List<Map<String, Object>> dest = new ArrayList<>();

		for (Course courseItem : pager.getDatas()) {
			//将pager的数据重新包装
			Map<String, Object> temp = new HashMap<String, Object>();
			List<CourseClass> courseClasses = courseClassDao.getByCourse(courseItem);
			temp.put("courseClasses", courseClasses);
			temp.put("course", courseItem);
			dest.add(temp);
		}
		Pager<Map<String, Object>> temp = new Pager<Map<String, Object>>(pager.getCurrentPage(), pager.getPagesize());
		temp.setCurrent(pager.getCurrent());
		temp.setTotalPage(pager.getTotalPage());
		temp.setTotal(pager.getTotal());
		temp.setDatas(dest);
		mv.addObject("pager", temp);
		mv.addObject("souresType", "coursemore");
		return mv;
	}

	@Override
	public ModelAndView getCoursePger(HttpServletRequest request, Long currentPage, int pageSize,String type) {
		ModelAndView mv = new ModelAndView("/templet/404");
		if("coursemore".equals(type)){
			mv.setViewName("/home/courseCard");
			//得到科目的分类Pager
			Pager<Course> pager = courseDao.getPager(new Pager<Course>(currentPage, pageSize));
			List<Map<String, Object>> dest = new ArrayList<>();
			//将pager的数据重新包装并换返回
			for (Course courseItem : pager.getDatas()) {
				Map<String, Object> temp = new HashMap<String, Object>();
				List<CourseClass> courseClasses = courseClassDao.getByCourse(courseItem);
				temp.put("courseClasses", courseClasses);
				temp.put("course", courseItem);
				dest.add(temp);
			}
			Pager<Map<String, Object>> temp = new Pager<Map<String, Object>>(pager.getCurrentPage(), pager.getPagesize());
			temp.setCurrent(pager.getCurrent());
			temp.setTotalPage(pager.getTotalPage());
			temp.setTotal(pager.getTotal());
			temp.setDatas(dest);
			mv.addObject("pager", temp);
			mv.addObject("souresType", type);
		}else if("classDetailPager".equals(type)){
			Long id = RequestUtil.getLong(request, "courseClassId");
			CourseClass courseClass = courseClassDao.getById(id);
			if(courseClass!=null){
				Pager<ClassDetail>  pager = classDetailDao.getPagerByCourseClass(new Pager<ClassDetail>(currentPage,pageSize),courseClass);
				mv.addObject("pager", pager);
				mv.addObject("souresType", "classDetailPager");
				mv.setViewName("/course/courseClassCard");
			}
		}else if("classDetail".equals(type)){
			Long courseClassId = RequestUtil.getLong(request, "courseClassId");
			CourseClass courseClass = courseClassDao.getById(courseClassId);
			Pager<ClassDetail> pager = classDetailDao.getPagerByCourseClass(new Pager<ClassDetail>(currentPage,1),courseClass);
			if(pager.getDatas()!=null&&pager.getDatas().size()>0){
				//返回该分页对象
				mv.addObject("pager", pager);
				mv.addObject("souresType", "classDetail");
				//查询课程内容回复
				List<ClassDetailComments> classDetailCommentses = classDetailCommentsDao.findAll(pager.getDatas().get(0));
				mv.addObject("classDetailCommentses", classDetailCommentses);
				mv.setViewName("/course/classDetail");
				
				mv.setViewName("/course/classDetailCrad");
			}
		}
		return mv;
	}

	@Override
	public ModelAndView view(HttpServletRequest request, Long id, String type) {
		ModelAndView mv = new ModelAndView("/templet/404");
		if("course".equals(type)){
			Course course = courseDao.getById(id);
			if(course!=null){
				List<CourseClass> courseClasses = courseClassDao.getByCourse(course);
				mv.addObject("course", course);
				mv.addObject("courseClasses", courseClasses);
				//科目的主页
				mv.setViewName("/course/courseIndex");
			}
		}else if("courseClass".equals(type)){
			//查出课程
			CourseClass courseClass = courseClassDao.getById(id);
			if(courseClass!=null){
				mv.addObject("courseClass",courseClass);
				//热门附件
				List<Attach> attachs =  attachDao.getAttachByCourseClassId(courseClass.getId());
				mv.addObject("attachs", attachs);
				//社区热帖
				List<BbsContent> bbsContentTops =  bbsService.getTop10Bbs(10);
				mv.addObject("bbsContents", bbsContentTops);
				//相关课程内容
				Pager<ClassDetail>  pager = classDetailDao.getPagerByCourseClass(new Pager<ClassDetail>(1L,10),courseClass);
				mv.addObject("pager", pager);
				mv.addObject("souresType", "classDetailPager");
				//课程主页
				mv.setViewName("/course/courseClass");
			}
		}else if("classDetail".equals(type)){
			ClassDetail classDetail = classDetailDao.getById(id);
			if(classDetail!=null){
				CourseClass courseClass = classDetail.getCourseClass();
				mv.addObject("courseClass",courseClass);
				//相关附件 
				List<Attach> attachs =  attachDao.getAttachByCourseClassId(courseClass.getId());
				mv.addObject("attachs", attachs);
				//相关附件 本记录的附件
				List<Attach> attachsThis =  new ArrayList<>();
				for (Attach attach : attachs) {
					if(attach.getFromSubId()==classDetail.getId()){
						attachsThis.add(attach);
					}
				}
				mv.addObject("attachsThis", attachsThis);
				//社区热帖
				List<BbsContent> bbsContentTops =  bbsService.getTop10Bbs(10);
				mv.addObject("bbsContents", bbsContentTops);
				//返回该分页对象
				//构建pager
				Pager<ClassDetail> pager = new Pager<>(id, 1);
				Long total = classDetailDao.getCountByCourseClass(courseClass);
				List<ClassDetail> datas = new ArrayList<>();
				datas.add(classDetail);
				pager.setDatas(datas);
				pager.setTotal(total);
				pager.setTotalPage(total%pager.getPagesize()==0?total/pager.getPagesize():total/pager.getPagesize()+1);
				mv.addObject("pager", pager);
				mv.addObject("souresType", "classDetail");
				
				//查询课程内容回复
				List<ClassDetailComments> classDetailCommentses = classDetailCommentsDao.findAll(classDetail);
				mv.addObject("classDetailCommentses", classDetailCommentses);
				mv.setViewName("/course/classDetail");
			}
		}
		return mv;
	}

	@Override
	public Map<String, Object> addClassDetailComments(HttpServletRequest request) {
		User currentUser = (User)request.getSession().getAttribute("currentUser");
		Map<String , Object> result = new HashMap<String, Object>();
		if(currentUser!=null){
			Long classDetailid= RequestUtil.getLong(request, "classDetailid");
			String content= RequestUtil.getString(request, "content");
			ClassDetail classDetail = classDetailDao.getById(classDetailid);
			if(classDetail!=null && !StringUtil.isNull(content)){
				try{
					ClassDetailComments classDetailComments = new ClassDetailComments(content, new Date(), currentUser, null, classDetail, classDetail.getCourseClass());
					classDetailCommentsDao.save(classDetailComments);
					result.put("success", true);
					result.put("msg", "评论成功");
					result.put("headImage", currentUser.getHeadImage());
					result.put("time", DateUtil.formatDate(classDetailComments.getCreateTime()));
					result.put("userName", HtmlUtils.htmlEscape(request.getSession().getAttribute("displayName").toString()));
					result.put("content", HtmlUtils.htmlEscape(classDetailComments.getContent()));
				}catch(Exception e){
					result.put("success", false);
					result.put("msg", "评论课程内容失败！");
				}
			}else{
				result.put("success", false);
				result.put("msg", "你评论的课程内容不存在！");
			}
		}else{
			result.put("success", false);
			result.put("msg", "你当前未登陆，不能评论额！");
		}
		return result;
	}

	
}
