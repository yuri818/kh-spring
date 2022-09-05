package com.spring4.mvc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
// ApplicationContext vs BeanFactory -> 컨테이너의 종류
// 빈을 관리해준다
// 이른 인스턴스화, 게으른 인스턴스화로 나뉘어짐
// 빈의 정의는 xml문서에서 (클래스 이름을 선언)한다

//직접 인스턴스화 하는 것은 실습 목표에 맞지 않으므로 주석처리
public class SonataSimulation {
	// 게으른 인스턴스화 - 시점
//	Sonata myCar = null; // 언제 초기화가 되나?
	// 이른 인스턴스화
//	Sonata herCar = new Sonata();
	void methodA() {
//		System.out.println(herCar.speed);
//		myCar = new Sonata();
//		System.out.println(myCar.speed);
	}
	public static void main(String[] args) {
		SonataSimulation ss = new SonataSimulation();
//		ss.methodA();
		// 스프링에서는 xml문서에 선언된 클래스 정보를 얻어와서 자바코드에 쓸 수 있도록
		// 제공하는 클래스가 있음
		ApplicationContext context 
		= new ClassPathXmlApplicationContext("com\\spring4\\mvc\\sonataBean.xml");
		Sonata myCar = (Sonata)context.getBean("myCar");
		Sonata herCar = (Sonata)context.getBean("herCar");// 인스턴스화 대신
		// 어 그런데 생성자가 여러개 이자나??
		// 이런 경우 그 중에 누가 호출 되나요???
		System.out.println(herCar.speed); 	 //0
		System.out.println(herCar.carName);  // null
		System.out.println(herCar.carColor); // null
		
		System.out.println(myCar);
		System.out.println(myCar.speed);
		System.out.println(myCar.carName);
		System.out.println("객체관리 책임이 개발자에게 있는 경우");
		myCar = new Sonata();
		System.out.println(myCar);
		System.out.println(myCar.speed);
		System.out.println(myCar.carName);
		System.out.println(myCar.carName);
		// 중지하고 싶다
		myCar = null; // 현재 줄에서 다음 줄로 갈 때Candidate 상태에 빠진다
		// 7번윗 번에 생성된 객체는 쓰레기값으로 인식되어 자원을 회수 당함 - 그 시점은 null할당과 재할당 사이이므로 지금 이부분!!
		myCar = new Sonata();
		System.out.println("null 초기화 후 비교, "+myCar);
		System.out.println(myCar.speed);
		System.out.println(myCar.carName);
	}
}
