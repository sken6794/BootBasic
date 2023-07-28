package com.simple.basic.command;

import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemoVO {
	
	
	private int mno;
	//@NotNull(message = "5글자 이상 입력하세요")
	@Pattern(message = "5글자 이상 입력하세요", regexp = "^[A-Za-z0-9가-힣]{5,}$")
	private String memo;
	@Pattern(message = "휴대폰 번호 형식으로 입력",regexp = "^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$")
	private String phone;
	@Pattern(message = "4자리만 가능",regexp = "[0-9]{4}")
	private String pw;
	private String secret;
	
	
}
