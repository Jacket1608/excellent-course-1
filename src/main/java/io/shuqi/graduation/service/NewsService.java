package io.shuqi.graduation.service;

import io.shuqi.graduation.domain.News;
import io.shuqi.graduation.domain.bo.Pager;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

public abstract class NewsService {
	/**
	 * 新闻管理
	 * @author shuqi
	 * @date   2015年5月2日
	 * @version since 1.0
	 * @param request
	 * @param crrentPage
	 * @param pageSize
	 * @return
	 */
	public abstract ModelAndView newsManager(HttpServletRequest request,Long crrentPage,int pageSize);
	/**
	 * 新闻发布
	 * @author shuqi
	 * @date   2015年5月2日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	public abstract Map<String, Object> publishNews(HttpServletRequest request);
	/**
	 * 通过id删除新闻
	 * @author shuqi
	 * @date   2015年5月2日
	 * @version since 1.0
	 * @param id
	 * @param request
	 * @return
	 */
	public abstract Map<String, Object> deleteNewsByID(Long id, HttpServletRequest request);
	/**
	 * 编辑新闻
	 * @author shuqi
	 * @date   2015年5月2日
	 * @version since 1.0
	 * @param id
	 * @param request
	 * @return
	 */
	public abstract ModelAndView editIndex(Long id, HttpServletRequest request);
	/**
	 * 编辑新闻保存
	 * @author shuqi
	 * @date   2015年5月2日
	 * @version since 1.0
	 * @param id
	 * @param request
	 * @return
	 */
	public abstract Map<String, Object> editAction(Long id, HttpServletRequest request);
	/**
	 * 得到最新的size条新闻
	 * @author shuqi
	 * @date   2015年5月6日
	 * @version since 1.0
	 * @param size
	 * @return
	 */
	public abstract List<News> getNewNews(int size);
	/**
	 * 得到热门的size条新闻
	 * @author shuqi
	 * @date   2015年5月6日
	 * @version since 1.0
	 * @param size
	 * @return
	 */
	public abstract List<News> getHotNews(int size);
	/**
	 * 得到新闻的分页对象
	 * @author shuqi
	 * @date   2015年5月13日
	 * @version since 1.0
	 * @param pager
	 * @return
	 */
	public abstract Pager<News> getPagerByPager(Pager<News> pager);
	

}
