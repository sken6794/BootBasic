package com.simple.basic.command;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidVO {
	
	/*
	 * @Notnull = Null 허용x, (String, Integer, Double 등 에 적용)
	 * @NotEmpty = Null 허용x, 공백 허용x, (String에 적용)
	 * @NotBlank = Null, 공백, whitespace 허용x (String 적용)
	 * 
	 * @Pattern = 정규표현식에 일치하지 않으면 error 발생
	 */
	
	
	
	//기본 타입은 wrapper타입으로 작성
	@NotEmpty(message = "이름은 필수 입니다.")
	private String name;
	@NotNull(message = "급여는 숫자로 입력")
	private Integer salary; //int 는 기본값 0 Integer는 null값
	@Pattern(message = "000-0000-0000 형식으로 입력", regexp = "[0-9]{3}-[0-9]{4}-[0-9]{4}")
	private String phone;
	@NotBlank(message = "공백일 수 없습니다.")
	@Email(message = "이메일 형식이어야 합니다") //이메일은 공백이 통과된다.
	private String email;
}
