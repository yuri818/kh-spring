package com.di;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class ListController extends AbstractController {
	Logger logger = Logger.getLogger(ListController.class);
	// 객체 주입받는 클래스의 변수명은 마음대로 바꿀 수 없다 (setter객체주입법) 
	List<String> insaBean = null;
	// setter가 꼭 필요하다 - 해주지 않을경우 에러뜨거나 주석처리 할 경우 null을 반환한다.
	public void setInsaBean(List<String> insaBean) {
		this.insaBean = insaBean;
	}
	// url -> /di/insaList.sp -> sp를 적어주는 것은 서블릿을 경유하기 위함이다!
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		logger.info("insaBean===> "+insaBean);
		ModelAndView mav = new ModelAndView();
		// 아래 코드는 POJO의 Board3Controller에서 가져온 것
		mav.addObject("insaBean",insaBean);
		// viewResolver를 보면 접미어로 jsp를 붙여주니까 뒤에 insaList.jsp로 하지말깅
		mav.setViewName("di/insaList");
		return mav;
	}

}
