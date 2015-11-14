package io.shuqi.graduation.dao;

import java.util.List;

import io.shuqi.graduation.base.AbstractBaseDao;
import io.shuqi.graduation.base.InterfaceBaseDao;
import io.shuqi.graduation.domain.BbsBlock;
import io.shuqi.graduation.domain.BbsContent;
import io.shuqi.graduation.domain.User;
import io.shuqi.graduation.domain.bo.Pager;

public abstract class BbsContentDao extends AbstractBaseDao<BbsContent> implements InterfaceBaseDao<BbsContent> {

	/**
	 * 统计我的帖子
	 * @author shuqi
	 * @date   2015年5月5日
	 * @version since 1.0
	 * @param pager
	 * @param currentUser
	 * @return
	 */
	public abstract Pager<BbsContent> getTiePagerByUser(Pager<BbsContent> pager, User currentUser);

	/**
	 * 统计我的帖子数
	 * @author shuqi
	 * @date   2015年5月5日
	 * @version since 1.0
	 * @param currenTUser
	 * @return
	 */
	public abstract Long getTieCountByUser(User currenTUser);
	
	/**
	 * 统计某个版块的帖子数
	 * @author shuqi
	 * @date   2015年5月5日
	 * @version since 1.0
	 * @param bbsBlock
	 * @return
	 */
	public abstract Long getTieCountByBbsBlock(BbsBlock bbsBlock);
	
	/**
	 * 通个论坛版块得到其帖子的pagger
	 * @author shuqi
	 * @date   2015年5月5日
	 * @version since 1.0
	 * @param pager
	 * @param bbsBlock
	 * @return
	 */
	public abstract Pager<BbsContent> getTiePagerByBbsBlock(Pager<BbsContent> pager,BbsBlock bbsBlock);
	/**
	 * 得到最新的帖子
	 * @author shuqi
	 * @date   2015年5月10日
	 * @version since 1.0
	 * @param size
	 * @return
	 */
	public abstract List<BbsContent> getTopNewBbs(int size);

	/**
	 * 社区最热size贴
	 * @author shuqi
	 * @date   2015年5月10日
	 * @version since 1.0
	 * @param size
	 * @return
	 */
	public abstract List<BbsContent> getTop10Bbs(int size);
	
}
