package com.example.demo.logic;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDao;

@Service
public class MemberLogic {
	Logger logger = LogManager.getLogger(MemberLogic.class);
	
	@Autowired(required=false)
	private MemberDao memberDao = null;

	public int memberInsert(Map<String, Object> pMap) {
		logger.info("로직의 memberInsert호출 성공");
		int result = 0;
		result = memberDao.memberInsert(pMap);
		return result;
	}
}
