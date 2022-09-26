package com.example.demo.logic;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemoDao;

@Service
public class MemoLogic {
	Logger logger = LogManager.getLogger(MemoLogic.class);
	
	@Autowired(required=false)
	private MemoDao memoDao = null;

	public int memoInsert(Map<String, Object> pMap) {
		logger.info("로직의 memoInsert 호출 성공");
		int result = 0;
		result = memoDao.memoInsert(pMap);
		return result;
	}

	public List<Map<String, Object>> sendMemoList(Map<String, Object> pMap) {
		logger.info("로직의 sendMemoList 호출 성공");
		List<Map<String, Object>> sendMemoList = null;
		sendMemoList = memoDao.sendMemoList(pMap);
		return sendMemoList;
	}

	public List<Map<String, Object>> receiveMemoList(Map<String, Object> pMap) {
		logger.info("로직의 receiveMemoList 호출 성공");
		List<Map<String, Object>> receiveMemoList = null;
		receiveMemoList = memoDao.receiveMemoList(pMap);
		return receiveMemoList;
	}
}
