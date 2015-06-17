package io.shuqi.graduation.service;

import java.util.List;

import io.shuqi.graduation.domain.Attach;
import io.shuqi.graduation.exception.NoResourceException;
import io.shuqi.graduation.exception.NoRightException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public abstract class AttachService {

	/**
	 * 附件下载
	 * @author shuqi
	 * @date   2015年5月10日
	 * @version since 1.0
	 * @param attachId
	 * @param request
	 * @param response
	 * @throws NoRightException
	 * @throws NoResourceException 
	 */
	public abstract void attachDownload(Long attachId,HttpServletRequest request,HttpServletResponse response) throws NoRightException, NoResourceException;

	/**
	 * 得到最新上传的附件
	 * @author shuqi
	 * @date   2015年5月10日
	 * @version since 1.0
	 * @param i
	 * @return
	 */
	public abstract List<Attach> getNewAttachs(int size);

	/**
	 * 附件中心
	 * @author shuqi
	 * @date   2015年5月13日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	public abstract ModelAndView attachIndex(HttpServletRequest request);
	
	/**
	 * 附件分页
	 * @author shuqi
	 * @date   2015年5月13日
	 * @version since 1.0
	 * @param request
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public abstract ModelAndView attachPager(HttpServletRequest request, Long currentPage, int pageSize);
}
