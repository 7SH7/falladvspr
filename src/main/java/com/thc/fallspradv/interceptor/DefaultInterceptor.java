package com.thc.fallspradv.interceptor;
//
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public class DefaultInterceptor implements HandlerInterceptor {
//
//	// 인터셉터는 컨트롤러가 실행되기 전, 후에 실행되는 코드
//	private final Logger logger = Logger.getLogger(this.getClass());
//
//	// 컨트롤러 실행 전에 실행되는 코드
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//		logger.info("preHandle / request [{}]", request);
//
//		request.setAttribute("reqTest", "done");
//		response.setHeader("resTest", "done1");
//
//		logger.info("preHandle / reqTest [{}]", request.getAttribute("reqTest"));
//		logger.info("preHandle / resTest [{}]", response.getHeader("resTest"));
//
//		String requestURI = request.getRequestURI();
//		String requestMethod = request.getMethod();
//		return true;
//	}
//
//	// 컨트롤러 실행 후에 실행되는 코드
//	// 컨트롤러 실행 후에 실행되는 코드는 컨트롤러에서 예외가 발생하면 실행되지 않음
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//		logger.info("postHandle / request [{}]", request);
//	}
//
//	// 모든 것을 마친 후, 실행되는 코드
//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//		logger.info("afterCompletion / request [{}]", request);
//	}
//}
//

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class DefaultInterceptor implements HandlerInterceptor {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	//컨트롤러 진입 전에 호출되는 메서드
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.info("preHandle / request [{}]", request);
		request.setAttribute("reqTest", "done");
		response.setHeader("resTest", "done1");
		logger.info("preHandle / reqTest [{}]", request.getAttribute("reqTest"));
		logger.info("preHandle / resTest [{}]", response.getHeader("resTest"));

		String requestURI = request.getRequestURI();
		String requestMethod = request.getMethod();
		return true;
	}

	//컨트롤러 실행 후에 호출되는 메서드
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		logger.info("postHandle / request [{}]", request);
	}

	//모든것을 마친 후 실행되는 메서드
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		logger.info("afterCompletion / request [{}]", request);
	}
}