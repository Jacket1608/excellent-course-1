package io.shuqi.graduation.controller;

import java.util.Map;

import io.shuqi.graduation.domain.ClassDetail;
import io.shuqi.graduation.domain.Course;
import io.shuqi.graduation.domain.CourseClass;
import io.shuqi.graduation.domain.bo.Pager;
import io.shuqi.graduation.service.CourseService;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CourseController {

	@Autowired 
	private CourseService courseService;
	/**
	 * 添加科目 -- index
	 * @author shuqi
	 * @date   2015年5月10日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/course/addCourse",method=RequestMethod.GET)
	public ModelAndView addCourseIndex(HttpServletRequest request) {
		return courseService.addCourseIndex(request);
	}
	/**
	 * 添加科目--action
	 * @author shuqi
	 * @date   2015年5月10日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/course/addCourse",method=RequestMethod.POST)
	@ResponseBody public Map<String, Object> addCourseAction(HttpServletRequest request) {
		return courseService.addCourseAction(request);
	}
	/**
	 * 科目管理
	 * @author shuqi
	 * @date   2015年5月10日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/course/courseManager/{currentPage}/{pageSize}",method=RequestMethod.GET)
	public ModelAndView courseManager(HttpServletRequest request,@PathVariable(value="currentPage")Long currentPage,@PathVariable(value="pageSize")int pageSize) {
		return courseService.courseManager(request,new Pager<Course>(currentPage, pageSize));
	}
	
	/**
	 * 编辑科目 -- index
	 * @author shuqi
	 * @date   2015年5月10日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/course/editCourse/{id}",method=RequestMethod.GET)
	public ModelAndView editCourseIndex(HttpServletRequest request,@PathVariable(value="id")Long id) {
		return courseService.editCourseIndex(request,id);
	}
	/**
	 * 编辑科目--action
	 * @author shuqi
	 * @date   2015年5月10日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/course/editCourse/{id}",method=RequestMethod.POST)
	@ResponseBody public Map<String, Object> editCourseAction(HttpServletRequest request,@PathVariable(value="id")Long id) {
		return courseService.editCourseAction(request,id);
	}
	
	
	/**
	 * 添加科目课程 -- index
	 * @author shuqi
	 * @date   2015年5月10日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/course/publishCourseClass",method=RequestMethod.GET)
	public ModelAndView publishCourseClassIndex(HttpServletRequest request) {
		return courseService.addCourseClassIndex(request);
	}
	/**
	 * 添加科目--action
	 * @author shuqi
	 * @date   2015年5月10日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/course/publishCourseClass",method=RequestMethod.POST)
	@ResponseBody public Map<String, Object> publishCourseClassAction(HttpServletRequest request) {
		return courseService.addCourseClassAction(request);
	}
	/**
	 * 管理课程--action
	 * @author shuqi
	 * @date   2015年5月10日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/course/courseClassManager/{currentPage}/{pageSize}",method=RequestMethod.GET)
	public ModelAndView courseClassManager(HttpServletRequest request,@PathVariable(value="currentPage")Long currentPage,@PathVariable(value="pageSize")int pageSize) {
		return courseService.courseClassManager(request,new Pager<CourseClass>(currentPage, pageSize));
	}
	
	
	/**
	 * 编辑课程 -- index
	 * @author shuqi
	 * @date   2015年5月10日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/course/editCourseClass/{id}",method=RequestMethod.GET)
	public ModelAndView editCourseClassIndex(HttpServletRequest request,@PathVariable(value="id")Long id) {
		return courseService.editCourseClassIndex(request,id);
	}
	/**
	 * 编辑课程--action
	 * @author shuqi
	 * @date   2015年5月10日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/course/editCourseClass/{id}",method=RequestMethod.POST)
	@ResponseBody public Map<String, Object> editCourseClassAction(HttpServletRequest request,@PathVariable(value="id")Long id) {
		return courseService.editCourseClassAction(request,id);
	}
	/**
	 * 添加课程内容 -- index
	 * @author shuqi
	 * @date   2015年5月10日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/course/publishCourseClassDetail",method=RequestMethod.GET)
	public ModelAndView publishCourseClassDetailIndex(HttpServletRequest request) {
		return courseService.addCourseClassDetailIndex(request);
	}
	/**
	 * 添加课程内容--action
	 * @author shuqi
	 * @date   2015年5月10日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/course/publishCourseClassDetail",method=RequestMethod.POST)
	@ResponseBody public Map<String, Object> publishCourseClassDetailAction(HttpServletRequest request) {
		return courseService.addCourseClassDetailAction(request);
	}
	/**
	 * 管理课程内容--action
	 * @author shuqi
	 * @date   2015年5月10日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/course/classDetailManager/{currentPage}/{pageSize}",method=RequestMethod.GET)
	public ModelAndView classDetailManager(HttpServletRequest request,@PathVariable(value="currentPage")Long currentPage,@PathVariable(value="pageSize")int pageSize) {
		return courseService.classDetailManager(request,new Pager<ClassDetail>(currentPage, pageSize));
	}
	/**
	 * 编辑课程内容 -- index
	 * @author shuqi
	 * @date   2015年5月10日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/course/editClassDetail/{id}",method=RequestMethod.GET)
	public ModelAndView editClasssDetailIndex(HttpServletRequest request,@PathVariable(value="id")Long id) {
		return courseService.editClasssDetailIndex(request,id);
	}
	/**
	 * 编辑课程内容--action
	 * @author shuqi
	 * @date   2015年5月10日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/course/editClassDetail/{id}",method=RequestMethod.POST)
	@ResponseBody public Map<String, Object> editClasssDetailAction(HttpServletRequest request,@PathVariable(value="id")Long id) {
		return courseService.editClasssDetailAction(request,id);
	}
	
	/**
	 * 课程中心首页
	 * @author shuqi
	 * @date   2015年5月10日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/course/index",method=RequestMethod.GET)
	public ModelAndView courseIndex(HttpServletRequest request) {
		return courseService.courseIndex(request);
	}
	/**
	 * 课程得到
	 * @author shuqi
	 * @date   2015年5月10日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/course/pager/{currentPage}/{pageSize}/{type}",method=RequestMethod.GET)
	public ModelAndView getCoursePger(HttpServletRequest request,@PathVariable(value="currentPage")Long currentPage,@PathVariable(value="pageSize")int pageSize,@PathVariable(value="type")String type) {
		return courseService.getCoursePger(request,currentPage,pageSize,type);
	}
	/**
	 * 课程相关的查看
	 * @author shuqi
	 * @date   2015年5月10日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/course/view/{id}/{type}",method=RequestMethod.GET)
	public ModelAndView view(HttpServletRequest request,@PathVariable(value="id")Long id,@PathVariable(value="type")String type) {
		return courseService.view(request,id,type);
	}
	
	/**
	 * 编辑课程内容--action
	 * @author shuqi
	 * @date   2015年5月10日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/course/addClassDetailComments",method=RequestMethod.POST)
	@ResponseBody public Map<String, Object> addClassDetailComments(HttpServletRequest request) {
		return courseService.addClassDetailComments(request);
	}
}
