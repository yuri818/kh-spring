package com.vo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		// 어노테이션 관리해주는 컨테이너라고 보면 됨
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppContext.class); // 파라미터로 어노테이션 붙였던 클래스 주입
		DeptVO dVO = ctx.getBean("getDeptVO", DeptVO.class);
		int deptno = dVO.getDeptno();
		System.out.println(deptno);

	}

}
