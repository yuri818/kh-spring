package com.spring4.mvc;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.dao.DataAccessException;

public class BoardDao {
	Logger logger = Logger.getLogger(BoardDao.class);
	private SqlSessionTemplate sqlSessionTemplate = null;
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

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
		logger.info("bStepUpdate 호출 성공");
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
		logger.info("boardMInsert 호출 성공");
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
		logger.info("boardUpdate 호출 성공");
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
		logger.info("hitCount 호출 성공");
		int result = 0;
		try {
			result = sqlSessionTemplate.update("hitCount",pMap);
			logger.info("result: "+ result);
		} catch (DataAccessException e) {
			logger.info("Exception : " + e.toString());
		} 
	}

	public int boardDelete(Map<String, Object> pMap) {
		logger.info("boardDelete 호출 성공");
		int result = 0;
		try {
			result = sqlSessionTemplate.delete("boardMDelete",pMap);
			logger.info("result: "+ result);
		} catch (DataAccessException e) {
			logger.info("Exception : " + e.toString());
		} 
		return result;
	}

	public int boardSInsert(Map<String, Object> pMap) {
		int result = 0;
		logger.info("boardSInsert 호출 성공");
		try {
			// 현재는 첨부파일이 한개인 경우라서 상수처리함.
			// TODO - 멀티처리를 위해서는 무엇을 해야 할까?
			pMap.put("bs_seq", 1);
			result = sqlSessionTemplate.update("boardSInsert", pMap);
		} catch (DataAccessException e) {
			logger.info("Exception : " + e.toString());
		}
		return result;
	}

}
