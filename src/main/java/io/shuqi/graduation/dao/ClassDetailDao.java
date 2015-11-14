package io.shuqi.graduation.dao;

import java.util.List;

import io.shuqi.graduation.base.AbstractBaseDao;
import io.shuqi.graduation.base.InterfaceBaseDao;
import io.shuqi.graduation.domain.ClassDetail;
import io.shuqi.graduation.domain.CourseClass;
import io.shuqi.graduation.domain.User;
import io.shuqi.graduation.domain.bo.Pager;

public abstract class ClassDetailDao extends AbstractBaseDao<ClassDetail> implements InterfaceBaseDao<ClassDetail> {

	/**
	 * 得到最新的十条课程更新
	 * @author shuqi
	 * @date   2015年5月10日
	 * @version since 1.0
	 * @param size
	 * @return
	 */
	public abstract List<ClassDetail> getNewClassDetail(int size);
	/**
	 * 得到分页对象通过教师
	 * @author shuqi
	 * @date   2015年5月12日
	 * @version since 1.0
	 * @param pager
	 * @param currentUser
	 * @return
	 */
	public abstract Pager<ClassDetail> getPagerByUser(Pager<ClassDetail> pager, User currentUser);
	/**
	 * 得到教师的总课程内容
	 * @author shuqi
	 * @date   2015年5月12日
	 * @version since 1.0
	 * @param pager
	 * @param currentUser
	 * @return
	 */
	public abstract Long getCountByUser(User currentUser);
	/**
	 * 通过课程得到课程内容
	 * @author shuqi
	 * @date   2015年5月14日
	 * @version since 1.0
	 * @param courseClass
	 * @return
	 */
	public abstract Pager<ClassDetail> getPagerByCourseClass(Pager<ClassDetail> pager,CourseClass courseClass);
	/**
	 * 统计课程的课程内容条数
	 * @author shuqi
	 * @date   2015年5月14日
	 * @version since 1.0
	 * @param currentUser
	 * @return
	 */
	public abstract Long getCountByCourseClass(CourseClass courseClass);
	
	

}
