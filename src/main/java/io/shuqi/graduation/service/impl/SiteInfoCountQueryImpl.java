package io.shuqi.graduation.service.impl;

import io.shuqi.graduation.dao.NewsCommentDao;
import io.shuqi.graduation.dao.NewsDao;
import io.shuqi.graduation.dao.NotifyDao;
import io.shuqi.graduation.dao.UserDao;
import io.shuqi.graduation.domain.bo.SiteInfoCount;
import io.shuqi.graduation.service.SiteInfoCountQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("siteInfoCountQuery")
@Transactional
public class SiteInfoCountQueryImpl extends  SiteInfoCountQuery{

	@Autowired
	private UserDao userDao;
	@Autowired
	private NewsDao newsDao;
	@Autowired
	private NewsCommentDao commentDao;
	@Autowired
	private NotifyDao notifyDao;
	
	@Override
	public SiteInfoCount newsCount() {
		return new SiteInfoCount("新闻",newsDao.getCount(),SiteInfoCount.ico_news);
	}

	@Override
	public SiteInfoCount newsCommentsCount() {
		return new SiteInfoCount("新闻评论",commentDao.getCount(),SiteInfoCount.ico_news);
	}

	@Override
	public SiteInfoCount notificationCount() {
		return new SiteInfoCount("公告",notifyDao.getAdminNotifyCount(),SiteInfoCount.ico_news);
	}

	@Override
	public SiteInfoCount userCount() {
		return new SiteInfoCount("用户",userDao.countTotalUser(),SiteInfoCount.ico_user);
	}

	@Override
	public SiteInfoCount teacherCount() {
		return new SiteInfoCount("教师",userDao.countTotalTeacher(),SiteInfoCount.ico_user);
	}

	@Override
	public SiteInfoCount studentCount() {
		return new SiteInfoCount("学生",userDao.countTotalStudent(),SiteInfoCount.ico_user);
	}

}
