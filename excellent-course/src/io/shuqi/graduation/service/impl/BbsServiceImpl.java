package io.shuqi.graduation.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import io.shuqi.graduation.dao.AttachDao;
import io.shuqi.graduation.dao.BbsBlockDao;
import io.shuqi.graduation.dao.BbsContentCommentsDao;
import io.shuqi.graduation.dao.BbsContentDao;
import io.shuqi.graduation.dao.UserDao;
import io.shuqi.graduation.domain.Attach;
import io.shuqi.graduation.domain.BbsBlock;
import io.shuqi.graduation.domain.BbsContent;
import io.shuqi.graduation.domain.BbsContentComments;
import io.shuqi.graduation.domain.ClassDetail;
import io.shuqi.graduation.domain.User;
import io.shuqi.graduation.domain.bo.Pager;
import io.shuqi.graduation.enumtype.UserTypeEnum;
import io.shuqi.graduation.service.AttachService;
import io.shuqi.graduation.service.BbsService;
import io.shuqi.graduation.service.ClassDetailService;
import io.shuqi.graduation.utils.RequestUtil;
import io.shuqi.graduation.utils.StringUtil;

@Service
@Transactional
public class BbsServiceImpl extends BbsService {
	
	private Logger log = Logger.getLogger(getClass());
	@Autowired 
	private BbsBlockDao bbsBlockDao;
	@Autowired 
	private BbsContentDao bbsContentDao;
	@Autowired 
	private BbsContentCommentsDao bbsContentCommentsDao;
	@Autowired 
	private AttachService attachService;
	@Autowired 
	private ClassDetailService classDetailService;
	@Autowired 
	private AttachDao attachDao;
	@Autowired 
	private UserDao userDao;
	
	
	@Override
	public ModelAndView bbsBlockManager(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		User user = (User) request.getSession().getAttribute("currentUser");
		if(UserTypeEnum.ADMIN.getName().equals(user.getUserType())){
			Set<BbsBlock> topBbs = bbsBlockDao.getTopBbsBlock();
			mv.addObject("list", topBbs);
			mv.setViewName("/admin/admin/editBbsBlock");
		}else{
			mv.setViewName("/templet/hasNoRight");
		}
		return mv;
	}

	@Override
	public ModelAndView addBbsBlockIndex(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		User user = (User) request.getSession().getAttribute("currentUser");
		if(UserTypeEnum.ADMIN.getName().equals(user.getUserType())){
			mv.addObject("parentBbs", bbsBlockDao.getTopBbsBlock());
			mv.setViewName("/admin/admin/addBbsBlock");
		}else{
			mv.setViewName("/templet/hasNoRight");
		}
		return mv;
	}

	@Override
	public Map<String, Object> addBbsBlockAction(HttpServletRequest request) {
		Map<String,Object> result = new HashMap<String,Object>();
		User user = (User) request.getSession().getAttribute("currentUser");
		if(UserTypeEnum.ADMIN.getName().equals(user.getUserType())){
			String name = RequestUtil.getString(request, "name");
			String description = RequestUtil.getString(request, "description");
			String blockRules = RequestUtil.getString(request, "blockRules");
			Long parentId = RequestUtil.getLong(request, "parentId");
			BbsBlock parent = null;
			Map<String ,Object > errors = new HashMap<>();
			if(StringUtil.isNull(name)){
				errors.put("name", "版块名不能为空");
			}else{
			   if(bbsBlockDao.getByNem(name)!=null){
				   errors.put("name", "已存在一个相同 的版块名");
				}
			}
			if(!parentId.equals(-1L)){
				parent = bbsBlockDao.getById(parentId);
			}
			if(errors.size()>0){
				result.put("success", false);
				StringBuilder msg = new StringBuilder();
				for (Entry<String, Object> key : errors.entrySet()) {
					msg.append(key.getValue()+"<br/>");
				}
				
				result.put("msg", msg.toString());
				return result;
			}
			try {
				BbsBlock bbsBlock = new BbsBlock(name, blockRules, description, parent);
				bbsBlockDao.save(bbsBlock);
				result.put("success", true);
				result.put("msg", "创建成功");
			} catch (Exception e) {
				log.error("创建论坛版块失败", e);
				result.put("success", false);
				result.put("msg", "创建论坛版块失败");
			}
		}else{
			result.put("success", false);
			result.put("msg", "你没有创建权限");
		}
		return result;
	}

