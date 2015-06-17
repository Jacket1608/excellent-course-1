package io.shuqi.graduation.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import io.shuqi.graduation.dao.AttachDao;
import io.shuqi.graduation.domain.Attach;
import io.shuqi.graduation.domain.BbsContent;
import io.shuqi.graduation.domain.ClassDetail;
import io.shuqi.graduation.domain.News;
import io.shuqi.graduation.domain.User;
import io.shuqi.graduation.domain.bo.Pager;
import io.shuqi.graduation.exception.NoResourceException;
import io.shuqi.graduation.exception.NoRightException;
import io.shuqi.graduation.service.AttachService;
import io.shuqi.graduation.service.BbsService;
import io.shuqi.graduation.service.ClassDetailService;
import io.shuqi.graduation.service.NewsService;

@Service
@Transactional
public class AttachServiceImpl extends AttachService {

	@Autowired
	private AttachDao attachDao;
	@Autowired
	private NewsService newsService;
	@Autowired
	private ClassDetailService classDetailService;
	@Autowired
	private BbsService bbsService;

	private Logger logger = Logger.getLogger(getClass());

	@Override
	public void attachDownload(Long attachId, HttpServletRequest request, HttpServletResponse response) throws NoRightException, NoResourceException {
		OutputStream out = null;
		InputStream in = null;
		try {
			User currentUser = (User) request.getSession().getAttribute("currentUser");
			//如果当前用户为空则抛出没有权限异常
			if (currentUser == null) {
				throw new NoRightException("你没有下载该资料的权限");
			}
			Attach attach = attachDao.getById(attachId);
			String basepath = "";
			if(attach!=null&&attach.getFileType().equals("classDetail")){
				basepath = request.getServletContext().getRealPath("/WEB-INF/attach/");
			}else{
				basepath = request.getServletContext().getRealPath("/WEB-INF/bbs/");
			}
			File destFile = new File(basepath, attach.getFilePath());
			if(attach!=null && destFile.exists() && destFile.isFile()){
				response.setHeader("", "");
				//设置文件名，attachment和filename之间是分号，注意！
				response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(attach.getFileName(), "UTF-8"));  
				//设置大小
				response.addHeader("Content-Length", destFile.length()+"");
				//准备I/O流
				in = new BufferedInputStream(new FileInputStream(destFile));
				out = response.getOutputStream();
				
				//循环发送
				byte[] bytes = new byte[1024];
				int length = 0;
				while((length=in.read(bytes, 0, bytes.length))>0){
					out.write(bytes, 0, length);
				}
				//更新下载次数
				attach.setDownloadCount(attach.getDownloadCount()+1);
				attachDao.update(attach);
				
				logger.info("登录名：" + currentUser.getLoginName() + ",用户ID为：" + currentUser.getId() + ",下载资料Id：" + attachId);
			}else{
				throw new NoResourceException("你下载该资料不存在");
			}

		} catch (IOException e) {
			e.printStackTrace();
			logger.error("文件下载失败！" + e.toString());
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public List<Attach> getNewAttachs(int size) {
		return attachDao.getNewAttachs(size);
	}

	@Override
	public ModelAndView attachIndex(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("/home/attach");
		//最新新闻：
		List<News> hotNewses = newsService.getHotNews(5);
		mv.addObject("hotNewses", hotNewses);
		//最新课程
		List<ClassDetail> classDetails = classDetailService.getNewClassDetail(10);
		mv.addObject("classDetails", classDetails);
		//最新讨论
		List<BbsContent> bbsContents = bbsService.getTopNewBbs(10);
		mv.addObject("bbsContents", bbsContents);

		Pager<Attach> pager = new Pager<Attach>(1L, 30);
		mv.addObject("pager", attachDao.getPagerByPager(pager));
		mv.addObject("souresType", "attach");

		return mv;
	}

	@Override
	public ModelAndView attachPager(HttpServletRequest request, Long currentPage, int pageSize) {
		ModelAndView mv = new ModelAndView("/home/attachPager");
		
		Pager<Attach> pager = new Pager<Attach>(currentPage, pageSize);
		mv.addObject("pager", attachDao.getPagerByPager(pager));
		mv.addObject("souresType", "attach");

		return mv;
	}

	
}
