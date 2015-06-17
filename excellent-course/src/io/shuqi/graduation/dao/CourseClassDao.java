package io.shuqi.graduation.dao;

import java.util.List;

import io.shuqi.graduation.base.AbstractBaseDao;
import io.shuqi.graduation.base.InterfaceBaseDao;
import io.shuqi.graduation.domain.Course;
import io.shuqi.graduation.domain.CourseClass;
import io.shuqi.graduation.domain.User;
import io.shuqi.graduation.domain.bo.Pager;

public abstract class CourseClassDao extends AbstractBaseDao<CourseClass> implements InterfaceBaseDao<CourseClass> {

	/**
	 * 科目课程
	 * @author shuqi
	 * @date   2015年5月11日
	 * @version since 1.0
	 * @param title
	 * @return
	 */
	public abstract CourseClass getByName(String title);
	/**
	 * 科目课程
	 * @author shuqi
	 * @date   2015年5月11日
	 * @version since 1.0
	 * @param title
	 * @return
	 */
	public abstract Pager<CourseClass> getPagerByUser(User currentUser,Pager<CourseClass> pager);
	/**
	 * 科目课程
	 * @author shuqi
	 * @date   2015年5月11日
	 * @version since 1.0
	 * @param title
	 * @return
	 */
	public abstract Long getCountByUser(User currentUser);
	/**
	 * 查出全部的课程
	 * @author shuqi
	 * @date   2015年5月11日
	 * @version since 1.0
	 * @return
	 */
	public abstract List<CourseClass> findAllByUser(User currentUser);
	/**
	 * 通过科目的ID 得到课程
	 * @author shuqi
	 * @date   2015年5月13日
	 * @version since 1.0
	 * @param long1
	 * @return
	 */
	public abstract List<CourseClass> getByCourse(Course course);

}
