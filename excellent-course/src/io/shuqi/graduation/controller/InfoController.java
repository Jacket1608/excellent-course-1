package io.shuqi.graduation.controller;

import io.shuqi.graduation.service.FileService;
import io.shuqi.graduation.service.InfoService;
import io.shuqi.graduation.service.NewsService;
import io.shuqi.graduation.service.NotifyService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class InfoController {
	
	@Autowired
	private FileService fileService;
	@Autowired
	private NewsService newsService;
	@Autowired
	private NotifyService notifyService;
	@Autowired
	private InfoService infoService;
	/**
	 * 发布新闻
	 * @author shuqi
	 * @date   2015年5月1日
	 * @version since 1.0
	 * @return
	 */
	@RequestMapping(value="/info/publishNews",method = RequestMethod.GET)
	public ModelAndView publishNews(){
		return new ModelAndView("/news/publishNews");
	}
	/**
	 * 发布新闻
	 * @author shuqi
	 * @date   2015年5月1日
	 * @version since 1.0
	 * @return
	 */
	@RequestMapping(value="/info/publishNews",method = RequestMethod.POST)
	@ResponseBody public Map<String,Object> publishNewsAction(HttpServletRequest request){
		return newsService.publishNews(request);
	}
	
	/**
	 * 发布公告
	 * @author shuqi
	 * @date   2015年5月1日
	 * @version since 1.0
	 * @return
	 */
	@RequestMapping(value="/info/publishNotification",method = RequestMethod.GET)
	public ModelAndView publishNotification(){
		return new ModelAndView("/notification/publishNotification");
	}
	/**
	 * 发布公告
	 * @author shuqi
	 * @date   2015年5月1日
	 * @version since 1.0
	 * @return
	 */
	@RequestMapping(value="/info/publishNotification",method = RequestMethod.POST)
	@ResponseBody public Map<String,Object> publishNotificationAction(HttpServletRequest request){
		return notifyService.publishNotification(request);
	}
	
	/**
	 * 图片文件上传action
	 * @author shuqi
	 * @date   2015年5月1日
	 * @version since 1.0
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/info/imageFileUpload",method=RequestMethod.POST)
	public void imageFileUpload(@RequestParam(value="imgFile",required=true)MultipartFile file,@RequestParam(value="dir",required=true)String dir, HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setContentType("text/html;charset=UTF-8");
		JSONObject jsonObject = JSONObject.fromObject(fileService.fileUpload(file,dir,request));
		response.getWriter().print(jsonObject.toString());
		response.flushBuffer();
	}
	/**
	 * 图片文件管理action
	 * @author shuqi
	 * @date   2015年5月1日
	 * @version since 1.0
	 * @return
	 */
	@RequestMapping(value= "/info/imageFileManager",method=RequestMethod.GET)
	@ResponseBody public Map<String,Object> imageFileManager(@RequestParam(value="dir",required=true)String dir,@RequestParam(value="path",required=true)String path,HttpServletRequest request){
		return fileService.fileManager(request,path,dir);
	}
	
	/**
	 * 删除信息
	 * @author shuqi
	 * @date   2015年5月13日
	 * @version since 1.0
	 * @param id
	 * @param souresType
	 * @param request
	 * @return
	 */
	@RequestMapping(value= "/info/delete/{id}/{souresType}",method=RequestMethod.GET)
	@ResponseBody public Map<String,Object> infoDelete(@PathVariable Long id,@PathVariable String souresType,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		if("notification".equals(souresType)){
			result = notifyService.deleteNotifyByID(id,request);
		}else if("news".equals(souresType)){
			result = newsService.deleteNewsByID(id,request);
		}else{
			result.put("success", false);
			result.put("msg", "无法删除");
		}
		return result;
	}
	
	/**
	 * 信息编辑
	 * @author shuqi
	 * @date   2015年5月13日
	 * @version since 1.0
	 * @param id
	 * @param souresType
	 * @param request
	 * @return
	 */
	@RequestMapping(value= "/info/edit/{id}/{souresType}",method=RequestMethod.GET)
	public ModelAndView infoEdit(@PathVariable Long id,@PathVariable String souresType,HttpServletRequest request){
		if("notification".equals(souresType)){
			return notifyService.editIndex(id,request);
		}else if("news".equals(souresType)){
			return newsService.editIndex(id,request);
		}else{
			return new ModelAndView("/templet/hasNoRight");
		}
	}
	
	/**
	 * 信息编辑 --action
	 * @author shuqi
	 * @date   2015年5月13日
	 * @version since 1.0
	 * @param id
	 * @param souresType
	 * @param request
	 * @return
	 */
	@RequestMapping(value= "/info/edit/{id}/{souresType}",method=RequestMethod.POST)
	@ResponseBody public Map<String,Object> infoEditAction(@PathVariable Long id,@PathVariable String souresType,HttpServletRequest request){
		Map<String, Object> result = new HashMap<String, Object>();
		if("notification".equals(souresType)){
			result = notifyService.editAction(id,request);
		}else if("news".equals(souresType)){
			result = newsService.editAction(id,request);
		}else{
			result.put("success", false);
			result.put("msg", "无法保存");
		}
		return result;
	}
	
	@RequestMapping(value= "/info/view/{id}/{sourseType}",method=RequestMethod.GET)
	public ModelAndView infoViewJsp(@PathVariable Long id,@PathVariable String sourseType,HttpServletRequest request){
		return infoService.infoViewJsp(id,sourseType,request);
	}
	
	@RequestMapping(value= "/info/index",method=RequestMethod.GET)
	public ModelAndView infoIndex(HttpServletRequest request){
		return infoService.getIndex(request);
	}
	/**
	 * 信息的ajax
	 * @author shuqi
	 * @date   2015年5月13日
	 * @version since 1.0
	 * @param request
	 * @return
	 */
	@RequestMapping(value= "/info/ajax",method=RequestMethod.GET)
	public ModelAndView infoAjax(HttpServletRequest request,HttpServletResponse response){
		return infoService.infoAjax(request,response);
	}
}
