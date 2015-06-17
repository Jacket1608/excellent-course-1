package io.shuqi.graduation.service;

import io.shuqi.graduation.domain.Notification;
import io.shuqi.graduation.domain.bo.Pager;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

public abstract class NotifyService {
	/**
	 * 网站公告管理
	 * @author shuqi
	 * @date   2015年4月30日
	 * @version since 1.0
	 * @param request
	 * @param crrentIndex
	 * @param pageSize
	 * @return
	 */
	public abstract ModelAndView notifyManager(HttpServletRequest request,Long crrentPage,int pageSize);
	/**
	 * 发布公告
	 * @author shuqi
	 * @date   2015年5月2日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	public abstract Map<String, Object> publishNotification(HttpServletRequest request);
	/**
	 * 通过ID删除公告
	 * @author shuqi
	 * @date   2015年5月2日
	 * @version since 1.0
	 * @param id
	 * @param request
	 * @return
	 */
	public abstract Map<String, Object> deleteNotifyByID(Long id, HttpServletRequest request);
	/**
	 * 编辑公告
	 * @author shuqi
	 * @date   2015年5月2日
	 * @version since 1.0
	 * @param id
	 * @param request
	 * @return
	 */
	public abstract ModelAndView editIndex(Long id, HttpServletRequest request);
	/**
	 * 编辑公告保存
	 * @author shuqi
	 * @date   2015年5月2日
	 * @version since 1.0
	 * @param id
	 * @param request
	 * @return
	 */
	public abstract Map<String, Object> editAction(Long id, HttpServletRequest request);
	/**
	 * 得到热门的size条公告
	 * @author shuqi
	 * @date   2015年5月6日
	 * @version since 1.0
	 * @param i
	 * @return
	 */
	public abstract List<Notification> getNewNotification(int i);
	/**
	 * 得到公告的分页对象
	 * @author shuqi
	 * @date   2015年5月13日
	 * @version since 1.0
	 * @param pager
	 * @return
	 */
	public abstract Pager<Notification> getPagerByPager(Pager<Notification> pager);

}
