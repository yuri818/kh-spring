package com.mvc.kh;
// ApplicationContext 로 주입 - 이른 인스턴스화
// BeanFactory로 주입 - 게으른 인스턴스화
public class View {
	Service service = null;
	public View() {

	}
	public View(Service service) {
		this.service = service;
	}
	public void methodA() {
		System.out.println(service);
		// 1. 생성자 선택의 문제
		// 2. 호출 위치의 문제
		// 500번 문제를 피할 수 있도록 관리를 받자 - spring framework로 부터
		// DI를 통해 관리받자
		service.methodB(); // 생성자의 선택에 따라 NullPointerException이 발생할 수 있다.
	}
}
