package com.example.demo.logic;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemoDao;

@Service
public class MemoLogic {
	Logger logger = LogManager.getLogger(MemoLogic.class);
	
	@Autowired(required=false)
	private MemoDao memoDao = null;

	public int memoInsert(Map<String, Object> pMap) {
		logger.info("로직의 memoInsert 호출 성공");
		int result = 0;
		result = memoDao.memoInsert(pMap);
		return result;
	}

	public List<Map<String, Object>> sendMemoList(Map<String, Object> pMap) {
		logger.info("로직의 sendMemoList 호출 성공");
		List<Map<String, Object>> sendMemoList = null;
		sendMemoList = memoDao.sendMemoList(pMap);
		return sendMemoList;
	}

	public List<Map<String, Object>> receiveMemoList(Map<String, Object> pMap, HttpSession session) {
		logger.info("로직의 receiveMemoList 호출 성공");
		List<Map<String, Object>> receiveMemoList = null;
		receiveMemoList = memoDao.receiveMemoList(pMap);
		// 읽었다면 읽음표시
		int cnt = memoDao.noReadMemo(pMap);
		session.setAttribute("s_cnt", cnt);
		return receiveMemoList;
	}

	public Map<String, Object> memoContent(Map<String, Object> pMap) {
		Map<String, Object> rMap = null;
		rMap = memoDao.memoContent(pMap); // select
		if(rMap != null) { // 하나가 선택되면 그걸 읽고나면 읽음상태로 바꿔줘!
			memoDao.readYnUpdate(pMap); // update -> 트랜잭션 처리대상이 아님
		}
		return rMap;
	}
}
