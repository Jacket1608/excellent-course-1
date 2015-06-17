package io.shuqi.graduation.controller;

import java.util.Map;

import io.shuqi.graduation.domain.BbsContent;
import io.shuqi.graduation.domain.BbsContentComments;
import io.shuqi.graduation.domain.bo.Pager;
import io.shuqi.graduation.service.BbsService;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BbsController {

	@Autowired 
	private BbsService bbsService;
	/**
	 * 论坛版块管理
	 * @author shuqi
	 * @date   2015年5月15日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/bbs/bbsBlockManager"},method= RequestMethod.GET)
	public ModelAndView bbsBlockManager(HttpServletRequest request){
		return bbsService.bbsBlockManager(request);
	}
	/**
	 * 添加论坛版块
	 * @author shuqi
	 * @date   2015年5月15日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/bbs/addBbsBlock"},method= RequestMethod.GET)
	public ModelAndView addBbsBlock(HttpServletRequest request){
		return bbsService.addBbsBlockIndex(request);
	}
	
	/**
	 * 添加论坛版块操作
	 * @author shuqi
	 * @date   2015年5月15日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/bbs/addBbsBlock"},method= RequestMethod.POST)
	@ResponseBody public Map<String,Object> addBbsBlockAction(HttpServletRequest request){
		return bbsService.addBbsBlockAction(request);
	}
	
	/**
	 * 编辑论坛版块
	 * @author shuqi
	 * @date   2015年5月15日
	 * @version since 1.0
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value={"/bbs/editBbsBlock/{id}"},method= RequestMethod.GET)
	public ModelAndView editBbsBlock(HttpServletRequest request,@PathVariable(value="id") Long id){
		return bbsService.editBbsBlock(request,id);
	}
	
	/**
	 * 编辑论坛版块操作
	 * @author shuqi
	 * @date   2015年5月15日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/bbs/editBbsBlock"},method= RequestMethod.POST)
	@ResponseBody public Map<String,Object> editBbsBlockAction(HttpServletRequest request){
		return bbsService.editBbsBlockAction(request);
	}
	/**
	 * 我的帖子jsp返回
	 * @author shuqi
	 * @date   2015年5月5日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/bbs/myTie/mv/{currentPage}/{pageSize}"},method=RequestMethod.GET)
	public ModelAndView myTieMv(HttpServletRequest request,@PathVariable(value="currentPage") Long crrentPage,@PathVariable(value="pageSize") int pageSize){
		return bbsService.getMyTieMv(request,new Pager<BbsContent>(crrentPage,pageSize));
	}
	/**
	 * 我的帖子json返回
	 * @author shuqi
	 * @date   2015年5月5日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/bbs/myTie/js/{currentPage}/{pageSize}"},method=RequestMethod.GET)
	@ResponseBody public Map<String,Object> myTieJson(HttpServletRequest request,@PathVariable(value="currentPage") Long crrentPage,@PathVariable(value="pageSize") int pageSize){
		return bbsService.getMyTieJson(request,new Pager<BbsContent>(crrentPage,pageSize));
		
	}
	/**
	 * 我的帖子评论jsp
	 * @author shuqi
	 * @date   2015年5月5日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/bbs/myTieComments/mv/{currentPage}/{pageSize}"},method=RequestMethod.GET)
	public ModelAndView myTieCommentsMv(HttpServletRequest request,@PathVariable(value="currentPage") Long crrentPage,@PathVariable(value="pageSize") int pageSize){
		return bbsService.getMyTieCommentsMv(request,new Pager<BbsContentComments>(crrentPage,pageSize));
	}
	/**
	 * 我的帖子评论json
	 * @author shuqi
	 * @date   2015年5月5日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/bbs/myTieComments/js/{currentPage}/{pageSize}"},method=RequestMethod.GET)
	@ResponseBody public Map<String,Object> myTieCommentsJson(HttpServletRequest request,@PathVariable(value="currentPage") Long crrentPage,@PathVariable(value="pageSize") int pageSize){
		return bbsService.getMyTieCommentsJson(request,new Pager<BbsContentComments>(crrentPage,pageSize));
	}
	/**
	 * 论坛的前台首页
	 * @author shuqi
	 * @date   2015年5月15日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/bbs/index",method=RequestMethod.GET)
	public ModelAndView homeIndex(HttpServletRequest request){
		return bbsService.getIndex(request);
	}
	/**
	 * 社区查看
	 * @author shuqi
	 * @date   2015年5月14日
	 * @version since 1.0
	 * @param request
	 * @param id
	 * @param type
	 * @return
	 */
	@RequestMapping(value="/bbs/{id}/{type}",method=RequestMethod.GET)
	public ModelAndView view(HttpServletRequest request,@PathVariable(value="id") Long id,@PathVariable(value="type") String type){
		return bbsService.view(request,id,type);
	}
	/**
	 * 论坛的分页查看
	 * @author shuqi
	 * @date   2015年5月15日
	 * @version since 1.0
	 * @param request
	 * @param currentPage
	 * @param pageSize
	 * @param type
	 * @return
	 */
	@RequestMapping(value="/bbs/pager/{currentPage}/{pageSize}/{type}",method=RequestMethod.GET)
	public ModelAndView viewPager(HttpServletRequest request,@PathVariable(value="currentPage") Long currentPage,@PathVariable(value="pageSize") int pageSize,@PathVariable(value="type") String type){
		return bbsService.viewPager(request,currentPage,pageSize,type);
	}
	/**
	 * 论坛的前台发帖/回复事件
	 * @author shuqi
	 * @date   2015年5月15日
	 * @version since 1.0
	 * @param request
	 * @param type
	 * @return
	 */
	@RequestMapping(value={"/bbs/add/{type}"},method=RequestMethod.POST)
	@ResponseBody public Map<String,Object> addEvent(HttpServletRequest request,@PathVariable(value="type") String type){
		return bbsService.addEvent(request,type);
	}
	/**
	 * 帖子或者回复的隐藏
	 * @author shuqi
	 * @date   2015年5月15日
	 * @version since 1.0
	 * @param request
	 * @param id
	 * @param type
	 * @return
	 */
	@RequestMapping(value={"/bbs/hidden/{id}/{type}"},method=RequestMethod.POST)
	@ResponseBody public Map<String,Object> bbsHidden(HttpServletRequest request,@PathVariable(value="id") Long id,@PathVariable(value="type") String type){
		return bbsService.bbsAccess(request,id,type,false);
	}
	
	/**
	 * 帖子或者回复的重新显示
	 * @author shuqi
	 * @date   2015年5月15日
	 * @version since 1.0
	 * @param request
	 * @param id
	 * @param type
	 * @return
	 */
	@RequestMapping(value={"/bbs/restart/{id}/{type}"},method=RequestMethod.POST)
	@ResponseBody public Map<String,Object> bbsRsstart(HttpServletRequest request,@PathVariable(value="id") Long id,@PathVariable(value="type") String type){
		return bbsService.bbsAccess(request,id,type,true);
	}
	
	/*
	 * 论坛的帖子或者回复编辑
	 */
	@RequestMapping(value="/course/edit/{id}/{type}",method=RequestMethod.GET)
	public ModelAndView editIndex(HttpServletRequest request,@PathVariable(value="id") Long id,@PathVariable(value="type") String type){
		return bbsService.editIndex(request,id,type);
	}
	/*
	 * 论坛的帖子或者回复编辑 --atcion
	 */
	@RequestMapping(value="/course/edit/{id}/{type}",method=RequestMethod.POST)
	@ResponseBody public Map<String,Object> editAction(HttpServletRequest request,@PathVariable(value="id") Long id,@PathVariable(value="type") String type){
		return bbsService.editAction(request,id,type);
	}
}
