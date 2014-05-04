package edu.pwr.zpi.web.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class RequestCountInterceptor extends HandlerInterceptorAdapter {
	private static Logger logger = LoggerFactory.getLogger(RequestCountInterceptor.class);
	
	public RequestCountInterceptor() {
		logger.info("RequestCountInterceptor created");
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("RequestCountInterceptor afterCompletion");
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		logger.info("RequestCountInterceptor afterConcurrentHandlingStarted");
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("RequestCountInterceptor postHandle");
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		logger.info("RequestCountInterceptor preHandle");
		System.out.println("RequestCountInterceptor preHandle");
		return true;
	}

	
	
}
