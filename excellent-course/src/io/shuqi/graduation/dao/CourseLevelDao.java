package io.shuqi.graduation.dao;


import java.util.List;

import io.shuqi.graduation.base.AbstractBaseDao;
import io.shuqi.graduation.base.InterfaceBaseDao;
import io.shuqi.graduation.domain.CourseLevel;


public abstract class CourseLevelDao extends AbstractBaseDao<CourseLevel> implements InterfaceBaseDao<CourseLevel> {

	/**
	 * 得到所有的课程级别
	 * @author shuqi
	 * @date   2015年5月10日
	 * @version since 1.0
	 * @return
	 */
	public abstract List<CourseLevel> getAll();

}