	@Override
	public ModelAndView editBbsBlock(HttpServletRequest request,Long id) {
		ModelAndView mv = new ModelAndView();
		User user = (User) request.getSession().getAttribute("currentUser");
		if(UserTypeEnum.ADMIN.getName().equals(user.getUserType())){
			mv.addObject("bbsBlock", bbsBlockDao.getById(id));
			mv.addObject("parentBbs", bbsBlockDao.getTopBbsBlock());
			mv.setViewName("/admin/admin/editBbsBlockById");
		}else{
			mv.setViewName("/templet/hasNoRight");
		}
		return mv;
	}

	@Override
	public Map<String, Object> editBbsBlockAction(HttpServletRequest request) {
		Map<String,Object> result = new HashMap<String,Object>();
		User user = (User) request.getSession().getAttribute("currentUser");
		if(UserTypeEnum.ADMIN.getName().equals(user.getUserType())){
			String name = RequestUtil.getString(request, "name");
			String description = RequestUtil.getString(request, "description");
			String blockRules = RequestUtil.getString(request, "blockRules");
			Long parentId = RequestUtil.getLong(request, "parentId");
			Long id = RequestUtil.getLong(request, "id");
			BbsBlock parent = null;
			Map<String ,Object > errors = new HashMap<>();
			if(StringUtil.isNull(name)){
				errors.put("name", "版块名不能为空");
			}else{
				BbsBlock old= bbsBlockDao.getByNem(name);
			   if(old!=null && old.getId()!=id){
					errors.put("name", "相同的版块已经存在");
				}
			}
			if(!parentId.equals(-1L)){
				parent = bbsBlockDao.getById(parentId);
			}
			BbsBlock old = bbsBlockDao.getById(id);
			if(old==null){
				errors.put("id", "不存在你编辑的论坛版块");
			}
			if(errors.size()>0){
				result.put("success", false);
				StringBuilder msg = new StringBuilder();
				for (Entry<String, Object> key : errors.entrySet()) {
					msg.append(key.getValue()+"<br/>");
				}
				
				result.put("msg", msg.toString());
				return result;
			}
			try {
				old.setName(name);
				old.setBlockRules(blockRules);
				old.setDescription(description);
				old.setName(name);
				old.setParent(parent);
				bbsBlockDao.update(old);
				result.put("success", true);
				result.put("msg", "更新成功");
			} catch (Exception e) {
				log.error("创建论坛版块失败", e);
				result.put("success", false);
				result.put("msg", "更新论坛版块失败");
			}
		}else{
			result.put("success", false);
			result.put("msg", "你没有更新权限");
		}
		return result;
	}

	@Override
	public ModelAndView getMyTieMv(HttpServletRequest request, Pager<BbsContent> pager) {
		ModelAndView mv = new ModelAndView();
		//判断权限
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		if(currentUser==null){
			mv.setViewName("/templet/hasNoRight");
			return mv;
		}
		//封装数据
		pager = bbsContentDao.getTiePagerByUser(pager,currentUser);
		mv.addObject("pager", pager);
		mv.addObject("souresType", "mytie");
		mv.setViewName("/templet/myTie");
		return mv;
	}

	@Override
	public ModelAndView getMyTieCommentsMv(HttpServletRequest request, Pager<BbsContentComments> pager) {
		ModelAndView mv = new ModelAndView();
		//判断权限
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		if(currentUser==null){
			mv.setViewName("/templet/hasNoRight");
			return mv;
		}
		//封装数据
		pager = bbsContentCommentsDao.getUserTieCommentsPagerByUser(pager,currentUser);
		mv.addObject("pager", pager);
		mv.addObject("souresType", "mytieFeedback");
		mv.setViewName("/templet/myTieComments");
		return mv;
	}
	
	
	
