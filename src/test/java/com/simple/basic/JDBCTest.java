package com.simple.basic;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.simple.basic.command.BuilderVO;
import com.simple.basic.command.BuilderVO.Builder;
import com.simple.basic.command.BuilderVO2;

@SpringBootTest //스프링 부트 테스트 클래스
public class JDBCTest {
	
	//빌더 패턴의 확인
	@Test
	public void testCode01() {
		/*
		Builder b = BuilderVO.builder();
		b = b.age(10);
		b = b.name("홍길동");
		BuilderVO vo = b.build();
		*/
		
		//vo는 setter가 없기 때문에 객체불변성을 보장한다.
		BuilderVO vo = BuilderVO.builder().age(10).name("이순신").build();
		System.out.println(vo.toString());
		
		BuilderVO2 vo2 = BuilderVO2.builder().name("갓갓갓").age(25).build();
		System.out.println(vo2.toString());
	}
	
	
}
