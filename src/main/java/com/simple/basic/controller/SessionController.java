package com.simple.basic.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class SessionController {
	
	//인증이 없는 경우
	@GetMapping("/mypage")
	public String mypage(HttpSession session) {
		/*
		 * if(session.getAttribute("username")==null) { return "user/login"; }
		 */
		return "user/mypage";
	}
	
	@GetMapping("/login")
	public String login() {
		
		return "user/login";
	}
	
	@GetMapping("/modify")
	public String modify(HttpSession session) {
		if(session.getAttribute("username")==null) {
			return "user/login";
		}
		return "user/modify";
	}
	
	@PostMapping("/loginForm")
	public String loginForm(/*HttpServletRequest request*/HttpSession session) {
		//로그인 시도(성공)
		//HttpSession session = request.getSession();
		if(true) {
			session.setAttribute("username", "aaa123");
			return "redirect:/user/mypage";
		}else {
			return "redirect:/"; // 홈화면
		}
	}
}