	@Override
	public Map<String, Object> getMyTieCommentsJson(HttpServletRequest request, Pager<BbsContentComments> pager) {
		Map<String, Object> result = new HashMap<>();
		//判断权限
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		if(currentUser==null){
			result.put("success", false);
			result.put("msg", "不能查看");
			return result;
		}
		pager = bbsContentCommentsDao.getUserTieCommentsPagerByUser(pager,currentUser);
		result.put("success", true);
		result.put("pager", pager);
		//封装数据
		return result;
	}

	@Override
	public Map<String, Object> getMyTieJson(HttpServletRequest request, Pager<BbsContent> pager) {
		Map<String, Object> result = new HashMap<>();
		//判断权限
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		if(currentUser==null){
			result.put("success", false);
			result.put("msg", "不能查看");
			return result;
		}
		pager = bbsContentDao.getTiePagerByUser(pager,currentUser);
		result.put("success", true);
		result.put("pager", pager);
		//封装数据
		return result;
	}

	@Override
	public List<BbsContent> getTopNewBbs(int size) {
		return bbsContentDao.getTopNewBbs(size);
	}

	@Override
	public List<BbsContent> getTop10Bbs(int size) {
		return bbsContentDao.getTop10Bbs(size);
	}

	@Override
	public ModelAndView getIndex(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/home/bbs");
		//attachs
		List<Attach> attachs = attachService.getNewAttachs(10);
		mv.addObject("attachs", attachs);
		//classDetails
		List<ClassDetail> classDetails = classDetailService.getNewClassDetail(10);
		mv.addObject("classDetails", classDetails);
		
		List<BbsBlock> bbsBlocks = new ArrayList<BbsBlock>(bbsBlockDao.getTopBbsBlock());
		mv.addObject("bbsBlocks", bbsBlocks);
		return mv;
	}

	@Override
	public ModelAndView view(HttpServletRequest request, Long id, String type) {
		ModelAndView mv = new ModelAndView("/templet/404");
		if("block".equals(type)){
			BbsBlock bbsBlock = bbsBlockDao.getById(id);
			if(bbsBlock!=null){
				mv.addObject("bbsBlock", bbsBlock);
				//attachs
				List<Attach> attachs = attachService.getNewAttachs(10);
				mv.addObject("attachs", attachs);
				//classDetails
				List<ClassDetail> classDetails = classDetailService.getNewClassDetail(10);
				mv.addObject("classDetails", classDetails);
				//帖子pager
				Pager<BbsContent> pager = bbsContentDao.getTiePagerByBbsBlock(new Pager<BbsContent>(1L,30), bbsBlock);
				mv.addObject("pager", pager);
				mv.addObject("souresType", "bbsContentPager");
				mv.setViewName("/home/bbsBlock");
			}
		}else if("content".equals(type)){
			BbsContent bcontent = bbsContentDao.getById(id);
			if(bcontent!=null){
				mv.addObject("bcontent", bcontent);
				//更新查看次数
				bcontent.setReadTimes(bcontent.getReadTimes()+1);
				bbsContentDao.update(bcontent);
				//本帖相关的附件
				if(!StringUtil.isNull(bcontent.getAttachIds())){
					String[] attas = bcontent.getAttachIds().split("\\|");
					List<Attach> attachs = new ArrayList<>();
					for (String string : attas) {
						attachs.add(attachDao.getById(Long.valueOf(string)));
					}
					mv.addObject("attachs", attachs);
				}
				//查询评论
				Pager<BbsContentComments> cpager= bbsContentCommentsDao.getTieCommentsPagerByBbsContent(new Pager<BbsContentComments>(1L, 20), bcontent);
				mv.addObject("pager", cpager);
				mv.addObject("souresType", "contentComments");
				
				mv.setViewName("/home/bbsContent");
			}
		}
		
		return mv;
	}

