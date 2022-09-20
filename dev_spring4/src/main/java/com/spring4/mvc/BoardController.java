package com.spring4.mvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.util.HashMapBinder;

// 여기선 어노테이션 사용하지 않는다.
// @RestController 쓰면 JSON포맷으로 처리해준다(xml도 가능) - 그치만 4버전에서는 쓸 수 없다
public class BoardController extends MultiActionController { // req,res를 사용하기 위해 상속 받음
	Logger logger = Logger.getLogger(BoardController.class);
	private BoardLogic boardLogic = null;
	public void setBoardLogic(BoardLogic boardLogic) {
		this.boardLogic = boardLogic;
	}
	// 리액트랑 연계한다고 생각하고 JSON포맷으로 출력해보자
	public String jsonBoardList(HttpServletRequest req, HttpServletResponse res) {
		logger.info("jsonBoardList 호출 성공");
		Map<String,Object> pMap = new HashMap<>(); 
		List<Map<String,Object>> boardList = null;
		boardList = boardLogic.boardList(pMap);
		req.setAttribute("boardList", boardList);
		/**JSON으로 내보내기
		 * Gson g = new Gson();
		 * String temp = g.toJson(boardList);
		 * let doc = JSON.stringify(temp);
		 * let array = doc.parse(doc);
		 */
		logger.info(boardList);
		return "forward:jsonBoardList.jsp";
	}
	public String boardList(HttpServletRequest req, HttpServletResponse res) {
		logger.info("boardList 호출 성공");
		Map<String,Object> pMap = new HashMap<>(); // @RequestParam Map<String,Object> pMap의 역할이다.
		List<Map<String,Object>> boardList = null;
		boardList = boardLogic.boardList(pMap);
//		model.addAttribute("boardList", boardList); // scope가 request임 // model은 4버전에서는 지원x
		req.setAttribute("boardList", boardList); // 표준으로 하면 된다.
		logger.info(boardList);
		return "forward:boardList.jsp";
	}
	public String boardDetail(HttpServletRequest req, HttpServletResponse res) {
		logger.info("boardDetail 호출 성공");
		Map<String,Object> pMap = new HashMap<>();
		HashMapBinder hmb = new HashMapBinder(req);
		hmb.bind(pMap);
		List<Map<String,Object>> boardList = null;
		boardList = boardLogic.boardDetail(pMap);
		req.setAttribute("boardList", boardList);
		return "forward:read.jsp";
	}
	public String boardInsert(HttpServletRequest req, HttpServletResponse res) {
		logger.info("boardInsert 호출 성공");
		Map<String,Object> pMap = new HashMap<>();
		HashMapBinder hmb = new HashMapBinder(req);
//		hmb.bind(pMap);
		hmb.multiBind(pMap);
		boardLogic.boardInsert(pMap);
		// redirect-forward, forward->forward는 에러
		return "redirect:boardList.sp";
	}
	public String boardUpdate(HttpServletRequest req, HttpServletResponse res) {
		logger.info("boardUpdate 호출 성공");
		Map<String,Object> pMap = new HashMap<>();
		HashMapBinder hmb = new HashMapBinder(req);
		hmb.bind(pMap);
		boardLogic.boardUpdate(pMap);
		return "redirect:boardList.sp";
	}
	public String boardDelete(HttpServletRequest req, HttpServletResponse res) {
		logger.info("boardDelete 호출 성공");
		Map<String,Object> pMap = new HashMap<>();
		HashMapBinder hmb = new HashMapBinder(req);
		hmb.bind(pMap);
		boardLogic.boardDelete(pMap);
		return "redirect:boardList.sp";
	}
}
