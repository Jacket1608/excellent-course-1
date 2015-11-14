package io.shuqi.graduation.dao;

import java.util.List;

import io.shuqi.graduation.base.AbstractBaseDao;
import io.shuqi.graduation.base.InterfaceBaseDao;
import io.shuqi.graduation.domain.Notification;
import io.shuqi.graduation.domain.User;
import io.shuqi.graduation.domain.bo.Pager;

public abstract class NotifyDao extends AbstractBaseDao<Notification> implements InterfaceBaseDao<Notification> {
	
	/**
	 * 查询公告pager
	 * @author shuqi
	 * @date   2015年4月30日
	 * @version since 1.0
	 * @param pager
	 * @return
	 */
	public abstract Pager<Notification> getNotifyPager(Pager<Notification> pager,User currentUser);
	/**
	 * 得到对应用户的公告统计
	 * @author shuqi
	 * @date   2015年5月6日
	 * @version since 1.0
	 * @param currentUser
	 * @return
	 */
	public abstract Long getNotifyCount(User currentUser);
	/**
	 * 得到管理员的公告统计
	 * @author shuqi
	 * @date   2015年5月6日
	 * @version since 1.0
	 * @return
	 */
	public abstract Long getAdminNotifyCount();
	/**
	 * 得到新的公告
	 * @author shuqi
	 * @date   2015年5月6日
	 * @version since 1.0
	 * @param i
	 * @return
	 */
	public abstract List<Notification> getNewNotification(int i);
	/**
	 * 得到分页对象
	 * @author shuqi
	 * @date   2015年5月13日
	 * @version since 1.0
	 * @param pager
	 * @return
	 */
	public abstract Pager<Notification> getNotifyPager(Pager<Notification> pager);
	/**
	 * 统计总条数
	 * @author shuqi
	 * @date   2015年5月13日
	 * @version since 1.0
	 * @return
	 */
	public abstract Long getCount();
	/**
	 * 顺序查看用于单条数据分页
	 * @author shuqi
	 * @date   2015年5月14日
	 * @version since 1.0
	 * @param pager
	 * @return
	 */
	public abstract Pager<Notification> getNotifyPagerASC(Pager<Notification> pager);
	
	/**
	 * 通过ID查询得到一个Pager
	 * @author shuqi
	 * @date   2015年5月14日
	 * @version since 1.0
	 * @param id
	 * @return
	 */
	public abstract Pager<Notification> getNotifyPagerById(Long id);
}
