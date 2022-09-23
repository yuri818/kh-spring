package com.example.demo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.demo.logic.BoardLogic;
import com.google.gson.Gson;
import com.util.HangulConversion;
import com.util.HashMapBinder;


@Controller
@RequestMapping("/board/*")
public class BoardController {
	Logger logger = LoggerFactory.getLogger(BoardController.class);
	@Autowired(required=false)
	private BoardLogic boardLogic = null;
	private final String filePath = "C:\\Users\\Lenovo\\git\\kh-spring\\demo0921\\src\\main\\webapp\\pds";
	@ResponseBody
	@GetMapping(value="helloworld", produces="text/plain;charset=UTF-8")
	public String helloWorld() {
	    return "한글처리";
	}
	@ResponseBody
	@GetMapping(value="jsonFormat", produces="application/json;charset=UTF-8")
	public String jsonFormat() {
		List<Map<String,Object>> names = new ArrayList<>();
		Map<String,Object> rmap = new HashMap<>();
		rmap.put("mem_id", "tomato");
		rmap.put("mem_name", "토마토");
		names.add(rmap);
		rmap = new HashMap<>();
		rmap.put("mem_id", "apple");
		rmap.put("mem_name", "사과");
		names.add(rmap);
		Gson g = new Gson();
		String temp = g.toJson(names);
		return temp;
	}
	// 사이드 관전 포인트 - 파라미터에 req, res가 없다.
	@GetMapping("testParam")
	public String testParam(@RequestParam String mem_id) {
		logger.info("boardList 호출 성공"+mem_id);
		return "redirect:/test/testList.jsp";
	}
	
	/**********************************************
	 * dev_web과 basic 비용 계산해보기
	 * Map 선언만함 - @RequestParam
	 * HashMapBinder가 필요없어짐
	 * ModelAndView도 필요없음 - Model
	 * 리턴타입: ModelAndView -> String으로 변경
	 ***********************************************/
	// 메소드의 파라미터로 객체 주입이 되고 있다 - 의존성 주입관계에 있다.
	// 스프링에서 제공[임스턴스화를 대신해주고 그 시점도 관리해줌 - 라이프사이클 관리]하는 클래스이다.
	// 이런걸 해주니까 개발자는 로직에만 집중할 수 있다 - 인스턴스화 안해도 된다, 자원반납도 안해도 안전함
	@GetMapping("boardList")
	public String boardList(Model model, @RequestParam Map<String,Object> pMap) {
		logger.info("boardList 호출 성공: "+ pMap); //cb_search:b_title컬럼 , tb_search:title 가 key값으로 넘어 올 것이다.
		// ModelAndView 객체를 설계함에 따라서 req가 없어도 조회결과를 담을 수 있게 되었다 - 의미
		List<Map<String,Object>> boardList = null;
		boardList = boardLogic.boardList(pMap);
		model.addAttribute("boardList",boardList);
//		return "forward:boardList.jsp"; // webapp/board/
		// pojo 1-3에서 Modelandview와 동일함
		return "board/boardList"; // WEB-INF/views/board
	}
	//@GetMapping("boardInsert")
	@PostMapping("boardInsert")
	public String boardInsert(MultipartHttpServletRequest mpRequest, @RequestParam(value="bs_file", required=false) MultipartFile bs_file) {
		int result = 0;
		Map<String,Object> pMap = new HashMap<>();
		HashMapBinder hmb = new HashMapBinder(mpRequest);
		hmb.mbind(pMap);
		logger.info("boardInsert 호출 성공: "+pMap);
		if(!bs_file.isEmpty()) {
			String filename = bs_file.getOriginalFilename();
//			String filename = HangulConversion.toUTF(bs_file.getOriginalFilename());
			logger.info("한글처리 테스트:"+filename);
			String savePath = "C:\\Users\\Lenovo\\git\\kh-spring\\demo0921\\src\\main\\webapp\\pds";
			// 파일에 대한 풀 네임 담기
			String fullPath = savePath+"\\"+filename;
			try {
				//File 객체는 파일명을 객체화 해줌
				File file = new File(fullPath);
				// board_sub_t에 파일크기를 담기 위해 계산
				byte[] bytes = bs_file.getBytes();
				BufferedOutputStream bos = 
						new BufferedOutputStream(new FileOutputStream(file));
				// 실제로 파일 내용이 채워짐
				bos.write(bytes);
				bos.close();
				long size = file.length();
				double d_size = Math.floor(size/1024.0); //kb
				logger.info("size:"+d_size);
				pMap.put("bs_file", filename);
				pMap.put("bs_size", d_size);
				logger.info("파일정보: "+pMap.get("bs_file")+", "+pMap.get("bs_size"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		result = boardLogic.boardInsert(pMap); 
		return "redirect:boardList"; // 페이지를 리턴해야함
	}
	@GetMapping("boardDetail")
	public Object boardDetail(Model model, @RequestParam Map<String,Object> pMap) {
		logger.info("boardDetail 호출 성공");
		List<Map<String,Object>> boardList = null;
		boardList = boardLogic.boardDetail(pMap); // 한건 조회했을때 이니까 boardDetail로 바꾸깅
		model.addAttribute("boardList",boardList);
		return "forward:read.jsp";
	}
	@GetMapping("boardUpdate")
	public Object boardUpdate(@RequestParam Map<String,Object> pMap) {
		logger.info("boardUpdate 호출 성공");
		int result = 0;
		result = boardLogic.boardUpdate(pMap); 
		String path = "redirect:boardList"; // 페이지를 리턴해야함
		return path;
	}
	@GetMapping("boardDelete")
	public Object boardDelete(@RequestParam Map<String,Object> pMap) {
		logger.info("boardDelete 호출 성공");
		int result = 0;
		result = boardLogic.boardDelete(pMap); 
		return "redirect:boardList";
	}
	
	
}
