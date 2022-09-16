package com.spring4.tr;

import java.util.Map;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.dao.DataAccessException;

public class EmpDao {
	Logger logger = Logger.getLogger(EmpDao.class);
	private SqlSessionTemplate sqlSessionTemplate = null;
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	// 스프링에서 제공하는 SQLException대신 사용하는 클래스
	public void empInsert(Map<String, Object> eMap) throws DataAccessException {
		logger.info("EmpDao에서의 eMap: "+eMap);
		sqlSessionTemplate.update("empInsert", eMap);
	}

}
