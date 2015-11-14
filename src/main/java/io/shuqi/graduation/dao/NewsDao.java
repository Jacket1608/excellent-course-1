package io.shuqi.graduation.dao;

import java.util.List;

import io.shuqi.graduation.base.AbstractBaseDao;
import io.shuqi.graduation.base.InterfaceBaseDao;
import io.shuqi.graduation.domain.News;
import io.shuqi.graduation.domain.bo.Pager;

public abstract class NewsDao extends AbstractBaseDao<News> implements InterfaceBaseDao<News> {
	
	/**
	 * 得到新闻的分页对象
	 * @author shuqi
	 * @date   2015年4月30日
	 * @version since 1.0
	 * @param pager
	 * @return
	 */
	public abstract Pager<News> getNewsPager(Pager<News> pager);
	/**
	 * 得到总数
	 * @author shuqi
	 * @date   2015年4月30日
	 * @version since 1.0
	 * @return
	 */
	public abstract Long getCount();
	/**
	 * 得到最新的先问投票size
	 * @author shuqi
	 * @date   2015年5月6日
	 * @version since 1.0
	 * @param size
	 * @return
	 */
	public abstract List<News> getNewNews(int size);
	/**
	 * 得到热门的先问投票size
	 * @author shuqi
	 * @date   2015年5月6日
	 * @version since 1.0
	 * @param size
	 * @return
	 */
	public abstract List<News> gethotNews(int size);
	/**
	 * 顺序查询，用于查询每一条
	 * @author shuqi
	 * @date   2015年5月14日
	 * @version since 1.0
	 * @param pager
	 * @return
	 */
	public abstract Pager<News> getNewsPagerASC(Pager<News> pager);
	
	/**
	 * 通过ID查询一个Pager
	 * @author shuqi
	 * @date   2015年5月14日
	 * @version since 1.0
	 * @param id
	 * @return
	 */
	public abstract Pager<News> getNewsPagerById(Long id);

}
