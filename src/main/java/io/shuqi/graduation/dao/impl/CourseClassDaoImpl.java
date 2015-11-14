package io.shuqi.graduation.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import io.shuqi.graduation.dao.CourseClassDao;
import io.shuqi.graduation.domain.Course;
import io.shuqi.graduation.domain.CourseClass;
import io.shuqi.graduation.domain.User;
import io.shuqi.graduation.domain.bo.Pager;

@Repository
public class CourseClassDaoImpl extends CourseClassDao {

	@Override
	public CourseClass getByName(String title) {
		return (CourseClass) getSession().createQuery(" from "+CourseClass.class.getName()+" ccs where ccs.title=:title")
										 .setParameter("title", title)
										 .uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<CourseClass> getPagerByUser(User currentUser, Pager<CourseClass> pager) {
		List<CourseClass> datas = new ArrayList<>();
		datas = getSession().createQuery("from "+CourseClass.class.getName()+" ccs  where ccs.createUser=:user")
					.setParameter("user", currentUser)	
					.setMaxResults(pager.getPagesize())
					.setFirstResult(((Number)((pager.getCurrentPage()-1)*pager.getPagesize())).intValue())
					.list();
		pager.setDatas(datas);
		//构建pager
		Long total = getCountByUser(currentUser);
		pager.setTotal(total);
		pager.setTotalPage(total%pager.getPagesize()==0?total/pager.getPagesize():total/pager.getPagesize()+1);
		return pager;
	}

	@Override
	public Long getCountByUser(User currentUser) {
		return (Long) getSession().createQuery("select count(*) from "+CourseClass.class.getName()+" as  ccs where ccs.createUser=:user ")
						   .setParameter("user", currentUser)
						   .uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CourseClass> findAllByUser(User currentUser) {
		return  getSession().createQuery("from "+CourseClass.class.getName()+" as ccs where ccs.createUser=:user")
						    .setParameter("user", currentUser)
							.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CourseClass> getByCourse(Course course) {
		return getSession().createQuery("from "+CourseClass.class.getName()+" as ccs  where ccs.course=:course")
			    .setParameter("course", course)
				.list();
	}

	
}
