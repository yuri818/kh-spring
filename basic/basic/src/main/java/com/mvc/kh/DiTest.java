package com.mvc.kh;

public class DiTest {
	// 이른 인스턴스화로 할건지 아님 게으른으로 할건지 - 이른 인스턴스화가 스프링의 기본임
	Service service = new Service();
//	View  	view 	= new View(); //NullPointer
	View  	view 	= new View(service);
	public void testRun() {
		view.methodA();
	}
	
	public static void main(String[] args) {
		DiTest dt = new DiTest();
		dt.testRun();
	}

}
