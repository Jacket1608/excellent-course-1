package io.shuqi.graduation.listener;

import java.io.File;

import io.shuqi.graduation.utils.AppContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Application Lifecycle Listener implementation class AppContext
 *
 */
public class AppContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public AppContextListener() {
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	//servlet容器加载时，初始化数据
    	
    	//初始换上传文件的目录
    	String uploadPath = sce.getServletContext().getInitParameter("uploadPath");
    	if(uploadPath !=null && !uploadPath.endsWith("\\/")){
    		File file = new File(uploadPath);
    		if(file.exists() && file.isDirectory() ){
    			AppContext.setBasePath(uploadPath);
    		}
    	}
    	
    }
	
}
