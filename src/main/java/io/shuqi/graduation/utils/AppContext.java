package io.shuqi.graduation.utils;

public class AppContext {
	//附件的上传的基位置
	private static String BasePath = "d:/upload";
	//论坛发帖的附件基本位置
	private static String BbsPath = "/bbs/";
	//教师发布课程的附件基本位置
	private static String AttachPath = "/attach/";
	
	//图片资源的基本位置
	private static String ImagePath = "/static/upload/";
	
	public static void setBasePath(String basePath){
		BasePath = basePath;
	}
	public static String getAttachPath(){
		return BasePath + AttachPath;
	} 
	public static String getBbsPath(){
		return BasePath + BbsPath;
	} 
	public static String getImagePath(){
		return ImagePath;
	} 
	public static String getBasePath(){
		return BasePath;
	} 
}
