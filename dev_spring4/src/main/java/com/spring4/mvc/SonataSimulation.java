package com.spring4.mvc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
		ApplicationContext context 
		= new ClassPathXmlApplicationContext("com\\spring4\\mvc\\sonataBean.xml");
		Sonata myCar = (Sonata)context.getBean("myCar");
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
		myCar = null; // 33번에서 34번으로 갈 떄 Candidate 상태에 빠진다
		// 27번에 생성된 객체는 쓰레기값으로 인식되어 자원을 회수 당함 - 그 시점은 null할당과 재할당 사이이므로 지금 이부분!!
		myCar = new Sonata();
		System.out.println("null 초기화 후 비교, "+myCar);
		System.out.println(myCar.speed);
		System.out.println(myCar.carName);
	}
}
