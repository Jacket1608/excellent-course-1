package io.shuqi.graduation.service;

import io.shuqi.graduation.domain.ClassDetail;
import io.shuqi.graduation.domain.Course;
import io.shuqi.graduation.domain.CourseClass;
import io.shuqi.graduation.domain.bo.Pager;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

public abstract class CourseService {

	/**
	 * 添加课程
	 * @author shuqi
	 * @date   2015年5月10日
	 * @version since 1.0
	 * @return
	 */
	public abstract ModelAndView addCourseIndex(HttpServletRequest request);
	/**
	 * 添加课程
	 * @author shuqi
	 * @date   2015年5月10日
	 * @version since 1.0
	 * @return
	 */
	public abstract Map<String, Object> addCourseAction(HttpServletRequest request);
	
	/**
	 * 科目管理
	 * @author shuqi
	 * @date   2015年5月10日
	 * @version since 1.0
	 * @param request
	 * @param pager
	 * @return
	 */
	public abstract ModelAndView courseManager(HttpServletRequest request, Pager<Course> pager);
	/**
	 * 编辑科目 -- index
	 * @author shuqi
	 * @date   2015年5月10日
	 * @version since 1.0
	 * @param request
	 * @param id
	 * @return
	 */
	public abstract ModelAndView editCourseIndex(HttpServletRequest request, Long id);
	/**
	 * 编辑科目 --action
	 * @author shuqi
	 * @date   2015年5月10日
	 * @version since 1.0
	 * @param request
	 * @param id
	 * @return
	 */
	public abstract Map<String, Object> editCourseAction(HttpServletRequest request, Long id);
	/**
	 * 科目课程管理 
	 * @author shuqi
	 * @date   2015年5月11日
	 * @version since 1.0
	 * @param request
	 * @param pager
	 * @return
	 */
	public abstract ModelAndView courseClassManager(HttpServletRequest request, Pager<CourseClass> pager);
	/**
	 * 科目课程发布 --action
	 * @author shuqi
	 * @date   2015年5月11日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	
	public abstract Map<String, Object> addCourseClassAction(HttpServletRequest request);
	/**
	 * 课程发布 --index
	 * @author shuqi
	 * @date   2015年5月11日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	public abstract ModelAndView addCourseClassIndex(HttpServletRequest request);
	/**
	 * 编辑index
	 * @author shuqi
	 * @date   2015年5月11日
	 * @version since 1.0
	 * @param request
	 * @param id
	 * @return
	 */
	public abstract ModelAndView editCourseClassIndex(HttpServletRequest request, Long id);
	/**
	 * 编辑 action
	 * @author shuqi
	 * @date   2015年5月11日
	 * @version since 1.0
	 * @param request
	 * @param id
	 * @return
	 */
	public abstract Map<String, Object> editCourseClassAction(HttpServletRequest request, Long id);
	/**
	 * 添加课程内容 --index
	 * @author shuqi
	 * @date   2015年5月11日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	public abstract ModelAndView addCourseClassDetailIndex(HttpServletRequest request);
	/**
	 * 添加课程类容 --action
	 * @author shuqi
	 * @date   2015年5月11日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	public abstract Map<String, Object> addCourseClassDetailAction(HttpServletRequest request);
	/**
	 * 课程内容管理 ---老师
	 * @author shuqi
	 * @date   2015年5月12日
	 * @version since 1.0
	 * @param request
	 * @param pager
	 * @return
	 */
	public abstract ModelAndView classDetailManager(HttpServletRequest request, Pager<ClassDetail> pager);
	/**
	 * 编辑课程内容  --index
	 * @author shuqi
	 * @date   2015年5月12日
	 * @version since 1.0
	 * @param request
	 * @param id
	 * @return
	 */
	public abstract ModelAndView editClasssDetailIndex(HttpServletRequest request, Long id);
	/**
	 * 编辑课程内容  --action
	 * @author shuqi
	 * @date   2015年5月12日
	 * @version since 1.0
	 * @param request
	 * @param id
	 * @return
	 */
	public abstract Map<String, Object> editClasssDetailAction(HttpServletRequest request, Long id);
	/**
	 * 课程中心
	 * @author shuqi
	 * @date   2015年5月13日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	public abstract ModelAndView courseIndex(HttpServletRequest request);
	/**
	 * 得到课程中心的分页
	 * @author shuqi
	 * @date   2015年5月13日
	 * @version since 1.0
	 * @param request
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public abstract ModelAndView getCoursePger(HttpServletRequest request, Long currentPage, int pageSize,String type);
	
	/**
	 * 课程相关的查看
	 * @author shuqi
	 * @date   2015年5月14日
	 * @version since 1.0
	 * @param request
	 * @param id
	 * @param type
	 * @return
	 */
	public abstract ModelAndView view(HttpServletRequest request, Long id, String type);
	
	/**
	 * 课程评论
	 * @author shuqi
	 * @date   2015年5月14日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	public abstract Map<String, Object> addClassDetailComments(HttpServletRequest request);
}
