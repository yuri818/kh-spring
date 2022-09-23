package com.example.demo.dao;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
// @Service는 모델계층에 붙이는 @Component의 자손 어노테이션이다.
@Service
public class MemberDao {
	Logger logger = LogManager.getLogger(MemberDao.class);
	@Autowired(required=false)
	private SqlSessionTemplate sqlSessionTemplate = null;

	public int memberInsert(Map<String, Object> pMap) {
		logger.info("다오의 memberInsert 호출 성공: "+pMap);
		int result = 0; //성공여부 판정
		try {
			sqlSessionTemplate.selectOne("proc_memberinsert", pMap);
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
