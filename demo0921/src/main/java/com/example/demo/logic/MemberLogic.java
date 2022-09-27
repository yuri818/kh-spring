package com.example.demo.logic;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDao;
import com.example.demo.dao.MemoDao;
import com.example.demo.vo.MemberVO;

// ApplicatonContext가 우선(왜냐면 BeanFactory기능을 내장 ... , 이른 인스턴스화), BeanFactory
@Service
public class MemberLogic {
	Logger logger = LogManager.getLogger(MemberLogic.class);
	
	@Autowired(required=false)
	private MemberDao memberDao = null;
	@Autowired(required=false) // 읽지 않은 쪽지 처리를 위해서
	private MemoDao memoDao = null;
	
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
		// 읽지 않은 쪽지 담기
		int cnt = 0;
		mVO = memberDao.login(pMap);
		// 로그인 성공했을 경우 - 이때만 아이디를 담아준다.
		if(mVO != null) {
			String to_id = null;
			to_id = mVO.getMem_id();
			pMap.put("to_id", to_id);
			cnt = memoDao.noReadMemo(pMap);
			mVO.setCount(cnt);
		}
		return mVO;
	}

	public List<Map<String, Object>> memberList(Map<String, Object> pMap) {
		logger.info("로직의 memberList호출 성공");
		List<Map<String, Object>> memberList = null;
		memberList = memberDao.memberList(pMap);
		return memberList;
	}
}
