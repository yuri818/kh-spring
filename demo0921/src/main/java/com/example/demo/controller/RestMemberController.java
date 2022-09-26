package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.logic.MemberLogic;
import com.google.gson.Gson;

@RestController
@RequestMapping("/member/*")
public class RestMemberController {
	Logger logger = LogManager.getLogger(RestMemberController.class);
	@Autowired(required=false)
	private MemberLogic memberLogic = null;
	
	// 스프링에서는 요청에 대한 출력 페이지 데이터셋 지원을 위해 Model을 제공한다.
	// 여기서는 필요한가? | 아닌가?
	@GetMapping("jsonMemberList")
	public String jsonMemberList(@RequestParam Map<String,Object> pMap) {
		List<Map<String, Object>> memberList = null;
		String temp = null;
		memberList = memberLogic.memberList(pMap);
		// [{mem_id=tomato, mem_pw=123, ......}] : memberList데이터셋 구조
		//temp = memberList.toString(); -> 이렇게 하면 망하는 것임
		Gson g = new Gson();
		temp = g.toJson(memberList);
		return temp;
	}
}
