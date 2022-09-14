package com.basic.step1.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.basic.step1.logic.BoardLogic;


@Controller
@RequestMapping("/board/*")
public class BoardController {
	Logger logger = LoggerFactory.getLogger(BoardController.class);
	@Autowired(required=false)
	private BoardLogic boardLogic = null;
	/**********************************************
	 * dev_web과 basic 비용 계산해보기
	 * Map 선언만함 - @RequestParam
	 * HashMapBinder가 필요없어짐
	 * ModelAndView도 필요없음 - Model
	 * 리턴타입: ModelAndView -> String으로 변경
	 ***********************************************/
	@GetMapping("boardList.sp4")
	public String boardList(Model model, @RequestParam Map<String,Object> pMap) {
		logger.info("boardList 호출 성공");
		// ModelAndView 객체를 설계함에 따라서 req가 없어도 조회결과를 담을 수 있게 되었다 - 의미
		List<Map<String,Object>> boardList = null;
		boardList = boardLogic.boardList(pMap);
		model.addAttribute("boardList",boardList);
		return "forward:boardList.jsp";
	}
	@GetMapping("boardInsert.sp4")
	public String boardInsert(@RequestParam Map<String,Object> pMap, @RequestParam(value="bs_file", required=false) MultipartFile bs_file) {
		logger.info("boardInsert 호출 성공: "+pMap);
		int result = 0;
		result = boardLogic.boardInsert(pMap); 
		return "redirect:boardList.sp4"; // 페이지를 리턴해야함
	}
	@GetMapping("boardDetail.sp4")
	public Object boardDetail(Model model, @RequestParam Map<String,Object> pMap) {
		logger.info("boardDetail 호출 성공");
		List<Map<String,Object>> boardList = null;
		boardList = boardLogic.boardDetail(pMap); // 한건 조회했을때 이니까 boardDetail로 바꾸깅
		model.addAttribute("boardList",boardList);
		return "forward:read.jsp";
	}
	@GetMapping("boardUpdate.sp4")
	public Object boardUpdate(@RequestParam Map<String,Object> pMap) {
		logger.info("boardUpdate 호출 성공");
		int result = 0;
		result = boardLogic.boardUpdate(pMap); 
		String path = "redirect:boardList.sp4"; // 페이지를 리턴해야함
		return path;
	}
	@GetMapping("boardDelete.sp4")
	public Object boardDelete(@RequestParam Map<String,Object> pMap) {
		logger.info("boardDelete 호출 성공");
		int result = 0;
		result = boardLogic.boardDelete(pMap); 
		return "redirect:boardList.sp4";
	}
	
	
}
