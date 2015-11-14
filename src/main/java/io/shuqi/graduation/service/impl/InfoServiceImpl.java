package io.shuqi.graduation.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.HtmlUtils;

import io.shuqi.graduation.dao.NewsCommentDao;
import io.shuqi.graduation.dao.NewsDao;
import io.shuqi.graduation.dao.NotifyDao;
import io.shuqi.graduation.domain.Attach;
import io.shuqi.graduation.domain.BbsContent;
import io.shuqi.graduation.domain.ClassDetail;
import io.shuqi.graduation.domain.News;
import io.shuqi.graduation.domain.NewsComments;
import io.shuqi.graduation.domain.Notification;
import io.shuqi.graduation.domain.User;
import io.shuqi.graduation.domain.bo.Pager;
import io.shuqi.graduation.service.AttachService;
import io.shuqi.graduation.service.BbsService;
import io.shuqi.graduation.service.ClassDetailService;
import io.shuqi.graduation.service.InfoService;
import io.shuqi.graduation.service.NewsService;
import io.shuqi.graduation.service.NotifyService;
import io.shuqi.graduation.utils.DateUtil;
import io.shuqi.graduation.utils.RequestUtil;
import io.shuqi.graduation.utils.StringUtil;

@Service
@Transactional
public class InfoServiceImpl extends InfoService {

	@Autowired
	private NewsService newsService;
	@Autowired
	private AttachService attachService;
	@Autowired
	private ClassDetailService classDetailService;
	@Autowired
	private BbsService bbsService;
	@Autowired
	private NotifyService notifyService;
	@Autowired
	private NewsDao newsDao ;
	@Autowired
	private NotifyDao notifyDao;
	@Autowired
	private NewsCommentDao newsCommentDao;

	@Override
	public ModelAndView getIndex(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/home/info");
		//最新新闻：
		List<News> hotNewses = newsService.getHotNews(5);
		mv.addObject("hotNewses", hotNewses);
		//热门资料
		List<Attach> attachs = attachService.getNewAttachs(7);
		mv.addObject("attachs", attachs);
		//最新课程
		List<ClassDetail> classDetails = classDetailService.getNewClassDetail(10);
		mv.addObject("classDetails", classDetails);
		//最新讨论
		List<BbsContent> bbsContents = bbsService.getTopNewBbs(10);
		mv.addObject("bbsContents", bbsContents);
		//最新新闻：
		List<News> newses = newsService.getNewNews(10);
		mv.addObject("newses", newses);
		//最新公告
		List<Notification> notifications = notifyService.getNewNotification(10);
		mv.addObject("notifications", notifications);

		return mv;
	}

