package io.shuqi.graduation.controller;

import java.util.List;

import io.shuqi.graduation.domain.Attach;
import io.shuqi.graduation.domain.BbsContent;
import io.shuqi.graduation.domain.ClassDetail;
import io.shuqi.graduation.domain.News;
import io.shuqi.graduation.domain.Notification;
import io.shuqi.graduation.service.AttachService;
import io.shuqi.graduation.service.BbsService;
import io.shuqi.graduation.service.ClassDetailService;
import io.shuqi.graduation.service.NewsService;
import io.shuqi.graduation.service.NotifyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller()
public class IndexController {
	
	@Autowired
	private NewsService newsService;
	@Autowired
	private NotifyService notifyService;
	@Autowired
	private BbsService bbsService;
	@Autowired
	private ClassDetailService classDetailService;
	@Autowired
	private AttachService attachService;
	
	@RequestMapping(value={"/index","/"})
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("/home/index");
		//最新新闻：
		List<News> newses = newsService.getNewNews(5);
		mv.addObject("newses", newses);
		//最新新闻：
		List<News> hotNewses = newsService.getHotNews(5);
		mv.addObject("hotNewses", hotNewses);
		//最新公告
		List<Notification> notifications = notifyService.getNewNotification(10);
		mv.addObject("notifications", notifications);
		//最新讨论
		List<BbsContent> bbsContents = bbsService.getTopNewBbs(10);
		mv.addObject("bbsContents", bbsContents);
		//最火讨论
		List<BbsContent> bbsContentTops = bbsService.getTop10Bbs(10);
		mv.addObject("bbsContentTops", bbsContentTops);
		//最新课程
		List<ClassDetail> classDetails = classDetailService.getNewClassDetail(10);
		mv.addObject("classDetails", classDetails);
		//热门资料
		List<Attach> attachs = attachService.getNewAttachs(7);
		mv.addObject("attachs", attachs);
		
		
		mv.addObject("user", "shuqi");
		return mv;
	}
	
	@RequestMapping(value={"/about/index","/about"})
	public ModelAndView aboutUs() {
		ModelAndView mv = new ModelAndView("/templet/aboutUs");
		mv.addObject("user", "shuqi");
		return mv;
	}
	
}
