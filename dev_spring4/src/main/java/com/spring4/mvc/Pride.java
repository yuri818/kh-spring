package com.spring4.mvc;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

// 상속은 재사용성에 대한 모범답안이 아니다 - 결합도가 높으니까....
// 그런데 스프링은 결합도를 낮추어야 하는 입장에 있다
// 프레임워크가 가져야하는 사상에 위배된다.
// 그러면 이러한 내부적 결함을 어떻게 해결해볼까? - 문제, 고민제기 + xml은 어려워?? -> 해결방법
// -> 어노테이션 이다.
// request와 response 주입을 받을 수 있는 클래스이다.
// 웹 서비스를 제공할 수 있다.
// response.setContentType("text/html;charset=utf-8");
// String mem_id = request.getParameter("mem_id"); // 사용자가 입력한 값을 듣는다
public class Pride extends MultiActionController {
	Logger logger = Logger.getLogger(Pride.class);
	String carColor = null;
	int speed = 0;
	int wheelNum = 0;
	public Pride() {
		logger.info("Pride 디폴트 생성자 호출");
	}
}
