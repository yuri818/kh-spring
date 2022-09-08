package com.di;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

// AbstractController를 상속받으면 메소드를 하나밖에 못쓴다...-> 별로이다.
public class MapController extends AbstractController {
	Logger logger = Logger.getLogger(MapController.class);
	//spring-servlet.xml에 선언된 bean[MapController]태그안에 정의된
	private Map<String,String> insaMsg = null; // 주입 받고 싶어요. 누가(스프링에서 필요할때 -게으른주입)
	// 아래 setter메소드는 setter객체주입법 코드임
	// 그 객체는 어디에 있는 객체인가요? - spring-servlet.xml에 있음 - Map
	public void setInsaMsg(Map<String, String> insaMsg) {
		this.insaMsg = insaMsg;
	}
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest req, HttpServletResponse res) throws Exception {
		logger.info("insaMsg====> "+insaMsg);
		ModelAndView mav = new ModelAndView();
		mav.addObject("insaMsg", insaMsg);
		mav.setViewName("di/insaMap");
		return mav;
	}

}
