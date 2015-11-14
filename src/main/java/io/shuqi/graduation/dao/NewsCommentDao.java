package io.shuqi.graduation.dao;

import java.util.List;

import io.shuqi.graduation.base.AbstractBaseDao;
import io.shuqi.graduation.base.InterfaceBaseDao;
import io.shuqi.graduation.domain.News;
import io.shuqi.graduation.domain.NewsComments;
import io.shuqi.graduation.domain.User;
import io.shuqi.graduation.domain.bo.Pager;

public abstract class NewsCommentDao extends AbstractBaseDao<NewsComments> implements InterfaceBaseDao<NewsComments> {

	/**
	 * 得到对应人的新闻评论
	 * @author shuqi
	 * @date   2015年4月30日
	 * @version since 1.0
	 * @param currentUserId
	 * @return
	 */
	public abstract Pager<NewsComments> getMyNewsComments(Pager<NewsComments> pager, User currentUser);
	/**
	 * 统计对应人的新闻评论
	 * @author shuqi
	 * @date   2015年4月30日
	 * @version since 1.0
	 * @param currentUserId
	 * @return
	 */
	public abstract Long countUserNewsComments(User currentUser);
	/**
	 * 累积新闻评论
	 * @author shuqi
	 * @date   2015年4月30日
	 * @version since 1.0
	 * @return
	 */
	public abstract Long getCount();
	/**
	 * 得到新闻的评论
	 * @author shuqi
	 * @date   2015年5月13日
	 * @version since 1.0
	 * @param id
	 * @return
	 */
	public abstract List<NewsComments> getByNewsId(News news);

}
