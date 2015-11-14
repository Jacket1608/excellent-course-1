package io.shuqi.graduation.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import io.shuqi.graduation.dao.ClassDetailDao;
import io.shuqi.graduation.domain.ClassDetail;
import io.shuqi.graduation.domain.CourseClass;
import io.shuqi.graduation.domain.User;
import io.shuqi.graduation.domain.bo.Pager;

@Repository
public class ClassDetailDaoImpl extends ClassDetailDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassDetail> getNewClassDetail(int size) {
		return getSession().createQuery(" from "+ClassDetail.class.getName()+" as cdt order by cdt.createTime desc")
						   .setFirstResult(0)
						   .setMaxResults(size)
						   .list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<ClassDetail> getPagerByUser(Pager<ClassDetail> pager, User currentUser) {
		List<ClassDetail> datas = new ArrayList<>();
		datas = getSession().createQuery("from "+ClassDetail.class.getName()+" ccs  where ccs.createUser=:user")
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
		return (Long) getSession().createQuery("select count(*) from "+ClassDetail.class.getName()+" as  ccs where ccs.createUser=:user ")
				   .setParameter("user", currentUser)
				   .uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Pager<ClassDetail> getPagerByCourseClass(Pager<ClassDetail> pager, CourseClass courseClass) {
		List<ClassDetail> datas = new ArrayList<>();
		datas = getSession().createQuery("from "+ClassDetail.class.getName()+" ccs  where ccs.courseClass=:courseClass")
					.setParameter("courseClass", courseClass)	
					.setMaxResults(pager.getPagesize())
					.setFirstResult(((Number)((pager.getCurrentPage()-1)*pager.getPagesize())).intValue())
					.list();
		pager.setDatas(datas);
		//构建pager
		Long total = getCountByCourseClass(courseClass);
		pager.setTotal(total);
		pager.setTotalPage(total%pager.getPagesize()==0?total/pager.getPagesize():total/pager.getPagesize()+1);
		return pager;
	}

	@Override
	public Long getCountByCourseClass(CourseClass courseClass) {
		return (Long) getSession().createQuery("select count(*) from "+ClassDetail.class.getName()+" as  ccs where ccs.courseClass=:courseClass ")
				   .setParameter("courseClass", courseClass)
				   .uniqueResult();
	}


	

	
	
	

}
