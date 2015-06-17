package io.shuqi.graduation.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public abstract class FileService {
	/**
	 * 文件管理-文件上传
	 * @author shuqi
	 * @date   2015年5月1日
	 * @version since 1.0
	 * @param file	文件
	 * @param dir	上传类型，分别为image、flash、media、file
	 * @param request	request请求
	 * @return
	 */
	public abstract Map<String, Object> fileUpload(MultipartFile file,String dir, HttpServletRequest request);
	/**
	 * 文件管理-图片文件管理
	 * @author shuqi
	 * @date   2015年5月1日
	 * @version since 1.0
	 * @param request
	 * @param path
	 * @param dir
	 * @return
	 */
	public abstract Map<String, Object> fileManager(HttpServletRequest request, String path, String dir);

}
