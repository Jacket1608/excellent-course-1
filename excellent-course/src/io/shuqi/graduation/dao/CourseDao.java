package io.shuqi.graduation.dao;

import java.util.List;

import io.shuqi.graduation.base.AbstractBaseDao;
import io.shuqi.graduation.base.InterfaceBaseDao;
import io.shuqi.graduation.domain.Course;
import io.shuqi.graduation.domain.bo.Pager;

public abstract class CourseDao extends AbstractBaseDao<Course> implements InterfaceBaseDao<Course> {
	
	/**
	 * 通过科目名称得到科目
	 * @author shuqi
	 * @date   2015年5月10日
	 * @version since 1.0
	 * @param name
	 * @return
	 */
	public abstract Course getCourseByName(String name);

	/**
	 * 通过Pager得到对应的Pager
	 * @author shuqi
	 * @date   2015年5月10日
	 * @version since 1.0
	 * @param pager
	 * @return
	 */
	public abstract Pager<Course> getPager(Pager<Course> pager);
	/**
	 * 通过Pager得到对应的Pager
	 * @author shuqi
	 * @date   2015年5月10日
	 * @version since 1.0
	 * @param pager
	 * @return
	 */
	public abstract Long getCount();
	/**
	 * 查出所有的科目
	 * @author shuqi
	 * @date   2015年5月11日
	 * @version since 1.0
	 * @return
	 */
	public abstract List<Course> findAll();
	
}
