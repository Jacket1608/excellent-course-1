package io.shuqi.graduation.service;

import io.shuqi.graduation.domain.bo.SiteInfoCount;



public abstract class SiteInfoCountQuery {
	/**
	 * 新闻统计
	 * @author shuqi
	 * @date   2015年4月28日
	 * @version since 1.0
	 * @return
	 */
	public abstract   SiteInfoCount newsCount();
	/**
	 * 新闻评理统计
	 * @author shuqi
	 * @date   2015年4月28日
	 * @version since 1.0
	 * @return
	 */
	public abstract   SiteInfoCount newsCommentsCount();
	/**
	 * 通知统计
	 * @author shuqi
	 * @date   2015年4月28日
	 * @version since 1.0
	 * @return
	 */
	public abstract   SiteInfoCount notificationCount();
	/**
	 * 用户统计
	 * @author shuqi
	 * @date   2015年4月28日
	 * @version since 1.0
	 * @return
	 */
	public abstract   SiteInfoCount userCount();
	/**
	 * 教师统计
	 * @author shuqi
	 * @date   2015年4月28日
	 * @version since 1.0
	 * @return
	 */
	public abstract   SiteInfoCount teacherCount();
	/**
	 * 学生统计
	 * @author shuqi
	 * @date   2015年4月28日
	 * @version since 1.0
	 * @return
	 */
	public abstract   SiteInfoCount studentCount();
}