	@Override
	public ModelAndView infoAjax(HttpServletRequest request,HttpServletResponse response) {
		String type  = RequestUtil.getString(request, "type");
		if("newsmore".equals(type)){
			ModelAndView mv = new ModelAndView("/news/indexNewsMore");
			Long currentPage  = RequestUtil.getLong(request, "currentPage");
			int pageSize  = Integer.valueOf(RequestUtil.getString(request, "pageSize"));
			Pager<News> pager = new Pager<News>(currentPage, pageSize);
			mv.addObject("pager", newsService.getPagerByPager(pager));
			mv.addObject("souresType", "newsmore");
			return mv;
		}else if("notificationmore".equals(type)){
			ModelAndView mv = new ModelAndView("/notification/indexNotificationMore");
			Long currentPage  = RequestUtil.getLong(request, "currentPage");
			int pageSize  = Integer.valueOf(RequestUtil.getString(request, "pageSize"));
			Pager<Notification> pager = new Pager<Notification>(currentPage, pageSize);
			mv.addObject("pager", notifyService.getPagerByPager(pager));
			mv.addObject("souresType", "notificationmore");
			return mv;
		}else if("news".equals(type)){
			ModelAndView mv = new ModelAndView("/home/viewInfoCard");
			Long currentPage  = RequestUtil.getLong(request, "currentPage");
			int pageSize  = Integer.valueOf(RequestUtil.getString(request, "pageSize"));
			Pager<News> pager = newsDao.getNewsPagerASC(new Pager<News>(currentPage, pageSize));
			mv.addObject("pager", pager);
			
			//如果pager的大小为1 表示是查看
			if(pager.getPagesize()==1){
				//更新新闻查看次数
				News temp = pager.getDatas().get(0);
				temp.setReadTimes(temp.getReadTimes()+1);
				newsDao.update(temp);
			}
			
			if(pager.getDatas()!=null && pager.getDatas().size()>0){
				mv.addObject("newsComments", newsCommentDao.getByNewsId(pager.getDatas().get(0)));
			}
			mv.addObject("souresType", "news");
			return mv;
		}else if("notification".equals(type)){
			ModelAndView mv = new ModelAndView("/home/viewInfoCard");
			Long currentPage  = RequestUtil.getLong(request, "currentPage");
			int pageSize  = Integer.valueOf(RequestUtil.getString(request, "pageSize"));
			mv.addObject("pager", notifyDao.getNotifyPagerASC(new Pager<Notification>(currentPage,pageSize)));
			mv.addObject("souresType", "notification");
			return mv;
		}else if("commitNewsComments".equals(type)){
			User currentUser = (User)request.getSession().getAttribute("currentUser");
			Map<String , Object> result = new HashMap<String, Object>();
			if(currentUser!=null){
				Long newsId= RequestUtil.getLong(request, "newsid");
				String content= RequestUtil.getString(request, "content");
				try {
					//尼玛  乱码
					content = new String(content.getBytes("ISO-8859-1"), "utf-8");
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				News newsP = newsDao.getById(newsId);
				if(newsP!=null && !StringUtil.isNull(content)){
					try{
						NewsComments newsComments = new NewsComments(content, new Date(), newsP, currentUser);
						
						//更新新闻评论次数
						newsCommentDao.save(newsComments);
						newsP.setCommentsCount(newsP.getCommentsCount()+1);
						newsDao.update(newsP);
						
						result.put("success", true);
						result.put("msg", "评论成功");
						result.put("headImage", currentUser.getHeadImage());
						result.put("time", DateUtil.formatDate(newsComments.getCreateTime()));
						result.put("userName", HtmlUtils.htmlEscape(request.getSession().getAttribute("displayName").toString()));
						result.put("content", HtmlUtils.htmlEscape(newsComments.getContent()));
					}catch(Exception e){
						result.put("success", false);
						result.put("msg", "评论新闻失败！");
					}
				}else{
					result.put("success", false);
					result.put("msg", "你评论的新闻不存在！");
				}
			}else{
				result.put("success", false);
				result.put("msg", "你当前未登陆，不能评论额！");
			}
			JSONObject jsonObject = JSONObject.fromObject(result);
			try {
				response.setContentType("text/json;charset=UTF-8");
				response.getWriter().print(jsonObject.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}else{
			return null;
		}
		
	}

	@Override
	public ModelAndView infoViewJsp(Long id, String sourseType, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/home/viewInfo");
		//热门资料
		List<Attach> attachs = attachService.getNewAttachs(7);
		mv.addObject("attachs", attachs);
		//最新课程
		List<ClassDetail> classDetails = classDetailService.getNewClassDetail(10);
		mv.addObject("classDetails", classDetails);
		//最新讨论
		List<BbsContent> bbsContents = bbsService.getTopNewBbs(10);
		mv.addObject("bbsContents", bbsContents);
		//得到对应的数据：sourseType
		if("news".equals(sourseType)){
			Pager<News> pager = newsDao.getNewsPagerById(id);
			
			//更新新闻查看次数
			News temp = pager.getDatas().get(0);
			temp.setReadTimes(temp.getReadTimes()+1);
			newsDao.update(temp);
			
			mv.addObject("pager", pager);
			if(pager.getDatas()!=null && pager.getDatas().size()>0){
				mv.addObject("newsComments", newsCommentDao.getByNewsId(pager.getDatas().get(0)));
			}
			mv.addObject("souresType", "news");
		}else if("notification".equals(sourseType)){
			Pager<Notification> pager = notifyDao.getNotifyPagerById(id);
			mv.addObject("pager", pager);
			
			//更新公告查看次数
			Notification temp = pager.getDatas().get(0);
			temp.setReadTimes(temp.getReadTimes()+1);
			notifyDao.update(temp);
			
			mv.addObject("souresType", "notification");
		}
		return mv;
	}
	
	

}
