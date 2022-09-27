package com.example.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.logic.MemberLogic;
import com.example.demo.vo.MemberVO;

@Controller
@RequestMapping("/member/*")
@SessionAttributes({"smem_id","smem_name","s_cnt"})
public class MemberController {
	Logger logger = LogManager.getLogger(MemberController.class);
	
	@Autowired(required=false)
	private MemberLogic memberLogic = null;
	
	@GetMapping("login")
	public String login(HttpSession session,@RequestParam Map<String,Object> pMap) {
		logger.info("컨트롤러의 login호출 성공! 넘어온 pMap: "+pMap);
		MemberVO mVO = null;
		mVO = memberLogic.login(pMap);
		if(mVO != null) {
			session.setAttribute("smem_id", mVO.getMem_id());
			session.setAttribute("smem_name", mVO.getMem_name());
			session.setAttribute("s_cnt", mVO.getCount());
		}
		return "redirect:/auth/index.jsp";
	}
	
	@GetMapping("memberInsert")
	public String memberInsert(@RequestParam Map<String,Object> pMap) {
		int result = 0;
		result = memberLogic.memberInsert(pMap);
		return "redirect:/auth/index.jsp";
	}
}
