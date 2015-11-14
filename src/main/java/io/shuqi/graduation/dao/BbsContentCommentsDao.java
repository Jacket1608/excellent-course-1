package io.shuqi.graduation.dao;

import io.shuqi.graduation.base.AbstractBaseDao;
import io.shuqi.graduation.base.InterfaceBaseDao;
import io.shuqi.graduation.domain.BbsContent;
import io.shuqi.graduation.domain.BbsContentComments;
import io.shuqi.graduation.domain.User;
import io.shuqi.graduation.domain.bo.Pager;

public abstract class BbsContentCommentsDao extends AbstractBaseDao<BbsContentComments> implements InterfaceBaseDao<BbsContentComments> {
	/**
	 * 通过用户查询该用户的帖子评论
	 * @author shuqi
	 * @date   2015年5月5日
	 * @version since 1.0
	 * @param pager
	 * @param currentUser
	 * @return
	 */
	public abstract Pager<BbsContentComments> getUserTieCommentsPagerByUser(Pager<BbsContentComments> pager, User currentUser);
	/**
	 * 通过用户查询该用户的帖子评论条数
	 * @author shuqi
	 * @date   2015年5月5日
	 * @version since 1.0
	 * @param currentUser
	 * @return
	 */
	public abstract Long getUserTieCommentsCountByUser(User currentUser);
	/**
	 * 通过帖子查询评论pager
	 * @author shuqi
	 * @date   2015年5月5日
	 * @version since 1.0
	 * @param pager
	 * @param bbsContent
	 * @return
	 */
	public abstract Pager<BbsContentComments> getTieCommentsPagerByBbsContent(Pager<BbsContentComments> pager, BbsContent bbsContent);
	/**
	 * 通过帖子查询评论的总数
	 * @author shuqi
	 * @date   2015年5月5日
	 * @version since 1.0
	 * @param bbsContent
	 * @return
	 */
	public abstract Long getTieCommentsCountByBbsContent(BbsContent bbsContent);
}
