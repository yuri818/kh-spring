package com.chap2;

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


// 스프링 컨테이너가 관리를 하는 클래스임
// 객체 주입을 위한 사전 조치, 추가해야 할 코드이다.
@Configuration
public class AppContext { 
	// 메소드 이름 앞에 @Bean 어노테이션을 붙이면 메소드 이름이 빈네임이 되는 것이다.
	// 인스턴스변수.getBean(빈네임);
	// 빈생성 객체를 인스턴스화 하면서 @Bean이 정의된 클래스를 생성자의 파라미터로 
	// 추가하는 것 만으로도 @Bean이 붙은 메소드를 호출할 수 있다.
	// @Configuration과 @Bean은 직접적인 의존관계는 아님
	@Bean
	public DeptVO getDeptVO() { // 메소드를 통해 객체 주입 받도록 @Bean을 사용한다.
		DeptVO dVO = new DeptVO();
		dVO.setDeptno(10);
		dVO.setDname("개발부");
		dVO.setLoc("서울");
		return dVO;
	}
}
