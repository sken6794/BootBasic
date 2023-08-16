package com.simple.basic.util.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class UserAuthHandler implements HandlerInterceptor{

	//컨트롤러 진입하기 이전에 실행됨
	//return true -> 컨트롤러 실행
	// 		 false -> 실행x
	//스프링 설정 파일에 인터셉터를 등록
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("userAuthHandler 동작함");
		System.out.println("======================");
		
		//세션 검사
		HttpSession session = request.getSession();
		if(session.getAttribute("username")!=null) { //로그인이 된 사람
			
			return true;
		} else {//로그인이 안되어 있는 경우
			
			response.sendRedirect(request.getContextPath()+"/user/login");
			return false;
		}
		
	}
	
	//컨트롤러 실행 후에 동작함
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("post 실행됨");
	}
	
	
	
	
}
