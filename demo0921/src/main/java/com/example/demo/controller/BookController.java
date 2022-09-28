package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.logic.BookLogic;
// GET방식 테스트 주소
//http://localhost:9000/book/bookList?bk_title=혼&choMode=N 
//http://localhost:9000/book/bookList?bk_title=ㅎ&choMode=Y

@Controller
@RequestMapping("/book/*")
public class BookController {
	Logger logger = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private BookLogic bookLogic = null;
	
	@PostMapping("bookList")
	public String bookList(Model model, @RequestParam Map<String,Object> pMap) { 
		//post방식이므로 화면과 함께 "bk_title="+user_word+"&choMode="+choMode가 넘어옴
		logger.info("Controller의 bookList호출 성공");
		List<Map<String,Object>> bookList = null;
		bookList = bookLogic.bookList(pMap);
		model.addAttribute("bookList",bookList);
		return "forward:searchResult.jsp";
	}
}
