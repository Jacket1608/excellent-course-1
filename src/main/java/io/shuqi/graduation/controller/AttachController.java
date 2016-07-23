package io.shuqi.graduation.controller;

import java.io.IOException;
import java.util.Map;

import io.shuqi.graduation.exception.NoResourceException;
import io.shuqi.graduation.exception.NoRightException;
import io.shuqi.graduation.service.AttachService;





import io.shuqi.graduation.service.FileService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.json.JSONObject;
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
public class AttachController {
	
	@Autowired
	private AttachService attachService;
	@Autowired
	private FileService fileService;
	
	/**
	 * 附件下载，全局唯一的接口
	 * @author shuqi
	 * @date   2015年5月15日
	 * @version since 1.0
	 * @param attachId
	 * @param request
	 * @param response
	 * @throws NoRightException
	 * @throws NoResourceException
	 */
	@RequestMapping(value="/attach/download/{attachId}")
	public void attachDownload(@PathVariable Long attachId,HttpServletRequest request,HttpServletResponse response) throws NoRightException, NoResourceException{
		attachService.attachDownload(attachId, request, response);
	}
	
	/**
	 * 附件上传
	 * @author shuqi
	 * @date   2015年5月15日
	 * @version since 1.0
	 * @param file
	 * @param dir
	 * @param request
	 * @param response
	 * @throws NoRightException
	 * @throws IOException
	 */
	@RequestMapping(value="/attach/upload/attach",method=RequestMethod.POST)
	public void attachUpload(@RequestParam(value="attach",required=true)MultipartFile file,@RequestParam(value="dir",required=true)String dir,HttpServletRequest request,HttpServletResponse response) throws NoRightException, IOException{
		response.setContentType("text/html;charset=UTF-8");
		JSONObject jsonObject = new JSONObject(fileService.fileUpload(file,dir,request));
		response.getWriter().print(jsonObject.toString());
		response.flushBuffer();
	}
	/**
	 * 媒体文件上传
	 * @author shuqi
	 * @date   2015年5月15日
	 * @version since 1.0
	 * @param file
	 * @param dir
	 * @param request
	 * @return
	 * @throws NoRightException
	 */
	@RequestMapping(value="/attach/upload/media",method=RequestMethod.POST)
	@ResponseBody public Map<String,Object> mediaUpload(@RequestParam(value="media",required=true)MultipartFile file,@RequestParam(value="dir",required=true)String dir,HttpServletRequest request) throws NoRightException{
		return fileService.fileUpload(file,dir,request);
	}
	
	/**
	 * 资源中心
	 * @author shuqi
	 * @date   2015年5月15日
	 * @version since 1.0
	 * @param request
	 * @return
	 * @throws NoRightException
	 * @throws IOException
	 */
	@RequestMapping(value="/attach/index",method=RequestMethod.GET)
	public ModelAndView attachIndex(HttpServletRequest request) throws NoRightException, IOException{
		return attachService.attachIndex(request);
	}
	
	/**
	 * 附件分页
	 * @author shuqi
	 * @date   2015年5月15日
	 * @version since 1.0
	 * @param request
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/attach/page/{currentPage}/{pageSize}",method=RequestMethod.GET)
	public ModelAndView attachPager(HttpServletRequest request,@PathVariable(value="currentPage") Long currentPage,@PathVariable(value="pageSize") int pageSize) {
		return attachService.attachPager(request,currentPage,pageSize);
	}
}