	@Override
	public Map<String, Object> addEvent(HttpServletRequest request, String type) {
		Map<String, Object> result = new HashMap<String ,Object>();
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		if("bbsContent".equals(type)&&currentUser!=null){
			//==================================发帖开始======================================
			Long blockId = RequestUtil.getLong(request, "blockId");
			String title = RequestUtil.getString(request, "title");
			String content = RequestUtil.getString(request, "content");
			String[] attachIds = request.getParameterValues("attachIds");
			BbsBlock bc = bbsBlockDao.getById(blockId);
			//判断
			Map<String, Object> erors = new HashMap<String, Object>();
			if (StringUtil.isNull(title)) {
				erors.put("title", "标题不能为空");
			}
			if (StringUtil.isNull(content)) {
				erors.put("content", "内容不能为空");
			}
			if(bc==null){
				erors.put("bc", "版块不存在");
			}
			//判断是否包含附件
			StringBuilder attachIdsStr = new StringBuilder();
			List<Attach> attachs = new ArrayList<>(0);
			if (attachIds != null && attachIds.length > 0) {
				for (String idstr : attachIds) {
					Attach attach = attachDao.getById(Long.valueOf(idstr));
					if (attach != null) {
						attachIdsStr.append(idstr).append("|");
						attachs.add(attach);
					} else {
						erors.put("courseClass", "上传的附件不存在，请重新上传附件");
					}
				}
			}
			if (erors.size() == 0) {
				//开始发帖
				try{
					BbsContent bcontent = new BbsContent(title, content, new Date(), new Date(), attachIdsStr.toString(), currentUser, bc);
					bbsContentDao.save(bcontent);
					for (Attach attach : attachs) {
						attach.setFileType("bbs");
						attach.setFromName(bcontent.getTitle());
						attach.setFromId(bcontent.getId());
						attach.setFromSubId(bcontent.getId());
						attachDao.update(attach);
					}
					//统计用户的发帖数
					User t_user = userDao.getById(currentUser.getId());
					t_user.setBbsCount(t_user.getBbsCount()+1);
					userDao.update(t_user);
					
					result.put("success", true);
					result.put("id", bcontent.getId());
					result.put("msg", "发帖成功");
				}catch(Exception e){
					e.printStackTrace();
					result.put("success", false);
					result.put("msg", "发帖失败");
				}
			}else{
				StringBuilder errorsString = new StringBuilder();
				for (Map.Entry<String, Object> item : erors.entrySet()) {
					errorsString.append(item.getValue()).append("<br/>");
				}
				result.put("success", false);
				result.put("msg", errorsString.toString());
			}
			//==================================发帖完成======================================
		}else if("bbsContentComments".equals(type)&&currentUser!=null){
			//==================================发评论完成======================================
			Long bcontentId = RequestUtil.getLong(request, "bcontentId");
			String content = RequestUtil.getString(request, "content");
			Map<String, Object> erors = new HashMap<String, Object>();
			BbsContent bsc = bbsContentDao.getById(bcontentId);
			if(StringUtil.isNull(content)){
				erors.put("title", "标题不能为空");
			}
			if(bsc==null){
				erors.put("bsc", "评论的帖子不存在");
			}
			if (erors.size() == 0) {
				//开始发帖
				try{
					BbsContentComments bccs = new BbsContentComments(content, new Date(), new Date(), bsc, currentUser, bsc.getBbsBlock());
					bbsContentCommentsDao.save(bccs);
					result.put("success", true);
					result.put("msg", "评论成功");
				}catch(Exception e){
					e.printStackTrace();
					result.put("success", false);
					result.put("msg", "评论失败");
				}
			}else{
				StringBuilder errorsString = new StringBuilder();
				for (Map.Entry<String, Object> item : erors.entrySet()) {
					errorsString.append(item.getValue()).append("<br/>");
				}
				result.put("success", false);
				result.put("msg", errorsString.toString());
			}
			//==================================发评论结束=====================================
			
		}else{
			result.put("success", false);
			result.put("msg", "你没有权限");
		}
		return result;
	}

