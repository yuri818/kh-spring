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
	// 받은 쪽지 중 읽지 않은 쪽지 카운트하기
	public int noReadMemo(Map<String,Object> pMap) {
		logger.info("다오의 noReadMemo 호출 성공: "+pMap); // pMap에 to_id가 있어야 함
		int cnt = 0;
		cnt = sqlSessionTemplate.selectOne("noReadMemo", pMap);
		logger.info("memoDao의 cnt : "+cnt); // 배달 사고가 어디서 발생한건지 체크하기 위한 코드 추가 필요
		return cnt;
	}
	
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
	/********************* [[ 쪽지 읽음 처리(내용+읽음상태) 시작 ]] *********************/
	public Map<String, Object> memoContent(Map<String, Object> pMap) {
		logger.info("다오의 memoContent호출 성공");
		Map<String,Object> rMap = null;
		rMap = sqlSessionTemplate.selectOne("memoContent", pMap);
		logger.info("rMap: "+rMap.toString()); //하나의 결과값만 받아오는지 확인
		return rMap;
	}

	public void readYnUpdate(Map<String, Object> pMap) {
		logger.info("다오의 readYnUpdate 호출 성공");
		sqlSessionTemplate.update("readYnUpdate", pMap);
	}
	/********************* [[ 쪽지 읽음 처리(내용+읽음상태)  끝 ]] *********************/
	
}
