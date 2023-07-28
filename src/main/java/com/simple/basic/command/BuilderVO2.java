package com.simple.basic.command;

import lombok.Builder;

/*
 * @Data => getter, setter, toString
 * @AllArgsConstructor => 모든 생성자
 * @NopArgsConstructor => 기본 생성자
 * @Builder => 빌더패턴으로 생성해줌
 */

@Builder
public class BuilderVO2 {
	
	private String name;
	private int age;
}