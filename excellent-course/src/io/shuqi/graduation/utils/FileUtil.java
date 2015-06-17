package io.shuqi.graduation.utils;

import io.shuqi.graduation.enumtype.EnumMimeType;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件的相关操作工具类
 * @author shuqi
 * @date   2015年5月1日
 * @version since 1.0
 */
public class FileUtil {
	
	//被支持的文件格式
	private static final HashMap<String, String> extMap = new HashMap<String, String>();
	private static final Long MAX_MEDIA_SIZE = 1024*1024*500L;//500M
	private static final Long MAX_FILE_SIZE = 1024*1024*50L;//50M
	private static final Long MAX_IMAGE_SIZE = 1024*1024*3L;//3M
	static{
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("media", "flv,mp4,m3u8,f4v");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,txt,zip,rar,gz,bz2,pdf");
		extMap.put("attach","doc,docx,xls,xlsx,ppt,txt,zip,rar,gz,bz2,pdf");
		extMap.put("bbs","doc,docx,xls,xlsx,ppt,txt,zip,rar,gz,bz2,pdf");
	}
	/**
	 * 判断文件的是否允许上传，并返回
	 * 			map[success:boolean,msg:String]
	 * @author shuqi
	 * @date   2015年5月1日
	 * @version since 1.0
	 * @param dir	上传文件的路径，即文件的类型 {image|media|file}
	 * @param FileName	上传文件的名称
	 * @param FileSize	上传文件的大小
	 * @return	map[success:boolean,msg:String]
	 */
	public static Map<String,Object> isAllowToUpload(String dir,String fileName,Long fileSize){
		Map<String,Object>  result = new HashMap<String, Object>();
		
		if(dir!=null){
			//1、判断文件格式是否被支持
			String fileExt = getFileExt(fileName);
			//后缀名不对,即格式不对
			if(!Arrays.asList(extMap.get(dir).split(",")).contains(fileExt)){
				result.put("success", false);
				result.put("message", "上传文件格式不支持！");
				return result;
			}
			//判断文件的大小是否被支持
			if("image".equalsIgnoreCase(dir)){
				if(fileSize>MAX_IMAGE_SIZE){
					result.put("success", false);
					result.put("message", "上传图片的大小过大！最大支持"+MAX_IMAGE_SIZE/1024/1024+"M");
					return result;
				}
			}else if("media".equalsIgnoreCase(dir)){
				if(fileSize>MAX_MEDIA_SIZE){
					result.put("success", false);
					result.put("message", "上传音频/视频的大小过大！最大支持"+MAX_MEDIA_SIZE/1024/1024+"M");
					return result;
				}
			}else if("attach".equalsIgnoreCase(dir)||"bbs".equalsIgnoreCase(dir)){
				if(fileSize>MAX_FILE_SIZE){
					result.put("success", false);
					result.put("message", "上传附件的大小过大！最大支持"+MAX_FILE_SIZE/1024/1024+"M");
					return result;
				}
			}else{
				result.put("success", false);
				result.put("message", "不被支持的文件");
				return result;
			}
			result.put("success", true);
			return result;
			
		}else{
			result.put("success",false);
			result.put("message", "上传文件的目录不能为空！");
		}
		return result;
	}
	/**
	 *判断文件的是否允许上传，并返回
	 * 			map[success:boolean,msg:String] 
	 * @author shuqi
	 * @date   2015年5月1日
	 * @version since 1.0
	 * @param dir
	 * @param file
	 * @return
	 */
	public static Map<String,Object> isAllowToUpload(String dir,File file){
		return isAllowToUpload(dir,file.getName(),file.length());
	}
	/**
	 * 判断文件的是否允许上传，并返回
	 * 			map[success:boolean,msg:String]
	 * @author shuqi
	 * @date   2015年5月1日
	 * @version since 1.0
	 * @param dir
	 * @param file
	 * @return
	 */
	public static Map<String,Object> isAllowToUpload(String dir,MultipartFile file){
		return isAllowToUpload(dir,file.getOriginalFilename(),file.getSize());
	}
	/**
	 * 图片文件上传
	 * @author shuqi
	 * @date   2015年5月1日
	 * @version since 1.0
	 * @param file
	 * @param basepath
	 * @param baseUrl
	 * @return
	 */
	public static Map<String, Object> saveImageToDisk(MultipartFile file, String basePath, String baseURI) {
		Map<String, Object> result = new HashMap<>();
		
		//创建根   基本路径/年/月/日
		String dateYearPath = DateUtil.formatDate(new Date(), "yyyy");
		String dateMonthPath = DateUtil.formatDate(new Date(), "MM");
		String dateDayPath = DateUtil.formatDate(new Date(), "dd");
		File parentPath = new File(basePath+"/"+dateYearPath+"/"+dateMonthPath+"/"+dateDayPath+"/");
		if(!parentPath.exists())
				parentPath.mkdirs();
		
		//创建目标文件
		String originalFilename = file.getOriginalFilename();
		String ext =  originalFilename.substring(originalFilename.lastIndexOf("."));
		File destFile = new File(parentPath, String.valueOf(System.currentTimeMillis())+ext);
		
		try {
			file.transferTo(destFile);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("error", 1);
			result.put("message", "保存文件失败");
			return result;
		} 
		//返回url
		String destURL = baseURI+dateYearPath+"/"+dateMonthPath+"/"+dateDayPath+"/"+destFile.getName();
		
		result.put("error", 0);
		result.put("url", destURL);
		return result;
	}
	
	
	public static Map<String, Object> fileManager(String path,String dir,String basePath,String baseURI){
		Map<String, Object> result = new HashMap<>();
		int error = 0;
		boolean success = true;
		String message = "";
		
		if(!"image".equals(dir)){
			success = false;
			error = 1;
			message = "访问路径为空";
		}else{
			if(path!=null && path.indexOf("..")==-1 && (path.endsWith("/")||path.equals(""))){
				File currentPath = new File(basePath, path);
				if(!currentPath.isDirectory()){
					success = false;
					error = 1;
					message = "访问的不是一个目录";
				}else{
					try{
						//存放目录的List
						List<Hashtable<String,Object>> fileList = new ArrayList<Hashtable<String,Object>>();
						if(currentPath.listFiles() != null) {
							for (File file : currentPath.listFiles()) {
								//一个文件项 使用一个Map表示
								Hashtable<String, Object> hash = new Hashtable<String, Object>();
								String fileName = file.getName();
								if(file.isDirectory()) {
									hash.put("is_dir", true);
									hash.put("has_file", (file.listFiles() != null));
									hash.put("filesize", 0L);
									hash.put("is_photo", false);
									hash.put("filetype", "");
								} else if(file.isFile()){
									String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
									hash.put("is_dir", false);
									hash.put("has_file", false);
									hash.put("filesize", file.length());
									//判断是否为图片
									hash.put("is_photo", Arrays.<String>asList(extMap.get("image").split(",")).contains(fileExt));
									hash.put("filetype", fileExt);
								}
								hash.put("filename", fileName);
								hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
								fileList.add(hash);
							}
						}
						//返回Json数据对象，用于js使用
						Map<String, Object> fileListInfo = new HashMap<String, Object>();
						String currentDirPath = path;
						String moveupDirPath = "";
						if (!"".equals(path)) {
							String str = currentDirPath.substring(0, currentDirPath.length() - 1);
							moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
						}
						//上级目录
						fileListInfo.put("moveup_dir_path", moveupDirPath);
						//当前路径
						fileListInfo.put("current_dir_path", path);
						//当前的根url
						fileListInfo.put("current_url", baseURI+path);
						//list集合的大小
						fileListInfo.put("total_count", fileList.size());
						//list集合 
						fileListInfo.put("file_list", fileList);
						
						result.put("result",fileListInfo);
					}catch(Exception e){
						success = false;
						error = 1;
						message = "访问路径非法";
					}
				}
			}else{
				success = false;
				error = 1;
				message = "访问路径非法";
			}
		}
		
		//返回结果集
		result.put("error", error);
		result.put("success", success);
		result.put("message", message);
		return result;
	}
	public static Map<String, Object> saveAttachToDisk(MultipartFile file, String basePath) {
		Map<String, Object> result = new HashMap<>();
		
		//创建根   基本路径/年/月/日
		String dateYearPath = DateUtil.formatDate(new Date(), "yyyy");
		String dateMonthPath = DateUtil.formatDate(new Date(), "MM");
		String dateDayPath = DateUtil.formatDate(new Date(), "dd");
		File parentPath = new File(basePath+"/"+dateYearPath+"/"+dateMonthPath+"/"+dateDayPath+"/");
		if(!parentPath.exists())
				parentPath.mkdirs();
		
		//创建目标文件
		String originalFilename = file.getOriginalFilename();
		String ext =  originalFilename.substring(originalFilename.lastIndexOf("."));
		File destFile = new File(parentPath, String.valueOf(System.currentTimeMillis())+ext);
		
		try {
			file.transferTo(destFile);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("error", 1);
			result.put("message", "保存文件失败");
			return result;
		} 
		//返回文件的保存路径
		String destPath = dateYearPath+"/"+dateMonthPath+"/"+dateDayPath+"/"+destFile.getName();
		
		result.put("error", 0);
		result.put("path", destPath);
		return result;
	}
	
	public static String getFileExt(String fileName){
		return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
	}
	
	public static String getFileMIMEImage(String fileName){
		String ext =  fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		return EnumMimeType.getFileMIMEImagePath(ext);
	}
}
