package com.simple.basic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.simple.basic.util.interceptor.UserAuthHandler;

@Configuration //해당 클래스가 설정파일이라는 것을 명시해줌
public class WebConfig implements WebMvcConfigurer{ //자바 빈설정을 하기 위해서 상속 받아야 하는 인터페이스
	
	
	
	//인터셉터로 사용할 클래스를 bean으로 등록
	@Bean
	public UserAuthHandler userAuthHandler() {
		return new UserAuthHandler();
	}

	//스프링설정에 인터셉터를 추가하는 기능
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(userAuthHandler())
				.addPathPatterns("/user/**") // /user로 시작하는 모든 요청에 대한 검사
				//.addPathPatterns("/memo/**")
				//.addPathPatterns("/product/**")
				.excludePathPatterns("/user/login") // user/login은 제외한다.
				.excludePathPatterns("/user/loginForm"); // 로그인 메소드 제외 
				//.addPathPatterns("/user/mypage")
				//.addPathPatterns("/user/modify")
		
	}
	
	
	
	
	
	
	
	
	
	//IOC확인
	/*
	@Autowired
	ApplicationContext applicationContext;
	
	//value 어노테이션 사용
	@Value("${spring.datasource.url}")
	String url;
	
	
	
	
	@Bean //해당 메소드를 빈으로 생성 - return 객체를 반환하는 모형을 만들면, 빈으로 등록
	public void test() {
		//System.out.println(1);
		TestBean test = applicationContext.getBean(TestBean.class);
		System.out.println("TestBean이 빈으로 등록됨"+test);
		
		HomeController home = applicationContext.getBean(HomeController.class);
		System.out.println("home컨트롤러 빈 "+home);
		
		int count = applicationContext.getBeanDefinitionCount();
		System.out.println("ioc안에 빈 갯수"+count);
		
		
		System.out.println("application url 키 값 : "+url);
		
	}
	*/
	
	//빈생성 2가지 전략 - @Controllerm, @Service 등 이용해서 빈으로 등록
	//스프링 설정파일에 빈으로 등록
	//return 객체를 반환하는 모형을 만들면, 빈으로 등록 
	/*
	@Bean
	public TestBean test2() {
		System.out.println("config파일의 test2메소드");
		TestBean b = new TestBean();
		return  b;
	}
	*/
	
	
	
}
