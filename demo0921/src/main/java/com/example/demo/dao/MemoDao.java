package com.example.demo.dao;

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
		int result = 0; //성공여부 판정
		try {
			sqlSessionTemplate.selectOne("proc_memoinsert", pMap);
			if(pMap.get("result")!=null) {
				result = Integer.parseInt(pMap.get("result").toString());
			}
			logger.info("result: "+result);
		} catch (DataAccessException e) { 
			logger.info("Exception : " + e.toString());
		} 
		return result;
	}
}
