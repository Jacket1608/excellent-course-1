package io.shuqi.graduation.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import io.shuqi.graduation.dao.AttachDao;
import io.shuqi.graduation.domain.Attach;
import io.shuqi.graduation.domain.User;
import io.shuqi.graduation.service.FileService;
import io.shuqi.graduation.utils.FileUtil;

@Service("fileService")
@Transactional
public class FileServiceImpl extends FileService {

	@Autowired
	private AttachDao attachDao;
	
	@Override
	public Map<String, Object> fileUpload(MultipartFile file,String dir, HttpServletRequest request) {
		Map<String,Object> result = new HashMap<String, Object>();
		User currentUser = (User) request.getSession().getAttribute("currentUser");
		if(currentUser==null){
			result.put("error", 1);
			result.put("message", "请登录");
			return result;
		}
		//1、判断文件是否允许上传
		result = FileUtil.isAllowToUpload(dir,file);
		if(!(boolean) result.get("success")){
			result.put("error", 1);
			return result;
		}
		//2、根据不同情况上传文件
		if (dir.equals("image")) {
			//图片文件的物理位置
			String basepath = request.getServletContext().getRealPath("/static/upload/");
			//图片文件保存目录的URL
			String baseUrl = request.getContextPath() + "/static/upload/";
			result = FileUtil.saveImageToDisk(file,basepath,baseUrl);
		} else if (dir.equals("attach")) {
			//附件文件的保存路径
			String basepath = request.getServletContext().getRealPath("/WEB-INF/attach/");
			//附件文件的保存路径
			result = FileUtil.saveAttachToDisk(file,basepath);
			if((int)result.get("error")==0){
				String filePath  = (String)result.get("path");
				String fileName  = file.getOriginalFilename();
				String fileType = FileUtil.getFileExt(fileName);
				String fileImages = FileUtil.getFileMIMEImage(fileName);
				Attach attach = new Attach(fileType, new Date(), filePath, fileName, fileImages, "unknow", -1L, -1L, currentUser);
				attachDao.save(attach);
				result.put("fileImages", request.getContextPath()+"/"+fileImages);
				result.put("fileName", fileName);
				result.put("attachId", attach.getId());
				result.put("url", "/attach/download/"+attach.getId());
				result.put("error", 0);
			}
		} else if (dir.equals("bbs")) {
			//附件文件的保存路径
			String basepath = request.getServletContext().getRealPath("/WEB-INF/bbs/");
			//附件文件的保存路径
			result = FileUtil.saveAttachToDisk(file,basepath);
			if((int)result.get("error")==0){
				String filePath  = (String)result.get("path");
				String fileName  = file.getOriginalFilename();
				String fileType = FileUtil.getFileExt(fileName);
				String fileImages = FileUtil.getFileMIMEImage(fileName);
				Attach attach = new Attach(fileType, new Date(), filePath, fileName, fileImages, "unknow", -1L, -1L, currentUser);
				attachDao.save(attach);
				result.put("fileImages", request.getContextPath()+"/"+fileImages);
				result.put("fileName", fileName);
				result.put("attachId", attach.getId());
				result.put("url", "/attach/download/"+attach.getId());
				result.put("error", 0);
			}
		} else if (dir.equals("media")) {
			result.put("error", 1);
			result.put("message", "上传视频文件的还未开通！");
		} else {
			result.put("error", 1);
			result.put("message", "上传文件的目录不被支持！");
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> fileManager(HttpServletRequest request, String path, String dir) {
		//返回的数据包含两个：error:number,(url|message)
		//文件物理位置
		String basepath = request.getServletContext().getRealPath("/static/upload/");
		//文件保存目录URL
		String baseUrl = request.getContextPath() + "/static/upload/";
		Map<String, Object> infomap = FileUtil.fileManager(path, dir, basepath, baseUrl);
		//如果不成功返回
		if (!(boolean) infomap.get("success")) {
			return infomap;
		}
		//返回结果集
		return (Map<String, Object>) infomap.get("result");
	}

}
