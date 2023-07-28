package com.simple.basic.command;

import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {
	
	@Pattern(message = "아이디 형식에 맞춰서 작성 바람",regexp = "[a-zA-Z0-9]{8,}")
	private String id;
	@Pattern(message = "비밀번호 형식에 맞춰서 작성 바람",regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$")
	private String pw;
}
