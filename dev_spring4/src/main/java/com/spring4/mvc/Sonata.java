package com.spring4.mvc;

import org.apache.log4j.Logger;

public class Sonata {
	Logger logger = Logger.getLogger(Sonata.class);
	int speed = 10;
	String carName = "2023년형 소나타";
	public Sonata() {
		logger.info("Sonata 디폴트 생성자 호출");
	}
	public Sonata(int speed) {
		this.speed = speed;
	}
}
