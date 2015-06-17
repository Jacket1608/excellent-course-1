package io.shuqi.graduation.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import io.shuqi.graduation.dao.CourseLevelDao;
import io.shuqi.graduation.domain.CourseLevel;

@Repository
public class CourseLevelDaoImpl extends CourseLevelDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<CourseLevel> getAll() {
		return getSession().createQuery("from "+CourseLevel.class.getName()).list();
	}

}
