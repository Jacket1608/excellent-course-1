package io.shuqi.graduation.resolver;

import io.shuqi.graduation.exception.NoResourceException;
import io.shuqi.graduation.exception.NoRightException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


/**
 * 全局异常处理
 * @author shuqi
 * @date   2015年5月13日
 * @version since 1.0
 */

@Component
public class GlobalExceptionResolcer implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception e) {
		e.printStackTrace();
		if(e instanceof NoResourceException){
			return new ModelAndView("/templet/noResource");
		}else if(e instanceof NoRightException){
			return new ModelAndView("/templet/hasNoRight");
		}else{
			//ModelAndView mv = new ModelAndView("/debugger");
			//mv.addObject("msg", e.toString());
//			return mv;
			return new ModelAndView("/templet/500");
		}
	}


}
