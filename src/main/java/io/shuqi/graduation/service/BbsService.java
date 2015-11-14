package io.shuqi.graduation.service;

import io.shuqi.graduation.domain.BbsContent;
import io.shuqi.graduation.domain.BbsContentComments;
import io.shuqi.graduation.domain.bo.Pager;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

public abstract class BbsService {

	/**
	 * 论坛版块管理
	 * @author shuqi
	 * @date   2015年5月4日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	public abstract ModelAndView bbsBlockManager(HttpServletRequest request);
	
	/**
	 * 添加论坛版块
	 * @author shuqi
	 * @date   2015年5月4日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	public abstract ModelAndView addBbsBlockIndex(HttpServletRequest request);
	/**
	 * 添加论坛版块
	 * @author shuqi
	 * @date   2015年5月4日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	public abstract Map<String, Object> addBbsBlockAction(HttpServletRequest request);
	/**
	 * 编辑论坛版块
	 * @author shuqi
	 * @date   2015年5月4日
	 * @version since 1.0
	 * @param request
	 * @param id
	 * @return
	 */
	public abstract ModelAndView editBbsBlock(HttpServletRequest request,Long id);
	
	/**
	 * 编辑论坛 --保存
	 * @author shuqi
	 * @date   2015年5月4日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	public abstract Map<String, Object> editBbsBlockAction(HttpServletRequest request);
	/**
	 * 得到我的帖子的Model
	 * @author shuqi
	 * @date   2015年5月5日
	 * @version since 1.0
	 * @param request
	 * @param pager 
	 * @return
	 */
	public abstract ModelAndView getMyTieMv(HttpServletRequest request, Pager<BbsContent> pager);
	/**
	 * 得到我的帖子回复的Model
	 * @author shuqi
	 * @date   2015年5月5日
	 * @version since 1.0
	 * @param request
	 * @param pager 
	 * @return
	 */
	public abstract ModelAndView getMyTieCommentsMv(HttpServletRequest request, Pager<BbsContentComments> pager);
	/**
	 * 得到我的帖子评论的json
	 * @author shuqi
	 * @date   2015年5月5日
	 * @version since 1.0
	 * @param request
	 * @param pager 
	 * @return
	 */
	public abstract Map<String, Object> getMyTieCommentsJson(HttpServletRequest request, Pager<BbsContentComments> pager);
	/**
	 * 
	 * @author shuqi
	 * @date   2015年5月5日
	 * @version since 1.0
	 * @param request
	 * @param pager 
	 * @return
	 */
	public abstract Map<String, Object> getMyTieJson(HttpServletRequest request, Pager<BbsContent> pager);

	/**
	 * 得到社区最新的帖子
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
	 * @param i
	 * @return
	 */
	public abstract List<BbsContent> getTop10Bbs(int i);

	/**
	 * 知识社区首页
	 * @author shuqi
	 * @date   2015年5月14日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	public abstract ModelAndView getIndex(HttpServletRequest request);
	/**
	 * 知识社区查看
	 * @author shuqi
	 * @date   2015年5月14日
	 * @version since 1.0
	 * @param request
	 * @param id
	 * @param type
	 * @return
	 */
	public abstract ModelAndView view(HttpServletRequest request, Long id, String type);

	/**
	 * 知识社区添加事件
	 * @author shuqi
	 * @date   2015年5月14日
	 * @version since 1.0
	 * @param request
	 * @param type
	 * @return
	 */
	public abstract Map<String, Object> addEvent(HttpServletRequest request, String type);

	/**
	 * 知识社区分页
	 * @author shuqi
	 * @date   2015年5月14日
	 * @version since 1.0
	 * @param request
	 * @param currentPage
	 * @param pageSize
	 * @param type
	 * @return
	 */
	public abstract ModelAndView viewPager(HttpServletRequest request, Long currentPage, int pageSize, String type);
	/**
	 * 论坛bbsAjax操作
	 * @author shuqi
	 * @date   2015年5月15日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	public abstract Map<String, Object> bbsAccess(HttpServletRequest request,Long id,String type,boolean access);
	/**
	 * 编辑  --index
	 * @author shuqi
	 * @date   2015年5月15日
	 * @version since 1.0
	 * @param request
	 * @param id
	 * @param type
	 * @return
	 */
	public abstract ModelAndView editIndex(HttpServletRequest request, Long id, String type);
	/**
	 * 编辑  --index
	 * @author shuqi
	 * @date   2015年5月15日
	 * @version since 1.0
	 * @param request
	 * @param id
	 * @param type
	 * @return
	 */
	public abstract Map<String, Object> editAction(HttpServletRequest request, Long id, String type);

}
