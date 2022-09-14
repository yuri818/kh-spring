package com.basic.step1.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class BoardDao {
	Logger logger = LoggerFactory.getLogger(BoardDao.class);
	@Autowired(required=false)
	private SqlSessionTemplate sqlSessionTemplate = null;

	public List<Map<String, Object>> boardList(Map<String, Object> pMap) {
		logger.info("boardList 호출 성공");
		List<Map<String,Object>> boardList = null;
		try {
			boardList = sqlSessionTemplate.selectList("boardList", pMap);
			logger.info(boardList.toString());
		} catch (DataAccessException e) { // 스프링에서 sql관련한 exception처리
			logger.info("Exception : " + e.toString());
		} 
		return boardList;
	}

	public int getBNo() {
		logger.info("getBNo호출 성공");
		int result = 0;
		try {
			result = sqlSessionTemplate.selectOne("getBNo");
			logger.info("result: "+ result);
		} catch (DataAccessException e) {
			logger.info("Exception : " + e.toString());
		} 
		return result;
	}

	public void bStepUpdate(Map<String, Object> pMap) {
		int result = 0;
		try {
			result = sqlSessionTemplate.update("bStepUpdate",pMap);
			logger.info("result: "+ result);
		} catch (DataAccessException e) {
			logger.info("Exception : " + e.toString());
		}
	}

	public int getBGroup() {
		logger.info("getBGroup 호출 성공");
		int result = 0;
		try {
			result = sqlSessionTemplate.selectOne("getBGroup");
			logger.info("result: "+ result);
		} catch (DataAccessException e) {
			logger.info("Exception : " + e.toString());
		}
		return result;
	}

	public int boardMInsert(Map<String, Object> pMap) {
		int result = 0;
		try {
			result = sqlSessionTemplate.update("boardMInsert",pMap);
			logger.info("result: "+ result);
		} catch (DataAccessException e) {
			logger.info("Exception : " + e.toString());
		}
		return result;
	}

	public int boardUpdate(Map<String, Object> pMap) {
		int result = 0;
		try {
			result = sqlSessionTemplate.update("boardMUpdate",pMap);
			logger.info("result: "+ result);
		} catch (DataAccessException e) {
			logger.info("Exception : " + e.toString());
		}
		return result;
	}

	public void hitCount(Map<String, Object> pMap) {
		int result = 0;
		try {
			result = sqlSessionTemplate.update("hitCount",pMap);
			logger.info("result: "+ result);
		} catch (DataAccessException e) {
			logger.info("Exception : " + e.toString());
		} 
	}

	public int boardDelete(Map<String, Object> pMap) {
		int result = 0;
		try {
			result = sqlSessionTemplate.delete("boardMDelete",pMap);
			logger.info("result: "+ result);
		} catch (DataAccessException e) {
			logger.info("Exception : " + e.toString());
		} 
		return result;
	}

}
