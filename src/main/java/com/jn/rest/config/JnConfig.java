package com.jn.rest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration	//설정 파일임을 나타내는 어노테이션
public class JnConfig implements WebMvcConfigurer {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		log.debug("요기가 실행되었는지 check?");
		
		/*
		 	/jnweb/aaa/merong.jpg => d:/jnUp/aaa/merong.jpg 
		 */
		
		registry.addResourceHandler("/jnweb/**")             // 웹 접근 경로 
		        .addResourceLocations("file:///d:/jnUp/");  // 서버내 실제 물리적 경로
		
		/*
		 *	d:/jhup/fib/aa.jpg => http://도메인명:포트번호/ysup/fbi/aa.jpg 
		 */
		
	}
}