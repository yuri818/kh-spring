package com.example.demo.logic;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDao;
import com.example.demo.vo.MemberVO;

@Service
public class MemberLogic {
	Logger logger = LogManager.getLogger(MemberLogic.class);
	
	@Autowired(required=false)
	private MemberDao memberDao = null;
	// 등록시에 프로시저를 사용하면 트랜잭션 처리를 따로 하지 않아도 된다.
	public int memberInsert(Map<String, Object> pMap) {
		logger.info("로직의 memberInsert호출 성공");
		int result = 0;
		result = memberDao.memberInsert(pMap);
		return result;
	}

	public MemberVO login(Map<String, Object> pMap) {
		logger.info("로직의 login호출 성공");
		MemberVO mVO = null;
		mVO = memberDao.login(pMap);
		return mVO;
	}

	public List<Map<String, Object>> memberList(Map<String, Object> pMap) {
		logger.info("로직의 memberList호출 성공");
		List<Map<String, Object>> memberList = null;
		memberList = memberDao.memberList(pMap);
		return memberList;
	}
}
