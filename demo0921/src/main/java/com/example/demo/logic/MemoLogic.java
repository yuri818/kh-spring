package com.example.demo.logic;

import java.util.Map;

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
		// TODO Auto-generated method stub
		return 0;
	}
}
