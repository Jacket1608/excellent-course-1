/**
 * @author shuqi
 * @date   2015年4月25日
 * @version since 1.0
 */
package io.shuqi.graduation.filter;

import io.shuqi.graduation.utils.BrowserUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author shuqi
 * @date   2015年4月25日
 * @version since 1.0
 */
public class LoginFilter implements Filter {
	List<String> passURIList;

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 * @file LoginFilter.java
	 * @date 2015年4月25日
	 */
	@Override
	public void destroy() {
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 * @file LoginFilter.java
	 * @date 2015年4月25日
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response  = (HttpServletResponse) resp;
		
		String url = request.getRequestURL().toString();
		String contextPath = request.getContextPath();
		/**
		 * 不允许IE8一下的浏览器浏览
		 */
		boolean isIE = BrowserUtil.getBrowserEnum(request).isIE();
		float IEVersion = BrowserUtil.getIEVersion(request);
		if(isIE&&BrowserUtil.getIEVersion(request)<8){
			request.getRequestDispatcher("WEB-INF/jsp/templet/notSupport.jsp").forward(request, response);
		}
		//将浏览器的信息保存到session中
		HttpSession session = request.getSession(true);
		session.setAttribute("isIE", isIE);
		session.setAttribute("browserVerion", IEVersion);
		//判断是否要对用户访问进行拦截
		if(canPass(passURIList,url,contextPath)){
			chain.doFilter(request, response);
		}else{
			//登录后将用户存在seeion中，如果不存在则表示该去登录了
			if(session.getAttribute("currentUser")!=null){
				chain.doFilter(request, response);
			}else{
				response.sendRedirect(request.getContextPath()+"/login");
			}
		}
	}
	/**
	 * 判断是否放行
	 * @author shuqi
	 * @date   2015年4月27日
	 * @version since 1.0
	 * @param passURIList 初始化的允许通过的url白名单
	 * @param url	请求url
	 * @param contextPath	app的path(contextPath)
	 * @return
	 */
	private boolean canPass(List<String> passURIList, String url,String contextPath) {
		for (String str : passURIList) {
			Pattern pattern  = Pattern.compile("^http://([0-9_a-zA-Z:\\.])+"+contextPath+str+"");
			Matcher matcher =  pattern.matcher(url);
			if(matcher.find()){
				return true;
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 * @file LoginFilter.java
	 * @date 2015年4月25日
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		/**
		 * 初始化直接放行的url
		 */
		String uri = filterConfig.getInitParameter("passURI");
		passURIList = Arrays.asList(uri.split("\\:"));
	}

}