	@Override
	public ModelAndView viewPager(HttpServletRequest request, Long currentPage, int pageSize, String type) {
		ModelAndView mv = new ModelAndView("/templet/404");
		if("bbsContentPager".equals(type)){
			Long id = RequestUtil.getLong(request,"blockId");
			BbsBlock bbsBlock = bbsBlockDao.getById(id);
			//帖子pager
			Pager<BbsContent> pager = bbsContentDao.getTiePagerByBbsBlock(new Pager<BbsContent>(currentPage,pageSize), bbsBlock);
			mv.addObject("pager", pager);
			mv.addObject("souresType", "bbsContentPager");
			mv.setViewName("/home/bbsBlockPager");
		}else if("contentComments".equals(type)){
			Long bcontentId = RequestUtil.getLong(request,"bcontentId");
			BbsContent bcontent = bbsContentDao.getById(bcontentId);
			if(bcontent!=null){
				//构建分页对象
				mv.addObject("bcontent", bcontent);
				Pager<BbsContentComments> cpager= bbsContentCommentsDao.getTieCommentsPagerByBbsContent(new Pager<BbsContentComments>(currentPage,pageSize), bcontent);
				mv.addObject("pager", cpager);
				mv.addObject("souresType", "contentComments");
				
				mv.setViewName("/home/bbsContentCommentsPager");
			}
		}
		return mv;
	}

	@Override
	public Map<String, Object> bbsAccess(HttpServletRequest request,Long id,String type,boolean access){
		Map<String, Object> result = new HashMap<>();
		User currentUser = (User)request.getSession().getAttribute("currentUser");
		if(currentUser!=null){
			if("tieComments".equals(type)){
				//隐藏评论
				BbsContentComments bccs = bbsContentCommentsDao.getById(id);
				if(bccs.getCreateUser().equals(currentUser)||UserTypeEnum.ADMIN.getName().equals(currentUser.getUserType())){
					bccs.setAccess(access);
					bbsContentCommentsDao.update(bccs);
					result.put("success", true);
					result.put("msg", "隐藏成功");
				}else{
					result.put("success", false);
					result.put("msg", "你没有操作权限");
				}
				
			}else if("tie".equals(type)){
				//隐藏帖子
				BbsContent bc = bbsContentDao.getById(id);
				if(bc.getCreateUser().equals(currentUser)||UserTypeEnum.ADMIN.getName().equals(currentUser.getUserType())){
					bc.setAccess(access);
					bbsContentDao.update(bc);
					result.put("success", true);
					result.put("msg", "隐藏成功");
				}else{
					result.put("success", false);
					result.put("msg", "你没有操作权限");
				}
			}else{
				result.put("success", false);
				result.put("msg", "未知的操作类型");
			}
		}else{
			result.put("success", false);
			result.put("msg", "你没有操作权限");
		}
		return result;
	}

