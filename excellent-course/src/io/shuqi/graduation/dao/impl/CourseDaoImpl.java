package io.shuqi.graduation.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import io.shuqi.graduation.dao.CourseDao;
import io.shuqi.graduation.domain.Course;
import io.shuqi.graduation.domain.bo.Pager;

@Repository
public class CourseDaoImpl extends CourseDao {

	@Override
	public Course getCourseByName(String name) {
		return (Course) getSession().createQuery("from "+Course.class.getName()+" as cus where cus.name=:name")
									.setParameter("name", name)
									.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<Course> getPager(Pager<Course> pager) {
		List<Course> datas = new ArrayList<>();
		datas = getSession().createQuery("from "+Course.class.getName()+" ns order by ns.id DESC")
					.setMaxResults(pager.getPagesize())
					.setFirstResult(((Number)((pager.getCurrentPage()-1)*pager.getPagesize())).intValue())
					.list();
		pager.setDatas(datas);
		//构建pager
		Long total = getCount();
		pager.setTotal(total);
		pager.setTotalPage(total%pager.getPagesize()==0?total/pager.getPagesize():total/pager.getPagesize()+1);
		return pager;
	}

	@Override
	public Long getCount() {
		return (Long) getSession().createQuery("select count(*) from "+Course.class.getName()).uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> findAll() {
		return getSession().createQuery("from "+Course.class.getName()+" ns order by ns.id DESC").list();
	}

	

}
