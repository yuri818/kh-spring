package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class MemoDao {
	Logger logger = LogManager.getLogger(MemoDao.class);
	@Autowired(required=false)
	private SqlSessionTemplate sqlSessionTemplate = null;

	public int memoInsert(Map<String, Object> pMap) {
		logger.info("다오의 memoInsert 호출 성공: "+pMap);
		int result = 0; 
		try {
			sqlSessionTemplate.update("memoInsert", pMap);
			logger.info("result: "+result);
		} catch (DataAccessException e) { 
			logger.info("Exception : " + e.toString());
		} 
		return result;
	}

	public List<Map<String, Object>> sendMemoList(Map<String, Object> pMap) {
		logger.info("다오의 sendMemoList 호출 성공: "+pMap);
		List<Map<String, Object>> sendMemoList = null;
		sendMemoList = sqlSessionTemplate.selectList("sendMemoList", pMap);
		return sendMemoList;
	}

	public List<Map<String, Object>> receiveMemoList(Map<String, Object> pMap) {
		logger.info("다오의 receiveMemoList 호출 성공: "+pMap);
		List<Map<String, Object>> receiveMemoList = null;
		receiveMemoList = sqlSessionTemplate.selectList("receiveMemoList", pMap);
		return receiveMemoList;
	}
}
