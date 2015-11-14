package io.shuqi.graduation.dao.impl;

import org.springframework.stereotype.Repository;

import io.shuqi.graduation.dao.NotifyDao;
import io.shuqi.graduation.domain.Notification;
import io.shuqi.graduation.domain.User;
import io.shuqi.graduation.domain.bo.Pager;
import io.shuqi.graduation.enumtype.UserTypeEnum;

import java.util.ArrayList;
import java.util.List;
@Repository
public class NotifyDaoImpl extends NotifyDao {
	
	
	//管理员的公告查询hql
	private final String adminNotifyPagerHql ="FROM "+Notification.class.getName()+" ntf order by ntf.modifyTime DESC";
	private final String adminNotifyCountHql ="SELECT COUNT(id) FROM "+Notification.class.getName();
	//老师的公告查询hql
	private final String teacherNotifyPagerHql ="FROM "+Notification.class.getName()+" ntf  WHERE ntf.createUser=:user";
	private final String teacherNotifyCountHql ="SELECT COUNT(id) FROM  "+Notification.class.getName()+" ntf  WHERE ntf.createUser=:user";
	@SuppressWarnings("unchecked")
	@Override
	public Pager<Notification> getNotifyPager(Pager<Notification> pager, User currentUser) {
		List<Notification> datas = new ArrayList<>();
		if(UserTypeEnum.ADMIN.getName().equals(currentUser.getUserType())){
			datas = getSession().createQuery(adminNotifyPagerHql)
					.setMaxResults(pager.getPagesize())
					.setFirstResult(((Number)((pager.getCurrentPage()-1)*pager.getPagesize())).intValue())
					.list();
		}else if(UserTypeEnum.TEACHER.getName().equals(currentUser.getUserType())){
			datas = getSession().createQuery(teacherNotifyPagerHql)
						.setParameter("user", currentUser)
						.setMaxResults(pager.getPagesize())
						.setFirstResult(((Number)((pager.getCurrentPage()-1)*pager.getPagesize())).intValue())
						.list();
		}
		pager.setDatas(datas);
		//构建pager
		Long total = getNotifyCount(currentUser);
		pager.setTotal(total);
		pager.setTotalPage(total%pager.getPagesize()==0?total/pager.getPagesize():total/pager.getPagesize()+1);
		return pager;
	}
	@Override
	public Long getNotifyCount(User currentUser) {
		if(UserTypeEnum.ADMIN.getName().equals(currentUser.getUserType())){
			return (Long) getSession().createQuery(adminNotifyCountHql).uniqueResult();
		}else if(UserTypeEnum.TEACHER.getName().equals(currentUser.getUserType())){
			return (Long) getSession().createQuery(teacherNotifyCountHql)
									  .setParameter("user", currentUser)
									  .uniqueResult();
		}
		return 0L;
	}
	@Override
	public Long getAdminNotifyCount() {
			return (Long) getSession().createQuery(adminNotifyCountHql).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Notification> getNewNotification(int i) {
		return getSession().createQuery("from  "+Notification.class.getName()+" as  ntf order by ntf.modifyTime desc,ntf.createTime desc")
						   .setMaxResults(i)
						   .list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public Pager<Notification> getNotifyPager(Pager<Notification> pager) {
		List<Notification> datas = new ArrayList<>();
		datas = getSession().createQuery(adminNotifyPagerHql)
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
		return getAdminNotifyCount();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Pager<Notification> getNotifyPagerASC(Pager<Notification> pager) {
		List<Notification> datas = new ArrayList<>();
		datas = getSession().createQuery("FROM "+Notification.class.getName()+" ntf order by ntf.modifyTime ASC")
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
	@SuppressWarnings("unchecked")
	@Override
	public Pager<Notification> getNotifyPagerById(Long id) {
		Pager<Notification> pager = new Pager<Notification>(id,1);
		List<Notification> datas = new ArrayList<>();
		datas = getSession().createQuery("FROM "+Notification.class.getName()+" as ntf where ntf.id=:id")
				.setParameter("id", id)
				.setMaxResults(pager.getPagesize())
				.list();
		pager.setDatas(datas);
		//构建pager
		Long total = getCount();
		pager.setTotal(total);
		pager.setTotalPage(total%pager.getPagesize()==0?total/pager.getPagesize():total/pager.getPagesize()+1);
		return pager;
	}
	
}
