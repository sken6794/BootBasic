package com.simple.basic.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.simple.basic.command.MemoVO;
import com.simple.basic.memo.service.MemoService;

@Controller
@RequestMapping("/memo")
public class MemoController {
	
	@Autowired
	@Qualifier("memoService")
	MemoService memoService;
	
	
	
	@GetMapping("/memoList")
	public String memoList(Model model) {
		
		model.addAttribute("list",memoService.getList());
		
		
		return "memo/memoList";
	}
	
	@GetMapping("/memoWrite")
	public String memoWrite() {
		return "memo/memoWrite";
	}
	
	
	@PostMapping("/memoForm")
	public String memoForm(@Valid @ModelAttribute("vo") MemoVO vo,Errors err,Model model) {
		
		if(err.hasErrors()) {
			List<FieldError> list = err.getFieldErrors();
			for(FieldError f : list) {
				
				model.addAttribute("valid_"+f.getField(),f.getDefaultMessage());
			}
			return "memo/memoWrite";
			
		}
		
		memoService.insertMemo(vo);
		
		return "redirect:/memo/memoList";
	}
	
	@PostMapping("/memoDelete")
	public String memoDelete(@RequestParam("mno") String mno) {
		memoService.deleteMemo(mno);
		return "redirect:/memo/memoList";
	}
	
	
}
