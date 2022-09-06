package com.vo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * POJO F/W
 * : 1-3에서 4버전의 중간쯤을 보여주자
 * 
 * Spring4.3.30(xml) - 이종간의 연계(연동)
 *  : DI와 IoC에 대해서 배우고 있다
 *  : IoC - 제어역전 이다 -> ApplicationContext, BeanFactory
 *  코드적으로 말하면 A a = null; 해야지 A a = new A(); 이렇게 하면 스프링을 활용하는게 아님
 * 
 * Spring5.0 - annotation
 * 
 * Spring Boot - annotation
 * 
 * 
 */



@Configuration
public class AppContext { 
	@Bean
	public DeptVO getDeptVO() { // 메소드를 통해 객체 주입 받도록 @Bean을 사용한다.
		DeptVO dVO = new DeptVO();
		dVO.setDeptno(10);
		dVO.setDname("개발부");
		dVO.setLoc("서울");
		return dVO;
	}
}
