package com.di;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;

public class Sonata {
	Logger logger = Logger.getLogger(Sonata.class);
	int speed = 10;
	String carName = "2023년형 소나타";
	String carColor = "흰색";
	public void init() {
		System.out.println("init호출 성공");
	}
	// 문법적인 문제는 해결했지만 호출(URL-웹)은 불가하다
	protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse res) throws Exception {
		logger.info("handleRequestInternal 호출 성공");
		ModelAndView mav = new ModelAndView();
		// -> /di/hello.sp 이 요청에 대한 응답페이지 이름
		// /WEB-INF/views/di/hello.jsp
		mav.setViewName("di/hello");
		return mav;
	}
	public Sonata() {
		logger.info("Sonata 디폴트 생성자 호출");
	}
	public Sonata(int speed) {
		this.speed = speed;
	}
	public Sonata(int speed, String carName) { // 속도, 자동차 색상
		this.speed = speed;
		this.carName = carName;
	}
	public Sonata(int speed, String carName, String carColor) {
		this.speed = speed;
		this.carName = carName;
		this.carColor = carColor;
	}
}
