package com.simple.basic.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.simple.basic.command.SimpleVO;


@Controller
@RequestMapping("/view")
public class ViewController {

	//@RequestMapping( value = "/ex01", method = RequestMethod.GET)
	@GetMapping("/ex01")
	public String ex01() {
		return "view/ex01"; //템플릿 폴더 하위가 기준이 됨
	}

	@GetMapping("/ex02")
	public String ex02(Model model) {

		SimpleVO vo =SimpleVO.builder().sno(1).first("김자바")
				.regdate(LocalDateTime.now()).build();

		ArrayList<SimpleVO> list = new ArrayList<>();
		for(int i = 1; i<=10; i++) {
			SimpleVO svo =SimpleVO.builder().sno(i).first("김자바")
					.regdate(LocalDateTime.now()).build();
			list.add(svo);
		}



		model.addAttribute("list", list);
		model.addAttribute("vo", vo);
		return "view/ex02"; //템플릿 폴더 하위가 기준이 됨
	}
	@GetMapping("/ex03")
	public String ex03(Model model) {
		ArrayList<SimpleVO> list = new ArrayList<>();
		for(int i = 1; i<=10; i++) {
			SimpleVO svo =SimpleVO.builder().sno(i).first("김자바")
					.regdate(LocalDateTime.now()).build();
			list.add(svo);
		}
		SimpleVO vo =SimpleVO.builder().sno(1).first("김자바")
				.regdate(LocalDateTime.now()).build();
		model.addAttribute("list", list);
		model.addAttribute("vo", vo);
		return "view/ex03";
	}
	@GetMapping("/result")
	public String result(SimpleVO vo) {
		System.out.println(vo.toString());
		return "view/ex03_result";
	}


	//키를 받는 두번째 방법-URL파람

	@GetMapping("/result02/{a}/{b}")
	public String ex03_result02(@PathVariable("a") String name,
			@PathVariable("b") int age) {

		System.out.println("넘어온값:"+name+","+age);

		return "view/ex03_result";
	}


	//ex04 - 타임리프 인클루드
	@GetMapping("/ex04")
	public String ex04() {

		return "view/ex04";
	}

	//ex05 - 타임리프 템플릿 형식으로 결합하기
	@GetMapping("/ex05")
	public String ex05() {

		return "view/ex05";
	}


	//quiz
	@GetMapping("/quiz")
	public String quiz(Model model) {
		SimpleVO vo =SimpleVO.builder().sno(1).first("김").last("자바")
				.regdate(LocalDateTime.now()).build();
		model.addAttribute("vo",vo);
		return "view/quiz";
	}

	//quiz_result
	@GetMapping("/quiz_result01")
	public String quiz_re(@ModelAttribute("sno") int sno,
							@ModelAttribute("name") String name) {
		System.out.println(name);
		
		return "view/quiz_result01";
	}
}























