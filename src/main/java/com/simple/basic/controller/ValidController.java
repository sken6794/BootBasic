package com.simple.basic.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.simple.basic.command.MemberVO;
import com.simple.basic.command.ValidVO;

@Controller
@RequestMapping("/valid")
public class ValidController {
	
	@GetMapping("/view")
	public String view() {
		return "valid/view";
	}
	
	@PostMapping("/viewForm")
	public String viewForm(@Valid @ModelAttribute("vo") ValidVO vo, Errors errors, Model model) {
		
		//System.out.println(vo.toString());
		//System.out.println(errors);
		
		if(errors.hasErrors()) { //에러가 있다면 t 없으면 f
			
			//1. 유효성 검사에 실패한 에러 확인
			List<FieldError> list = errors.getFieldErrors();
			
			//2. 반복처리
			for(FieldError err : list) {
				//System.out.println(err);
				//System.out.println(err.getField());
				//System.out.println(err.getDefaultMessage());
				//System.out.println(err.isBindingFailure()); //유효성검사에 의해서 err 인지(false) 자바내부 err 인지(true) 반환
				if(err.isBindingFailure()) {
					model.addAttribute("valid_"+err.getField(), "잘못된 입력임 숫자만");
				}else {
					model.addAttribute("valid_"+err.getField(),err.getDefaultMessage());
				}
			}
			
			return "valid/view"; //실패시 원래 화면
		}
		return "valid/result";
	}
	
	@GetMapping("/quiz1")
	public String quiz1() {
		return "valid/quiz01";
	}
	
	@PostMapping("/quizForm")
	public String quizForm(@Valid @ModelAttribute("vo") MemberVO vo, Errors error, Model model) {
		System.out.println(vo.getPw());
		if(error.hasErrors()) {
			List<FieldError> list = error.getFieldErrors();
			for(FieldError err : list) {
				model.addAttribute("valid_"+err.getField(),err.getDefaultMessage());
			}
			return "valid/quiz01";
		}
		
		return "valid/quiz_result";
	}
}
