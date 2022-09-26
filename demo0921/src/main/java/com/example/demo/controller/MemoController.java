package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.logic.MemoLogic;
import com.google.gson.Gson;


@Controller
@RequestMapping("/memo/*")
public class MemoController {
Logger logger = LogManager.getLogger(MemoController.class);
	
	@Autowired(required=false)
	private MemoLogic memoLogic = null;
	
	/************ [[ 쪽지 보내기 ]] ************/
	@GetMapping("memoInsert")
	public String memoInsert(@RequestParam Map<String,Object> pMap) {
		logger.info("컨트롤러의 memoInsert호출 성공! 넘어온 pMap: "+pMap);
		int result = 0;
		result = memoLogic.memoInsert(pMap);
		return "redirect:/auth/index.jsp";
	}
	/************ [[ 보낸쪽지함 ]] ************/
	@GetMapping("sendMemoList")
	public String sendMemoList(@RequestParam Map<String,Object> pMap) {
		List<Map<String, Object>> sendMemoList = null;
		sendMemoList = memoLogic.sendMemoList(pMap);
		//@RestController, @ResponseBody사이의 차이점
		return "redirect:/memo/jsonSendMemoList.jsp";
	}
	/************ [[ 받은쪽지함 ]] ************/
	@GetMapping("receiveMemoList")
	public String receiveMemoList(@RequestParam Map<String,Object> pMap) {
		List<Map<String, Object>> receiveMemoList = null;
		receiveMemoList = memoLogic.receiveMemoList(pMap);
		return "redirect:/memo/jsonReceiveMemoList.jsp";
	}
}
