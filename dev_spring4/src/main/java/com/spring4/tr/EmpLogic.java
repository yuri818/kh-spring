package com.spring4.tr;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

public class EmpLogic {
	Logger logger = Logger.getLogger(EmpLogic.class);
	private DeptDao deptDao = null;
	public void setDeptDao(DeptDao deptDao) {
		this.deptDao = deptDao;
	}
	private EmpDao empDao = null;
	public void setEmpDao(EmpDao empDao) {
		this.empDao = empDao;
	}
	//@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor= {DataAccessException.class})
	public int doEmp() {
		logger.info("doEmp 호출");
		Map<String,Object> eMap = new HashMap<>();
		eMap.put("empno", 9003);
		eMap.put("ename", "이유리");
		eMap.put("deptno", 100);
		try {
			Map<String,Object> dMap = new HashMap<>();
			dMap.put("deptno",100);
			dMap.put("dname", "개발부");
			dMap.put("loc", "부산");
			deptDao.deptInsert(dMap);
			empDao.empInsert(eMap);
		} catch (DataAccessException de) {
			throw de;
		}
		return 0;
	}

}
