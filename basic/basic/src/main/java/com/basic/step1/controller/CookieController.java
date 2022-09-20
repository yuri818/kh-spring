package com.basic.step1.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cookie/*")
public class CookieController {
	Logger logger = LoggerFactory.getLogger(CookieController.class);
	@GetMapping("cookieMake.sp4")
	public String cookieMake(HttpServletResponse res) {
		logger.info("cookieMake 호출");
		Cookie cook1 = new Cookie("empno", "9900");
		res.addCookie(cook1);
		return "redirect:cookieMake.jsp";
	}
	@GetMapping("cookieView.sp4")
	public String cookieView(@CookieValue(value="sneakers", defaultValue="운동화")String sneakers
			,@CookieValue(value="age", defaultValue="1")String age
			,@CookieValue(value="empno", defaultValue="7655")String empno
			,@CookieValue(value="coffee", defaultValue="아이스아메리카노")String coffee
			) {
		logger.info("cookieView 호출/ sneakers: "+sneakers);
		logger.info("cookieView 호출/ age: "+age);
		logger.info("cookieView 호출/ empno: "+empno);
		logger.info("cookieView 호출/ coffee: "+coffee);
		return "redirect:cookieView.jsp";
	}
}
