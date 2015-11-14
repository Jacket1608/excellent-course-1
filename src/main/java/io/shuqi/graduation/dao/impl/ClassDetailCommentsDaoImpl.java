package io.shuqi.graduation.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import io.shuqi.graduation.dao.ClassDetailCommentsDao;
import io.shuqi.graduation.domain.ClassDetail;
import io.shuqi.graduation.domain.ClassDetailComments;

@Repository
public class ClassDetailCommentsDaoImpl extends ClassDetailCommentsDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<ClassDetailComments> findAll(ClassDetail classDetail) {
		return getSession().createQuery("from "+ClassDetailComments.class.getName()+" as cdc where cdc.classDetail =:classDetail")
						   .setParameter("classDetail", classDetail)
						   .list();
	}

}
