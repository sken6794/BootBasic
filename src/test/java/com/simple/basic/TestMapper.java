package com.simple.basic;

import org.apache.ibatis.annotations.Mapper;

@Mapper //interface에 붙이는 어노테이션
public interface TestMapper {
	
	String getTime();
}

