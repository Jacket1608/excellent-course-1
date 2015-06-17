package io.shuqi.graduation.dao;

import java.util.List;

import io.shuqi.graduation.base.AbstractBaseDao;
import io.shuqi.graduation.base.InterfaceBaseDao;
import io.shuqi.graduation.domain.ClassDetail;
import io.shuqi.graduation.domain.ClassDetailComments;

public abstract class ClassDetailCommentsDao extends AbstractBaseDao<ClassDetailComments> implements InterfaceBaseDao<ClassDetailComments> {

	/**
	 * 查询全部的评论
	 * @author shuqi
	 * @date   2015年5月14日
	 * @version since 1.0
	 * @return
	 */
	public abstract List<ClassDetailComments> findAll(ClassDetail classDetail);

}
