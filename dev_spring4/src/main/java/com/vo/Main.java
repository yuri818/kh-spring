package com.vo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//나는 순수한 자바입니다.
//어떤 추상클래스나 어떤 인터페이스도 상속, implements하지 않은 클래스
//어떠한 메소드도 오버라이드 하지 않아도 된다
public class Main {

	public static void main(String[] args) {
		// 어노테이션 관리해주는 컨테이너라고 보면 됨
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppContext.class); // 파라미터로 어노테이션 붙였던 클래스 주입
		DeptVO dVO = ctx.getBean("getDeptVO", DeptVO.class);
		int deptno = dVO.getDeptno();
		String dname = dVO.getDname();
		String loc = dVO.getLoc();
		System.out.println(deptno);
		ctx.close();
	}

}
