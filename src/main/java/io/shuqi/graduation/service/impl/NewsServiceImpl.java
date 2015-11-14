package io.shuqi.graduation.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import io.shuqi.graduation.dao.NewsDao;
import io.shuqi.graduation.domain.News;
import io.shuqi.graduation.domain.User;
import io.shuqi.graduation.domain.bo.Pager;
import io.shuqi.graduation.enumtype.UserTypeEnum;
import io.shuqi.graduation.exception.DaoException;
import io.shuqi.graduation.service.NewsService;
import io.shuqi.graduation.utils.DateUtil;
import io.shuqi.graduation.utils.RequestUtil;
import io.shuqi.graduation.utils.StringUtil;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

@Service(value="newsService") 
@Transactional
public class NewsServiceImpl extends NewsService{
	@Autowired
	private NewsDao newsDao;
	
	private Logger log = Logger.getLogger(getClass());
	
	@Override
	public ModelAndView newsManager(HttpServletRequest request,Long crrentPage, int pageSize) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/templet/InfoManager");
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		Pager<News> pager = new Pager<>(crrentPage, pageSize);
		if(currentUser!=null&&UserTypeEnum.ADMIN.getName().equals(currentUser.getUserType())){
			pager = newsDao.getNewsPager(pager);
		}else{
			//不知道的用户则不让看
			mv.setViewName("/templet/personalInfo");
			return mv;
		}
		mv.addObject("pager", pager);
		mv.addObject("souresType","news");
		return mv;
	}


	@Override
	public Map<String, Object> publishNews(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		// 0、验证用户的权限(只有管理员发布新闻)
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		if(!(currentUser!=null&&UserTypeEnum.ADMIN.getName().equals(currentUser.getUserType()))){
			result.put("success", false);
			result.put("msg", "你没有操作权限");
			return result;
		}
		
		boolean success = true;
		StringBuilder msg = new StringBuilder();
		//1、得到前台传递的参数
		String title = RequestUtil.getString(request, "title");
		String source = RequestUtil.getString(request, "source");
		String createTime = RequestUtil.getString(request, "createTime");
		String content = RequestUtil.getString(request, "content");
		String key = RequestUtil.getString(request, "key");
		String images = RequestUtil.getString(request, "images");
		
		// 2、存在一个漫长的验证过程
		if(StringUtil.isNull(title)){
			success = false;
			msg.append("新闻标题不能为空<br/>");
		}
		if(StringUtil.isNull(source)){
			success = false;
			msg.append("新闻来源不能为空<br/>");
		}
		if(!StringUtil.isNull(createTime)&&!StringUtil.isDateString(createTime)){
			success = false;
			msg.append("发布时间格式错误<br/>");
		}
		if(StringUtil.isNull(content)){
			success = false;
			msg.append("发布内容不能为空<br/>");
		}
		// 3、如果验证通过就可以进行下一步存储了
		if(success){
			News news = new News();
			news.setTitle(title);
			news.setCreateUser(currentUser);
			news.setImages(images);
			//日期不重要，转换不成功，就当前时间
			try {
				news.setCreateTime(DateUtil.parseStringToDate(createTime, "yyyy-MM-dd"));
			} catch (ParseException e) {
				news.setCreateTime(new Timestamp(System.currentTimeMillis()));
			}
			news.setModifyTime(new Timestamp(System.currentTimeMillis()));
			news.setNewsKey(key);
			news.setContent(content);
			news.setSource(source);
			//保存成功  ok
			try{
				newsDao.save(news);
				msg.append("发布成功<br/>");
			}catch(Exception e){
				success = false;
				msg.append("发布成功<br/>");
				log.error(e.toString());
				e.printStackTrace();
			}
			
		}
		
		// 4、封装结果返回
		result.put("success", success);
		result.put("msg", msg);
		return result;
	}


	@Override
	public Map<String, Object> deleteNewsByID(Long id, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		// 0、验证用户的权限(只有管理员发布新闻)
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		if(!(currentUser!=null&&UserTypeEnum.ADMIN.getName().equals(currentUser.getUserType()))){
			result.put("success", false);
			result.put("msg", "你没有操作权限");
			return result;
		}
		try {
			newsDao.deleteById(id);
			result.put("success", true);
			result.put("msg", "删除成功");
		} catch (DaoException e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", "删除失败");
		}
		return result;
	}


	@Override
	public ModelAndView editIndex(Long id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		if(currentUser!=null&&UserTypeEnum.ADMIN.getName().equals(currentUser.getUserType())){
			mv.addObject("news",newsDao.getById(id));
			mv.setViewName("/news/editNews");
		}else{
			mv.setViewName("/templet/hasNoRight");
		}
		return mv;
	}


	@Override
	public Map<String, Object> editAction(Long id, HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		// 0、验证用户的权限(只有管理员发布新闻)
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		if(!(currentUser!=null&&UserTypeEnum.ADMIN.getName().equals(currentUser.getUserType()))){
			result.put("success", false);
			result.put("msg", "你没有操作权限");
			return result;
		}
		News news = newsDao.getById(id);
		if(news==null){
			result.put("success", false);
			result.put("msg", "没有你编辑的新闻");
			return result;
		}
		
		boolean success = true;
		StringBuilder msg = new StringBuilder();
		//1、得到前台传递的参数
		String title = RequestUtil.getString(request, "title");
		String source = RequestUtil.getString(request, "source");
		String content = RequestUtil.getString(request, "content");
		String key = RequestUtil.getString(request, "key");
		String images = RequestUtil.getString(request, "images");
		
		// 2、存在一个漫长的验证过程
		if(StringUtil.isNull(title)){
			success = false;
			msg.append("新闻标题不能为空<br/>");
		}
		if(StringUtil.isNull(source)){
			success = false;
			msg.append("新闻来源不能为空<br/>");
		}
		if(StringUtil.isNull(content)){
			success = false;
			msg.append("发布内容不能为空<br/>");
		}
		// 3、如果验证通过就可以进行下一步存储了
		if(success){
			news.setTitle(title);
			news.setCreateUser(currentUser);
			news.setImages(images);
			//日期不重要，转换不成功，就当前时间
			news.setModifyTime(new Timestamp(System.currentTimeMillis()));
			news.setNewsKey(key);
			news.setContent(content);
			news.setSource(source);
			//保存成功  ok
			try{
				newsDao.update(news);
				msg.append("编程成功<br/>");
			}catch(Exception e){
				success = false;
				msg.append("编辑不成功<br/>");
				log.error(e.toString());
				e.printStackTrace();
			}
			
		}
		
		// 4、封装结果返回
		result.put("success", success);
		result.put("msg", msg);
		return result;
	}


	@Override
	public List<News> getNewNews(int size) {
		return newsDao.getNewNews(size);
	}


	@Override
	public List<News> getHotNews(int size) {
		return newsDao.gethotNews(size);
	}


	@Override
	public Pager<News> getPagerByPager(Pager<News> pager) {
		return newsDao.getNewsPager(pager);
	}

}