	@Override
	public ModelAndView editIndex(HttpServletRequest request, Long id, String type) {
		ModelAndView mv = new ModelAndView();
		User currentUser = (User)request.getSession().getAttribute("currentUser");
		if(currentUser!=null){
			if("tieComments".equals(type)){
				//评论
				BbsContentComments bccs = bbsContentCommentsDao.getById(id);
				if(bccs.getCreateUser().equals(currentUser)||UserTypeEnum.ADMIN.getName().equals(currentUser.getUserType())){
					mv.addObject("bbsContentComments", bccs);
					mv.addObject("bbsContent", bccs.getBbsContent());
					mv.setViewName("/admin/bbs/modifyBbsContentComments");
				}else{
					mv.setViewName("/templet/hasNoRight");
				}
				
			}else if("tie".equals(type)){
				//帖子
				BbsContent bc = bbsContentDao.getById(id);
				if(bc.getCreateUser().equals(currentUser)||UserTypeEnum.ADMIN.getName().equals(currentUser.getUserType())){
					//查出全部附件
					List<Attach> attachs = new ArrayList<>(0);
					if (!StringUtil.isNull(bc.getAttachIds())) {
						for (String idstr : bc.getAttachIds().split("\\|")) {
							Attach attach = attachDao.getById(Long.valueOf(idstr));
							if (attach != null) {
								attachs.add(attach);
							}
						}
					}
					mv.addObject("attachs", attachs);
					mv.addObject("bbsContent", bc);
					mv.setViewName("/admin/bbs/modifyBbsContent");
				}else{
					mv.setViewName("/templet/hasNoRight");
				}
			}else{
				mv.setViewName("/templet/404");
			}
		}else{
			mv.setViewName("/templet/hasNoRight");
		}
		return mv;
	}

	@Override
	public Map<String, Object> editAction(HttpServletRequest request, Long id, String type) {
		Map<String, Object> result = new HashMap<>();
		User currentUser = (User)request.getSession().getAttribute("currentUser");
		if(currentUser!=null){
			if("tieComments".equals(type)){
				//评论
				BbsContentComments bccs = bbsContentCommentsDao.getById(id);
				String content = RequestUtil.getString(request, "content");
				if(!StringUtil.isNull(content)&&bccs.getCreateUser().equals(currentUser)||UserTypeEnum.ADMIN.getName().equals(currentUser.getUserType())){
					bccs.setContent(content);
					bbsContentCommentsDao.update(bccs);
					result.put("success", true);
					result.put("msg", "操作成功");
				}else{
					result.put("success", false);
					result.put("msg", "你没有操作权限");
				}
				
			}else if("tie".equals(type)){
				//帖子
				String content = RequestUtil.getString(request, "content");
				String title = RequestUtil.getString(request, "title");
				String[] attachIds = request.getParameterValues("attachIds");
				if(StringUtil.isNull(content)||StringUtil.isNull(title)){
					result.put("success", false);
					result.put("msg", "标题与内容不能为空");
					return result;
				}
				
				//判断是否包含附件
				StringBuilder attachIdsStr = new StringBuilder();
				List<Attach> attachs = new ArrayList<>(0);
				if (attachIds != null && attachIds.length > 0) {
					for (String idstr : attachIds) {
						Attach attach = attachDao.getById(Long.valueOf(idstr));
						if (attach != null) {
							attachIdsStr.append(idstr).append("|");
							attachs.add(attach);
						} else {
							result.put("courseClass", "上传的附件不存在，请重新上传附件");
							return result;
						}
					}
				}
				
				BbsContent bc = bbsContentDao.getById(id);
				if(bc != null && bc.getCreateUser().equals(currentUser)||UserTypeEnum.ADMIN.getName().equals(currentUser.getUserType())){
					bc.setContent(content);
					bc.setTitle(title);
					bc.setModifyTime(new Date());
					bc.setAttachIds(attachIdsStr.toString());
					bbsContentDao.update(bc);
					//将原有的附件清除掉
					attachDao.updateToUnknowById(bc.getId(), bc.getId(), "bbs");
					//在更新的附件
					for (Attach attach : attachs) {
						attach.setFileType("bbs");
						attach.setFromName(bc.getTitle());
						attach.setFromId(bc.getId());
						attach.setFromSubId(bc.getId());
						attachDao.update(attach);
					}
					result.put("success", true);
					result.put("msg", "操作成功");
				}else{
					result.put("success", false);
					result.put("msg", "你没有操作权限");
				}
			}else{
				result.put("success", false);
				result.put("msg", "未知操作");
			}
		}else{
			result.put("success", false);
			result.put("msg", "你没有操作权限");
		}
		return result;
	}
	
	
	
	
}
